package nl.hanze.application.controller;

import nl.hanze.application.domain.Person;
import nl.hanze.application.domain.PersonEnquete;
import nl.hanze.application.domain.PersonPeriod;
import nl.hanze.application.domain.TeamName;
import nl.hanze.application.service.PersonService;
import nl.hanze.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static nl.hanze.application.util.Session.isActiveSession;

@RestController
@CrossOrigin
public class PersonController {


    private final PersonService personService;
    private final UserService userService;

    @Autowired
    public PersonController(PersonService personService, UserService userService) {
        this.personService = personService;
        this.userService = userService;
    }

    /**
     * When given a trainer ID a list of persons which shares the same team period as the trainer wil be returned
     *
     * @param trainerId  id of trainer
     * @param sessionId  session id
     * @param teamNameId teamnameid
     * @return list of persons which shares the same period as the trainer with the requested ID
     */


    @RequestMapping(value = "/getpersonsbytrainerid")
    public ResponseEntity personByTrainerPeriod(
            @RequestParam(value = "trainerid") Integer trainerId,
            @RequestParam(value = "sessionid") String sessionId,
            @RequestParam(value = "teamnameid", required = false) int teamNameId) {

        if (isActiveSession(sessionId)) {
            try {
                List<PersonPeriod> periods = personService.findPersonByTrainerPeriodAndTeam(trainerId, teamNameId);
                return new ResponseEntity<>(periods, HttpStatus.OK);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: not authorized");
        }
    }

    @RequestMapping(value = "/person/personenquete")
    public ResponseEntity personEnqueteByPersonId(
            @RequestParam(value = "personId") Integer personId) {
        PersonEnquete personEnquete = personService.findPersonEnqueteByPersonId(personId);
        return new ResponseEntity<>(personEnquete, HttpStatus.OK);
    }


    @RequestMapping(value = "/person/all")
    public ResponseEntity getPersonParameterized(
            @RequestParam(value = "age", required = false) Integer age,
            @RequestParam(value = "currentTeam", required = false) String team) {
        List<Person> personList = new ArrayList<>();
        if (null == age && null == team) {
            personList = personService.findAll();
        } else if (null != age && null == team) {
            if (age >= 5) {
                personList = personService.findPersonOnAge(age);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Jonger dat 5 jaar wordt er niet gevoetbald!");
            }
        } else if (null == age) {
            personList = personService.findPersonByTeamName(team);
        }

        personList.removeIf(p -> p.getUser() != null && p.getUser().getRole().getId() != 4);

        return new ResponseEntity<>(personList, HttpStatus.OK);
    }


    @RequestMapping(value = "/trainer/all")
    public ResponseEntity findAllTrainers() {
        List<Person> trainers = personService.findAllTrainers();
        return new ResponseEntity<>(trainers, HttpStatus.OK);
    }

    @RequestMapping(value = "/trainer/getteams")
    public ResponseEntity findTeamsOfTrainer(
            @RequestParam(value = "trainerid") Integer trainerId,
            @RequestParam(value = "sessionid") String sessionId) {
        if (isActiveSession(sessionId)) {
            Set<TeamName> teamNames = personService.findTeamsByTrainerPeriod(trainerId);
            return new ResponseEntity<>(teamNames, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: not authorized");
        }

    }


}



