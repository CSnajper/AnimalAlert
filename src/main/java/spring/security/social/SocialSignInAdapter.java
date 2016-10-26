package spring.security.social;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import javax.inject.Inject;

@Service
public class SocialSignInAdapter implements SignInAdapter {

    @Inject
    private UserDetailsService userDetailsService;

    public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {
        UserDetails user = userDetailsService.loadUserByUsername(userId);
        Authentication newAuth = new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
        return null;
    }
}
