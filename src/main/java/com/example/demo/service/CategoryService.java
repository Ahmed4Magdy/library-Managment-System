package com.example.demo.service;

import com.example.demo.Dto.CategoryDto;
import com.example.demo.entity.Category;
import com.example.demo.mapper.CategoryMapper;
import com.example.demo.repo.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {


    private final CategoryRepository categoryrepository;

    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryrepository, CategoryMapper categoryMapper) {
        this.categoryrepository = categoryrepository;
        this.categoryMapper = categoryMapper;
    }


    public CategoryDto addCategory(CategoryDto dto) {

        Category category = categoryMapper.toEntity(dto);

        if (dto.getParentId() != null) {
            Category parent = categoryrepository.findById(dto.getParentId()).orElseThrow(() -> new RuntimeException("Category parent not found"));
            category.setParent(parent);
        }
        Category saved = categoryrepository.save(category);
        return categoryMapper.toDto(saved);


    }


    public CategoryDto updateCategory(Long id, CategoryDto dto) {

        Category existCategory = categoryrepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found with ID"));

        existCategory.setName(dto.getName());

        if (dto.getParentId() != null) {
            Category parent = categoryrepository.findById(dto.getParentId())
                    .orElseThrow(() -> new RuntimeException("Parent category not found with ID: " + dto.getParentId()));
            existCategory.setParent(parent);
        } else {
            existCategory.setParent(null);
        }

        Category saved = categoryrepository.save(existCategory);

        return categoryMapper.toDto(saved);


    }


    public void deleteCategory(Long id) {

        categoryrepository.deleteById(id);

    }


    public CategoryDto getCategoryById(Long id) {

        Category saved = categoryrepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + id));

        return categoryMapper.toDto(saved);

    }


    public List<CategoryDto> getAllCategories() {

        return categoryrepository.findAll().stream().map(categoryMapper::toDto).collect(Collectors.toList());
    }


}




