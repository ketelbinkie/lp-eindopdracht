package nl.hanze.application.controller;

import nl.hanze.application.CombinedEnquete;
import nl.hanze.application.domain.PersonEnquete;
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


/*niet nodig ivm met personByTrainerPeriod */

//    @RequestMapping(value = "/findPersonPeriods")
//    public ResponseEntity personPeriods(
//            @RequestParam(value = "teamPeriodId") Integer id) {
//        List<PersonPeriod> periods = personService.findPersonPeriodByTeamPeriodId(id);
//        return new ResponseEntity<>(periods, HttpStatus.OK);
//    }


    /**
     * When given a trainer ID a list of persons which shares the same team period as the trainer wil be returned
     *
     * @param trainerId
     * @return list of persons which shares the same period as the trainer with the requested ID
     *
     */


    @RequestMapping(value = "/getpersonsbytrainerid")
    public ResponseEntity personByTrainerPeriod(
            @RequestParam(value = "trainerid") Integer trainerId) {
        try {
            List<PersonPeriod> periods = personService.findPersonByTrainerPeriod(trainerId);
            return  new ResponseEntity<>(periods, HttpStatus.OK);
        }catch (Exception e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }

    }

    @RequestMapping(value = "/person/personenquete")
    public ResponseEntity personEnqueteByPersonId(
            @RequestParam(value = "personId") Integer personId) {
       List<CombinedEnquete> enquete = personService.findPersonEnqueteByPersonId(personId);
        return  new ResponseEntity<>(enquete, HttpStatus.OK);
    }







}



