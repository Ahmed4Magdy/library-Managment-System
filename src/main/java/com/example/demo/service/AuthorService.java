package com.example.demo.service;

import com.example.demo.Dto.AuthorDto;
import com.example.demo.entity.Author;
import com.example.demo.mapper.AuthorMapper;
import com.example.demo.repo.AuthorRepository;

import java.util.List;
import java.util.stream.Collectors;

public interface AuthorService {


    public AuthorDto addAuthor(AuthorDto dto);

    public AuthorDto updateAuthor(Long id, AuthorDto dto);


    public AuthorDto findById(Long id);

    public void deleteAuthor(Long id);

    public List<AuthorDto> findAll();


}
