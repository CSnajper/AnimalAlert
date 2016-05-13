package spring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.domain.Authority;
import spring.domain.User;
import spring.rest.dto.UserDTO;
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
        user.setActivated(true);
        userRepository.save(user);
        log.debug("Created Information for User: {}", user);
        return user;
    }

    public User registerUser(UserDTO userDTO) {
        User user = new User();

        Authority authority = authorityRepository.findOne("ROLE_USER");
        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        user.setAuthorities(authorities);
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
