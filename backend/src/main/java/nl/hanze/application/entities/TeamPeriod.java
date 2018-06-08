package nl.hanze.application.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "team_period")
public class TeamPeriod {


    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "period_name", nullable = false, length = 45)
    private String periodName;

    @Basic
    @Column(name = "startdate", nullable = true)
    private Date startdate;

    @Basic
    @Column(name = "enddate", nullable = true)
    private Date enddate;


    @OneToMany(mappedBy = "teamPeriodByTeamPeriodId")
    private List<PersonPeriod> personPeriodsById;


    @ManyToOne
    @JoinColumn(name = "team_name_id", referencedColumnName = "id", nullable = false)
    private TeamName teamNameByTeamNameId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPeriodName() {
        return periodName;
    }

    public void setPeriodName(String periodName) {
        this.periodName = periodName;
    }

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public List<PersonPeriod> getPersonPeriodsById() {
        return personPeriodsById;
    }

    public void setPersonPeriodsById(List<PersonPeriod> personPeriodsById) {
        this.personPeriodsById = personPeriodsById;
    }

    public TeamName getTeamNameByTeamNameId() {
        return teamNameByTeamNameId;
    }

    public void setTeamNameByTeamNameId(TeamName teamNameByTeamNameId) {
        this.teamNameByTeamNameId = teamNameByTeamNameId;
    }
}


