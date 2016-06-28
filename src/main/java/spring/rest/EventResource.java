package spring.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.domain.Event;
import spring.rest.dto.CreateEventDTO;
import spring.rest.dto.EventDTO;
import spring.service.events.EventService;

import javax.inject.Inject;


@RestController
@RequestMapping("/api")
public class EventResource {
    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    @Inject
    private EventService eventService;

    @RequestMapping(value = "/events/{name:[_'.@a-z0-9-]+}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getEventByName(@PathVariable String name){
        log.debug("REST request to get Events : {}", name);
        return new ResponseEntity<>(eventService.getEventsByName(name), HttpStatus.OK);
    }

    @RequestMapping(value = "/events/",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventDTO> createEvent(@RequestBody CreateEventDTO createEventDTO){
        return new ResponseEntity<>(eventService.createEvent(createEventDTO), HttpStatus.OK);
    }
    @RequestMapping(value = "/events/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteEvent(@PathVariable long id){
        eventService.deleteEvent(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(value = "/events/author/{username:[_'.@a-z0-9-]+}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getEventByAuthor(@PathVariable String username){
        log.debug("REST request to get Events of : {}", username);
        return new ResponseEntity<>(eventService.getEventsByAuthor(username), HttpStatus.OK);
    }
    @RequestMapping(value = "/events/{id}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventDTO> finishEvent(@PathVariable long id){
        return new ResponseEntity<>(eventService.finishEvent(id), HttpStatus.OK);
    }

}
