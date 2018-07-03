package nl.hanze.application.repositories;

import nl.hanze.application.domain.Enquete;
import nl.hanze.application.domain.Person;
import nl.hanze.application.domain.Question;
import nl.hanze.application.domain.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;


@Repository
@RestResource(exported = false)
public interface EnqueteRepository extends JpaRepository<Enquete, Integer> {

    Enquete findById(int id);

//        @Query("insert into enquete_questions (enquete_id, question_id) values (, ?)");

}

