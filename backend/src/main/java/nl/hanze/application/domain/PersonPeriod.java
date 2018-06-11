package nl.hanze.application.domain;

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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_period_id", referencedColumnName = "id")
    private TeamPeriod teamPeriod;

    @OneToOne()
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany(mappedBy = "personPeriod")
    private List<Rating> ratings;
}
