package nl.hanze.application.entities;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "role", schema = "soccerpracticerating", catalog = "")
public class Role {
    private int id;
    private String role;
    private Collection<PersonPeriod> personPeriodsById;
    private Collection<User> usersById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "role", nullable = false, length = 45)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role that = (Role) o;

        if (id != that.id) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "roleByRoleId")
    public Collection<PersonPeriod> getPersonPeriodsById() {
        return personPeriodsById;
    }

    public void setPersonPeriodsById(Collection<PersonPeriod> personPeriodsById) {
        this.personPeriodsById = personPeriodsById;
    }


}
