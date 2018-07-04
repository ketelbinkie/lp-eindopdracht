package nl.hanze.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "team_period", schema = "soccerpracticerating")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TeamPeriod {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "season", nullable = false, length = 45)
    private String season;

    @Column(name = "period_name", nullable = false, length = 45)
    private String periodName;

    @Column(name = "startdate", nullable = true)
    private Date startdate;

    @Column(name = "enddate", nullable = true)
    private Date enddate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "team_name_id", referencedColumnName = "id")
    private TeamName teamName;


//    @OneToMany(mappedBy = "teamPeriod")
//    @JsonBackReference
//    private List<PersonPeriod> personPeriods;

//    @OneToMany
//    @JoinTable(name = "teamperiod_enquetes",
//            joinColumns ={@JoinColumn(name = "team_period_id", referencedColumnName = "id")},
//            inverseJoinColumns ={@JoinColumn(name = "enquete_id", referencedColumnName = "id")})
//    private List<Enquete> enquetes;

}


