package spring.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class KeyAndPasswordDTO {

    private String key;
    private String newPassword;
}
