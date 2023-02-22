package com.mallohaja.letsspeak.web.question;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>{

    Question save(Question question);
    Optional<Question> findById(Long id);
    List<Question> findAll();
    
}
