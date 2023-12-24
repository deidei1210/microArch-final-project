package cn.edu.tongji.login.service.impl;

import cn.edu.tongji.login.dto.AddUserRequest;
import cn.edu.tongji.login.dto.SmsInfo;
import cn.edu.tongji.login.dto.UpdateUserRequest;
import cn.edu.tongji.login.dto.UserInfo;
import cn.edu.tongji.login.mapper.UserMapper;
import cn.edu.tongji.login.model.User;
import cn.edu.tongji.login.service.EncryptService;
import cn.edu.tongji.login.service.SmsService;
import cn.edu.tongji.login.service.UserService;
import cn.edu.tongji.login.utils.SmsCodeGenerator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private EncryptService encryptService;
    @Resource
    private SmsService smsService;

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAll();
    }

    @Override
    public User getUserById(int id) {
        return userMapper.getById(id);
    }

    @Override
    public UserInfo getUserInfoById(int id) {
        User user = userMapper.getById(id);

        return new UserInfo(
                user.getName(),
                user.getEmail(),
                user.getAge(),
                user.getRegion(),
                user.getAvatar(),
                user.getPhone(),
                user.getGender()
        );
    }

    @Override
    @Transactional
    public User addUser(AddUserRequest addUserRequest) {
        User user = User.builder()
                .name(addUserRequest.getName())
                .password(encryptService.encryptPassword(addUserRequest.getPassword()))  //加密
                .email(addUserRequest.getEmail())
                .age(addUserRequest.getAge())
                .region(addUserRequest.getRegion())
                .avatar(addUserRequest.getAvatar())
                .phone(addUserRequest.getPhone())
                .gender(addUserRequest.getGender())
                .build();

        userMapper.add(user);
        return userMapper.getById(user.getId());
    }

    @Override
    public void updateUser(UpdateUserRequest updateUserRequest) {
        userMapper.update(User.builder()
                .id(updateUserRequest.getId())
                .name(updateUserRequest.getName())
                .password(encryptService.encryptPassword(updateUserRequest.getPassword()))
                .email(updateUserRequest.getEmail())
                .age(updateUserRequest.getAge())
                .region(updateUserRequest.getRegion())
                .avatar(updateUserRequest.getAvatar())
                .phone(updateUserRequest.getPhone())
                .gender(updateUserRequest.getGender())
                .build()
        );
    }

    @Override
    public boolean checkPhoneAvailable(String phone) {
        return userMapper.getByPhone(phone).isEmpty();
    }

    @Override
    public SmsInfo sendSmsCode(String phone) {
        String code = SmsCodeGenerator.getCode();
        boolean ifSend = smsService.sendSmsCode(code, phone);
        return new SmsInfo(ifSend ? code : null, ifSend);
    }
}
