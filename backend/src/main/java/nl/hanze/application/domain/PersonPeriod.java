package nl.hanze.application.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "person_period", schema = "soccerpracticerating")
public class PersonPeriod {

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "startdate", nullable = true)
    private Date startdate;

    @Column(name = "enddate", nullable = true)
    private Date enddate;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @JsonBackReference
    private Person person;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_period_id", referencedColumnName = "id")
    private TeamPeriod teamPeriod;

    @OneToOne()
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "personPeriod")
    private List<Rating> ratings;
}
