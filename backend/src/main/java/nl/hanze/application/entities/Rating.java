package nl.hanze.application.entities;

import javax.persistence.*;

@Entity
@Table(name = "rating", schema = "soccerpracticerating", catalog = "")
public class Rating {
    private int periodId;
    private int questionId;
    private String openAnswer;
    private Integer rating;
    private int pupilPeriodId;
    private PersonPeriod personPeriodByPupilPeriodId;

    @Id
    @Column(name = "period_id", nullable = false)
    public int getPeriodId() {
        return periodId;
    }

    public void setPeriodId(int periodId) {
        this.periodId = periodId;
    }

//    @Basic
//    @Column(name = "question_id", nullable = false)
//    public int getQuestionId() {
//        return questionId;
//    }
//
//    public void setQuestionId(int questionId) {
//        this.questionId = questionId;
//    }

    @Basic
    @Column(name = "open_answer", nullable = true, length = 1024)
    public String getOpenAnswer() {
        return openAnswer;
    }

    public void setOpenAnswer(String openAnswer) {
        this.openAnswer = openAnswer;
    }

    @Basic
    @Column(name = "rating", nullable = true)
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

//    @Basic
//    @Column(name = "pupil_period_id", nullable = false)
//    public int getPupilPeriodId() {
//        return pupilPeriodId;
//    }
//
//    public void setPupilPeriodId(int pupilPeriodId) {
//        this.pupilPeriodId = pupilPeriodId;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating that = (Rating) o;

        if (periodId != that.periodId) return false;
        if (questionId != that.questionId) return false;
        if (pupilPeriodId != that.pupilPeriodId) return false;
        if (openAnswer != null ? !openAnswer.equals(that.openAnswer) : that.openAnswer != null) return false;
        if (rating != null ? !rating.equals(that.rating) : that.rating != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = periodId;
        result = 31 * result + questionId;
        result = 31 * result + (openAnswer != null ? openAnswer.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        result = 31 * result + pupilPeriodId;
        return result;
    }



    @ManyToOne
    @JoinColumn(name = "pupil_period_id", referencedColumnName = "id", nullable = false)
    public PersonPeriod getPersonPeriodByPupilPeriodId() {
        return personPeriodByPupilPeriodId;
    }

    public void setPersonPeriodByPupilPeriodId(PersonPeriod personPeriodByPupilPeriodId) {
        this.personPeriodByPupilPeriodId = personPeriodByPupilPeriodId;
    }
}
