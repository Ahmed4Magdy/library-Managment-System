package com.example.demo.service;


import com.example.demo.Dto.CategoryDto;
import com.example.demo.entity.Category;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.service.impl.CategoryServiceimpl;
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {


    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private CategoryMapper categoryMapper;
    @InjectMocks
    private CategoryServiceimpl categoryServiceimpl;

    private Category parent;
    private Category category;
    private CategoryDto dto;

    @BeforeEach
    void setup() {


        parent = new Category();
        parent.setId(1L);
        parent.setName("DB");

        category = new Category();
        category.setName("sql and is child");
        category.setId(2L);
        category.setParent(parent);


        dto = new CategoryDto();
        dto.setName("DB");
        dto.setParentId(parent.getId());


    }


    @Test
    void createCategory_WithParent() {

        when(categoryMapper.toEntity(dto)).thenReturn(category);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(parent));
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryMapper.toDto(category)).thenReturn(dto);


        CategoryDto result = categoryServiceimpl.addCategory(dto);
        assertEquals(dto.getParentId(), result.getParentId());
        assertNotNull(result);
    }


    @Test
    void createCategory_NotFound() {

        dto.setParentId(null);
        when(categoryMapper.toEntity(dto)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryMapper.toDto(category)).thenReturn(dto);

        CategoryDto result = categoryServiceimpl.addCategory(dto);

        assertNotNull(result);
        assertEquals("Child Category", result.getName());
        verify(categoryRepository, times(1)).save(category);


    }


    @Test
    void testupdateCategory_WithParent() {

        //db sql
        when(categoryRepository.findById(2L)).thenReturn(Optional.of(category));
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(parent));
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryMapper.toDto(category)).thenReturn(dto);
        CategoryDto result = categoryServiceimpl.updateCategory(2L, dto);

        assertNotNull(result);

    }


    @Test
    void testUpdateCategory_RemoveParent() {
        dto.setParentId(null);
        when(categoryRepository.findById(2L)).thenReturn(Optional.of(category));
        when(categoryRepository.save(category)).thenReturn(category);
        when(categoryMapper.toDto(category)).thenReturn(dto);

        CategoryDto result = categoryServiceimpl.updateCategory(2L, dto);

        assertNotNull(result);
        assertNull(category.getParent());
        verify(categoryRepository, times(1)).save(category);
    }


    @Test
    void testGetCategoryById() {


        when(categoryRepository.findById(11L)).thenReturn(Optional.of(category));
        when(categoryMapper.toDto(category)).thenReturn(dto);


        CategoryDto result = categoryServiceimpl.getCategoryById(11L);

        assertNotNull(result);

    }


    @Test
    void testGetAllCategory() {


        when(categoryRepository.findAll()).thenReturn(List.of(category));
        when(categoryMapper.toDto(category)).thenReturn(dto);


        List<CategoryDto> result = categoryServiceimpl.getAllCategories();

        assertNotNull(result);

    }


}
