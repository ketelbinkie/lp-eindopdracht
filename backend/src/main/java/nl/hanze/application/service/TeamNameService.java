package nl.hanze.application.service;

import nl.hanze.application.domain.TeamName;

import java.util.List;

public interface TeamNameService {
    List<TeamName> findAll();
    TeamName save(TeamName teamName);
}
