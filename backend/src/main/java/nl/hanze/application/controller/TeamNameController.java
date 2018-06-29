package nl.hanze.application.controller;

import nl.hanze.application.domain.TeamName;
import nl.hanze.application.service.TeamNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    public @ResponseBody List<TeamName> getAllTeamNames() {
        List<TeamName> teamNamesList = teamNameService.findAll();
        if(!teamNamesList.isEmpty()){
            return teamNamesList;
        }else{
            return null;
        }
    }

    @PostMapping(value = "/teamname/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void add(@Valid @RequestBody TeamName teamName){
        teamNameService.save(teamName);
    }

}



