package com.example.demo.controller;


import com.example.demo.Dto.AuthorDto;
import com.example.demo.service.impl.AuthorServiceimpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {


    private final AuthorServiceimpl authorService;


    public AuthorController(AuthorServiceimpl authorService) {
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
