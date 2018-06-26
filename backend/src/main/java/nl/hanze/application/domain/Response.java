package nl.hanze.application.domain;


import lombok.Data;

import javax.persistence.*;

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

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "pp_enquete_id", referencedColumnName = "id")
//    private Person person;
}
