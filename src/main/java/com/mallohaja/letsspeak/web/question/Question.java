package com.mallohaja.letsspeak.web.question;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {

    @Id @GeneratedValue
    @Column(name = "question_id")
    private Long id;
    private Long memberId;
    private Long upvote;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public static Question createQuestion(Long memberId, String content){

        Question question = new Question();
        question.memberId = memberId;
        question.content = content;
        question.upvote = 0L;
        question.createdAt = LocalDateTime.now();
        return question;
    }
    
    public void increaseUpvote(){
        this.upvote++;
    }

    public void decreaseUpvote(){
        this.upvote--;
    }

    public void updateQuestion(String content){
        this.content = content;
        updatedAt = LocalDateTime.now();
    }

    public void deleteQuestion(){
        this.deletedAt = LocalDateTime.now();
    }
    
}
