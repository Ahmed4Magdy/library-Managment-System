package com.example.demo.service.impl;

import com.example.demo.Dto.MemberDto;
import com.example.demo.entity.Member;
import com.example.demo.mapper.MemberMapper;
import com.example.demo.repo.MemberRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceimpl {


    private final MemberRepository memberRepository;

    private final MemberMapper memberMapper;

    public MemberServiceimpl(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    public MemberDto addMember(MemberDto dto) {

        Member exist = memberMapper.toEntity(dto);
        exist.setMembership_date(LocalDate.now());

        Member saved = memberRepository.save(exist);
        return memberMapper.toDto(saved);

    }

    public MemberDto updateMember(Long id, MemberDto dto) {

        Member existmemeber = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("member not found with " + id));

        memberMapper.updateMemberFromDto(dto, existmemeber);

        Member saved = memberRepository.save(existmemeber);
        return memberMapper.toDto(saved);

    }

    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    public MemberDto getMemberById(Long id) {

        Member saved = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("member not found with " + id));
        return memberMapper.toDto(saved);
    }

    public List<MemberDto> getAllMembers() {
        return memberRepository.findAll().stream().map(memberMapper::toDto).collect(Collectors.toList());
    }
}


