package com.example.demo.mapper;

import com.example.demo.Dto.LoginResponseDto;
import com.example.demo.entity.SystemUser;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoginMapper {



    LoginResponseDto toLoginResponseDto(SystemUser systemUser);


}
