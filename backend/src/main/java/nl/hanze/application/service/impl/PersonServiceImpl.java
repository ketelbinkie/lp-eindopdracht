package nl.hanze.application.service.impl;

import nl.hanze.application.CombinedEnquete;
import nl.hanze.application.domain.*;
import nl.hanze.application.repositories.*;
import nl.hanze.application.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service("personService")
public class PersonServiceImpl implements PersonService {

    private final PersonPeriodRepository personPeriodRepository;
    private PersonEnqueteRepository personEnqueteRepository;
    private final PersonRepository personRepository;
    private final TeamPeriodRepository teamPeriodRepository;
    private final TeamNameRepository teamNameRepository;

    @Autowired
    public PersonServiceImpl(PersonPeriodRepository personPeriodRepository, PersonEnqueteRepository personEnqueteRepository, PersonRepository personRepository, TeamPeriodRepository teamPeriodRepository, TeamNameRepository teamNameRepository) {
        this.personPeriodRepository = personPeriodRepository;
        this.personEnqueteRepository = personEnqueteRepository;
        this.personRepository = personRepository;
        this.teamPeriodRepository = teamPeriodRepository;
        this.teamNameRepository = teamNameRepository;
    }


//    public List<PersonPeriod> findPersonPeriodByTeamPeriodId(Integer id) {
//        return personPeriodRepository.findAllByTeamPeriodId(id);
//    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public List<PersonPeriod> findPersonByTrainerPeriod(Integer trainerId) throws Exception {
        PersonPeriod trainerPeriod = personPeriodRepository.findByPersonId(trainerId);
        if (!trainerPeriod.getRole().getRole().equals("beoordelaar")) {
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
        for (Question q : personEnquete.getEnquetes().getQuestions()) {
            CombinedEnquete ce = new CombinedEnquete();
            ce.setQuestionId(q.getId());
            ce.setQuestion(q.getQuestion());
            ce.setQuestionCategory(q.getCategory());
            for (Response r : personEnquete.getResponses()) {
                if (q.getId() == Integer.parseInt(r.getQuestionId())) {
                    ce.setAnswer(r.getAnswer());
                }
            }
            enquete.add(ce);
        }
        return enquete;
    }


    @Override
    public List<Person> findPersonOnAge(int age) {
        Calendar dateAfter = Calendar.getInstance();

        dateAfter.setTime(new Date());
        dateAfter.add(Calendar.YEAR, (age + 1) * -1);
        dateAfter.add(Calendar.DATE, 1);

        Calendar dateBefore = Calendar.getInstance();
        dateBefore.setTime(new Date());
        dateBefore.add(Calendar.YEAR, age * -1);

        return personRepository.findAllByDateOfBirthBetween(dateAfter.getTime(), dateBefore.getTime());


    }


    @Override
    public List<Person> findPersonByTeamName(String teamName) {
        List<TeamPeriod> teamPeriods = teamPeriodRepository.findByTeamName(teamNameRepository.findByName(teamName));
        List<PersonPeriod> personPeriods = new ArrayList<>();
        for (TeamPeriod tPeriod : teamPeriods) {
            personPeriods.addAll(personPeriodRepository.findByTeamPeriod(tPeriod));
        }
        List<Person> persons = new ArrayList<>();
        for (PersonPeriod p : personPeriods) {
            persons.add(p.getPerson());
        }
        return persons;
    }

}
