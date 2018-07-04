package nl.hanze.application.service.impl;

import nl.hanze.application.domain.TeamName;
import nl.hanze.application.repositories.TeamNameRepository;
import nl.hanze.application.service.TeamNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("teamNameService")
public class TeamNameServiceImpl implements TeamNameService {

    private final TeamNameRepository teamNameRepository;

    @Autowired
    public TeamNameServiceImpl(TeamNameRepository teamNameRepository) {
        this.teamNameRepository = teamNameRepository;
    }

    public TeamName save(TeamName teamName) {
        return teamNameRepository.save(teamName);
    }

    public List<TeamName> findAll() {
        return teamNameRepository.findAll();
    }

    public TeamName findById(int id) {
        return teamNameRepository.findById(id);
    }

    public void deleteById(int id) {
        teamNameRepository.deleteById(id);
    }
}
