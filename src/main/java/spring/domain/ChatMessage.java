package spring.domain;

import lombok.Data;
import spring.domain.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.util.Date;


/**
 * Created by Tomasz Komoszeski on 2016-06-18.
 */

@Entity
@Data
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name ="message_date_time")
    private Date dateMessage;

    @Column(name="active", nullable = false)
    private boolean activated;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private User sender;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recevier_id", referencedColumnName = "id")
    private User recevier;

    @Column(name = "message_content")
    private String content;
}
