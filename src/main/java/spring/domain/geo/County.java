package spring.domain.geo;

import spring.domain.Profile;

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
    private Set<Profile> citizens=new HashSet<>();

    public County(String id, Set<Profile> citizens) {
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

    public Set<Profile> getCitizens() {
        return citizens;
    }

    public void setCitizens(Set<Profile> citizens) {
        this.citizens = citizens;
    }
    public void addCitizen(Profile citizen) {
        this.citizens.add(citizen);
    }
    public void removeCitizen(Profile citizen) {
        this.citizens.remove(citizen);
    }
}
