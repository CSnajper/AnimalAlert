package spring.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import spring.domain.Organisation;
import spring.rest.dto.RegisterOrganisationDTO;
import spring.repository.AddressRepository;
import spring.repository.OrganisationRepository;

import javax.inject.Inject;

@Service
public class OrganisationService {

    private final Logger log = LoggerFactory.getLogger(OrganisationService.class);

    @Inject
    OrganisationRepository organisationRepository;

    @Inject
    AddressRepository AddressRepository;

    public Organisation createOrganisation(RegisterOrganisationDTO organisationDTO) {
        Organisation organisation = new Organisation();

        AddressRepository.save(organisationDTO.getAddress());

        organisation.setName(organisationDTO.getName());
        organisation.setAddress(organisationDTO.getAddress());
        organisation.setPhoneNumber(organisationDTO.getPhoneNumber());

        organisationRepository.save(organisation);

        log.debug("Created Information for Organisation: {}", organisation);

        return organisation;
    }
}
