package nl.hanze.application.service.impl;


import nl.hanze.application.domain.TeamName;
import nl.hanze.application.domain.TeamPeriod;
import nl.hanze.application.repositories.TeamNameRepository;
import nl.hanze.application.repositories.TeamPeriodRepository;
import nl.hanze.application.service.TeamPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("teamPeriodService")
class TeamPeriodServiceImpl implements TeamPeriodService {

    private final TeamPeriodRepository teamPeriodRepository;
    private final TeamNameRepository teamNameRepository;

    @Autowired
    public TeamPeriodServiceImpl(TeamPeriodRepository teamPeriodRepository, TeamNameRepository teamNameRepository) {
        this.teamPeriodRepository = teamPeriodRepository;
        this.teamNameRepository = teamNameRepository;
    }

    public TeamPeriod save(TeamPeriod teamPeriod) {
        return teamPeriodRepository.save(teamPeriod);
    }

    public List<TeamPeriod> findAll() {
        return teamPeriodRepository.findAll();
    }

    public TeamPeriod findById(int id) {
        return teamPeriodRepository.findById(id);
    }

    public List<TeamPeriod> findByTeamName(String teamName) {
        TeamName team = teamNameRepository.findByName(teamName);
        return teamPeriodRepository.findByTeamName(team);

    }


}

