package com.example.demo.mapper;

import com.example.demo.Dto.AuthorDto;
import com.example.demo.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthorMapper {


  AuthorDto toDto (Author author);

  @Mapping(target = "books" ,ignore = true)
  @Mapping(target = "id", ignore = true)
  Author toEntity(AuthorDto dto);

}
