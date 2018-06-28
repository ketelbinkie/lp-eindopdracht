package nl.hanze.application.domain;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "personEnquete", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Column(nullable = true)
    @JsonManagedReference
    private List<Response> responses;

    @Override
    public String toString() {
        return "PersonEnquete{" +
                "id=" + id +
                ", enquetes=" + enquetes +
                ", personPeriod=" + personPeriod +
                '}';
    }


    //
//    @OneToMany
//    @JoinTable(
//            name="response",
//            joinColumns = @JoinColumn( name="pp_enquete_id"),
//            inverseJoinColumns = @JoinColumn( name="id")
//
//    )
//    public Set<Response> responses;
}