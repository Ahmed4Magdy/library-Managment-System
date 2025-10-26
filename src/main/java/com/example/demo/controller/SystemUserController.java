package com.example.demo.controller;


import com.example.demo.Dto.SystemUserDto;
import com.example.demo.entity.SystemUser;
import com.example.demo.service.SystemUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/systemuser")
public class SystemUserController {


    private final SystemUserService systemUserService;


    public SystemUserController(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }


    @PostMapping("/add")
    public SystemUserDto addSystemUser(@RequestBody SystemUserDto dto) {
        return systemUserService.addSystemUser(dto);
    }

    @PutMapping("/{id}")
    public SystemUserDto updateSystemUser(@PathVariable Long id, @RequestBody SystemUserDto systemUser) {
        return systemUserService.updateSystemUser(id, systemUser);
    }


    @DeleteMapping("/{id}")
    public void deleteSystemUser(@PathVariable Long id) {
        systemUserService.deleteSystemUser(id);
    }

    @GetMapping("/{id}")
    public SystemUserDto getSystemUserById(@PathVariable Long id) {
        return systemUserService.getSystemUserById(id);
    }

    @GetMapping("")
    public List<SystemUserDto> getAllSystemUsers() {
        return systemUserService.getAllSystemUsers();
    }


}
