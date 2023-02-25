package com.mallohaja.letsspeak.domain.question;

import java.time.LocalDateTime;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuestionTest {

    private Question createQuestion() {
        Question question = Question.createQuestion(1L, "테스트 내용");
        question.updateQuestion("1111");
        return question;
    }

    @Test
    void increaseUpvote는_추천수를_증가시킨다(){
        Question question = createQuestion();
        Long beforeUpvote = question.getUpvote();

        question.increaseUpvote();

        Assertions.assertThat(question.getUpvote()).isEqualTo(beforeUpvote+1);
    }

    @Test
    void decreaseUpvote는_추천수를_감소시킨다(){
        Question question = createQuestion();
        Long beforeUpvote = question.getUpvote();

        question.decreaseUpvote();

        Assertions.assertThat(question.getUpvote()).isEqualTo(beforeUpvote-1);
    }

    @Test
    void 수정_시_내용이_갱신된다(){
        Question question = createQuestion();
        String content = "수정내용";

        question.updateQuestion(content);

        Assertions.assertThat(content).isEqualTo(question.getContent());
    }

    @Test
    void 첫_번째_수정_시_수정시간이_생긴다(){
        Question question = createQuestion();

        Assertions.assertThat(question.getUpdatedAt()).isNull();

        question.updateQuestion("수정");

        Assertions.assertThat(question.getUpdatedAt()).isNotNull();
    }

    @Test
    void question수정_시_수정시간이_갱신된다(){
        Question question = createQuestion();

        question.updateQuestion("첫 번째 수정");
        LocalDateTime beforeUpdateTime = question.getUpdatedAt();

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        question.updateQuestion("두 번째 수정");
        
        Assertions.assertThat(question.getUpdatedAt().isAfter(beforeUpdateTime)).isTrue();
    }
    
    @Test
    void 삭제_시_삭제시간이_생긴다(){
        Question question = createQuestion();

        Assertions.assertThat(question.getDeletedAt()).isNull();

        question.deleteQuestion();

        Assertions.assertThat(question.getUpdatedAt()).isNotNull();
    }
}
