package eu.programisci.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by CHUCKNORRIS on 2016-04-24.
 */
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*@Inject
    private UserDetailsService userDetailsService;

    @Inject
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService);
                //.passwordEncoder(passwordEncoder());
    }*/

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().permitAll();

    }
}
