package com.mallohaja.letsspeak.web.question;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mallohaja.letsspeak.web.Member.MemberService;
import com.mallohaja.letsspeak.web.question.requestdto.ChangeUpvoteDto;
import com.mallohaja.letsspeak.web.question.requestdto.PostQuestionDto;
import com.mallohaja.letsspeak.web.question.requestdto.UpdateQuestionDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionUpvoteListService questionUpvoteListService;
    private final MemberService memberService;

    public Question findById(Long id){
        return questionRepository.findById(id).orElseThrow(()->new IllegalArgumentException(id + "번 게시글이 존재하지 않습니다"));
    }

    public List<Question> findAll(){
        return questionRepository.findAll();
    }

    @Transactional
    public Long postQuestion(PostQuestionDto dto){
        Long memberId = dto.getMemberId();
        memberService.findById(memberId);
        Question question = Question.createQuestion(memberId, dto.getContent());
        return questionRepository.save(question).getId();
    }

    @Transactional
    public Long updateQuestion(Long id, UpdateQuestionDto dto){

        memberService.findById(dto.getMemberId());
        Question question = findById(id);
        if(question.getMemberId() != dto.getMemberId()){
            throw new IllegalArgumentException(dto.getMemberId() + "번 회원은 " + question.getMemberId() + "번 글 작성자가 아닙니다");
        }
        question.updateQuestion(dto.getContent());
        return question.getId();
    }

    @Transactional
    public boolean changeUpvote(Long questionId, ChangeUpvoteDto dto){
        Long memberId = dto.getMemberId();
        Question question = findById(questionId);
        if(questionUpvoteListService.updateUpvote(questionId, memberId)){
            question.increaseUpvote();
            return true;
        } else {
            question.decreaseUpvote();
            return false;
        }
    }
}
