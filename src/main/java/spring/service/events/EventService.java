package spring.service.events;

import spring.domain.Event;
import spring.rest.dto.CreateEventDTO;
import spring.rest.dto.EventDTO;

import java.util.List;


public interface EventService {
    EventDTO createEvent(CreateEventDTO createEventDTO);
    void deleteEvent(long id);
    EventDTO finishEvent(long id);
    List<EventDTO> getAllEvents();
    List<EventDTO> getEventsByAuthor(String author);
    List<EventDTO> getEventsByName(String author);

}
