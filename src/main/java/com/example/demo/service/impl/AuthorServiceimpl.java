package com.example.demo.service.impl;


import com.example.demo.Dto.AuthorDto;
import com.example.demo.entity.Author;
import com.example.demo.mapper.AuthorMapper;
import com.example.demo.repo.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceimpl {


    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;

    public AuthorServiceimpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }


    public AuthorDto addAuthor(AuthorDto dto) {

        Author author = authorMapper.toEntity(dto);
        Author saved = authorRepository.save(author);
        return authorMapper.toDto(saved);

    }

    public AuthorDto updateAuthor(Long id, AuthorDto dto) {

        Author existAuthor = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("not found author with id " + id));

        Author saved = authorRepository.save(existAuthor);

        return authorMapper.toDto(saved);

    }


    public AuthorDto findById(Long id) {

        Author saved = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("not found author with id " + id));

        return authorMapper.toDto(saved);

    }


    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }


    public List<AuthorDto> findAll() {
        return authorRepository.findAll().stream().map(authorMapper::toDto).collect(Collectors.toList());
    }


}
