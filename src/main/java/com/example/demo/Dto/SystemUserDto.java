package com.example.demo.Dto;

import com.example.demo.entity.BorrowTransaction;
import com.example.demo.entity.SystemUser;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class SystemUserDto {


    private String username;

    private String password; // should be encrypted (BCrypt)

    private String full_name;


    private SystemUser.Role role;

    private LocalDateTime created_at = LocalDateTime.now();


}
