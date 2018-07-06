package nl.hanze.application.service.impl;

import nl.hanze.application.CombinedEnquete;
import nl.hanze.application.domain.*;
import nl.hanze.application.repositories.*;
import nl.hanze.application.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.annotation.Retention;
import java.util.*;

import static nl.hanze.application.util.ActivePeriodUtil.getStartDate;

@Service("personService")
public class PersonServiceImpl implements PersonService {

    private final PersonPeriodRepository personPeriodRepository;
    private PersonEnqueteRepository personEnqueteRepository;
    private final PersonRepository personRepository;
    private final TeamNameRepository teamNameRepository;

    @Autowired
    public PersonServiceImpl(PersonPeriodRepository personPeriodRepository, PersonEnqueteRepository personEnqueteRepository, PersonRepository personRepository, TeamNameRepository teamNameRepository) {
        this.personPeriodRepository = personPeriodRepository;
        this.personEnqueteRepository = personEnqueteRepository;
        this.personRepository = personRepository;
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
    public List<Person> findAllTrainers() {
        List<Person> personList = personRepository.findAll();
        List<Person> trainerList = new ArrayList<>();
        for (Person person : personList) {
            if (null != person.getUser() && person.getUser().getRole().getId() == 3) {
                trainerList.add(person);
            }
        }
        return trainerList;

    }

    @Override
    public List<PersonPeriod> findPersonByTrainerPeriodAndTeam(Integer trainerId, int teamNameId) {
            List<PersonPeriod> periods = personPeriodRepository.findAllByTeamNameId(teamNameId);

                List<PersonPeriod> trainerPeriods = personPeriodRepository.findAllByPersonId(trainerId);
        PersonPeriod personPeriodToRemove = new PersonPeriod();
        for (PersonPeriod trainerPeriod : trainerPeriods) {
            if (trainerPeriod.getTeamName().getId() == teamNameId) {
                personPeriodToRemove = trainerPeriod;
            }
        }
        periods.remove(personPeriodToRemove);

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
        List<PersonPeriod> personPeriods = personPeriodRepository.findByTeamName(teamNameRepository.findByName(teamName));

        List<Person> persons = new ArrayList<>();
        for (PersonPeriod p : personPeriods) {
            persons.add(p.getPerson());
        }
        return persons;
    }


    @Override
    public Set<TeamName> findTeamsByTrainerPeriod(Integer trainerId) {
        List<PersonPeriod> trainerPeriods = personPeriodRepository.findAllByPersonId(trainerId);
        Set<TeamName> teamNames = new HashSet<>();
        for (PersonPeriod period : trainerPeriods) {
            if (null != period.getStartdate() && period.getStartdate().equals(getStartDate())) {
                teamNames.add(period.getTeamName());
            }
        }
        return teamNames;
    }


}
