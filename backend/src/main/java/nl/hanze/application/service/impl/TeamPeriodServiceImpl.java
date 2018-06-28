package nl.hanze.application.service.impl;


import nl.hanze.application.domain.TeamPeriod;
import nl.hanze.application.repositories.TeamPeriodRepository;
import nl.hanze.application.service.TeamPeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("teamPeriodService")
public class TeamPeriodServiceImpl implements TeamPeriodService {

    private final TeamPeriodRepository teamPeriodRepository;

    @Autowired
    public TeamPeriodServiceImpl(TeamPeriodRepository teamPeriodRepository) {
        this.teamPeriodRepository = teamPeriodRepository;
    }

    public TeamPeriod save(TeamPeriod teamPeriod) {return teamPeriodRepository.save(teamPeriod);}
    public List<TeamPeriod> findAll() {return teamPeriodRepository.findAll();}
    public TeamPeriod findById(int id) {return teamPeriodRepository.findById(id);}
    public TeamPeriod findByTeamName(String teamName) {return teamPeriodRepository.findByTeamName(teamName);}


}

