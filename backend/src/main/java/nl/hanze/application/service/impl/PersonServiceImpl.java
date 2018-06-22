package nl.hanze.application.service.impl;

import nl.hanze.application.domain.PersonEnquete;
import nl.hanze.application.domain.PersonPeriod;
import nl.hanze.application.repositories.PersonEnqueteRepository;
import nl.hanze.application.repositories.PersonPeriodRepository;
import nl.hanze.application.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
