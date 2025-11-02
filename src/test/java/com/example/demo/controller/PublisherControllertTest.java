package com.example.demo.controller;

import com.example.demo.Dto.PublisherDto;
import com.example.demo.service.PublisherService;
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
public class PublisherControllertTest {

    private MockMvc mockMvc;

    @Mock
    private PublisherService publisherService;

    @InjectMocks
    private PublisherController publisherController;

    private PublisherDto dto;

    @BeforeEach
    void setup(){

        dto = new PublisherDto();
        dto.setName("ahmed");

        mockMvc= MockMvcBuilders.standaloneSetup(publisherController).build();

    }

    @Test
    void testCreatePublisher() throws Exception {
        when(publisherService.addPublisher(any(PublisherDto.class))).thenReturn(dto);

        mockMvc.perform(post("/publihser/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("ahmed"));

        verify(publisherService, times(1)).addPublisher(any(PublisherDto.class));
    }

    @Test
    void testGetAllPublishers() throws Exception {
        when(publisherService.getAllPublisher()).thenReturn(List.of(dto));

        mockMvc.perform(get("/publihser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("ahmed"));

        verify(publisherService, times(1)).getAllPublisher();
    }

    @Test
    void testGetPublisherById() throws Exception {
        when(publisherService.getPublisherById(1L)).thenReturn(dto);

        mockMvc.perform(get("/publihser/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("ahmed"));

        verify(publisherService, times(1)).getPublisherById(1L);
    }

    @Test
    void testUpdatePublisher() throws Exception {
        when(publisherService.updatePublisher(eq(1L), any(PublisherDto.class))).thenReturn(dto);

        mockMvc.perform(put("/publihser/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("ahmed"));

        verify(publisherService, times(1)).updatePublisher(eq(1L), any(PublisherDto.class));
    }

    @Test
    void testDeletePublisher() throws Exception {
        doNothing().when(publisherService).deletepublisher(1L);

        mockMvc.perform(delete("/publihser/1"))
                .andExpect(status().isOk());

        verify(publisherService, times(1)).deletepublisher(1L);
    }

}
