package spring.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spring.domain.Organisation;
import spring.domain.User;
import spring.dto.RegisterOrganisationDTO;
import spring.dto.UserDTO;
import spring.repository.OrganisationRepository;
import spring.service.OrganisationService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/api")
public class OrganisationResource {

    private final Logger log = LoggerFactory.getLogger(OrganisationResource.class);

    @Inject
    OrganisationRepository organisationRepository;

    @Inject
    OrganisationService organisationService;


    /**
     * POST  /organisation  : Creates a new user.
     * <p>
     * Creates a new organisation
     * </p>
     *
     * @param organisationDTO the user to create
     * @param request the HTTP request
     * @return the ResponseEntity with status 201 (Created) and with body the new user, or with status 400 (Bad Request) if the login or email is already in use
     * @throws URISyntaxException if the Location URI syntaxt is incorrect
     */
    @RequestMapping(value = "/organisations",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createOrganisation(@RequestBody RegisterOrganisationDTO organisationDTO, HttpServletRequest request) throws URISyntaxException {
        log.debug("REST request to save User : {}", organisationDTO);
        HttpHeaders headers = new HttpHeaders();
        Organisation newOrganisation = organisationService.createOrganisation(organisationDTO);
        String baseUrl = request.getScheme() + // "http"
                "://" +                                // "://"
                request.getServerName() +              // "myhost"
                ":" +                                  // ":"
                request.getServerPort() +              // "80"
                request.getContextPath();              // "/myContextPath" or "" if deployed in root context
        headers.set("userManagement", "A user is created with identifier " + newOrganisation.getName());
        return ResponseEntity.created(new URI("/api/organisations/" + newOrganisation.getName()))
                .headers(headers)
                .body(newOrganisation);
    }

}
