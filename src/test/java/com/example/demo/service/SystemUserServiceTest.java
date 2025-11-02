package com.example.demo.service;


import com.example.demo.Dto.LoginDto;
import com.example.demo.Dto.LoginResponseDto;
import com.example.demo.Dto.SignupDto;
import com.example.demo.Dto.SystemUserDto;
import com.example.demo.entity.SystemUser;
import com.example.demo.mapper.LoginMapper;
import com.example.demo.mapper.SignupMapper;
import com.example.demo.mapper.SystemUserMapper;
import com.example.demo.repo.SystemUserRepository;
import com.example.demo.service.impl.SystemUserServiceimpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class SystemUserServiceTest {


    @Mock
    private SystemUserRepository systemUserRepository;
    @Mock
    private SystemUserMapper systemUserMapper;
    @Mock
    private SignupMapper signupMapper;

    @Mock
    private LoginMapper loginMapper;
    @Mock
    private PasswordEncoder passwordEncoder;


    @InjectMocks
    private SystemUserServiceimpl systemUserServiceimpl;

    private SystemUser systemUser;
    private SystemUserDto dto;

    @BeforeEach
    void setup() {

        systemUser = new SystemUser();
        systemUser.setUsername("admin-123");

        dto = new SystemUserDto();
        dto.setUsername("admin-123");


    }


    @Test
    void register() {

        SignupDto dto = new SignupDto();
        dto.setEmail("ahmed@gmail.com");
        dto.setFull_name("ahmed magdy");


        when(systemUserRepository.findByEmail(dto.getEmail())).thenReturn(Optional.empty()); // معناها ان يموكيتو لما يكون ف فانكشن عندك وعياز تشوف الايميل موجود ولا لا رجع انه فاضي يعني مفيش ايميل
        when(signupMapper.toEntity(dto)).thenReturn(systemUser);
        when(systemUserRepository.save(systemUser)).thenReturn(systemUser);
        when(signupMapper.toDto(systemUser)).thenReturn(dto);


        SignupDto result = systemUserServiceimpl.register(dto);


        Assertions.assertNotNull(result);


    }


    @Test
    void login() {

        LoginDto dto = new LoginDto();
        dto.setEmail("ahmed@gmail.com");
        LoginResponseDto dto1 = new LoginResponseDto();
        dto1.setEmail("ahmed@gmail.com");

        when(systemUserRepository.findByEmail(dto.getEmail())).thenReturn(Optional.of(systemUser));
        when(passwordEncoder.matches(dto.getPassword(), systemUser.getPassword())).thenReturn(true);

        when(loginMapper.toLoginResponseDto(systemUser)).thenReturn(dto1);

        LoginResponseDto result = systemUserServiceimpl.login(dto);


    }


    @Test
    void testCreatSystemUser() {


        when(systemUserMapper.toEntity(dto)).thenReturn(systemUser);
        when(systemUserRepository.save(systemUser)).thenReturn(systemUser);
        when(systemUserMapper.toDto(systemUser)).thenReturn(dto);


        SystemUserDto result = systemUserServiceimpl.addSystemUser(dto);

        assertNotNull(result);


    }


    @Test
    void testGetAllUsers() {


        when(systemUserRepository.findAll()).thenReturn(List.of(systemUser));
        when(systemUserMapper.toDto(systemUser)).thenReturn(dto);


        List<SystemUserDto> result = systemUserServiceimpl.getAllSystemUsers();


        assertNotNull(result);
        assertEquals("admin-123", result.get(0).getUsername());

    }

    @Test
    void testGetUserById_Found() {

        when(systemUserRepository.findById(100L)).thenReturn(Optional.of(systemUser));
        when(systemUserMapper.toDto(systemUser)).thenReturn(dto);

        SystemUserDto result = systemUserServiceimpl.getSystemUserById(100L);

        assertNotNull(result);

    }


    @Test
    void testGetUserById_NotFound() {

        when(systemUserRepository.findById(100L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                systemUserServiceimpl.getSystemUserById(100L));

        assertEquals("systemuser not found with 100", exception.getMessage());

    }


    @Test
    void testUpdateUser() {

        when(systemUserRepository.findById(11L)).thenReturn(Optional.of(systemUser));
        doNothing().when(systemUserMapper).updateSystemUserFromtodto(dto, systemUser);

        when(systemUserRepository.save(systemUser)).thenReturn(systemUser);
        when(systemUserMapper.toDto(systemUser)).thenReturn(dto);


        SystemUserDto result = systemUserServiceimpl.updateSystemUser(11L, dto);


    }


    @Test
    void deleteUser() {


        doNothing().when(systemUserRepository).deleteById(11L);

        systemUserServiceimpl.deleteSystemUser(11L);

        verify(systemUserRepository, times(1)).deleteById(11L);


    }


}
