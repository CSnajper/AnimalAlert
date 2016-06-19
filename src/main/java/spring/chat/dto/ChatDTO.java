package spring.chat.dto;

import io.swagger.annotations.ApiModel;
import spring.domain.ChatMessage;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Tomasz Komoszeski on 2016-06-19.
 */

@ApiModel
public class ChatDTO implements Serializable {
    private Long id;
    private boolean acitvated;
    private String sender;

    @Pattern(regexp = "^[a-z0-9]*$")
    @NotNull
    @Size(min = 1, max = 50)
    private String recevier;

    @Pattern(regexp = "^[a-z0-9]*$")
    @NotNull
    @Size(min = 1, max = 50)
    private String content;
    private String date;

    public ChatDTO() {
    }

    public ChatDTO(Long id, boolean acitvated, String sender, String recevier, String content, String date) {
        this.id = id;
        this.acitvated = acitvated;
        this.sender = sender;
        this.recevier = recevier;
        this.content = content;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAcitvated() {
        return acitvated;
    }

    public void setAcitvated(boolean acitvated) {
        this.acitvated = acitvated;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecevier() {
        return recevier;
    }

    public void setRecevier(String recevier) {
        this.recevier = recevier;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
