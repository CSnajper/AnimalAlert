package eu.programisci.domain;


import javax.persistence.*;

@Entity
@Table(name = "user_organisation", uniqueConstraints={@UniqueConstraint(columnNames={"user_id", "organisation_id"})})
public class UserOrganisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "organisation_id", referencedColumnName = "id")
    private Organisation organisation;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Organisation getOrganisation() {
        return organisation;
    }

    public void setOrganisation(Organisation organisation) {
        this.organisation = organisation;
    }
}
