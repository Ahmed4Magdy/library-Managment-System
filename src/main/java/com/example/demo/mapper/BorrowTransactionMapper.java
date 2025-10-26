package com.example.demo.mapper;


import com.example.demo.Dto.BorrowTransactionDto;
import com.example.demo.Dto.SystemUserDto;
import com.example.demo.entity.BorrowTransaction;
import com.example.demo.entity.SystemUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface BorrowTransactionMapper {



    @Mapping(target = "bookId",source = "book.id")
    @Mapping(target = "memberId",source = "member.id")
    @Mapping(target = "processedById",source = "processedBy.id")
    BorrowTransactionDto toDto (BorrowTransaction borrowTransaction);

    @Mapping(target = "book", ignore = true)
    @Mapping(target = "member", ignore = true)
    @Mapping(target = "processedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    BorrowTransaction toEntity(BorrowTransactionDto borrowTransactionDto);


    @Mapping(target = "book", ignore = true)
    @Mapping(target = "member", ignore = true)
    @Mapping(target = "processedBy", ignore = true)
    @Mapping(target = "id", ignore = true)
    void updateBorrowTransactionFromtodto(BorrowTransactionDto dto, @MappingTarget BorrowTransaction entity);


}
