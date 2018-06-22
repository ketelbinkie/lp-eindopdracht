package nl.hanze.application.domain;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "response", schema = "soccerpracticerating")
public class Response {

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "enquete_questions_question_id", nullable = true, length = 45)
    private String questionId;


    @Column(name = "answer", nullable = true, length = 45)
    private String answer;


}
