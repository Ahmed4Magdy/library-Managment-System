package com.example.demo.service;

import com.example.demo.Dto.PublisherDto;
import com.example.demo.entity.Publisher;

import java.util.List;
import java.util.stream.Collectors;

public interface PublisherService {



    public PublisherDto addPublisher(PublisherDto dto);

    public PublisherDto updatePublisher(Long id, PublisherDto dto);

    public void deletepublisher(Long id);

    public PublisherDto getPublisherById(Long id);



}
