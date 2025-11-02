package com.example.demo.controller;


import com.example.demo.Dto.CategoryDto;
import com.example.demo.service.CategoryService;
import com.example.demo.service.impl.CategoryServiceimpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {


    private final CategoryService categoryService;

    public CategoryController(CategoryServiceimpl categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping("/add")
    public CategoryDto addCategory(@RequestBody CategoryDto dto) {
        return categoryService.addCategory(dto);
    }


    @PutMapping("/{id}")
    public CategoryDto updateCategory(@PathVariable Long id, @RequestBody CategoryDto dto) {

        return categoryService.updateCategory(id, dto);

    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {

        categoryService.deleteCategory(id);
    }


    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable Long id) {

        return categoryService.getCategoryById(id);

    }


    @GetMapping("")
    public List<CategoryDto> getAllCategories() {

        return categoryService.getAllCategories();
    }

}
