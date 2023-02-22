package com.mallohaja.letsspeak.web.question;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionUpvoteList {

    @Id @GeneratedValue
    @Column(name = "questionUpvoteList_id")
    private Long id;
    
    @Column(name = "question_id")
    private Long questionId;

    @Column(name = "member_id")
    private Long memberId;

    public static QuestionUpvoteList createQuestionUpvoteList(Long questionId, Long memberId){
        QuestionUpvoteList questionUpvoteList = new QuestionUpvoteList();
        questionUpvoteList.questionId = questionId;
        questionUpvoteList.memberId = memberId;
        return questionUpvoteList;
    }
}
