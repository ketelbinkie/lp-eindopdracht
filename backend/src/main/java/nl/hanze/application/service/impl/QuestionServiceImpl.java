package nl.hanze.application.service.impl;


import nl.hanze.application.domain.Question;
import nl.hanze.application.repositories.QuestionRepository;
import nl.hanze.application.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("questionService")
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public Question save(Question question) {return questionRepository.save(question);}
    public List<Question> findAll() {return questionRepository.findAll();}
    public Question findById(int id) {return questionRepository.findById(id);}

}

