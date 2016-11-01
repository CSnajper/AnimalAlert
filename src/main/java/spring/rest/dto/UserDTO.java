package spring.rest.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import spring.domain.Authority;
import spring.domain.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UserDTO {
    public static final int PASSWORD_MIN_LENGTH = 4;
    public static final int PASSWORD_MAX_LENGTH = 100;

    @Pattern(regexp = "^[a-z0-9]*$")
    @NotNull
    @Size(min = 1, max = 50)
    private String username;

    @NotNull
    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    private boolean activated;

    @Email
    @Size(min = 5, max = 100)
    private String email;

    private Set<String> authorities;

    public UserDTO(User user) {
        this(user.getUsername(), null, user.getEmail(), user.getActivated(),
                user.getUserRoles().stream().map(Authority::getName)
                        .collect(Collectors.toSet()));
    }

    public UserDTO(String username, String password, String email, boolean activated, Set<String> authorities) {
        this.username = username;
        this.password = password;
        this.activated = activated;
        this.email = email;
        this.authorities = authorities;
    }
}
