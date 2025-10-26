package com.example.demo.controller;

import com.example.demo.Dto.MemberDto;
import com.example.demo.service.impl.MemberServiceimpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberServiceimpl memberService;


    public MemberController(MemberServiceimpl memberService) {
        this.memberService = memberService;
    }


    @PostMapping("/add")
    public MemberDto addMember(@RequestBody MemberDto member) {
        return memberService.addMember(member);
    }

    @PutMapping("/{id}")
    public MemberDto updateMember(@PathVariable Long id,@RequestBody MemberDto member) {

        return memberService.updateMember(id, member);
    }

   @DeleteMapping("/{id}")
    public void deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
    }

    @GetMapping("/{id}")
    public MemberDto getMemberById(@PathVariable Long id) {
        return memberService.getMemberById(id);
    }

    @GetMapping("")
    public List<MemberDto> getAllMembers() {
        return memberService.getAllMembers();
    }


}
