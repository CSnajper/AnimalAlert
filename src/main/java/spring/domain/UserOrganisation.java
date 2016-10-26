package spring.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import spring.domain.geo.County;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_organisation", uniqueConstraints={@UniqueConstraint(columnNames={"user_id", "organisation_id"})})
@Data
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

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_organisation_authority",
            joinColumns = {@JoinColumn(name = "user_organisation_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    private Set<Authority> authorities = new HashSet<>();


}
