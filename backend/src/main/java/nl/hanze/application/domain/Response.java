package nl.hanze.application.domain;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
@Table(name = "response", schema = "soccerpracticerating")
public class Response {

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "enquete_questions_question_id")
    private String questionId;

    @Column(name = "enquete_questions_enquete_id")
    private String enqueteId;

    @Column(name = "answer", length = 45)
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pp_enquete_id", nullable = false, referencedColumnName = "id")
    @JsonBackReference
    private PersonEnquete personEnquete;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response = (Response) o;
        return getId() == response.getId() &&
                Objects.equals(getQuestionId(), response.getQuestionId()) &&
                Objects.equals(getEnqueteId(), response.getEnqueteId()) &&
                Objects.equals(getAnswer(), response.getAnswer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getQuestionId(), getEnqueteId(), getAnswer());
    }

    @Override
    public String toString() {
        return "Response{" +
                "id=" + id +
                ", questionId='" + questionId + '\'' +
                ", enqueteId='" + enqueteId + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }

    //    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "pp_enquete_id", referencedColumnName = "id")
//    private Person person;
}
