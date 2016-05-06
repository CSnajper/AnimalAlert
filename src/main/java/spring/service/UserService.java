package spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import spring.domain.Authority;
import spring.domain.Profile;
import spring.domain.User;
import spring.dto.UserDTO;
import spring.repository.AuthorityRepository;
import spring.repository.UserRepository;
import spring.service.util.RandomUtil;

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
    MailService mailService;

    public User createUser(UserDTO userDTO) {
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
        user.setActivated(false);
        String key = RandomUtil.generateActivationKey();
        user.setActivationKey(key);
        userRepository.save(user);
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

    public Optional<User> activateRegistration(String key) {
        log.debug("Activating user for activation key {}", key);
        return userRepository.findOneByActivationKey(key)
                .map(user -> {
                    // activate given user for the registration key.
                    user.setActivated(true);
                    user.setActivationKey(null);
                    userRepository.save(user);
                    log.debug("Activated user: {}", user);
                    return user;
                });
    }

}
