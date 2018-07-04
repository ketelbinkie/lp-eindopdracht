package nl.hanze.application.controller;

import nl.hanze.application.domain.TeamName;
import nl.hanze.application.domain.TeamPeriod;
import nl.hanze.application.service.TeamNameService;
import nl.hanze.application.service.TeamPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class TeamPeriodController {


    private final TeamPeriodService teamPeriodService;
    private final TeamNameService teamNameService;

    @Autowired
    public TeamPeriodController(TeamPeriodService teamPeriodService, TeamNameService teamNameService) {
        this.teamPeriodService = teamPeriodService;
        this.teamNameService = teamNameService;
    }

    @GetMapping(value = "/teamperiod/all")
    public @ResponseBody
    List<TeamPeriod> getAllTeamPeriods() {
        List<TeamPeriod> teamPeriodList = teamPeriodService.findAll();
        if (!teamPeriodList.isEmpty()) {
            return teamPeriodList;
        } else {
            return null;
        }
    }

    @GetMapping(value = "/teamperiod/findTeamPeriod")
    public TeamPeriod findTeamPeriod(
            @RequestParam(value = "id") int id) {
        return teamPeriodService.findById(id);
    }

    @PostMapping(value = "/teamperiod/add", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> add(@Valid @RequestBody TeamPeriod teamPeriod) {
        try {
            if (teamPeriod != null) {
                // Bepalen of het een 'all-teams' situatie is
                if (teamPeriod.getTeamName().getId() == 99999 && teamPeriod.getTeamName().getName().equals("allteams")) {
                    List<TeamName> allTeamsList = new ArrayList<>();
                    allTeamsList = teamNameService.findAll();

                    String season = teamPeriod.getSeason();
                    String periodName = teamPeriod.getPeriodName();
                    Date startdate = teamPeriod.getStartdate();
                    Date enddate = teamPeriod.getEnddate();

                    for (int i = 0; i < allTeamsList.size(); i++) {
                        TeamPeriod teamPeriodNew = new TeamPeriod();

                        teamPeriodNew.setSeason(season);
                        teamPeriodNew.setPeriodName(periodName);
                        teamPeriodNew.setStartdate((java.sql.Date) startdate);
                        teamPeriodNew.setEnddate((java.sql.Date) enddate);

                        int teamId = allTeamsList.get(i).getId();
                        String teamName = allTeamsList.get(i).getName();

                        TeamName teamNameNew = new TeamName();
                        teamNameNew.setId(teamId);
                        teamNameNew.setName(teamName);

                        teamPeriodNew.setTeamName(teamNameNew);

                        teamPeriodService.save(teamPeriodNew);
                    }
                    return ResponseEntity.status(HttpStatus.OK).body("Teamperiode is sucesvol opgeslagen voor alle teams");
                } else {
                    // Check team is present
                    if (0 != teamPeriod.getTeamName().getId()) {
                        // Check start date before end date
                        if (teamPeriod.getStartdate().before(teamPeriod.getEnddate())) {

                            teamPeriodService.save(teamPeriod);

                            String teamName = teamPeriod.getTeamName().getName();
                            return ResponseEntity.status(HttpStatus.OK).body("Teamperiode is succesvol opgeslagen voor team '" + teamName + "'!");
                        } else {
                            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Startdatum moet voor einddatum liggen!");
                        }
                    } else {
                        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Teamperiode is niet toegevoegd! Team niet gevuld!");
                    }
                }
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Teamperiode is niet toegevoegd!");
            }
        } catch (Exception e) {
            return ResponseEntity.unprocessableEntity().body(e.getMessage());
        }
    }
}