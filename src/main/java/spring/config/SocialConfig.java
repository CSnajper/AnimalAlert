package spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import spring.repository.SocialUserConnectionRepository;
import spring.repository.social.CustomSocialUsersConnectionRepository;
import spring.security.social.FacebookCustomConnectInterceptor;
import spring.security.social.SimpleSignInAdapter;

import javax.inject.Inject;

@Configuration
@EnableSocial
public class SocialConfig implements SocialConfigurer {

    private final Logger log = LoggerFactory.getLogger(SocialConfig.class);

    @Inject
    Environment environment;

    @Inject
    ConnectionRepository customSocialConnectionRepository;

    @Inject
    SocialUserConnectionRepository socialUserConnectionRepository;

    @Inject
    ConnectionFactoryLocator connectionFactoryLocator;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer connectionFactoryConfigurer, Environment environment) {
        // Facebook configuration
        String facebookClientId = environment.getProperty("spring.social.facebook.clientId");
        String facebookClientSecret = environment.getProperty("spring.social.facebook.clientSecret");
        if (facebookClientId != null && facebookClientSecret != null) {
            log.debug("Configuration FacebookConnectionFactory");
            connectionFactoryConfigurer.addConnectionFactory(
                    new FacebookConnectionFactory(
                            facebookClientId,
                            facebookClientSecret
                    )
            );
        } else {
            log.error("Cannot configure FacebookConnectionFactory id or secret null");
        }
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new AuthenticationNameUserIdSource();
    }

    @Override
    @Scope(value="singleton", proxyMode= ScopedProxyMode.INTERFACES)
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new CustomSocialUsersConnectionRepository(socialUserConnectionRepository, connectionFactoryLocator);
    }

    @Bean
    public ProviderSignInController providerSignInController(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository usersConnectionRepository) throws Exception {
        ProviderSignInController providerSignInController = new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository, new SimpleSignInAdapter());
        providerSignInController.setSignUpUrl("/social/signup");
        return providerSignInController;
    }

    @Bean
    public ProviderSignInUtils getProviderSignInUtils(ConnectionFactoryLocator connectionFactoryLocator, UsersConnectionRepository usersConnectionRepository) {
        return new ProviderSignInUtils(connectionFactoryLocator, usersConnectionRepository);
    }
}
