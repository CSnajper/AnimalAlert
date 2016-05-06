package spring.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import spring.domain.Organisation;
import spring.dto.RegisterOrganisationDTO;
import spring.repository.AdressRepository;
import spring.repository.OrganisationRepository;

import javax.inject.Inject;

@Service
public class OrganisationService {

    private final Logger log = LoggerFactory.getLogger(OrganisationService.class);

    @Inject
    OrganisationRepository organisationRepository;

    @Inject
    AdressRepository adressRepository;

    public Organisation createOrganisation(RegisterOrganisationDTO organisationDTO) {
        Organisation organisation = new Organisation();

        adressRepository.save(organisationDTO.getAdress());

        organisation.setName(organisationDTO.getName());
        organisation.setAdress(organisationDTO.getAdress());
        organisation.setPhoneNumber(organisationDTO.getPhoneNumber());

        organisationRepository.save(organisation);

        log.debug("Created Information for Organisation: {}", organisation);

        return organisation;
    }
}
