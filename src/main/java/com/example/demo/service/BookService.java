package com.example.demo.service;

import com.example.demo.Dto.BookDto;
import com.example.demo.entity.Book;
import com.example.demo.entity.Publisher;

import java.util.List;
import java.util.stream.Collectors;

public interface BookService {


    public BookDto addbook(BookDto dto);

    public BookDto updateBook(Long id, BookDto dto);

    public void deleteByBook(Long id);

    public BookDto getbookyById(Long id);

    public List<BookDto> getAllBook();

}
