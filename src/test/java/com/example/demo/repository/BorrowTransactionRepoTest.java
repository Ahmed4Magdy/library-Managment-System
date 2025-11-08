package com.example.demo.repository;


import com.example.demo.entity.Book;
import com.example.demo.entity.BorrowTransaction;
import com.example.demo.entity.Member;
import com.example.demo.entity.SystemUser;
import com.example.demo.repo.BookRepository;
import com.example.demo.repo.BorrowTransactionRepository;
import com.example.demo.repo.MemberRepository;
import com.example.demo.repo.SystemUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BorrowTransactionRepoTest {


    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private SystemUserRepository systemUserRepository;
    @Autowired
    private BorrowTransactionRepository borrowTransactionRepository;

    private Book book;
    private Member member;
    private BorrowTransaction borrowTransaction1;
    private SystemUser librarian;

    @BeforeEach
    void setup() {

        book = new Book();
        book.setTitle("Junit testing");

        member = new Member();
        member.setEmail("ahmed@gmail.com");

        librarian = new SystemUser();
        librarian.setFull_name("admin");
        librarian.setEmail("a7med@gmail.com");
        librarian.setRole(SystemUser.Role.LIBRARIAN);

        borrowTransaction1 = new BorrowTransaction();
        borrowTransaction1.setMember(member);
        borrowTransaction1.setBook(book);
    }


    @Test
    public void testCreateBorrowTransactionandFindById() {


        bookRepository.save(book);
        memberRepository.save(member);
        systemUserRepository.save(librarian);
        borrowTransactionRepository.save(borrowTransaction1);

        BorrowTransaction borrowTransaction = borrowTransactionRepository.findById(borrowTransaction1.getId()).orElseThrow(() -> new RuntimeException("borrowtransaction not found with " + borrowTransaction1.getId()));
        assertThat(borrowTransaction.getBook().getTitle()).isEqualTo("Junit testing");
    }

}
