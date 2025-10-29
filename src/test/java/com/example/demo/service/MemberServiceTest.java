package com.example.demo.service;


import com.example.demo.Dto.MemberDto;
import com.example.demo.entity.Member;
import com.example.demo.mapper.MemberMapper;
import com.example.demo.repo.MemberRepository;
import com.example.demo.service.impl.MemberServiceimpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {


    @Mock
    private MemberRepository memberRepository;
    @Mock
    private MemberMapper memberMapper;
    @InjectMocks
    private MemberServiceimpl memberServiceimpl;


    private Member member;
    private MemberDto dto;

    @BeforeEach
    void setup() {

        member = new Member();
        member.setEmail("ahmed@example.com");

        dto = new MemberDto();
        dto.setEmail("ahmed@example.com");

    }


    @Test
    void createMember(){

        when(memberMapper.toEntity(dto)).thenReturn(member);
        when(memberRepository.save(member)).thenReturn(member);
        when(memberMapper.toDto(member)).thenReturn(dto);


        MemberDto result =memberServiceimpl.addMember(dto);

        assertNotNull(result);

    }


    @Test
    void updatemember(){

         when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
          doNothing().when(memberMapper).updateMemberFromDto(dto,member);
          when(memberRepository.save(member)).thenReturn(member);
          when(memberMapper.toDto(member)).thenReturn(dto);

        MemberDto result =memberServiceimpl.updateMember(1L,dto);
        assertEquals(dto.getEmail(),result.getEmail());
    }


    @Test
    void updatemember_NotFound(){

        when(memberRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception =assertThrows(RuntimeException.class,()->memberServiceimpl.updateMember(1L,dto));

        assertEquals("member not found with 1",exception.getMessage());



    }



    @Test
    void testDeleteMember() {
        doNothing().when(memberRepository).deleteById(1L);

        memberServiceimpl.deleteMember(1L);

        verify(memberRepository, times(1)).deleteById(1L);
        // وظيفه ف موكيتو بيتاكد (verify)ان الفانكشن الديليت دي اتنادت مره واحده  من الموكيتو ريبو
    }
}
