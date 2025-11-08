package com.example.demo.service;


import com.example.demo.Dto.PublisherDto;
import com.example.demo.entity.Publisher;
import com.example.demo.mapper.PublisherMapper;
import com.example.demo.repo.PublisherRepository;
import com.example.demo.service.impl.PublisherServiceimpl;
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

@ExtendWith(MockitoExtension.class) //for active mockito
public class PublisherServiceTest {

    @Mock
    private PublisherRepository publisherRepository;

    @Mock
    private PublisherMapper publisherMapper;

    @InjectMocks
    private PublisherServiceimpl publisherServiceimpl;

    private Publisher publisher;
    private PublisherDto dto;
    @BeforeEach
    void setup() {

        publisher = new Publisher();
        publisher.setName("T.A Ahmed");

        dto = new PublisherDto();
        dto.setName("T.A Ahmed");

    }


    @Test
    void createPublisher_shouldReturnPublisherDto() {


        when(publisherMapper.toEntity(dto)).thenReturn(publisher);
        when(publisherRepository.save(publisher)).thenReturn(publisher);
        when(publisherMapper.toDto(publisher)).thenReturn(dto);


        PublisherDto result = publisherServiceimpl.addPublisher(dto);

        assertNotNull(result);
        assertEquals("T.A Ahmed", dto.getName());

    }


    @Test
    void testGetFindAllPublishers() {

        when(publisherRepository.findAll()).thenReturn(List.of(publisher));
        when(publisherMapper.toDto(publisher)).thenReturn(dto);


        List<PublisherDto> result = publisherServiceimpl.getAllPublisher();

        assertEquals(1, result.size());
        assertEquals("T.A Ahmed", result.get(0).getName());

    }

    @Test
    void testGetPublisherById_Found() {
        when(publisherRepository.findById(10L)).thenReturn(Optional.of(publisher));
        when(publisherMapper.toDto(publisher)).thenReturn(dto);

        PublisherDto result = publisherServiceimpl.getPublisherById(10L);

        assertNotNull(result);
        assertEquals("T.A Ahmed", result.getName());
        verify(publisherRepository, times(1)).findById(10L);
    }


    @Test
    void testGetPublisherById_NotFound() {
        when(publisherRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            publisherServiceimpl.getPublisherById(1L);
        });

        assertEquals("publisher not found with id 1", exception.getMessage());
        verify(publisherRepository, times(1)).findById(1L);
    }


    @Test
    void testUpdatePublisher() {

        when(publisherRepository.findById(100L)).thenReturn(Optional.of(publisher));
        doNothing().when(publisherMapper).updatePublisherFromDto(dto, publisher);
        when(publisherRepository.save(publisher)).thenReturn(publisher);
        when(publisherMapper.toDto(publisher)).thenReturn(dto);


        PublisherDto result = publisherServiceimpl.updatePublisher(100L, dto);

        assertNotNull(result);
        assertEquals("T.A Ahmed",dto.getName());

    }

    @Test
    void testdeleteAuthor(){


        doNothing().when(publisherRepository).deleteById(100L);

        publisherServiceimpl.deletepublisher(100L);

        verify(publisherRepository, times(1)).deleteById(100L);


    }



}
