package com.example.demo.service;

import com.example.demo.Dto.LoginDto;
import com.example.demo.Dto.LoginResponseDto;
import com.example.demo.Dto.SignupDto;
import com.example.demo.Dto.SystemUserDto;

import java.util.List;
public interface SystemUserService {


    public SystemUserDto addSystemUser(SystemUserDto dto);

    public SystemUserDto updateSystemUser(Long id, SystemUserDto dto);

    public void deleteSystemUser(Long id);

    public SystemUserDto getSystemUserById(Long id);

    public List<SystemUserDto> getAllSystemUsers();

    public SignupDto register(SignupDto dto);

    public LoginResponseDto login(LoginDto dto);

}
