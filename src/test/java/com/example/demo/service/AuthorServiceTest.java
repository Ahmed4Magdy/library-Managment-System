package com.example.demo.service;


import com.example.demo.Dto.AuthorDto;
import com.example.demo.entity.Author;
import com.example.demo.mapper.AuthorMapper;
import com.example.demo.repo.AuthorRepository;
import com.example.demo.service.impl.AuthorServiceimpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //mockito framework use for mock object
public class AuthorServiceTest {


    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorServiceimpl authorServiceimpl;


    private Author author;
    private AuthorDto dto;

    @BeforeEach
    void setup() {

        author = new Author();
        author.setName("eng.ahmed");

        dto = new AuthorDto();
        dto.setName("eng.ahmed");

    }


    @Test
    void createAuthor_shouldReturnAuthorDto() {


        when(authorMapper.toEntity(dto)).thenReturn(author);
        when(authorRepository.save(author)).thenReturn(author);
        when(authorMapper.toDto(author)).thenReturn(dto);


        AuthorDto result = authorServiceimpl.addAuthor(dto);

        assertNotNull(result);
        assertEquals("eng.ahmed", dto.getName());

    }


    @Test
    void testGetFindAllAuthors() {

        when(authorRepository.findAll()).thenReturn(List.of(author));
        when(authorMapper.toDto(author)).thenReturn(dto);


        List<AuthorDto> result = authorServiceimpl.findAll();

        assertEquals(1, result.size());
        assertEquals("eng.ahmed", result.get(0).getName());

    }

    @Test
    void testGetAuthorById_Found() {
        when(authorRepository.findById(10L)).thenReturn(Optional.of(author));
        when(authorMapper.toDto(author)).thenReturn(dto);

        AuthorDto result = authorServiceimpl.findById(10L);

        assertNotNull(result);
        assertEquals("eng.ahmed", result.getName());
        verify(authorRepository, times(1)).findById(10L);
    }


    @Test
    void testGetAuthorById_NotFound() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authorServiceimpl.findById(1L);
        });

        assertEquals("not found author with id 1", exception.getMessage());
        verify(authorRepository, times(1)).findById(1L);
    }


    @Test
    void testUpdateAuthor() {

        when(authorRepository.findById(100L)).thenReturn(Optional.of(author));
        doNothing().when(authorMapper).updateBookFromDto(dto, author);
        when(authorRepository.save(author)).thenReturn(author);
        when(authorMapper.toDto(author)).thenReturn(dto);


        AuthorDto result = authorServiceimpl.updateAuthor(100L, dto);

        assertNotNull(result);
        assertEquals("eng.ahmed",dto.getName());

    }

    @Test
    void testdeleteAuthor(){


        doNothing().when(authorRepository).deleteById(100L);

        authorServiceimpl.deleteAuthor(100L);

        verify(authorRepository, times(1)).deleteById(100L);


    }

}
