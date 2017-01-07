package spring.rest;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.rest.dto.chat.CreateChatMessageDTO;
import spring.rest.dto.chat.ChatDTO;
import spring.service.chat.ChatService;

import java.util.List;

/**
 * Created by Tomasz Komoszeski on 2016-06-18.
 */

@RestController
@RequestMapping("/api")
public class ChatResource {

    private final Logger log = LoggerFactory.getLogger(ChatResource.class);

    @Autowired
    ChatService iChatService;

    @RequestMapping(value = "/chat",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ChatDTO> sendMessage(@RequestBody CreateChatMessageDTO aCreateChatMessageDTO){
        return new ResponseEntity<>(iChatService.sendMessage(aCreateChatMessageDTO),HttpStatus.OK);
    }

    @RequestMapping(value = "/chat/{recevier:[_'.@a-z0-9-]+}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<ChatDTO>> getMyMessagesTo(@PathVariable("recevier") String aRecevier){
        return new ResponseEntity<>(iChatService.getMessagesByReceiver(aRecevier),HttpStatus.OK);
    }

    @RequestMapping(value = "/chat",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<ChatDTO>> getAllMyMessages(){
        return new ResponseEntity<>(iChatService.getAllMessages(),HttpStatus.OK);
    }

    @RequestMapping(value = "/chat/{id}",
            method = RequestMethod.POST
    )
    public ResponseEntity<Void> deactivateMessage(@PathVariable("id") Long aId){
        iChatService.deactivateMessage(aId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
