//package com.example.demo.config;
//
//import com.example.demo.entity.SystemUser;
//import com.example.demo.repo.SystemUserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@RequiredArgsConstructor
//public class DataInitializer {
//
//    private final SystemUserRepository systemUserRepository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Bean
//    public CommandLineRunner initUsers() {
//        return args -> {
//            if (systemUserRepository.count() == 0) {
//                SystemUser admin = new SystemUser();
//                admin.setUsername("admin");
//
//                admin.setPassword(passwordEncoder.encode("admin123"));
//                admin.setFull_name("Admin User");
//                admin.setRole(SystemUser.Role.ADMIN);
//
//                SystemUser librarian = new SystemUser();
//                librarian.setUsername("librarian");
//                librarian.setPassword(passwordEncoder.encode("lib123"));
//                librarian.setFull_name("Librarian User");
//                librarian.setRole(SystemUser.Role.LIBRARIAN);
//
//                SystemUser staff = new SystemUser();
//                staff.setUsername("staff");
//                staff.setPassword(passwordEncoder.encode("staff123"));
//                staff.setFull_name("Staff User");
//                staff.setRole(SystemUser.Role.STAFF);
//
//                systemUserRepository.save(admin);
//                systemUserRepository.save(librarian);
//                systemUserRepository.save(staff);
//
//            }
//        };
//    }
//}
//
