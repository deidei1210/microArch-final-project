package cn.edu.tongji.login.service;

import cn.edu.tongji.login.dto.VerifyInfo;
import cn.edu.tongji.login.model.User;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    User checkUserLogin(String phone, String name, String password);
    VerifyInfo getVerifyCodeInfo();
}
