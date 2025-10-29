package com.example.demo.mapper;

import com.example.demo.Dto.AuthorDto;
import com.example.demo.Dto.BookDto;
import com.example.demo.entity.Author;
import com.example.demo.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {


  AuthorDto toDto (Author author);

  @Mapping(target = "books" ,ignore = true)
  @Mapping(target = "id", ignore = true)
  Author toEntity(AuthorDto dto);


  @Mapping(target = "id", ignore = true)
  @Mapping(target = "books" ,ignore = true)
  void updateBookFromDto(AuthorDto dto, @MappingTarget Author entity);
}
