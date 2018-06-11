package nl.hanze.application.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

    @Data
    @Entity
    @Table(name = "answer_type", schema = "soccerpracticerating")
    public class AnswerType {

        @Id
        @Column(name = "id", nullable = false, length = 11)
        private int id;

        @Column(name = "answertype", nullable = true, length = 255)
        private String answertype;

        @Column(name = "answersubtyp", nullable = true, length = 45)
        private String answersubtyp;

        @OneToMany(mappedBy = "answerType")
        private List<Question> questions;

    }
