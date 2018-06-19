package nl.hanze.application.controller;

import nl.hanze.application.domain.Person;
import nl.hanze.application.domain.PersonPeriod;
import nl.hanze.application.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class PersonController {


    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @RequestMapping(value = "/findPersonPeriods")
    public ResponseEntity personPeriods(
            @RequestParam(value = "teamPeriodId") Integer id) {
        List<PersonPeriod> periods = personService.findPersonPeriodByTeamPeriodId(id);
        return new ResponseEntity<>(periods, HttpStatus.OK);
    }

//    @RequestMapping(value = "/getpersononbytrainerid")
//    public ResponseEntity personByTrainerPeriod(
//            @RequestParam(value = "trainerid") Integer trainerId) {
//        Person trainer = personService.findPersonP(trainerId)
//
//    }






}



