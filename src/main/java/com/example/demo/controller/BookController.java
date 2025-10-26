package com.example.demo.controller;

import com.example.demo.Dto.BookDto;
import com.example.demo.entity.Book;
import com.example.demo.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {


    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @PostMapping("/add")
    public BookDto addbook(@RequestBody BookDto request) {
        return bookService.addbook(request);
    }


    @PutMapping("/{id}")
    public BookDto updateBook(@PathVariable Long id, @RequestBody BookDto dto) {

        return bookService.updateBook(id, dto);


    }


    @DeleteMapping("/{id}")
    public void deleteByBook(@PathVariable Long id) {

        bookService.deleteByBook(id);

    }


    @GetMapping("/{id}")
    public BookDto getbookyById(@PathVariable Long id) {

        return bookService.getbookyById(id);

    }


    @GetMapping("")
    public List<BookDto> getAllBook() {

        return bookService.getAllBook();
    }


}
