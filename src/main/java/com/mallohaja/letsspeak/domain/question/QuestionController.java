package com.mallohaja.letsspeak.domain.question;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mallohaja.letsspeak.domain.question.requestdto.ChangeUpvoteDto;
import com.mallohaja.letsspeak.domain.question.requestdto.PostQuestionDto;
import com.mallohaja.letsspeak.domain.question.requestdto.UpdateQuestionDto;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class QuestionController {
    
    private final QuestionService questionService;

    @PostMapping("/question")
    public Long postQuestion(@RequestBody PostQuestionDto dto){
        return questionService.postQuestion(dto);
    }

    @PutMapping("/question/{id}")
    public Long updateQuestion(@PathVariable("id") Long id, @RequestBody UpdateQuestionDto dto){
        return questionService.updateQuestion(id, dto);
    }

    @PutMapping("question/{id}/upvote")
    public boolean changeUpvote(@PathVariable("id") Long id, @RequestBody ChangeUpvoteDto dto){
        return questionService.changeUpvote(id, dto);
    }
}
