package com.example.demo.repository;


import com.example.demo.entity.Author;
import com.example.demo.repo.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest //springboot tells will load only Jpa and deal with h2 memory
public class AuthorRepoTest {

    @Autowired
    private AuthorRepository authorRepository;

    private Author author;

    @BeforeEach
    public void setup() {

        author = new Author();
        author.setName("hr.tata");
        author=authorRepository.save(author);


    }


    @Test
    public void testFindAll() {

        List<Author> authors = authorRepository.findAll();
        assertThat(authors).hasSize(1);

    }


    @Test
    public void testFindById() {

        Long id =author.getId();
        Author authorexist = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("not found author with id " +id));
        assertThat(author.getName()).isEqualTo("hr.tata");

    }


    @Test
    public void testupdateAuthor() {

        Long id =author.getId();
        Author authorexist = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("not found author with id " +id));
        authorexist.setName("hr.ahmedupdate");
        assertThat(authorexist.getName()).isEqualTo("hr.ahmedupdate");
    }


}
