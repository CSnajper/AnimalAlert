package spring.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class UserService {

    @Inject
    PasswordEncoder passwordEncoder;

}
