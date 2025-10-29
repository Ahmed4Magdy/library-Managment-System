package com.example.demo.repository;

import com.example.demo.entity.Publisher;
import com.example.demo.repo.PublisherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class PublisherRepoTest {


    @Autowired
    private PublisherRepository publisherRepository;

    private Publisher publisher;
    @BeforeEach
    void setup() {

        publisher = new Publisher();
        publisher.setName("ashraf");

    }


    @Test
    public void createPublisher() {
        publisherRepository.save(publisher);

        Publisher publisher1 =publisherRepository.findById(1L).orElseThrow(()-> new RuntimeException("Publisher not found with "+publisher.getId()));
       assertThat(publisher1.getName()).isEqualTo("ashraf");
    }

}
