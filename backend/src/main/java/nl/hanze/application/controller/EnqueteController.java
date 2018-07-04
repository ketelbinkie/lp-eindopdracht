package nl.hanze.application.controller;

import nl.hanze.application.domain.Enquete;
import nl.hanze.application.domain.PersonEnquete;
import nl.hanze.application.domain.Question;
import nl.hanze.application.domain.Response;
import nl.hanze.application.service.EnqueteService;
import nl.hanze.application.service.PersonService;
import nl.hanze.application.service.QuestionService;
import nl.hanze.application.service.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class EnqueteController {


    private final EnqueteService enqueteService;
    private final QuestionService questionService;
    private final PersonService personService;
    private final ResponseService responseService;


    @Autowired
    public EnqueteController(EnqueteService enqueteService, QuestionService questionService,
                             PersonService personService, ResponseService responseService) {
        this.enqueteService = enqueteService;
        this.questionService = questionService;
        this.personService = personService;
        this.responseService = responseService;
    }

    @GetMapping(value = "/enquete/all")
    public @ResponseBody
    List<Enquete> getAllEnquetes() {
        List<Enquete> enqueteList = enqueteService.findAll();
        if (!enqueteList.isEmpty()) {
            return enqueteList;
        } else {
            return null;
        }
    }

    @GetMapping(value = "/enquete/allchangeable")
    public @ResponseBody
    List<Enquete> getAllChangeableEnquetes() {

        List<Enquete> alleEnquetes;
        List<Enquete> alleAanTePassenEnquetes = new ArrayList<>();

        alleEnquetes = getAllEnquetes();

        for (int i = 0; i < alleEnquetes.size(); i++) {
            Enquete enquete;
            enquete = findEnquete(alleEnquetes.get(i).getId());

            String enqueteId = Integer.toString(enquete.getId());

            List<Response> responseList;
            responseList = responseService.findDistinctByEnqueteId(enqueteId);

            if (responseList.isEmpty()) {

                alleAanTePassenEnquetes.add(getAllEnquetes().get(i));
            }
        }

        if (!alleAanTePassenEnquetes.isEmpty()) {
            return alleAanTePassenEnquetes;
        } else {
            return null;
        }
    }

    @GetMapping(value = "/enquete/findenquete")
    public Enquete findEnquete(
            @RequestParam(value = "id") int id) {
        return enqueteService.findById(id);
    }

    @PostMapping(value = "/enquete/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> add(@Valid @RequestBody Enquete enquete) {
        try {
            if (enquete != null) {
                enqueteService.save(enquete);
                return ResponseEntity.status(HttpStatus.OK).body("Enquete is succesvol toegevoegd!");
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Enquete is niet toegevoegd!");
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }


    @PutMapping(value = "/reponse/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> add(@Valid @RequestBody PersonEnquete personEnquete) {

        try {
            if (personEnquete != null) {
                PersonEnquete currentPersonEnquete = personService.findPersonEnqueteByPersonId(personEnquete.getPersonPeriod().getPerson().getId());
                if (currentPersonEnquete.getResponses().toString().equals(personEnquete.getResponses().toString())) {
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body("Het is wel de bedoeling dat je wat wijzigt! Er is dus geen wijziging doorgevoerd.");
                }
                enqueteService.save(personEnquete);
                return ResponseEntity.status(HttpStatus.OK).body("Beoordeling is succesvol verwerkt!");
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Beoordeling kon niet worden verwerkt!");
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }


    @PutMapping(value = "/enquete/save", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> save(
            @Valid @RequestBody Enquete enquete,

            BindingResult errors,
            ModelMap modelMap
    ) {
        if (!errors.hasErrors()) {


            for (int i = 0; i < enquete.getQuestions().size(); i++) {
                // bepalen question id van de question in de enquete
                int questionId = enquete.getQuestions().get(i).getId();

                // ophalen van de betreffende question uit de db om het bijbehorende anwertype te bepalen
                Question enqueteQuestion = new Question();
                enqueteQuestion = questionService.findById(questionId);
                int answertypId = enqueteQuestion.getAnswerType().getId();

                // het answertype toevoegen aan de question van de enquete
                enquete.getQuestions().get(i).getAnswerType().setId(answertypId);
            }

            final Enquete savedEnquete = enqueteService.save(enquete);

            String messagePart;
            if (enquete.getQuestions().size() > 1) {
                messagePart = "Vragen zijn ";
            } else {
                messagePart = "Vraag is ";
            }

            return ResponseEntity.status(HttpStatus.OK).body(messagePart + "succesvol aan de enquête '" + enquete.getName() + "' toegevoegd!");

        } else {

            return ResponseEntity.status(HttpStatus.CONFLICT).body("Er is iets foutgegaan!");
        }
    }


    @PostMapping(value = "/personenquete/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> addPesonEnquete(@Valid @RequestBody PersonEnquete personEnquete) {
        try {
            if (personEnquete != null) {
                enqueteService.save(personEnquete);
                return ResponseEntity.status(HttpStatus.OK).body("De geselecteerde persoon/personen zijn gekoppeld aan een beoordelingsperiode");
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Helaas er ging wat mis.. Sorry ik ben ook maar een beginnend Java programmeur!");
            }
        } catch (
                Exception e)

        {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }
}
