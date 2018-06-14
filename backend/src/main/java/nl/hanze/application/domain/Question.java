package nl.hanze.application.domain;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "question", schema = "soccerpracticerating")
public class Question {

    @Id
    @Column(name = "id", nullable = false, length = 11)
    private int id;

    @Column(name = "question", nullable = true, length = 45)
    private String question;

    @Column(name = "category", nullable = true, length = 45)
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answertype_id", nullable = false, referencedColumnName = "id")
    private AnswerType answerType;
//
//    @OneToMany(mappedBy = "question")
//    private List<Rating> ratings;

    @OneToMany
    @JoinTable(name = "enquete_questions",
            joinColumns ={@JoinColumn(name = "question_id", referencedColumnName = "id")},
            inverseJoinColumns ={@JoinColumn(name = "enquete_id", referencedColumnName = "id")})
    private List<Enquete> enquetes;

}
