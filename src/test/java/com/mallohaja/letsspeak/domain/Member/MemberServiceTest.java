package com.mallohaja.letsspeak.domain.Member;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mallohaja.letsspeak.domain.Member.requestdto.JoinMemberDto;

@SpringBootTest
public class MemberServiceTest {

    @Autowired private MemberService memberService;

    @Test
    void 회원가입_후_멤버수가_증가해야_한다() {
        int beforeMemberCount = memberService.findAll().size();
        JoinMemberDto dto = new JoinMemberDto("member1");

        memberService.join(dto);
        int afterMemberCount = memberService.findAll().size();

        Assertions.assertThat(beforeMemberCount+1).isEqualTo(afterMemberCount);
    }

    @Test
    void 회원가입_후_조회_테스트(){
        String name = "member1";
        JoinMemberDto dto = new JoinMemberDto(name);
        Long memberId = memberService.join(dto);

        String memberName = memberService.findById(memberId).getName();

        Assertions.assertThat(name).isEqualTo(memberName);
    }
    

    @Test
    void 없는_회원_조회시_예외를_반환한다() {
        JoinMemberDto dto = new JoinMemberDto("member1");
        Long memberId = memberService.join(dto);

        assertThrows(IllegalArgumentException.class, ()->memberService.findById(memberId+1));
    }
}
