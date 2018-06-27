package nl.hanze.application.controller;

import nl.hanze.application.domain.Enquete;
import nl.hanze.application.domain.PersonEnquete;
import nl.hanze.application.service.EnqueteService;
import nl.hanze.application.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class EnqueteController {


    private final EnqueteService enqueteService;
    private final PersonService personService;

    @Autowired
    public EnqueteController(EnqueteService enqueteService, PersonService persionService) {
        this.enqueteService = enqueteService;
        this.personService = persionService;
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

        PersonEnquete currentPersonEnquete = personService.findPersonEnqueteByPersonId(personEnquete.getPersonPeriod().getPerson().getId());
        if(currentPersonEnquete.getResponses().toString().equals(personEnquete.getResponses().toString())){
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Het is wel de bedoeling dat je wat wijzigt! Er is dus geen wijziging doorgevoerd.");
        }
        try {
            if (personEnquete != null) {
                enqueteService.save(personEnquete);
                return ResponseEntity.status(HttpStatus.OK).body("Beoordeling is succesvol verwerkt!");
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Beoordeling kon niet worden verwerkt!");
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }

    @PutMapping (value = "/enquete/save", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String save (
            @Valid @RequestBody Enquete enquete,

            BindingResult errors,
            ModelMap modelMap
    ) {
        if (!errors.hasErrors()) {

            final Enquete savedEnquete = enqueteService.save(enquete);

            return "OK";
        } else {
            // BLABLA
            return "NOK";
        }
    }


}