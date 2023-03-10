package com.mallohaja.letsspeak.domain.question.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateQuestionDto {
    private Long memberId;
    private String content;
}
