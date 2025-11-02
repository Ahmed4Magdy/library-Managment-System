package com.example.demo.controller;


import com.example.demo.Dto.AuthorDto;
import com.example.demo.service.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class AuthorcontrollerTest {


    private MockMvc mockMvc;

    @Mock
    private AuthorService authorService;  // هيعمل نسخه من نوع Authorserviceimpl

    @InjectMocks
    private AuthorController authorController;


    private AuthorDto dto;

    @BeforeEach
    void setup() {

        dto = new AuthorDto();
        dto.setName("author.Ahmed");

        mockMvc = MockMvcBuilders.standaloneSetup(authorController).build();
//mockmvc build object to simulate http requests
    }


    @Test
    void testCreateAuthor() throws Exception {

        when(authorService.addAuthor(any(AuthorDto.class))).thenReturn(dto);
        mockMvc.perform(post("/author/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto))) // convert java object to json
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("author.Ahmed"));


    }

    @Test
    void testgetById() throws Exception {

        when(authorService.findById(1L)).thenReturn(dto);
        mockMvc.perform(get("/author/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("author.Ahmed"));


        verify(authorService, times(1)).findById(1L);


    }


    @Test
    void testgetAllAuthor() throws Exception {

        when(authorService.findAll()).thenReturn(List.of(dto));
        mockMvc.perform(get("/author"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("author.Ahmed"));


        verify(authorService, times(1)).findAll();


    }


    @Test
    void testupdateAuthor() throws Exception {


        when(authorService.updateAuthor(eq(1L),any(AuthorDto.class))).thenReturn(dto);

        mockMvc.perform(put("/author/1")

                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("author.Ahmed"));

        verify(authorService, times(1)).updateAuthor(eq(1L), any(AuthorDto.class));


    }




    @Test
    void testdeleteAuthor() throws Exception {


        doNothing().when(authorService).deleteAuthor(1L);

        mockMvc.perform(delete("/author/1"))

                .andExpect(status().isOk());

        verify(authorService,times(1)).deleteAuthor(1L);

    }


}
