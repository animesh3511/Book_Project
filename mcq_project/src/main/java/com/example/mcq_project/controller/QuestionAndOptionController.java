package com.example.mcq_project.controller;

import com.example.mcq_project.model.request.QuestionAndOptionRequest;
import com.example.mcq_project.model.response.CustomEntityResponse;
import com.example.mcq_project.model.response.EntityResponse;
import com.example.mcq_project.service.QuestionAndOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class QuestionAndOptionController {

    @Autowired
    private QuestionAndOptionService service;

    @PostMapping("/saveOrUpdate")
    public ResponseEntity<?> saveOrUpdate(@RequestBody QuestionAndOptionRequest request) {
        try {

            return new ResponseEntity<>(new EntityResponse(service.saveOrUpdate(request.getQuestionRequest(),
                    request.getOptionList()), 0), HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(new CustomEntityResponse(e.getMessage(), -1), HttpStatus.BAD_REQUEST);
        }
    }

//class ends here
}
