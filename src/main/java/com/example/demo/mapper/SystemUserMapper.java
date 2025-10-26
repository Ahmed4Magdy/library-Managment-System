package com.example.demo.mapper;


import com.example.demo.Dto.SystemUserDto;
import com.example.demo.entity.SystemUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SystemUserMapper {


    SystemUserDto toDto(SystemUser systemUser);


    @Mapping(target = "borrowTransactions", ignore = true)
    @Mapping(target = "id", ignore = true)
    SystemUser toEntity(SystemUserDto dto);

    @Mapping(target = "id", ignore = true) // علشان لما هيحول من الدي تي اوو لي انتيتي الدي تي او ملهوش اي دي ف مش هيعرف يماب معاه ف ساعتها الانتيتي هيحتفظ بالاي دي بتاعه لان لو معملتش كده ممكن يحطه ب نل او ينشا اوبجكت جديد
    @Mapping(target = "borrowTransactions", ignore = true)
    void updateSystemUserFromtodto(SystemUserDto dto, @MappingTarget SystemUser entity);

}
