package com.example.demo.mapper;

import com.example.demo.Dto.CategoryDto;
import com.example.demo.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(source = "parent.id", target = "parentId")
    CategoryDto toDto(Category category);

    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "subcategories", ignore = true)
    @Mapping(target = "books", ignore = true)
    @Mapping(target = "id", ignore = true)
    Category toEntity(CategoryDto dto);


}
