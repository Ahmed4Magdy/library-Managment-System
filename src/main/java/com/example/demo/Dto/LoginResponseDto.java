package com.example.demo.Dto;


import com.example.demo.entity.SystemUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {


    private String full_name;

    private String email;

    private SystemUser.Role role;


}
