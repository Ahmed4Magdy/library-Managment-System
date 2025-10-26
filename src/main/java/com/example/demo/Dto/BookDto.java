package com.example.demo.Dto;

import com.example.demo.entity.Author;
import com.example.demo.entity.BorrowTransaction;
import com.example.demo.entity.Category;
import com.example.demo.entity.Publisher;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
public class BookDto {

    private String title;
    private String isbn;
    private String edition;
    private String language;
    private Integer publicationYear;
    private String summary;
    private String coverImage;
    private Long publisherId;


}
