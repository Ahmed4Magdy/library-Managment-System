package com.example.demo.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupDto {


    @NotBlank(message = "full_name must notBlank")
    private String full_name;
    private String email;

    private String password;


}
