package com.example.demo.mapper;

import com.example.demo.Dto.SignupDto;
import com.example.demo.entity.SystemUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SignupMapper {


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "borrowTransactions", ignore = true)
    @Mapping(target = "role", ignore = true)
    SystemUser toEntity(SignupDto dto);


    @Mapping(target = "password", ignore = true)
    SignupDto toDto(SystemUser systemUser);

}


