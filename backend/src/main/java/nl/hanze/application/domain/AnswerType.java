package nl.hanze.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "answer_type", schema = "soccerpracticerating")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AnswerType {
    public AnswerType() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 11)
    private int id;

    @Column(name = "answertype", nullable = true, length = 255)
    private String answertype;

    @Column(name = "answersubtype", nullable = true, length = 45)
    private String answersubtype;

    @OneToMany(mappedBy = "answerType", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Column(nullable = true)
    @JsonManagedReference
    private List<Question> questions;

}
