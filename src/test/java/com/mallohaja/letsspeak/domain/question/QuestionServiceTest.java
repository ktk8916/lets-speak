package com.mallohaja.letsspeak.domain.question;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mallohaja.letsspeak.domain.Member.MemberService;
import com.mallohaja.letsspeak.domain.Member.requestdto.JoinMemberDto;
import com.mallohaja.letsspeak.domain.question.requestdto.ChangeUpvoteDto;
import com.mallohaja.letsspeak.domain.question.requestdto.PostQuestionDto;
import com.mallohaja.letsspeak.domain.question.requestdto.UpdateQuestionDto;

@SpringBootTest
public class QuestionServiceTest {

    @Autowired private MemberService memberService;
    @Autowired private QuestionService questionService;

    // @BeforeEach
    // void 회원등록(){
    //     JoinMemberDto dto = new JoinMemberDto("member1");
    //     memberService.join(dto);
    // }

    @Test
    void 질문작성_시_회원이_아니면_예외를_반환한다(){

        PostQuestionDto dto = new PostQuestionDto(100L, "content");
        Assertions.assertThrows(IllegalArgumentException.class, ()->questionService.postQuestion(dto)); 
    }

    @Test
    void 질문작성_시_질문의_수가_증가한다(){
        JoinMemberDto memberDto = new JoinMemberDto("member1");
        Long memberId = memberService.join(memberDto);
        int beforeQuestionCount = questionService.findAll().size();

        PostQuestionDto QuestionDto = new PostQuestionDto(memberId, "content");
        questionService.postQuestion(QuestionDto);

        org.assertj.core.api.Assertions.assertThat(questionService.findAll().size()).isEqualTo(beforeQuestionCount+1);
    }

    @Test
    void 질문조회_시_존재하지_않으면_예외반환(){

        Assertions.assertThrows(IllegalArgumentException.class, ()->questionService.findById(100L)); 
    }

    @Test
    void 질문수정_시_작성자가_아니면_예외를_반환한다(){
        Long memberId1 = memberService.join(new JoinMemberDto("member1"));
        Long memberId2 = memberService.join(new JoinMemberDto("member2"));
        PostQuestionDto postQuestionDto = new PostQuestionDto(memberId1, "content");
        Long questionId = questionService.postQuestion(postQuestionDto);
        UpdateQuestionDto updateQuestionDto = new UpdateQuestionDto(memberId2, "new Content");

        Assertions.assertThrows(IllegalArgumentException.class, ()->questionService.updateQuestion(questionId, updateQuestionDto));
    }

    @Test
    void 질문수정_시_내용이_갱신되어야_한다(){
        Long memberId = memberService.join(new JoinMemberDto("member"));
        PostQuestionDto postQuestionDto = new PostQuestionDto(memberId, "content");
        Long questionId = questionService.postQuestion(postQuestionDto);
        String newContent = "new Content";
        UpdateQuestionDto updateQuestionDto = new UpdateQuestionDto(memberId, newContent);

        questionService.updateQuestion(questionId, updateQuestionDto);

        org.assertj.core.api.Assertions.assertThat(questionService.findById(questionId).getContent()).isEqualTo(newContent);
    }

    @Test
    void 추천_시_추천수를_증가시키고_재추천_시_추천수를_감소시킨다(){
        Long memberId = memberService.join(new JoinMemberDto("member"));
        PostQuestionDto postQuestionDto = new PostQuestionDto(memberId, "content");
        Long questionId = questionService.postQuestion(postQuestionDto);
        Question question = questionService.findById(questionId);
        Long upvoteCount = question.getUpvote();
        ChangeUpvoteDto changeUpvoteDto = new ChangeUpvoteDto(memberId);

        questionService.changeUpvote(questionId, changeUpvoteDto);
        org.assertj.core.api.Assertions.assertThat(questionService.findById(questionId).getUpvote()).isEqualTo(upvoteCount+1);

        upvoteCount = questionService.findById(questionId).getUpvote();
        questionService.changeUpvote(questionId, changeUpvoteDto);
        org.assertj.core.api.Assertions.assertThat(questionService.findById(questionId).getUpvote()).isEqualTo(upvoteCount-1);

    }
}
