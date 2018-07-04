//package nl.hanze.application.repositories;
//
//import nl.hanze.application.domain.TeamName;
//import nl.hanze.application.domain.TeamPeriod;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.rest.core.annotation.RestResource;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//
//@Repository
//@RestResource(exported = false)
//public interface TeamPeriodRepository extends JpaRepository<TeamPeriod, Integer> {
//
//    TeamPeriod findById(int id);
//
//    List<TeamPeriod> findByTeamName(TeamName teamName);
//
//}
//
