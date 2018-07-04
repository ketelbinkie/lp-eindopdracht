package nl.hanze.application.controller;

import nl.hanze.application.domain.TeamName;
import nl.hanze.application.service.TeamNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
public class TeamNameController {

    private final TeamNameService teamNameService;

    @Autowired
    public TeamNameController(TeamNameService teamNameService) {
        this.teamNameService = teamNameService;
    }

    @GetMapping(value = "/teamname/all")
    public @ResponseBody
    List<TeamName> getAllTeamNames() {
        List<TeamName> teamNamesList = teamNameService.findAll();
        if (!teamNamesList.isEmpty()) {
            return teamNamesList;
        } else {
            return null;
        }
    }

    @PostMapping(value = "/teamname/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> add(@Valid @RequestBody TeamName teamName) {
        try {
            if(!teamName.getName().isEmpty()){
                teamNameService.save(teamName);
                return ResponseEntity.status(HttpStatus.OK).body("Teamnaam is succesvol opgevoerd!");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Geen teamnaam ingevuld!");
            }
        } catch (Exception e) {

         if(e.getMessage().contains("name_UNIQUE")){
             return ResponseEntity.status(HttpStatus.CONFLICT).body("Teamnaam bestaat al!");
         }
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Er is iets foutgegaan!");
    }

}



