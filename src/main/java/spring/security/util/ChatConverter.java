package spring.security.util;

import lombok.experimental.UtilityClass;
import spring.rest.dto.ChatDTO;
import spring.domain.ChatMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomasz Komoszeski on 2016-06-19.
 */
@UtilityClass
public class ChatConverter {
    public static ChatDTO chatMessageToChatDTOConverter(ChatMessage aChatMessage){
        return aChatMessage == null ? null  :
                new ChatDTO(
                        aChatMessage.getId(),
                        aChatMessage.isActivated(),
                        aChatMessage.getSender().getUsername(),
                        aChatMessage.getRecevier().getUsername(),
                        aChatMessage.getContent(),aChatMessage.getDateMessage().toString()
                );
    }

    public static List<ChatDTO> chatMessageListToChatDTOListConverter(List<ChatMessage> aChatMessages){
        List<ChatDTO> listOfChatDTO = new ArrayList<>();
        for(ChatMessage chatMessage : aChatMessages){
           listOfChatDTO.add(chatMessageToChatDTOConverter(chatMessage));
        }
        return listOfChatDTO;
    }
}
