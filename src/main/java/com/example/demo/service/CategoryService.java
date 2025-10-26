package com.example.demo.service;

import com.example.demo.Dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    public CategoryDto addCategory(CategoryDto dto);


    public CategoryDto updateCategory(Long id, CategoryDto dto);


    public void deleteCategory(Long id);

    public CategoryDto getCategoryById(Long id);


    public List<CategoryDto> getAllCategories();


}
