package nl.hanze.application.repositories;

import nl.hanze.application.domain.TeamName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;


@Repository
@RestResource(exported = false)
public interface TeamNameRepository extends JpaRepository<TeamName, Integer> {

    TeamName findById(int id);

    TeamName findByName(String name);
}

