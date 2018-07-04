package nl.hanze.application.service;

import nl.hanze.application.domain.TeamPeriod;

import java.util.List;

public interface TeamPeriodService {

    TeamPeriod save(TeamPeriod teamPeriod);

    List<TeamPeriod> findAll();

    TeamPeriod findById(int id);

    List<TeamPeriod> findByTeamName(String teamName);
}
