package com.example.demo.controller;


import com.example.demo.Dto.MemberDto;
import com.example.demo.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MemberControllerTest {


    private MockMvc mockMvc;

    @Mock
    private MemberService memberService;

    @InjectMocks
    private MemberController memberController;

    private MemberDto dto;

    @BeforeEach
    void setup() {

        dto = new MemberDto();
        dto.setEmail("ahmed@example.com");
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
    }


    @Test
    void testAddMember() throws Exception {
        when(memberService.addMember(any(MemberDto.class))).thenReturn(dto);

        mockMvc.perform(post("/member/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().registerModule(new JavaTimeModule())
                                .writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("ahmed@example.com"));

        verify(memberService, times(1)).addMember(any(MemberDto.class));
    }

    @Test
    void testGetAllMembers() throws Exception {
        when(memberService.getAllMembers()).thenReturn(List.of(dto));

        mockMvc.perform(get("/member"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));

        verify(memberService, times(1)).getAllMembers();
    }

    @Test
    void testGetMemberById() throws Exception {
        when(memberService.getMemberById(1L)).thenReturn(dto);

        mockMvc.perform(get("/member/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("ahmed@example.com"));

        verify(memberService, times(1)).getMemberById(1L);
    }

    @Test
    void testUpdateMember() throws Exception {
        when(memberService.updateMember(eq(1L), any(MemberDto.class))).thenReturn(dto);

        mockMvc.perform(put("/member/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().registerModule(new JavaTimeModule())
                                .writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("ahmed@example.com"));

        verify(memberService, times(1)).updateMember(eq(1L), any(MemberDto.class));
    }

    @Test
    void testDeleteMember() throws Exception {
        doNothing().when(memberService).deleteMember(1L);

        mockMvc.perform(delete("/member/1"))
                .andExpect(status().isOk());

        verify(memberService, times(1)).deleteMember(1L);
    }
}
