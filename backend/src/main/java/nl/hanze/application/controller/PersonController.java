package nl.hanze.application.controller;

import nl.hanze.application.domain.PersonEnquete;
import nl.hanze.application.domain.PersonPeriod;
import nl.hanze.application.service.PersonService;
import nl.hanze.application.service.TeamPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static nl.hanze.application.session.Session.isActiveSession;

@RestController
@CrossOrigin
public class PersonController {


    private final PersonService personService;
    private final TeamPeriodService teamPeriodService;

    @Autowired
    public PersonController(PersonService personService, TeamPeriodService teamPeriodService) {
        this.personService = personService;
        this.teamPeriodService = teamPeriodService;
    }

    /**
     * When given a trainer ID a list of persons which shares the same team period as the trainer wil be returned
     *
     * @param trainerId
     * @return list of persons which shares the same period as the trainer with the requested ID
     */


    @RequestMapping(value = "/getpersonsbytrainerid")
    public ResponseEntity personByTrainerPeriod(
            @RequestParam(value = "trainerid") Integer trainerId,
            @RequestParam(value = "sessionid") String sessionId) {

        if (isActiveSession(sessionId)) {
            try {
                List<PersonPeriod> periods = personService.findPersonByTrainerPeriod(trainerId);
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

        if (null == age && null == team) {
            return new ResponseEntity<>(personService.findAll(), HttpStatus.ACCEPTED);
        } else if (null != age && null == team) {
            if (age >= 5) {
                return new ResponseEntity<>(personService.findPersonOnAge(age), HttpStatus.ACCEPTED);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Jonger dat 5 jaar wordt er niet gevoetbald!");
            }
        }
        return new ResponseEntity<>(personService.findPersonByTeamName(team), HttpStatus.ACCEPTED);

    }


}



