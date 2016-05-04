package spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.domain.Authority;
import spring.domain.Profile;
import spring.domain.User;
import spring.dto.UserDTO;
import spring.repository.AuthorityRepository;
import spring.repository.UserRepository;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Inject
    PasswordEncoder passwordEncoder;

    @Inject
    AuthorityRepository authorityRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    MailService emailService;

    public User createUser(UserDTO userDTO) {
        Profile userProfile = new Profile();

        User user = new User();

        if (!userDTO.getAuthorities().isEmpty()) {
            Set<Authority> authorities = new HashSet<>();
            userDTO.getAuthorities().stream().forEach(
                    authority -> authorities.add(authorityRepository.findOne(authority))
            );
            user.setAuthorities(authorities);
        }

        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        userRepository.save(user);
        emailService.sendActivationEmail("kondzixu@gmail.com", user);
        log.debug("Created Information for User: {}", user);
        return user;
    }

    //ogarnac
    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthoritiesByUsername(String username) {
        return userRepository.findOneByUsername(username).map(u -> {
            u.getAuthorities().size();
            return u;
        });
    }

}
