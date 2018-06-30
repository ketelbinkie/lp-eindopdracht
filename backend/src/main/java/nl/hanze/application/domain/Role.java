package nl.hanze.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@Entity
@Table(name = "role", schema = "soccerpracticerating")
public class Role {

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "role", nullable = false, length = 45)
    private String role;

//    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @Column(nullable = true)
////    @JsonManagedReference
//    private List<User> users;
}
