package spring.rest;

import io.swagger.annotations.*;
import io.swagger.models.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import spring.domain.User;
import spring.dto.UserDTO;
import spring.repository.UserRepository;
import spring.service.UserService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Inject
    UserRepository userRepository;

    @Inject
    UserService userService;

    /**
     * GET  /users/:username : get the "username" user.
     *
     * @param username the login of the user to find
     * @return the ResponseEntity with status 200 (OK) and with body the "login" user, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/users/{username:[_'.@a-z0-9-]+}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        log.debug("REST request to get User : {}", username);
        return userRepository.findOneByUsername(username)
                .map(UserDTO::new)
                .map(userDTO -> new ResponseEntity<>(userDTO, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * POST  /users  : Creates a new user.
     * <p>
     * Creates a new user if the login and email are not already used, and sends an
     * mail with an activation link.
     * The user needs to be activated on creation.
     * </p>
     *
     * @param userDTO the user to create
     * @param request the HTTP request
     * @return the ResponseEntity with status 201 (Created) and with body the new user, or with status 400 (Bad Request) if the login or email is already in use
     * @throws URISyntaxException if the Location URI syntaxt is incorrect
     */
    @RequestMapping(value = "/users",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO, HttpServletRequest request) throws URISyntaxException {
        log.debug("REST request to save User : {}", userDTO);
        HttpHeaders headers = new HttpHeaders();
        if (userRepository.findOneByUsername(userDTO.getUsername()).isPresent()) {
            headers.set("userManagement", "Login already in use");
            return ResponseEntity.badRequest()
                    .headers(headers)
                    .body(null);
        } else if (userRepository.findOneByEmail(userDTO.getEmail()).isPresent()) {
            headers.set("userManagement", "Email already in use");
            return ResponseEntity.badRequest()
                    .headers(headers)
                    .body(null);
        } else {
            User newUser = userService.createUser(userDTO);
            String baseUrl = request.getScheme() + // "http"
                    "://" +                                // "://"
                    request.getServerName() +              // "myhost"
                    ":" +                                  // ":"
                    request.getServerPort() +              // "80"
                    request.getContextPath();              // "/myContextPath" or "" if deployed in root context
            //mailService.sendCreationEmail(newUser, baseUrl);
            headers.set("userManagement", "A user is created with identifier " + newUser.getUsername());
            return ResponseEntity.created(new URI("/api/users/" + newUser.getUsername()))
                    .headers(headers)
                    .body(newUser);
        }
    }
}
