package nl.hanze.application.repositories;

import nl.hanze.application.domain.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Integer> {

    List<Response> findDistinctByEnqueteId(String id);
}