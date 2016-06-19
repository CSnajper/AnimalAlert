package spring.service.chatservice;

import spring.rest.dto.ChatCreateDTO;
import spring.rest.dto.ChatDTO;

import java.util.List;

/**
 * Created by Tomasz Komoszeski on 2016-06-18.
 */
public interface ChatService {
    ChatDTO sendMessage(ChatCreateDTO chatCreateDTO);
    void deactivateMessage(Long aId);
    List<ChatDTO> getMessagesByRecevier(String recevier);
    List<ChatDTO> getAllMessages();


}
