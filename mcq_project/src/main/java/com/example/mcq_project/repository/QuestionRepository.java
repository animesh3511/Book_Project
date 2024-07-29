package com.example.mcq_project.repository;

import com.example.mcq_project.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    Question findByQuestion(String question);
}
