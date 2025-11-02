package com.example.demo.service.impl;

import com.example.demo.Dto.LoginDto;
import com.example.demo.Dto.LoginResponseDto;
import com.example.demo.Dto.SignupDto;
import com.example.demo.Dto.SystemUserDto;
import com.example.demo.entity.SystemUser;
import com.example.demo.mapper.LoginMapper;
import com.example.demo.mapper.SignupMapper;
import com.example.demo.mapper.SystemUserMapper;
import com.example.demo.repo.SystemUserRepository;
import com.example.demo.service.SystemUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class SystemUserServiceimpl implements SystemUserService {

    private final SystemUserRepository systemUserRepository;

    private final PasswordEncoder passwordEncoder;

    private final SystemUserMapper systemUserMapper;

    private final SignupMapper signupMapper;

    private final LoginMapper loginMapper;

    public SystemUserServiceimpl(SystemUserRepository systemUserRepository, PasswordEncoder passwordEncoder, SystemUserMapper systemUserMapper, SignupMapper signupMapper, LoginMapper loginMapper) {
        this.systemUserRepository = systemUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.systemUserMapper = systemUserMapper;
        this.signupMapper = signupMapper;
        this.loginMapper = loginMapper;
    }


    public SignupDto register(SignupDto dto) {

        if (systemUserRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("this email aleady exist");
        }
        SystemUser entity = signupMapper.toEntity(dto);
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        String generatedUsername = dto.getFull_name().replaceAll("\\s", "").toLowerCase() +
                new Random().nextInt(1000);
        entity.setUsername(generatedUsername);

        entity.setRole(SystemUser.Role.MEMBER);
        SystemUser saved = systemUserRepository.save(entity);

        return signupMapper.toDto(saved);
    }

    @Override
    public LoginResponseDto login(LoginDto dto) {

        SystemUser user = systemUserRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new RuntimeException("this is email not found"));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("The password is invalid!");
        }

        return loginMapper.toLoginResponseDto(user);


    }


    public SystemUserDto addSystemUser(SystemUserDto dto) {

        SystemUser exist = systemUserMapper.toEntity(dto);
        exist.setPassword(passwordEncoder.encode(dto.getPassword()));
        // هنا مش لازم اعمل الرول مانيوال لان بكل بساطه دي فانكشن خاصه للادمن وال عنده اكسس ليها يعني ف يقدر يحدد مين ال رول  من  الشاشه لانه يببقي معاه كل الصلاحيات ع عكس اليوزر ان اي حد بيسجل جديد بيكون دوره يوزر
        SystemUser saved = systemUserRepository.save(exist);
        return systemUserMapper.toDto(saved);

    }


    public SystemUserDto updateSystemUser(Long id, SystemUserDto dto) {

        SystemUser existSystemUser = systemUserRepository.findById(id).orElseThrow(() -> new RuntimeException("systemuser not found with " + id));

        systemUserMapper.updateSystemUserFromtodto(dto, existSystemUser);

        SystemUser saved = systemUserRepository.save(existSystemUser);

        return systemUserMapper.toDto(saved);

    }

    public void deleteSystemUser(Long id) {
        systemUserRepository.deleteById(id);
    }

    public SystemUserDto getSystemUserById(Long id) {
        SystemUser saved = systemUserRepository.findById(id).orElseThrow(() -> new RuntimeException("systemuser not found with " + id));
        return systemUserMapper.toDto(saved);
    }

    public List<SystemUserDto> getAllSystemUsers() {
        return systemUserRepository.findAll().stream().map(systemUserMapper::toDto).collect(Collectors.toList());
    }
}




