package spring.rest.dto.geo;

import com.google.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by jakub on 22.06.16.
 */
public class LatLngDTO implements Serializable {
    private Double lat;
    private Double lng;

    public Double getLat() {
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

    public LatLngDTO() {
    }

    public LatLngDTO(LatLng coordinat) {
        this.lat = coordinat.lat;
        this.lng = coordinat.lng;
    }

    public LatLngDTO(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public LatLng getLatLong(){
        return new LatLng(this.lat,this.lng);
    }
}
