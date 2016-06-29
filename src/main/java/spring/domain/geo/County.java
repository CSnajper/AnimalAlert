package spring.domain.geo;

import spring.domain.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jakub on 22.06.16.
 */
@Entity
public class County implements Serializable {
    @Id
    private String id;
    @OneToMany(mappedBy="citizen")
    private Set<User> citizens=new HashSet<>();

    public County(String id, Set<User> citizens) {
        this.id = id;
        this.citizens = citizens;
    }

    public County(String id) {
        this.id = id;
    }

    public County() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Set<User> getCitizens() {
        return citizens;
    }

    public void setCitizens(Set<User> citizens) {
        this.citizens = citizens;
    }
    public void addCitizen(User citizen) {
        this.citizens.add(citizen);
    }
    public void removeCitizen(User citizen) {
        this.citizens.remove(citizen);
    }
}
