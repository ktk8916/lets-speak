package com.mallohaja.letsspeak.web.question;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuestionUpvoteListServiceTest {

    @Autowired private QuestionUpvoteListService questionUpvoteListService;
    @Autowired private QuestionUpvoteListRepository questionUpvoteListRepository;

    @Test
    void 추천목록에_없으면_추가하고_true를_반환한다(){
        Long questionId = 1L;
        Long memberId = 1L;
        int beforeUpvoteCount = questionUpvoteListRepository.findAll().size();

        Assertions.assertThat(questionUpvoteListService.updateUpvote(questionId, memberId)).isTrue();
        Assertions.assertThat(beforeUpvoteCount+1).isEqualTo(questionUpvoteListRepository.findAll().size());
    }
    
    @Test
    void 추천목록에_있으면_삭제하고_false를_반환한다(){
        Long questionId = 1L;
        Long memberId = 1L;
        
        QuestionUpvoteList questionUpvoteList = QuestionUpvoteList.createQuestionUpvoteList(questionId, memberId);
        questionUpvoteListRepository.save(questionUpvoteList);
        int beforeUpvoteCount = questionUpvoteListRepository.findAll().size();

        Assertions.assertThat(questionUpvoteListService.updateUpvote(questionId, memberId)).isFalse();
        Assertions.assertThat(beforeUpvoteCount-1).isEqualTo(questionUpvoteListRepository.findAll().size());
    }
}
