package spring.service.chat.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.rest.dto.chat.CreateChatMessageDTO;
import spring.rest.dto.chat.ChatDTO;
import spring.service.chat.ChatService;
import spring.service.util.ChatConverter;
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
    public ChatDTO sendMessage(CreateChatMessageDTO aCreateChatMessageDTO) {
        ChatMessage message = new ChatMessage();

        User sender = userRepository.findByUsername(SecurityUtils.getCurrentUserLogin()).get();
        User receiver = userRepository.findByUsername(aCreateChatMessageDTO.getReceiver()).get();

        message.setActivated(true);
        message.setDateMessage(new Date());
        message.setContent(aCreateChatMessageDTO.getContent());
        message.setReceiver(receiver);
        message.setSender(sender);

        log.debug("Sending a chat message to" + aCreateChatMessageDTO.getReceiver()
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
    public List<ChatDTO> getMessagesByReceiver(String aReceiver) {
        return ChatConverter.chatMessageListToChatDTOListConverter(
                chatRepository.findAllChatMessagesBySenderAndRecevier(SecurityUtils.getCurrentUserLogin(), aReceiver,true));
    }

    @Override
    public List<ChatDTO> getAllMessages() {
        return ChatConverter.chatMessageListToChatDTOListConverter(chatRepository.findAllChatMessagesBySender(SecurityUtils.getCurrentUserLogin(),true));
    }
}
