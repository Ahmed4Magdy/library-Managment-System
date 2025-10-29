package com.example.demo.controller;

import com.example.demo.Dto.SystemUserDto;
import com.example.demo.entity.SystemUser;
import com.example.demo.service.SystemUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class SystemUserControllerTest {


    private MockMvc mockMvc;

    @Mock
    private SystemUserService systemUserService;
    @InjectMocks
    private SystemUserController systemUserController;

    private SystemUserDto dto;

    @BeforeEach
    void setup(){


        dto = new SystemUserDto();
        dto.setRole(SystemUser.Role.ADMIN);
        dto.setUsername("admin-123");

        mockMvc= MockMvcBuilders.standaloneSetup(systemUserController).build();
    }


    @Test
    void testAddSystemUser() throws Exception {
        Mockito.when(systemUserService.addSystemUser(any(SystemUserDto.class)))
                .thenReturn(dto);

        mockMvc.perform(post("/systemuser/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(dto.getUsername()));
    }

    @Test
    void testUpdateSystemUser() throws Exception {
        Mockito.when(systemUserService.updateSystemUser(eq(1L), any(SystemUserDto.class)))
                .thenReturn(dto);

        mockMvc.perform(put("/systemuser/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(dto.getUsername()));
    }

    @Test
    void testGetSystemUserById() throws Exception {
        Mockito.when(systemUserService.getSystemUserById(1L))
                .thenReturn(dto);

        mockMvc.perform(get("/systemuser/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(dto.getUsername()));
    }

    @Test
    void testGetAllSystemUsers() throws Exception {
        Mockito.when(systemUserService.getAllSystemUsers())
                .thenReturn(List.of(dto));

        mockMvc.perform(get("/systemuser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value(dto.getUsername()));
    }

    @Test
    void testDeleteSystemUser() throws Exception {
        mockMvc.perform(delete("/systemuser/1"))
                .andExpect(status().isOk());
    }


}
