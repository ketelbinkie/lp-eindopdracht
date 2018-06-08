package nl.hanze.application.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@Table(name = "person", schema = "soccerpracticerating", catalog = "")
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

//    @OneToMany(mappedBy = "person")
//    private List<PersonPeriod> persons;


}
