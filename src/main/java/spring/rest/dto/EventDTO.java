package spring.rest.dto;


import lombok.Data;
import lombok.NoArgsConstructor;
import spring.domain.Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
public class EventDTO {
    private long id;
    private String name;
    private String author;
    private String description;
    private String type;
    private String date;
    private boolean isFinished;
    private String geolocalization;

    public EventDTO(long id, String name, String author, String description, String type, boolean isFinished, String date, String geolocalization) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.description = description;
        this.isFinished = isFinished;
        this.type = type;
        this.date = date;
        this.geolocalization = geolocalization;
    }

    public EventDTO(Event event){
        this(event.getId() ,event.getName(), event.getAuthor().getUsername(), event.getDescription(), event.getType().toString(),  event.isFinished(), event.getCreationDate().toString(), event.getGeolocalization().getId());
    }
}
