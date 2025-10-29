package com.example.demo.repository;

import com.example.demo.entity.SystemUser;
import com.example.demo.repo.SystemUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class SystemUserRepoTest {

    @Autowired
    private SystemUserRepository systemUserRepository;

    private SystemUser admin;

    @BeforeEach
    void setup() {

        admin = new SystemUser();
        admin.setFull_name("Admin");
        admin.setUsername("admin-123");
        admin.setRole(SystemUser.Role.ADMIN);

    }

    @Test
    public void CreateUserandFindByUserName() {

        systemUserRepository.save(admin);

        SystemUser exist = systemUserRepository.findByUsername("admin-123").orElseThrow(() -> new RuntimeException("systemuser not found with" + admin.getId()));
        assertThat(exist.getUsername()).isEqualTo("admin-123");
    }

}
