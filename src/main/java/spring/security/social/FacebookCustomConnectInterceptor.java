package spring.security.social;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.WebRequest;
import spring.domain.SocialUserConnection;
import spring.repository.SocialUserConnectionRepository;
import spring.repository.social.CustomSocialUsersConnectionRepository;
import spring.service.SocialService;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class FacebookCustomConnectInterceptor implements ConnectInterceptor<Facebook> {

    private final Logger log = LoggerFactory.getLogger(FacebookCustomConnectInterceptor.class);

    @Inject
    SocialService socialService;

    @Inject
    CustomSocialUsersConnectionRepository customSocialUsersConnectionRepository;

    @Inject
    SocialUserConnectionRepository socialUserConnectionRepository;

    @Override
    public void preConnect(ConnectionFactory<Facebook> connectionFactory, MultiValueMap<String, String> valueMap, WebRequest request) {
        // events before connection
    }
    @Override
    public void postConnect(Connection<Facebook> connection, WebRequest request) {
        UserProfile userProfile = connection.fetchUserProfile();

        //tu jest problem
        socialService.loginOrCreateFacebookUser(userProfile);

    }
}
