package nl.hanze.application.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "team_name", schema = "soccerpracticerating")
public class TeamName {

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 45)
    private String name;

//    @OneToMany(mappedBy = "teamName",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @Column(nullable = true)
//    @JsonManagedReference
//    private List<TeamPeriod> teamPeriods;
}