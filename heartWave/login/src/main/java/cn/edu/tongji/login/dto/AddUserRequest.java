package cn.edu.tongji.login.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserRequest {
    private String name;
    private String password;
    private String email;
    private Integer age;
    private String region;
    private String avatar;
    private String phone;
    private String gender;
}
