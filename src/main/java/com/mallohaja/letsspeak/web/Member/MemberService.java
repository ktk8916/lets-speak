package com.mallohaja.letsspeak.web.Member;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mallohaja.letsspeak.web.Member.requestdto.JoinMemberDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    @Transactional
    public Long join(JoinMemberDto dto){
        Member member = Member.createMember(dto.getName());
        return memberRepository.save(member).getId();
    }

    public Member findById(Long id){
        return memberRepository.findById(id).orElseThrow(()->new IllegalArgumentException(id + "번 회원이 존재하지 않습니다"));
    }
    
}
