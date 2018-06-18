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
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<String> add(@Valid @RequestBody Question question) {
        try {
            if(question != null) {
                if(0 != question.getAnswerType().getId()){
                    questionService.save(question);
                    return ResponseEntity.status(HttpStatus.OK).body("Vraag succesvol toegevoegd!");
                }else{
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Vraag niet toegevoegd! Antwoordsoort niet gevuld!");
                }
            } else{
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Vraag niet toegevoegd!");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}



