package spring.service.geoEvent.impl;

import org.springframework.beans.factory.annotation.Autowired;
import spring.domain.Organisation;
import spring.domain.User;
import spring.domain.geo.Geolocalization;
import spring.repository.EventRepository;
import spring.repository.OrganisationRepository;
import spring.repository.UserRepository;
import spring.service.geoEvent.IGeoEventService;

import java.util.Set;

/**
 * Created by jakub on 29.06.16.
 */
public class IGeoEventServiceImpl implements IGeoEventService {
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private OrganisationRepository organisationRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Set<Organisation> getLocalInstitutions(Geolocalization geolocalization) {
        Set<Organisation> allInLocation = organisationRepository.findAllInLocationByPlaceId(geolocalization.getId());
        return allInLocation;
    }

    @Override
    public Set<User> getLocalPrivateUsers(Geolocalization geolocalization) {
        Set<User> allInLocation = userRepository.findAllInLocationByPlaceId(geolocalization.getId());
        return allInLocation;
    }
}
