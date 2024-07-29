package com.example.mcq_project.repository;

import com.example.mcq_project.model.OptionClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<OptionClass,Long> {
    void deleteByQuestionId(Long questionId);
}
