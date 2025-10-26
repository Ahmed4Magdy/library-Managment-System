package com.example.demo.service;

import com.example.demo.Dto.BorrowTransactionDto;
import com.example.demo.entity.Book;
import com.example.demo.entity.BorrowTransaction;
import com.example.demo.entity.Member;
import com.example.demo.entity.SystemUser;

public interface BorrowTransactionService {


    public BorrowTransactionDto createBorrowTransaction(BorrowTransactionDto dto) ;


    public BorrowTransactionDto updateBorrowTransaction(Long id, BorrowTransactionDto dto);



    public BorrowTransactionDto findByIdBorrowTransaction(Long id);



}
