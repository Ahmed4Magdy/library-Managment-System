package com.example.demo.service;

import com.example.demo.Dto.SystemUserDto;
import com.example.demo.entity.SystemUser;

import java.util.List;
import java.util.stream.Collectors;

public interface SystemUserService {


    public SystemUserDto addSystemUser(SystemUserDto dto);

    public SystemUserDto updateSystemUser(Long id, SystemUserDto dto);

    public void deleteSystemUser(Long id);

    public SystemUserDto getSystemUserById(Long id);

    public List<SystemUserDto> getAllSystemUsers();

}
