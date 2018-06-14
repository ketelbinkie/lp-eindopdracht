package nl.hanze.application.repositories;

import nl.hanze.application.domain.AnswerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;


@Repository
@RestResource(exported = false)
public interface AnswerTypeRepository extends JpaRepository<AnswerType, Integer> {

}

