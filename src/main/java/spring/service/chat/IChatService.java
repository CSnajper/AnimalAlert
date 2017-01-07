package spring.service.chat;

import spring.rest.dto.chat.CreateChatMessageDTO;
import spring.rest.dto.chat.ChatDTO;

import java.util.List;

/**
 * Created by Tomasz Komoszeski on 2016-06-18.
 */
public interface IChatService {
    ChatDTO sendMessage(CreateChatMessageDTO aCreateChatMessageDTO);
    void deactivateMessage(Long aId);
    List<ChatDTO> getMessagesByReceiver(String aReceiver);
    List<ChatDTO> getAllMessages();


}
