package com.example.demo.controller;

import com.example.demo.Dto.CategoryDto;
import com.example.demo.service.CategoryService;
import com.example.demo.service.impl.CategoryServiceimpl;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {


    private MockMvc  mockMvc;

    @Mock
    private CategoryService  categoryService;

    @InjectMocks
    private CategoryController categoryController;


    private CategoryDto dto;
    @BeforeEach
    void setup(){

        dto = new CategoryDto();
        dto.setName("programming");
        dto.setParentId(1L);

        mockMvc= MockMvcBuilders.standaloneSetup(categoryController).build();

    }

    @Test
    void testCreateCategory() throws Exception {
        when(categoryService.addCategory(any(CategoryDto.class))).thenReturn(dto);

        mockMvc.perform(post("/category/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("programming"));

        verify(categoryService, times(1)).addCategory(any(CategoryDto.class));
    }


    @Test
    void testGetCategoryById() throws Exception {
        when(categoryService.getCategoryById(1L)).thenReturn(dto);

        mockMvc.perform(get("/category/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("programming"));

        verify(categoryService, times(1)).getCategoryById(1L);
    }

    @Test
    void testUpdateCategory() throws Exception {
        when(categoryService.updateCategory(eq(1L), any(CategoryDto.class))).thenReturn(dto);

        mockMvc.perform(put("/category/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("programming"));

        verify(categoryService, times(1)).updateCategory(eq(1L), any(CategoryDto.class));
    }

    @Test
    void testDeleteCategory() throws Exception {
        doNothing().when(categoryService).deleteCategory(1L);

        mockMvc.perform(delete("/category/1"))
                .andExpect(status().isOk());

        verify(categoryService, times(1)).deleteCategory(1L);
    }

}
