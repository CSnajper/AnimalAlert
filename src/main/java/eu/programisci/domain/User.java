package eu.programisci.domain;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 35, nullable = false)
    @Size(min = 3, max = 35)
    private String username;

    @Column(length = 35, nullable = false)
    @Size(min = 6, max = 35)
    private String password;

    @Column(length = 100, nullable = false)
    @Size(max = 100)
    @Email
    private String email;

    /*@OneToOne
    @JoinColumn(name = "adress_id")
    private Adress adress;*/

    @ManyToMany(mappedBy = "users")
    private Set<Organisation> organisations;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Organisation> getOrganisations() {
        return organisations;
    }

    public void setOrganisations(Set<Organisation> organisations) {
        this.organisations = organisations;
    }
}
