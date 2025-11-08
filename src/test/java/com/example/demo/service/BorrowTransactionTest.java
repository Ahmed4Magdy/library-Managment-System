package com.example.demo.service;


import com.example.demo.Dto.BorrowTransactionDto;
import com.example.demo.entity.Book;
import com.example.demo.entity.BorrowTransaction;
import com.example.demo.entity.Member;
import com.example.demo.entity.SystemUser;
import com.example.demo.mapper.BorrowTransactionMapper;
import com.example.demo.repo.BookRepository;
import com.example.demo.repo.BorrowTransactionRepository;
import com.example.demo.repo.MemberRepository;
import com.example.demo.repo.SystemUserRepository;
import com.example.demo.service.impl.BorrowTransactionServiceimpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BorrowTransactionTest {


    @Mock
    private BorrowTransactionRepository borrowTransactionRepository;
    @Mock
    private BorrowTransactionMapper borrowTransactionMapper;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private SystemUserRepository systemUserRepository;
    @InjectMocks
    private BorrowTransactionServiceimpl borrowTransactionServiceimpl;

    private BorrowTransaction entity;
    private BorrowTransactionDto dto;
    private Book book;
    private Member member;
    private SystemUser systemUser;

    @BeforeEach
    void setup() {

        book = new Book();
        book.setTitle("Db");

        member = new Member();
        member.setEmail("ahmed@example.com");

        systemUser = new SystemUser();
        systemUser.setUsername("admin-11");
        systemUser.setEmail("ahmed@example.com");
        systemUser.setRole(SystemUser.Role.ADMIN);


        entity = new BorrowTransaction();
        entity.setBook(book);
        entity.setMember(member);
        entity.setProcessedBy(systemUser);

        dto = new BorrowTransactionDto();
        dto.setBookId(11L);
        dto.setMemberId(100L);
        dto.setProcessedById(1L);

    }


    @Test
    void createBorrowTransaction() {


        when(bookRepository.findById(11L)).thenReturn(Optional.of(book));
        when(memberRepository.findById(100L)).thenReturn(Optional.of(member));
        when(systemUserRepository.findById(1L)).thenReturn(Optional.of(systemUser));
        when(borrowTransactionMapper.toEntity(dto)).thenReturn(entity);
        when(borrowTransactionRepository.save(entity)).thenReturn(entity);
        when(borrowTransactionMapper.toDto(entity)).thenReturn(dto);


        BorrowTransactionDto result = borrowTransactionServiceimpl.createBorrowTransaction(dto);

        assertNotNull(result);
        assertEquals(dto.getBookId(), result.getBookId());
    }


    @Test
    void testGetTransactionById_Found() {
        when(borrowTransactionRepository.findById(1000L)).thenReturn(Optional.of(entity));
        when(borrowTransactionMapper.toDto(entity)).thenReturn(dto);

        BorrowTransactionDto result = borrowTransactionServiceimpl.findByIdBorrowTransaction(1000L);

        assertNotNull(result);
        verify(borrowTransactionRepository, times(1)).findById(1000L);
    }


    @Test
    void testGetTransactionById_NotFound() {
        when(borrowTransactionRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () ->
                borrowTransactionServiceimpl.findByIdBorrowTransaction(1L));

        assertEquals("not found borrowtransaction with1", ex.getMessage());
        verify(borrowTransactionRepository, times(1)).findById(1L);
    }


}