package spring.service.events.Impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import spring.domain.Event;
import spring.repository.EventRepository;
import spring.repository.UserRepository;
import spring.rest.dto.CreateEventDTO;
import spring.rest.dto.EventDTO;
import spring.security.util.SecurityUtils;
import spring.service.events.EventService;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService{

    private final Logger log = LoggerFactory.getLogger(EventServiceImpl.class);

    @Inject
    UserRepository userRepository;

    @Inject
    EventRepository eventRepository;

    @Override
    public EventDTO createEvent(CreateEventDTO createEventDTO) {
        Event event = new Event();
        event.setAuthor(userRepository.findOneByUsername(SecurityUtils.getCurrentUserLogin()).get());
        event.setDescription(createEventDTO.getDescription());
        event.setCreationDate(new Date());
        event.setFinished(false);
        event.setName(createEventDTO.getName());
        event.setType(Event.Type.valueOf(createEventDTO.getType()));
        eventRepository.save(event);
        return new EventDTO(event);
    }

    @Override
    public void deleteEvent(long id) {
        eventRepository.findOneById(id).ifPresent(e -> {
            eventRepository.delete(e);
            log.debug("Deleted Event: {}", e);
        });
    }

    @Override
    public EventDTO finishEvent(long id) {
        return new EventDTO(eventRepository.findOneById(id).map(e -> {
            e.setFinished(true);
            eventRepository.save(e);
            log.debug("Finished Event: {}", e);
            return e;}).get());

    }

    @Override
    public List<EventDTO> getAllEvents() {
        return eventRepository.findAll().stream().map(EventDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<EventDTO> getEventsByAuthor(String author) {
        return eventRepository.findByAuthor(author).stream().map(EventDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<EventDTO> getEventsByName(String author) {
        return eventRepository.findByName(author).stream().map(EventDTO::new).collect(Collectors.toList());
    }
}
