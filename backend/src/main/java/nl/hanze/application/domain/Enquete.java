package nl.hanze.application.domain;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "enquete", schema = "soccerpracticerating")
public class Enquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 11)
    private int id;

    @Column(name = "name", nullable = true, length = 45)
    private String name;


    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "enquete_questions",
            joinColumns = @JoinColumn(name = "enquete_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questions;


}

