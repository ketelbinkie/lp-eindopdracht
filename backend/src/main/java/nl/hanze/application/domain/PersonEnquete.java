package nl.hanze.application.domain;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "person_enquete", schema = "soccerpracticerating")
public class PersonEnquete {

       @Id
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "enquete_id", referencedColumnName = "id")
    private Enquete enquetes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_period_id", referencedColumnName = "id")
    private PersonPeriod personPeriod;

//    @OneToMany
//    @JoinTable(
//            name="response",
//            joinColumns = @JoinColumn( name="pp_enquete_id"),
//            inverseJoinColumns = @JoinColumn( name="id")
//    )
//    public Set<Response> responses;
}
