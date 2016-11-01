package spring.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import spring.domain.geo.County;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, insertable = false, updatable = false)
    @Size(min = 2, max = 50)
    private String username;

    @NotBlank
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    @Size(max = 100)
    @Email
    private String email;

    @Column(name="enabled", nullable = false)
    private Boolean enabled = true;

    @Column(name="active", nullable = false)
    private Boolean activated = false;

    @Size(max = 20)
    @Column(name = "activation_key", length = 20)
    private String activationKey;

    @Size(max = 20)
    @Column(name = "reset_key", length = 20)
    private String resetKey;

    @Column(name = "reset_date", nullable = true)
    private LocalDateTime resetDate;

    @ManyToMany
    @JoinTable(
            name = "user_site_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    private Set<Authority> userRoles = new HashSet<>();
    @ManyToOne
    @JoinColumn(name="county")
    private County citizen;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Profile profile = new Profile();

    public User(User user) {
        this(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getEnabled(),
                user.getActivated(),
                user.getActivationKey(),
                user.getResetKey(),
                user.getResetDate(),
                user.getUserRoles(),
                user.getCitizen(),
                user.getProfile()
        );
    }
}
