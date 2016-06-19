package spring.chat.service;

import spring.chat.dto.ChatCreateDTO;
import spring.chat.dto.ChatDTO;

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
