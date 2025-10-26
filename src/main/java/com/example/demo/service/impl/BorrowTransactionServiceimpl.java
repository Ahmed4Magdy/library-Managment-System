package com.example.demo.service.impl;


import com.example.demo.Dto.BorrowTransactionDto;
import com.example.demo.entity.*;
import com.example.demo.mapper.BorrowTransactionMapper;
import com.example.demo.mapper.SystemUserMapper;
import com.example.demo.repo.BookRepository;
import com.example.demo.repo.BorrowTransactionRepository;
import com.example.demo.repo.MemberRepository;
import com.example.demo.repo.SystemUserRepository;
import org.springframework.stereotype.Service;

@Service
public class BorrowTransactionServiceimpl {


    private final BorrowTransactionRepository borrowTransactionRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final SystemUserRepository systemUserRepository;
    private final BorrowTransactionMapper borrowTransactionMapper;

    public BorrowTransactionServiceimpl(BorrowTransactionRepository transactionRepository, BorrowTransactionRepository borrowTransactionRepository, MemberRepository memberRepository, BookRepository bookRepository, SystemUserRepository systemUserRepository, SystemUserMapper systemUserMapper, BorrowTransactionMapper borrowTransactionMapper) {
        this.borrowTransactionRepository = borrowTransactionRepository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.systemUserRepository = systemUserRepository;
        this.borrowTransactionMapper = borrowTransactionMapper;
    }

    public BorrowTransactionDto createBorrowTransaction(BorrowTransactionDto dto) {


        BorrowTransaction exist = borrowTransactionMapper.toEntity(dto);

        Book book = bookRepository.findById(dto.getBookId()).orElseThrow(() -> new RuntimeException("Book not found"));
        exist.setBook(book);

        Member member = memberRepository.findById(dto.getMemberId()).orElseThrow(() -> new RuntimeException("Member not found"));
        exist.setMember(member);


        SystemUser systemUser = systemUserRepository.findById(dto.getProcessedById()).orElseThrow(() -> new RuntimeException("system_user not found"));
        exist.setProcessedBy(systemUser);


        BorrowTransaction saved = borrowTransactionRepository.save(exist);

        return borrowTransactionMapper.toDto(saved);


    }


    public BorrowTransactionDto updateBorrowTransaction(Long id, BorrowTransactionDto dto) {

        BorrowTransaction existborrowtransaction = borrowTransactionRepository.findById(id).orElseThrow(() -> new RuntimeException("not found borrowtransaction with" + id));

        borrowTransactionMapper.updateBorrowTransactionFromtodto(dto, existborrowtransaction);

        BorrowTransaction saved = borrowTransactionRepository.save(existborrowtransaction);

        return borrowTransactionMapper.toDto(saved);

    }


    public BorrowTransactionDto findByIdBorrowTransaction(Long id) {

        BorrowTransaction saved = borrowTransactionRepository.findById(id).orElseThrow(() -> new RuntimeException("not found borrowtransaction with" + id));
        return borrowTransactionMapper.toDto(saved);
    }


}
