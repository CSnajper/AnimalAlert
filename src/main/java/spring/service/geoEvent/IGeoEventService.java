package spring.service.geoEvent;

import spring.domain.Event;
import spring.domain.Organisation;
import spring.domain.User;
import spring.domain.geo.Geolocalization;

import java.util.Set;

/**
 * Created by jakub on 29.06.16.
 */
public interface IGeoEventService {
    Set<Organisation> getLocalInstitutions(Geolocalization geolocalization);
    Set<User> getLocalPrivateUsers(Geolocalization geolocalization);
    Set<Event> getEventFromLocation(Geolocalization geolocalization);
}
