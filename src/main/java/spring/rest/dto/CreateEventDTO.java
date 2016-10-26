package spring.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateEventDTO {
    private String name;
    private String description;
    private String type;
    private String geolocalization;

    public CreateEventDTO(String name, String description, String type, String geolocalization) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.geolocalization = geolocalization;
    }
}
