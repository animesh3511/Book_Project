package com.example.mcq_project.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionAndOptionRequest {

    private QuestionRequest questionRequest;
    private List<String> optionList;

}
