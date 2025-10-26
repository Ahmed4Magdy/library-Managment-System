package com.example.demo.service;


import com.example.demo.Dto.BookDto;
import com.example.demo.entity.Book;
import com.example.demo.entity.Publisher;
import com.example.demo.mapper.BookMapper;
import com.example.demo.repo.AuthorRepository;
import com.example.demo.repo.BookRepository;
import com.example.demo.repo.CategoryRepository;
import com.example.demo.repo.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {


    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;

    public BookService(BookRepository bookRepository,
                       PublisherRepository publisherRepository,
                       AuthorRepository authorRepository,
                       CategoryRepository categoryRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
        this.bookMapper = bookMapper;
    }


    public BookDto addbook(BookDto dto) {


        Book exist = bookMapper.toEntity(dto);

        Publisher publisher = publisherRepository.findById(dto.getPublisherId())
                .orElseThrow(() -> new RuntimeException("Publisher not found"));

        exist.setPublisher(publisher);

        Book saved = bookRepository.save(exist);

        return bookMapper.toDto(saved);

    }


    public BookDto updateBook(Long id, BookDto dto) {


        Book existing = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("no book with id" + id));

        bookMapper.updateBookFromDto(dto, existing);
        Book saved = bookRepository.save(existing);
        return bookMapper.toDto(saved);


    }


    public void deleteByBook(Long id) {

        bookRepository.deleteById(id);

    }


    public BookDto getbookyById(Long id) {

        Book saved = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("no book with id" + id));
        return bookMapper.toDto(saved);

    }


    public List<BookDto> getAllBook() {

        return bookRepository.findAll().stream().map(bookMapper::toDto).collect(Collectors.toList());
    }


}
