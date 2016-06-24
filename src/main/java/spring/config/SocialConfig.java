package spring.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurer;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import spring.repository.SocialUserConnectionRepository;
import spring.repository.social.CustomSocialUsersConnectionRepository;
import spring.security.social.FacebookCustomConnectInterceptor;

import javax.inject.Inject;

@Configuration
@EnableSocial
@ComponentScan("spring.repository")
public class SocialConfig implements SocialConfigurer {

    private final Logger log = LoggerFactory.getLogger(SocialConfig.class);

    @Inject
    Environment environment;

    @Inject
    ConnectionRepository customSocialConnectionRepository;

    @Inject
    SocialUserConnectionRepository socialUserConnectionRepository;

    @Bean
    public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator) {
        ConnectController controller = new ConnectController(connectionFactoryLocator, customSocialConnectionRepository);
        controller.addInterceptor(new FacebookCustomConnectInterceptor());
        return controller;
    }

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
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        return new CustomSocialUsersConnectionRepository(socialUserConnectionRepository, connectionFactoryLocator);
    }
}
