package spring.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import spring.rest.dto.ChatCreateDTO;
import spring.rest.dto.ChatDTO;
import spring.service.chatservice.ChatService;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Tomasz Komoszeski on 2016-06-18.
 */

@RestController
@RequestMapping("/api")
public class ChatResource {

    private final Logger log = LoggerFactory.getLogger(ChatResource.class);

    @Inject
    ChatService chatService;

    @RequestMapping(value = "/chat",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ChatDTO> sendMessage(@RequestBody ChatCreateDTO chatCreateDTO){
        return new ResponseEntity<>(chatService.sendMessage(chatCreateDTO),HttpStatus.OK);
    }

    @RequestMapping(value = "/chat/{recevier:[_'.@a-z0-9-]+}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<ChatDTO>> getMyMessagesTo(@PathVariable("recevier") String aRecevier){
        return new ResponseEntity<>(chatService.getMessagesByRecevier(aRecevier),HttpStatus.OK);
    }

    @RequestMapping(value = "/chat/",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<ChatDTO>> getAllMyMessages(){
        return new ResponseEntity<>(chatService.getAllMessages(),HttpStatus.OK);
    }

    @RequestMapping(value = "/chat/{id}",
            method = RequestMethod.POST
    )
    public ResponseEntity<Void> deactivateMessage(@PathVariable("id") Long aId){
        chatService.deactivateMessage(aId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
