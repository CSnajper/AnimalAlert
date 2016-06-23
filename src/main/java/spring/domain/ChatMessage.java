package spring.domain;

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
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDateMessage() {
        return dateMessage;
    }

    public void setDateMessage(Date dateMessage) {
        this.dateMessage = dateMessage;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getRecevier() {
        return recevier;
    }

    public void setRecevier(User recevier) {
        this.recevier = recevier;
    }
}
