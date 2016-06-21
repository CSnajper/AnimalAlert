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

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    private double lat;
    private double lng;

    private Double accuracy;

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