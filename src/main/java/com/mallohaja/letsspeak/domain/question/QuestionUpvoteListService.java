package com.mallohaja.letsspeak.domain.question;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class QuestionUpvoteListService {

    private final QuestionUpvoteListRepository questionUpvoteListRepository;

    private Optional<QuestionUpvoteList> isAlreadyUpvote(Long questionId, Long memberId){
        return questionUpvoteListRepository.findByQuestionIdAndMemberId(questionId, memberId);
    }

    @Transactional
    public boolean updateUpvote(Long questionId, Long memberId){

        Optional<QuestionUpvoteList> questionUpvoteList = isAlreadyUpvote(questionId, memberId);

        if(!questionUpvoteList.isPresent()){
            QuestionUpvoteList newQuestionUpvoteList = QuestionUpvoteList.createQuestionUpvoteList(questionId, memberId);
            questionUpvoteListRepository.save(newQuestionUpvoteList);
            return true;
        } else {
            questionUpvoteListRepository.delete(questionUpvoteList.get());
            return false;
        }

    }

}
