package com.example.demo.controller;


import com.example.demo.Dto.PublisherDto;
import com.example.demo.service.PublisherService;
import com.example.demo.service.impl.PublisherServiceimpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publihser")
public class PublisherController {


    private final PublisherService publisherService;


    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }


    @PostMapping("/add")
    public PublisherDto addPublisher(@RequestBody PublisherDto publisher) {
        return publisherService.addPublisher(publisher);
    }


    @PutMapping("/{id}")
    public PublisherDto updatePublisher(@PathVariable Long id, @RequestBody PublisherDto publisher) {

        return publisherService.updatePublisher(id, publisher);

    }


    @DeleteMapping("/{id}")
    public void deletepublisher(@PathVariable Long id) {

        publisherService.deletepublisher(id);

    }


    @GetMapping("/{id}")
    public PublisherDto getPublisherById(@PathVariable Long id) {

        return publisherService.getPublisherById(id);

    }

@GetMapping("")
    public List<PublisherDto> getAllPublisher() {

        return publisherService.getAllPublisher();
    }

}
