package com.example.demo.service.impl;


import com.example.demo.Dto.PublisherDto;
import com.example.demo.entity.Publisher;
import com.example.demo.mapper.PublisherMapper;
import com.example.demo.repo.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublisherServiceimpl {

    private final PublisherRepository publisherRepository;

    private final PublisherMapper publisherMapper;

    public PublisherServiceimpl(PublisherRepository publisherRepository, PublisherMapper publisherMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
    }


    public PublisherDto addPublisher(PublisherDto dto) {

        Publisher exist = publisherMapper.toEntity(dto);

        Publisher saved = publisherRepository.save(exist);

        return publisherMapper.toDto(saved);


    }


    public PublisherDto updatePublisher(Long id, PublisherDto dto) {

        Publisher existpublisher = publisherRepository.findById(id).orElseThrow(() -> new RuntimeException("not found publisher with id" + id));

        publisherMapper.updatePublisherFromDto(dto, existpublisher);


        Publisher saved = publisherRepository.save(existpublisher);

        return publisherMapper.toDto(saved);


    }


    public void deletepublisher(Long id) {

        publisherRepository.deleteById(id);

    }


    public PublisherDto getPublisherById(Long id) {

        Publisher saved = publisherRepository.findById(id).orElseThrow(() -> new RuntimeException("publisher not found with id " + id));

        return publisherMapper.toDto(saved);
    }


    public List<PublisherDto> getAllPublisher() {

        return publisherRepository.findAll().stream().map(publisherMapper::toDto).collect(Collectors.toList());
    }


}
