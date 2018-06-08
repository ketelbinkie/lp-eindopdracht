package nl.hanze.application.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "person_period", schema = "soccerpracticerating", catalog = "")
public class PersonPeriod {
    private int id;
    private int personId;
    private int teamPeriodId;
    private Date startdate;
    private Date enddate;
    private int roleId;
    private Person personByPersonId;
    private TeamPeriod teamPeriodByTeamPeriodId;
    private Role roleByRoleId;
    private Collection<Rating> ratingsById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    @Basic
//    @Column(name = "person_id", nullable = false)
//    public int getPersonId() {
//        return personId;
//    }
//
//    public void setPersonId(int personId) {
//        this.personId = personId;
//    }
//
//    @Basic
//    @Column(name = "team_period_id", nullable = false)
//    public int getTeamPeriodId() {
//        return teamPeriodId;
//    }
//
//    public void setTeamPeriodId(int teamPeriodId) {
//        this.teamPeriodId = teamPeriodId;
//    }

    @Basic
    @Column(name = "startdate", nullable = true)
    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    @Basic
    @Column(name = "enddate", nullable = true)
    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
//
//    @Basic
//    @Column(name = "role_id", nullable = false)
//    public int getRoleId() {
//        return roleId;
//    }
//
//    public void setRoleId(int roleId) {
//        this.roleId = roleId;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonPeriod that = (PersonPeriod) o;

        if (id != that.id) return false;
        if (personId != that.personId) return false;
        if (teamPeriodId != that.teamPeriodId) return false;
        if (roleId != that.roleId) return false;
        if (startdate != null ? !startdate.equals(that.startdate) : that.startdate != null) return false;
        if (enddate != null ? !enddate.equals(that.enddate) : that.enddate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + personId;
        result = 31 * result + teamPeriodId;
        result = 31 * result + (startdate != null ? startdate.hashCode() : 0);
        result = 31 * result + (enddate != null ? enddate.hashCode() : 0);
        result = 31 * result + roleId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id", nullable = false)
    public Person getPersonByPersonId() {
        return personByPersonId;
    }

    public void setPersonByPersonId(Person personByPersonId) {
        this.personByPersonId = personByPersonId;
    }

    @ManyToOne
    @JoinColumn(name = "team_period_id", referencedColumnName = "id", nullable = false)
    public TeamPeriod getTeamPeriodByTeamPeriodId() {
        return teamPeriodByTeamPeriodId;
    }

    public void setTeamPeriodByTeamPeriodId(TeamPeriod teamPeriodByTeamPeriodId) {
        this.teamPeriodByTeamPeriodId = teamPeriodByTeamPeriodId;
    }

    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    public Role getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(Role roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }

    @OneToMany(mappedBy = "personPeriodByPupilPeriodId")
    public Collection<Rating> getRatingsById() {
        return ratingsById;
    }

    public void setRatingsById(Collection<Rating> ratingsById) {
        this.ratingsById = ratingsById;
    }
}
