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
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.security.SocialUserDetails;
import spring.domain.Authority;
import spring.domain.SocialUserConnection;
import spring.domain.User;
import spring.repository.AuthorityRepository;
import spring.repository.SocialUserConnectionRepository;
import spring.repository.UserRepository;
import spring.security.util.SecurityUtils;
import spring.service.util.RandomUtil;

import javax.inject.Inject;
import java.util.*;
import java.util.stream.Collectors;

public class SocialService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Inject
    SocialUserConnectionRepository socialUserConnectionRepository;

    @Inject
    UserRepository userRepository;

    @Inject
    AuthorityRepository authorityRepository;

    @Inject
    PasswordEncoder passwordEncoder;

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
            user.setAuthorities(authorities);

            user = userRepository.save(user);
        }
        else {
            user = foundUser.get();
        }

        String lowercaseLogin = user.getUsername().toLowerCase();
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .collect(Collectors.toList());
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(lowercaseLogin,
                user.getPassword(),
                grantedAuthorities);
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        //SecurityContextHolder.clearContext();
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
