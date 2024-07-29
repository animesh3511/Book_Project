package com.example.mcq_project.service;

import com.example.mcq_project.model.request.QuestionRequest;

import java.util.List;

public interface QuestionAndOptionService {
    Object saveOrUpdate(QuestionRequest questionRequest, List<String> optionList);
}
