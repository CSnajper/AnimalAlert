package spring.domain.geo;

import com.google.maps.model.LatLng;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by jakub on 21.06.16.
 */
@Entity
public class Geolocalization implements Serializable{
    @Id
    private String id;
    private Double lat;
    private Double lng;
    private Double accuracy;

    public double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Geolocalization() {
    }

    public Geolocalization(String id, LatLng location, Double accuracy) {
        this.id = id;
        this.lat = location.lat;
        this.lng = location.lng;
        this.accuracy = accuracy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LatLng getLocation() {
        return new LatLng(this.lat,this.lng);
    }

    public void setLocation(LatLng location) {
        this.lat = location.lat;
        this.lng = location.lng;
    }

    public Double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Double accuracy) {
        this.accuracy = accuracy;
    }
}