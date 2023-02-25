package com.mallohaja.letsspeak.domain.question;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionUpvoteListRepository extends JpaRepository<QuestionUpvoteList, Long>{

    List<QuestionUpvoteList> findAll();
    Optional<QuestionUpvoteList> findByQuestionIdAndMemberId(Long questionId, Long memberId);
    QuestionUpvoteList save(QuestionUpvoteList questionUpvoteList);
    void delete(QuestionUpvoteList questionUpvoteList);
}
