package spring.rest.dto;


public class CreateEventDTO {
    private String name;
    private String description;
    private String type;
    private String geolocalization;

    public CreateEventDTO() {
    }

    public CreateEventDTO(String name, String description, String type, String geolocalization) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.geolocalization = geolocalization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGeolocalization() {
        return geolocalization;
    }

    public void setGeolocalization(String geolocalization) {
        this.geolocalization = geolocalization;
    }
}
