package spring.rest.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by Tomasz Komoszeski on 2016-06-18.
 */

@ApiModel
@Data
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
}
