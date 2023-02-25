package com.mallohaja.letsspeak.domain.question.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostQuestionDto {
    private Long memberId;
    private String content;
}
