package spring.rest.dto.geo;

import com.google.maps.model.Distance;

import java.io.Serializable;

/**
 * Created by jakub on 22.06.16.
 */
public class DistanceDTO implements Serializable{
    private String humanReadable;
    private Long inMeters;

    public DistanceDTO() {
    }
    public DistanceDTO(Distance distance) {
        this.setHumanReadable(distance.humanReadable);
        this.setInMeters(distance.inMeters);
    }

    public DistanceDTO(String humanReadable, Long inMeters) {
        this.humanReadable = humanReadable;
        this.inMeters = inMeters;
    }

    public String getHumanReadable() {
        return humanReadable;
    }

    public void setHumanReadable(String humanReadable) {
        this.humanReadable = humanReadable;
    }

    public Long getInMeters() {
        return inMeters;
    }

    public void setInMeters(Long inMeters) {
        this.inMeters = inMeters;
    }


}
