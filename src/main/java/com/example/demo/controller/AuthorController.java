package com.example.demo.controller;


import com.example.demo.Dto.AuthorDto;
import com.example.demo.entity.Author;
import com.example.demo.service.AuthorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/author")
public class AuthorController {


    private final AuthorService authorService;


    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }



    @PostMapping("/add")
    public AuthorDto addAuthor(@RequestBody AuthorDto author) {

        return authorService.addAuthor(author);
    }



    @GetMapping("/{id}")
    public AuthorDto findById(@PathVariable Long id) {

        return authorService.findById(id);

    }



    @PutMapping("/{id}")
    public AuthorDto updateAuthor(@PathVariable Long id,@RequestBody AuthorDto author) {

        return authorService.updateAuthor(id, author);

    }


    @DeleteMapping("")
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }


    @GetMapping("")
    public List<AuthorDto> findAll() {
        return authorService.findAll();
    }

}
