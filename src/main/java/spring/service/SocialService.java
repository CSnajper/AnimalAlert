package spring.service;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.stereotype.Service;
import spring.domain.Authority;
import spring.domain.SocialUserConnection;
import spring.domain.User;
import spring.repository.AuthorityRepository;
import spring.repository.SocialUserConnectionRepository;
import spring.repository.UserRepository;
import spring.service.util.RandomUtil;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SocialService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Inject
    private UsersConnectionRepository usersConnectionRepository;

    @Inject
    SocialUserConnectionRepository socialUserConnectionRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    AuthorityRepository authorityRepository;

    @Inject
    PasswordEncoder passwordEncoder;

    @Inject
    private MailService mailService;

    public void loginOrCreateFacebookUser(UserProfile userProfile) {

        //TODO: przy probie pobrania uzytkownika zarzuca error `Exception while handling OAuth2 callback (null). Redirecting to facebook connection status page.`
        SocialUserConnection socialUserConnection = this.socialUserConnectionRepository.findOneByEmailAndProviderId(userProfile.getEmail(), "facebook");

        Optional<User> foundUser = userRepository.findByUsername(socialUserConnection.getEmail());

        User user = new User();
        if(!foundUser.isPresent()) {
            user.setUsername(socialUserConnection.getEmail());
            user.setEmail(socialUserConnection.getEmail());
            user.setPassword(RandomUtil.generatePassword());
            user.setActivated(true);
            Authority authority = authorityRepository.findOne("ROLE_USER");
            Set<Authority> authorities = new HashSet<>();
            authorities.add(authority);
            user.setUserRoles(authorities);

            user = userRepository.save(user);
        }
        else {
            user = foundUser.get();
        }

        String lowercaseLogin = user.getUsername().toLowerCase();
        List<GrantedAuthority> grantedAuthorities = user.getUserRoles().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(lowercaseLogin,
                user.getPassword(),
                grantedAuthorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContextHolder.clearContext();
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public void deleteUserSocialConnection(String login) {
        ConnectionRepository connectionRepository = usersConnectionRepository.createConnectionRepository(login);
        connectionRepository.findAllConnections().keySet().stream()
                .forEach(providerId -> {
                    connectionRepository.removeConnections(providerId);
                    log.debug("Delete user social connection providerId: {}", providerId);
                });
    }

    public void createSocialUser(Connection<?> connection) {
        if (connection == null) {
            log.error("Cannot create social user because connection is null");
            throw new IllegalArgumentException("Connection cannot be null");
        }
        UserProfile userProfile = connection.fetchUserProfile();
        String providerId = connection.getKey().getProviderId();
        User user = createUserIfNotExist(userProfile, providerId);
        createSocialConnection(user.getUsername(), connection);
        //mailService.sendSocialRegistrationValidationEmail(user, providerId);
    }

    private User createUserIfNotExist(UserProfile userProfile, String providerId) {
        String email = userProfile.getEmail();
        String userName = userProfile.getUsername();
        if (StringUtils.isBlank(email) && StringUtils.isBlank(userName)) {
            log.error("Cannot create social user because email and login are null");
            throw new IllegalArgumentException("Email and login cannot be null");
        }
        if (StringUtils.isBlank(email) && userRepository.findOneByUsername(userName).isPresent()) {
            log.error("Cannot create social user because email is null and login already exist, login -> {}", userName);
            throw new IllegalArgumentException("Email cannot be null with an existing login");
        }
        Optional<User> user = userRepository.findOneByEmail(email);
        if (user.isPresent()) {
            log.info("User already exist associate the connection to this account");
            return user.get();
        }

        String login = getLoginDependingOnProviderId(userProfile, providerId);
        String encryptedPassword = passwordEncoder.encode(RandomStringUtils.random(10));
        Set<Authority> authorities = new HashSet<>(1);
        authorities.add(authorityRepository.findOne("ROLE_USER"));

        User newUser = new User();
        newUser.setUsername(login);
        newUser.setPassword(encryptedPassword);
        newUser.setEmail(email);
        newUser.setActivated(true);
        newUser.setUserRoles(authorities);

        return userRepository.save(newUser);
    }

    /**
     * @return login if provider manage a login like Twitter or Github otherwise email address.
     *         Because provider like Google or Facebook didn't provide login or login like "12099388847393"
     */
    private String getLoginDependingOnProviderId(UserProfile userProfile, String providerId) {
        switch (providerId) {
            case "twitter":
                return userProfile.getUsername().toLowerCase();
            default:
                return userProfile.getEmail();
        }
    }

    private void createSocialConnection(String login, Connection<?> connection) {
        ConnectionRepository connectionRepository = usersConnectionRepository.createConnectionRepository(login);
        connectionRepository.addConnection(connection);
    }
}
