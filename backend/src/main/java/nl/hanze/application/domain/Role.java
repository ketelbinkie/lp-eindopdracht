package nl.hanze.application.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "role", schema = "soccerpracticerating")
public class Role {

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "role", nullable = false, length = 45)
    private String role;


}
