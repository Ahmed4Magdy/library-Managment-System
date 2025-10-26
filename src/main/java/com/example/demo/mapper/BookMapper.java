package com.example.demo.mapper;

import com.example.demo.Dto.BookDto;
import com.example.demo.Dto.PublisherDto;
import com.example.demo.entity.Book;
import com.example.demo.entity.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {


    @Mapping(target = "publisherId",source = "publisher.id")
    BookDto toDto(Book book);

    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "transactions", ignore = true)
    @Mapping(target = "id", ignore = true)
    Book toEntity(BookDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "authors", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "transactions", ignore = true)
    void updateBookFromDto(BookDto dto, @MappingTarget Book entity);


}
