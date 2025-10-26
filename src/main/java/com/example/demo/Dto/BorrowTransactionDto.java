package com.example.demo.Dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BorrowTransactionDto {

    private Long bookId;
    private Long memberId;
    private Long processedById;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

}
