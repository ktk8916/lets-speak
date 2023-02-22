package com.mallohaja.letsspeak.web.Member;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mallohaja.letsspeak.web.Member.requestdto.JoinMemberDto;

import lombok.RequiredArgsConstructor;

@RestController()
@RequiredArgsConstructor
public class MemberController {
    
    private final MemberService memberService;

    @PostMapping("/member")
    public Long join(@RequestBody JoinMemberDto dto){
        return memberService.join(dto);
    }
}
