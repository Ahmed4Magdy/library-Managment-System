package com.example.demo.controller;


import com.example.demo.Dto.AuthorDto;
import com.example.demo.Dto.BookDto;
import com.example.demo.service.BookService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class BookControllerTest {


    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private BookDto dto;

    @BeforeEach
    void setup() {
        dto = new BookDto();
        dto.setTitle("Junit");
        dto.setPublisherId(1L);

        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
//
    }

    @Test
    void createBook() throws Exception {

        when(bookService.addbook(any(BookDto.class))).thenReturn(dto);
        mockMvc.perform(post("/book/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk());
        verify(bookService, times(1)).addbook(any(BookDto.class));

    }


    @Test
    void testUpdateBook() throws Exception {

        when(bookService.updateBook(eq(11L), any(BookDto.class))).thenReturn(dto);
        mockMvc.perform(put("/book/11")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk());

        verify(bookService,times(1)).updateBook(eq(11L),any(BookDto.class));


    }

    @Test
    void testGetBookById() throws Exception {
        when(bookService.getbookyById(1L)).thenReturn(dto);

        mockMvc.perform(get("/book/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Junit"));

        verify(bookService, times(1)).getbookyById(1L);
    }




    @Test
    void testfindAll() throws Exception {
        when(bookService.getAllBook()).thenReturn(List.of(dto));

        mockMvc.perform(get("/book"))
                .andExpect(status().isOk());

        verify(bookService, times(1)).getAllBook();
    }

    @Test
    void testDeleteBook() throws Exception {
        doNothing().when(bookService).deleteByBook(1L);

        mockMvc.perform(delete("/book/1"))
                .andExpect(status().isOk());

        verify(bookService, times(1)).deleteByBook(1L);
    }



}
