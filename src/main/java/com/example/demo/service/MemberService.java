package com.example.demo.service;

import com.example.demo.Dto.MemberDto;
import com.example.demo.entity.Member;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public interface MemberService {


    public MemberDto addMember(MemberDto dto);

    public MemberDto updateMember(Long id, MemberDto dto);

    public void deleteMember(Long id);

    public MemberDto getMemberById(Long id);

    public List<MemberDto> getAllMembers();


}
