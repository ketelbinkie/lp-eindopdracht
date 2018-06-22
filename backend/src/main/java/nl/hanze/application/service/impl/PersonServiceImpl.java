package nl.hanze.application.service.impl;

import nl.hanze.application.domain.PersonPeriod;
import nl.hanze.application.repositories.PersonPeriodRepository;
import nl.hanze.application.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("personService")
public class PersonServiceImpl implements PersonService {

    private final PersonPeriodRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonPeriodRepository personRepository ) {
        this.personRepository = personRepository;
    }


//    public List<PersonPeriod> findPersonPeriodByTeamPeriodId(Integer id) {
//        return personRepository.findAllByTeamPeriodId(id);
//    }

    @Override
    public List<PersonPeriod> findPersonByTrainerPeriod(Integer trainerId) throws Exception {
       PersonPeriod trainerPeriod = personRepository.findByPersonId(trainerId);
       if(!trainerPeriod.getRole().getRole().equals("beoordelaar")){
           throw new Exception("Error Wrong argument, given id is not of a person with the trainer role");
       }

        List<PersonPeriod> periods = personRepository.findAllByTeamPeriodId(trainerPeriod.getTeamPeriod().getId());

       periods.remove(trainerPeriod);
       return periods;
    }

}
