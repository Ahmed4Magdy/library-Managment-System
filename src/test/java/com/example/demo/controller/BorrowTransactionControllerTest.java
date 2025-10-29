package com.example.demo.controller;


import com.example.demo.Dto.BorrowTransactionDto;
import com.example.demo.entity.BorrowTransaction;
import com.example.demo.service.BorrowTransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BorrowTransactionControllerTest {


    private MockMvc mockMvc;

    @Mock
    private BorrowTransactionService borrowTransactionService;

    @InjectMocks
    private BorrowTransactionController borrowTransactionController;

    private BorrowTransactionDto dto;

    @BeforeEach
    void setup(){

        dto= new BorrowTransactionDto();
        dto.setMemberId(1L);
        dto.setBookId(1L);
        dto.setProcessedById(1L);

        mockMvc= MockMvcBuilders.standaloneSetup(borrowTransactionController).build();

    }

    @Test
    void createBorrowTransaction() throws Exception{

        when(borrowTransactionService.createBorrowTransaction(any(BorrowTransactionDto.class))).thenReturn(dto);
        mockMvc.perform(post("/borrowwtrasaction/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk());

        verify(borrowTransactionService,times(1)).createBorrowTransaction(any(BorrowTransactionDto.class));




    }


    @Test
    void testGetTransactionById() throws Exception {
        when(borrowTransactionService.findByIdBorrowTransaction(1L)).thenReturn(dto);

        mockMvc.perform(get("/borrowwtrasaction/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId").value(1))
                .andExpect(jsonPath("$.memberId").value(1));

        verify(borrowTransactionService, times(1)).findByIdBorrowTransaction(1L);
    }


    @Test
    void testUpdateTransaction() throws Exception {
        when(borrowTransactionService.updateBorrowTransaction(eq(1L), any(BorrowTransactionDto.class)))
                .thenReturn(dto);

        mockMvc.perform(put("/borrowwtrasaction/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId").value(1));

        verify(borrowTransactionService, times(1)).updateBorrowTransaction(eq(1L), any(BorrowTransactionDto.class));
    }





}
