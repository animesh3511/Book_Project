package com.example.mcq_project.serviceimpl;

import com.example.mcq_project.model.OptionClass;
import com.example.mcq_project.model.Question;
import com.example.mcq_project.model.request.QuestionRequest;
import com.example.mcq_project.repository.OptionRepository;
import com.example.mcq_project.repository.QuestionRepository;
import com.example.mcq_project.service.QuestionAndOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class QuestionAndOptionServiceImpl implements QuestionAndOptionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private OptionRepository optionRepository;

    @Override
    @Transactional
    public Object saveOrUpdate(QuestionRequest questionRequest, List<String> optionList) {

        if (questionRepository.existsById(questionRequest.getQuestionId())) {
            Question question = questionRepository.findById(questionRequest.getQuestionId()).get();
            question.setQuestionId(questionRequest.getQuestionId());
            question.setQuestion(questionRequest.getQuestion());
            Question q = questionRepository.save(question);

            optionRepository.deleteByQuestionId(questionRequest.getQuestionId());
            for (String op : optionList) {
                OptionClass oc = new OptionClass();
                oc.setQuestionId(q.getQuestionId());
                oc.setOptionValue(op);
                optionRepository.save(oc);
            }

            return "Question is Updated";
        } else {
            Question question1 = new Question();
            question1.setQuestion(questionRequest.getQuestion());
            question1 = questionRepository.save(question1);

            for (String op : optionList) {
                OptionClass oclass = new OptionClass();
                oclass.setOptionValue(op);
                oclass.setQuestionId(question1.getQuestionId());
                optionRepository.save(oclass);
            }
            return "Data Saved";
        }
        //saveOrUpdate() method ends here
    }
    //class ends here
}

