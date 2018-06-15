package nl.hanze.application.service.impl;

import nl.hanze.application.domain.AnswerType;
import nl.hanze.application.repositories.AnswerTypeRepository;
import nl.hanze.application.service.AnswerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("answerTypeService")
public class AnswerTypeServiceImpl implements AnswerTypeService {

    private final AnswerTypeRepository answerTypeRepository;

    @Autowired
    public AnswerTypeServiceImpl(AnswerTypeRepository answerTypeRepository) {
        this.answerTypeRepository = answerTypeRepository;
    }

    public AnswerType save(AnswerType answerType) {return answerTypeRepository.save(answerType);}
    public List<AnswerType> findAll() {return answerTypeRepository.findAll();}
    public AnswerType findById(int id) {return answerTypeRepository.findById(id);}
    public void deleteById(int id) { answerTypeRepository.deleteById(id);
    }
}
