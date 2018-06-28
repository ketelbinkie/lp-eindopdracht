package nl.hanze.application.controller;

import nl.hanze.application.CombinedEnquete;
import nl.hanze.application.domain.PersonEnquete;
import nl.hanze.application.domain.PersonPeriod;
import nl.hanze.application.domain.Question;
import nl.hanze.application.domain.Response;
import nl.hanze.application.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static nl.hanze.application.session.Session.isActiveSession;

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
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: not authorized");
        }
    }

//    @RequestMapping(value = "/person/personenquete")
//    public ResponseEntity personEnqueteByPersonId(
//            @RequestParam(value = "personId") Integer personId) {
//       List<CombinedEnquete> enquete = personService.findPersonEnqueteByPersonId(personId);
//        return  new ResponseEntity<>(enquete, HttpStatus.OK);
//    }

    @RequestMapping(value = "/person/personenquete")
    public ResponseEntity personEnqueteByPersonId(
            @RequestParam(value = "personId") Integer personId) {
        PersonEnquete personEnquete = personService.findPersonEnqueteByPersonId(personId);
        return new ResponseEntity<>(personEnquete, HttpStatus.OK);
    }


//    @PostMapping(value = "/reponse/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//    public ResponseEntity<String> add(@Valid @RequestBody PersonEnquete enquete) {
//
//        System.out.println();
//
//        return null;
//    }
//
//
//    @RequestMapping(value = "/response", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody List<Response> batchCreate(@RequestBody List<Response> responses) {
//        System.out.println();
//
//
//        return null;
//    }


//    @RequestMapping(value = "/person/personrespons")
//    public ResponseEntity responseByPersonPeriodId(
//            @RequestParam(value = "personPeriodId") Integer personPeriodId) {
//        List<CombinedEnquete> enquete = personService.findPersonEnqueteByPersonId(personId);
//        return  new ResponseEntity<>(enquete, HttpStatus.OK);
//    }


}



