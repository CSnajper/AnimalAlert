package spring.chat.service;

import spring.chat.dto.ChatCreateDTO;
import spring.chat.dto.ChatDTO;

import java.util.List;

/**
 * Created by Tomasz Komoszeski on 2016-06-18.
 */
public interface ChatService {
    public ChatDTO sendMessage(ChatCreateDTO chatCreateDTO);
    public void deactivateMessage(Long aId);
    public List<ChatDTO> getMessagesByRecevier(String recevier);
    public List<ChatDTO> getAllMessages();


}
