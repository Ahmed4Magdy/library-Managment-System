package com.example.demo.Dto;

import com.example.demo.entity.Book;
import com.example.demo.entity.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CategoryDto {


    private String name;


    private Long parentId;  // انا دلوقتي عايز الفورن كي بتاع نوع الفءه  فلازم يكون ف نفس الجدول يعني من الكاتجوري وغير كده فلازم يكون الماللك علشان اقدر احط الفورن كي فالعلاقه هتبقي مني ل ون


}
