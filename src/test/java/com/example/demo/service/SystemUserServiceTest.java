package com.example.demo.service;


import com.example.demo.Dto.SystemUserDto;
import com.example.demo.entity.SystemUser;
import com.example.demo.mapper.SystemUserMapper;
import com.example.demo.repo.SystemUserRepository;
import com.example.demo.service.impl.SystemUserServiceimpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    void deleteUser(){


        doNothing().when(systemUserRepository).deleteById(11L);

        systemUserServiceimpl.deleteSystemUser(11L);

        verify(systemUserRepository, times(1)).deleteById(11L);



    }


}
