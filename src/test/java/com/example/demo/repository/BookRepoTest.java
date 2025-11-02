package com.example.demo.repository;


import com.example.demo.entity.Book;
import com.example.demo.entity.Publisher;
import com.example.demo.repo.BookRepository;
import com.example.demo.repo.PublisherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepoTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PublisherRepository publisherRepository;

    @BeforeEach
    void setup() {

        Publisher publisher1 = new Publisher();
        publisher1.setName("7amda hnbka");
        publisherRepository.save(publisher1);

        Publisher publisher2 = new Publisher();
        publisher2.setName("adel shakl");
        publisherRepository.save(publisher2);

        Book book = new Book();
        book.setLanguage("franch");
        book.setPublisher(publisher1);
        bookRepository.save(book);

    }


    @Test
    void testFindAll() {

        List<Publisher> publisher = publisherRepository.findAll();
         assertThat(publisher).hasSize(2);

    }


}
