package com.example.demo.mapper;

import com.example.demo.Dto.BookDto;
import com.example.demo.Dto.MemberDto;
import com.example.demo.entity.Book;
import com.example.demo.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface MemberMapper {



    MemberDto toDto(Member member);


    @Mapping(target = "transactions",ignore = true)
    @Mapping(target = "id", ignore = true)
    Member toEntity(MemberDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "transactions",ignore = true)
    void updateMemberFromDto(MemberDto dto, @MappingTarget Member entity);


    // this annotation for  mapping between dto's and entity,It tells MapStruct to update an existing object instead of creating a new one.
}
