package nl.hanze.application.controller;


import nl.hanze.application.domain.AnswerType;
import nl.hanze.application.service.AnswerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class AnswerTypeController {

    private final AnswerTypeService answerTypeService;

    @Autowired
    public AnswerTypeController(AnswerTypeService answerTypeService) {
        this.answerTypeService = answerTypeService;
    }

    @GetMapping(value = "/answertype/all")
    public @ResponseBody
    List<AnswerType> getAllAnswertypes() {
        List<AnswerType> answerTypesList = answerTypeService.findAll();
        if (!answerTypesList.isEmpty()) {
            return answerTypesList;
        } else {
            return null;
        }
    }

    @GetMapping(value = "/answertype/findanswertype")
    public AnswerType findAnswerType(
            @RequestParam(value = "id") int id) {
        return answerTypeService.findById(id);
    }

    @PostMapping(value = "/answertype/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void add(@Valid @RequestBody AnswerType answerType) {
        answerTypeService.save(answerType);
    }

    @DeleteMapping("/answertype/{id}")
    @ResponseBody
    public ResponseEntity<String> del(@PathVariable("id") int id) {

        try {
            answerTypeService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("DELETE Success!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}



