package spring.rest.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Data
@NoArgsConstructor
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

    public ChatDTO(Long id, boolean acitvated, String sender, String recevier, String content, String date) {
        this.id = id;
        this.acitvated = acitvated;
        this.sender = sender;
        this.recevier = recevier;
        this.content = content;
        this.date = date;
    }
}
