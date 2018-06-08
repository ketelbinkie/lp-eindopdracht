package nl.hanze.application.entities;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @OneToOne()
    @JoinColumn(name = "person_id")
    private Person person;

    @OneToOne()
    @JoinColumn(name = "role_id")
    private Role role;


}