package nl.hanze.application.service;


import nl.hanze.application.domain.AnswerType;

import java.util.List;

public interface AnswerTypeService {
    List<AnswerType> findAll();

    AnswerType save(AnswerType answerType);

    AnswerType findById(int id);

    void deleteById(int id);
}
