package nl.hanze.application.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="rating", schema = "soccerpracticerating")
public class Rating {

    @Id
    @Column(name = "period_id", nullable = false, length = 11)
    private int periodId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false, referencedColumnName = "id")
    private Question question;

    @Column(name = "open_answer", nullable = true, length = 1024)
    private String openAnswer;

    @Column(name = "rating", nullable = true, length = 11)
    private int rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_period_id", nullable = false, referencedColumnName = "id")
    private PersonPeriod personPeriod;

}
