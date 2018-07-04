package nl.hanze.application.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "person_period", schema = "soccerpracticerating")
public class PersonPeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "startdate", nullable = true)
    private Date startdate;

    @Column(name = "enddate", nullable = true)
    private Date enddate;


//    @Column(name = "person_id")
//    private int personId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_period_id", referencedColumnName = "id")
    private TeamPeriod teamPeriod;

    @OneToOne()
    @JoinColumn(name = "role_id")
    private Role role;


//
}
