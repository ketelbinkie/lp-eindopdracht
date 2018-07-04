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

//    @OneToMany
//    @JoinTable(name = "enquete_questions",
//            joinColumns ={@JoinColumn(name = "enquete_id", referencedColumnName = "id")},
//            inverseJoinColumns ={@JoinColumn(name = "question_id", referencedColumnName = "id")})
////    @JsonManagedReference
//    @JsonBackReference(value="enquete_question")
//    private List<Question> questions;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "enquete_questions",
            joinColumns = @JoinColumn(name = "enquete_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
    private List<Question> questions;

//    @OneToMany
//    @JoinTable(
//            name="response",
//            joinColumns = @JoinColumn( name="enquete_questions_enquete_id"),
//            inverseJoinColumns = @JoinColumn( name="id")
//    )
//    public Set<Response> responses;


//    @OneToMany
//    @JoinTable(name = "teamperiod_enquetes",
//            joinColumns ={@JoinColumn(name = "enquete_id", referencedColumnName = "id")},
//            inverseJoinColumns ={@JoinColumn(name = "team_period_id", referencedColumnName = "id")})
//    @JsonBackReference
////    @JsonManagedReference
//    private List<TeamPeriod> teamPeriods;

}

