package com.example.demo.repository;

import com.example.demo.entity.Category;
import com.example.demo.repo.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CategoryRepoTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private Category parent;
    @BeforeEach
    void setup() {
        parent = new Category();
        parent.setName("programming");
        categoryRepository.save(parent);  // كده الفورن كي بنل
    }


    @Test
    void testCreateCategorywithoutParent() {

        assertThat(parent.getName()).isEqualTo("programming");
        assertThat(parent.getParent()).isNull();
        List<Category> categories = categoryRepository.findAll();
        assertThat(categories).hasSize(1);
// يعني معندهوش فرع من الفءه دي لسه لانه اول فءه
    }

    @Test
    void testCreateCategoryParent() {


        Category child = new Category();
        child.setName("java");
        child.setParent(parent); // يعتبر بعين جافا فرع من كتاب البروجرامينج وهو الرفرنس منه زي ف البوست مان بتكتب 1 وهو قصد ع كتاب البروجرامينج
        categoryRepository.save(child);

        Category foundChild = categoryRepository.findById(child.getId()).orElse(null);
        assertThat(foundChild).isNotNull();
        assertThat(foundChild.getParent()).isNotNull();


    }


}
