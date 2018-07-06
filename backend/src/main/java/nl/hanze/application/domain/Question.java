package nl.hanze.application.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//@Data
@Getter
@Setter
@Entity
@Table(name = "question", schema = "soccerpracticerating")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Question {

    public Question() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 11)
    private int id;

    @Column(name = "question", nullable = true, length = 45)
    private String question;

    @Column(name = "category", nullable = true, length = 45)
    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answertype_id", nullable = false, referencedColumnName = "id")
    @JsonBackReference
    private AnswerType answerType;


}
