package spring.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import spring.domain.geo.County;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 50, nullable = false)
    @Size(min = 2, max = 50)
    private String username;

    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    @Size(max = 100)
    @Email
    private String email;

    @Column(name="active", nullable = false)
    private boolean activated;

    @Size(max = 20)
    @Column(name = "activation_key", length = 20)
    private String activationKey;

    @Size(max = 20)
    @Column(name = "reset_key", length = 20)
    private String resetKey;

    @Column(name = "reset_date", nullable = true)
    private ZonedDateTime resetDate = null;

    @ManyToMany
    @JoinTable(
            name = "user_site_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    private Set<Authority> authorities = new HashSet<>();
    @ManyToOne
    @JoinColumn(name="county")
    private County citizen;
}
