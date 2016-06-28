package spring.rest.dto;


import spring.domain.Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventDTO {
    private long id;
    private String name;
    private String author;
    private String description;
    private String type;
    private String date;
    private boolean isFinished;
    private String geolocalization;

    public EventDTO() {
    }

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGeolocalization() {
        return geolocalization;
    }

    public void setGeolocalization(String geolocalization) {
        this.geolocalization = geolocalization;
    }
}
