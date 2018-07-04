package nl.hanze.application.service;


import nl.hanze.application.domain.Question;

import java.util.List;

public interface QuestionService {

    Question save(Question question);

    List<Question> findAll();

    Question findById(int id);
}
