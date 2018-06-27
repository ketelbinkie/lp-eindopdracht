package nl.hanze.application.service.impl;

import nl.hanze.application.CombinedEnquete;
import nl.hanze.application.domain.PersonEnquete;
import nl.hanze.application.domain.PersonPeriod;
import nl.hanze.application.domain.Question;
import nl.hanze.application.domain.Response;
import nl.hanze.application.repositories.PersonEnqueteRepository;
import nl.hanze.application.repositories.PersonPeriodRepository;
import nl.hanze.application.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("personService")
public class PersonServiceImpl implements PersonService {

    private final PersonPeriodRepository personPeriodRepository;
    private PersonEnqueteRepository personEnqueteRepository;

    @Autowired
    public PersonServiceImpl(PersonPeriodRepository personPeriodRepository , PersonEnqueteRepository personEnqueteRepository) {
        this.personPeriodRepository = personPeriodRepository;
        this.personEnqueteRepository =personEnqueteRepository;
    }


//    public List<PersonPeriod> findPersonPeriodByTeamPeriodId(Integer id) {
//        return personPeriodRepository.findAllByTeamPeriodId(id);
//    }

    @Override
    public List<PersonPeriod> findPersonByTrainerPeriod(Integer trainerId) throws Exception {
       PersonPeriod trainerPeriod = personPeriodRepository.findByPersonId(trainerId);
       if(!trainerPeriod.getRole().getRole().equals("beoordelaar")){
           throw new Exception("Error Wrong argument, given id is not of a person with the trainer role");
       }

        List<PersonPeriod> periods = personPeriodRepository.findAllByTeamPeriodId(trainerPeriod.getTeamPeriod().getId());

       periods.remove(trainerPeriod);
       return periods;
    }

    @Override
    public PersonEnquete findPersonEnqueteByPersonId(Integer personId) {
        PersonPeriod period = personPeriodRepository.findByPersonId(personId);
        return personEnqueteRepository.findPersonEnqueteByPersonPeriod(period);
    }

    public List<CombinedEnquete> findCombinedPersonEnqueteByPersonId(Integer personId) {
        PersonPeriod period = personPeriodRepository.findByPersonId(personId);
        PersonEnquete personEnquete = personEnqueteRepository.findPersonEnqueteByPersonPeriod(period);

        List<CombinedEnquete> enquete = new ArrayList<>();
        for (Question q : personEnquete.getEnquetes().getQuestions()){
            CombinedEnquete ce = new CombinedEnquete();
            ce.setQuestionId(q.getId());
            ce.setQuestion(q.getQuestion());
            ce.setQuestionCategory(q.getCategory());
            for (Response r :  personEnquete.getResponses()){
                if(q.getId()==Integer.parseInt(r.getQuestionId())){
                    ce.setAnswer(r.getAnswer());
                }
            }
            enquete.add(ce);
        }
        return enquete;
    }

}
