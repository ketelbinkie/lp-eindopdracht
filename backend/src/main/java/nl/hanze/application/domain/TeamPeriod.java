package nl.hanze.application.domain;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "team_period")
public class TeamPeriod {


    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "period_name", nullable = false, length = 45)
    private String periodName;

    @Column(name = "startdate", nullable = true)
    private Date startdate;

    @Column(name = "enddate", nullable = true)
    private Date enddate;

    @ManyToOne
    @JoinColumn(name = "team_name_id", referencedColumnName = "id", nullable = false)
    private TeamName teamName;

    @OneToMany(mappedBy = "teamPeriod")
    private List<PersonPeriod> personPeriods;

    @OneToMany
    @JoinTable(name = "teamperiod_enquetes",
            joinColumns ={@JoinColumn(name = "team_period_id", referencedColumnName = "id")},
            inverseJoinColumns ={@JoinColumn(name = "enquete_id", referencedColumnName = "id")})
    private List<Enquete> enquetes;

}


