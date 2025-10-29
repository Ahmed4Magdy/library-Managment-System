package com.example.demo.controller;

import com.example.demo.Dto.BorrowTransactionDto;
import com.example.demo.service.BorrowTransactionService;
import com.example.demo.service.impl.BorrowTransactionServiceimpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrowwtrasaction")
public class BorrowTransactionController {

    private final BorrowTransactionService borrowTransactionService;

    public BorrowTransactionController(BorrowTransactionService borrowTransactionService) {
        this.borrowTransactionService = borrowTransactionService;
    }


    @PostMapping("/add")
    public BorrowTransactionDto create(@RequestBody BorrowTransactionDto request) {


        return borrowTransactionService.createBorrowTransaction(request);


    }


    @PutMapping("/{id}")
    public BorrowTransactionDto updateBorrowTransaction(@PathVariable Long id, @RequestBody BorrowTransactionDto borrow) {

        return borrowTransactionService.updateBorrowTransaction(id, borrow);

    }


    @GetMapping("/{id}")
    public BorrowTransactionDto findByIdBorrowTransaction(@PathVariable Long id) {

        return borrowTransactionService.findByIdBorrowTransaction(id);

    }


}
