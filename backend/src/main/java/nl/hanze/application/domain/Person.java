package nl.hanze.application.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "person", schema = "soccerpracticerating")
public class Person {

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "firstname", nullable = true, length = 45)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 45)
    private String lastname;

    @Column(name = "date_of_birth", nullable = false)
    private Timestamp dateOfBirth;

    @Column(name = "gender", nullable = false, length = 1)
    private String gender;
//
//    @OneToMany(mappedBy = "person",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @Column(nullable = true)
//    @JsonManagedReference
//    private List<PersonPeriod> periods;

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getFirstname() {
//        return firstname;
//    }
//
//    public void setFirstname(String firstname) {
//        this.firstname = firstname;
//    }
//
//    public String getLastname() {
//        return lastname;
//    }
//
//    public void setLastname(String lastname) {
//        this.lastname = lastname;
//    }
//
//    public Timestamp getDateOfBirth() {
//        return dateOfBirth;
//    }
//
//    public void setDateOfBirth(Timestamp dateOfBirth) {
//        this.dateOfBirth = dateOfBirth;
//    }
//
//    public String getGender() {
//        return gender;
//    }
//
//    public void setGender(String gender) {
//        this.gender = gender;
//    }
//
//    public List<PersonPeriod> getPeriods() {
//        return periods;
//    }
//
//    public void setPeriods(List<PersonPeriod> periods) {
//        this.periods = periods;
//    }
}
