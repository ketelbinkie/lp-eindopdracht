package nl.hanze.application.controller;

import nl.hanze.application.domain.AnswerType;
import nl.hanze.application.domain.Question;
import nl.hanze.application.domain.User;
import nl.hanze.application.service.QuestionService;
import nl.hanze.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class QuestionController {


    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping(value = "/question/all")
    public @ResponseBody List<Question> getAllQuestions() {
        List<Question> questionsList = questionService.findAll();
        if(!questionsList.isEmpty()){
            return questionsList;
        }else{
            return null;
        }
    }

    @GetMapping(value = "/question/findquestion")
    public Question findQuestion(
            @RequestParam(value = "id") int id) {
        return questionService.findById(id);
    }


    @PostMapping(value = "/question/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Question> add(
            @Valid @RequestBody Question question) {
        return new ResponseEntity<Question>(questionService.save(question),HttpStatus.CREATED);
    }



}



