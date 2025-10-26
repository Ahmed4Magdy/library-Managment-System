package com.example.demo.mapper;


import com.example.demo.Dto.PublisherDto;
import com.example.demo.entity.Publisher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PublisherMapper {


      PublisherDto toDto (Publisher publisher);


      @Mapping(target = "books",ignore = true)
      @Mapping(target = "id", ignore = true)
      Publisher toEntity(PublisherDto dto);

      @Mapping(target = "id", ignore = true)
      @Mapping(target = "books",ignore = true)
      void updatePublisherFromDto(PublisherDto dto, @MappingTarget Publisher entity);


}
