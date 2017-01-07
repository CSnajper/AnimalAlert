package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.domain.ChatMessage;

import java.util.List;

/**
 * Created by Tomasz Komoszeski on 2016-06-18.
 */
public interface ChatRepository extends JpaRepository<ChatMessage, Long> {
    @Query("SELECT message FROM  ChatMessage message WHERE message.sender.username =?1 AND message.receiver.username =?2 AND message.activated = ?3")
    List<ChatMessage> findAllChatMessagesBySenderAndRecevier(String sender, String receiver,boolean activated);

    @Query("SELECT message FROM ChatMessage message WHERE message.sender.username = ?1 AND message.activated = ?2")
    List<ChatMessage> findAllChatMessagesBySender(String sender, boolean activated);
}
