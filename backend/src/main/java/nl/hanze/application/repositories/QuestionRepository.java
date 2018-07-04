package nl.hanze.application.repositories;

import nl.hanze.application.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;


@Repository
@RestResource(exported = false)
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Question findByCategory(String category);

    Question findById(int id);
}

