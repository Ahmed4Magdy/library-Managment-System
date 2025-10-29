package com.example.demo.repository;


import com.example.demo.entity.Member;
import com.example.demo.repo.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class MemberRepoTest {


    @Autowired
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    void setup() {


        member = new Member();
        member.setPhone("01233242");
        member.setFull_name("ahmed magdy");
        member.setEmail("ahmed@gmail.com");

    }


    @Test
    void testCreateMemberandFindById() {

        memberRepository.save(member);

        Member member1 =memberRepository.findById(member.getId()).orElseThrow(()->new RuntimeException("member not found with"+member.getId()));
         assertThat(member1.getFull_name()).isEqualTo("ahmed magdy");
    }

}
