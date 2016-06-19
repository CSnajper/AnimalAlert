package spring.chat.dto;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Tomasz Komoszeski on 2016-06-18.
 */

@ApiModel
public class ChatCreateDTO implements Serializable {

    @Pattern(regexp = "^[a-z0-9]*$")
    @NotNull
    @Size(min = 1, max = 50)
    private String recevier;
    private String content;

    public ChatCreateDTO() {
    }

    public ChatCreateDTO(String recevier, String content) {
        this.recevier = recevier;
        this.content = content;
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
}
