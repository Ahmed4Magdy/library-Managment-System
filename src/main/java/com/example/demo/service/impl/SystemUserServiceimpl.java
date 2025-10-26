package com.example.demo.service.impl;

import com.example.demo.Dto.SystemUserDto;
import com.example.demo.entity.SystemUser;
import com.example.demo.mapper.SystemUserMapper;
import com.example.demo.repo.SystemUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SystemUserServiceimpl {

    private final SystemUserRepository systemUserRepository;

    private final PasswordEncoder passwordEncoder;

    private final SystemUserMapper systemUserMapper;



    public SystemUserServiceimpl(SystemUserRepository systemUserRepository, PasswordEncoder passwordEncoder, SystemUserMapper systemUserMapper) {
        this.systemUserRepository = systemUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.systemUserMapper = systemUserMapper;
    }


    public SystemUserDto addSystemUser(SystemUserDto dto) {

        SystemUser exist = systemUserMapper.toEntity(dto);
        SystemUser saved = systemUserRepository.save(exist);
        return systemUserMapper.toDto(saved);

    }


    public SystemUserDto updateSystemUser(Long id, SystemUserDto dto) {

        SystemUser existSystemUser = systemUserRepository.findById(id).orElseThrow(() -> new RuntimeException("systemuser not found with " + id));

        systemUserMapper.updateSystemUserFromtodto(dto, existSystemUser);

        SystemUser saved = systemUserRepository.save(existSystemUser);

        return systemUserMapper.toDto(saved);

    }

    public void deleteSystemUser(Long id) {
        systemUserRepository.deleteById(id);
    }

    public SystemUserDto getSystemUserById(Long id) {
        SystemUser saved = systemUserRepository.findById(id).orElseThrow(() -> new RuntimeException("systemuser not found with " + id));
        return systemUserMapper.toDto(saved);
    }

    public List<SystemUserDto> getAllSystemUsers() {
        return systemUserRepository.findAll().stream().map(systemUserMapper::toDto).collect(Collectors.toList());
    }
}




