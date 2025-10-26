package com.example.demo.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class MemberDto {



    private String full_name;

    private String email;

    private String phone;

    private String address;

    private LocalDate membership_date = LocalDate.now();



}
