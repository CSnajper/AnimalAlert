package spring.chat.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.chat.dto.ChatCreateDTO;
import spring.chat.dto.ChatDTO;
import spring.chat.service.ChatService;
import spring.chat.util.ChatConverter;
import spring.domain.ChatMessage;
import spring.domain.User;
import spring.repository.ChatRepository;
import spring.repository.UserRepository;
import spring.security.util.SecurityUtils;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * Created by Tomasz Komoszeski on 2016-06-18.
 */

@Service
@Transactional
public class ChatServiceImpl implements ChatService {

    private final Logger log = LoggerFactory.getLogger(ChatServiceImpl.class);

    @Inject
    UserRepository userRepository;

    @Inject
    ChatRepository chatRepository;

    @Override
    public ChatDTO sendMessage(ChatCreateDTO chatCreateDTO) {
        ChatMessage message = new ChatMessage();

        User sender = userRepository.findByUsername(SecurityUtils.getCurrentUserLogin()).get();
        User recevier = userRepository.findByUsername(chatCreateDTO.getRecevier()).get();

        message.setActivated(true);
        message.setDateMessage(new Date());
        message.setContent(chatCreateDTO.getContent());
        message.setRecevier(recevier);
        message.setSender(sender);

        log.debug("Sending a chat message to" + chatCreateDTO.getRecevier()
        + "from" + SecurityUtils.getCurrentUserLogin(),message);

        return ChatConverter.chatMessageToChatDTOConverter(chatRepository.save(message));
    }

    @Override
    public void deactivateMessage(Long aId) {
        ChatMessage message = chatRepository.findOne(aId);
        message.setActivated(false);
        chatRepository.save(message);
    }

    @Override
    public List<ChatDTO> getMessagesByRecevier(String recevier) {
        return ChatConverter.chatMessageListToChatDTOListConverter(
                chatRepository.findAllChatMessagesBySenderAndRecevier(SecurityUtils.getCurrentUserLogin(),recevier));
    }

    @Override
    public List<ChatDTO> getAllMessages() {
        return ChatConverter.chatMessageListToChatDTOListConverter(chatRepository.findAllChatMessagesBySender(SecurityUtils.getCurrentUserLogin()));
    }
}
