package spring.service.geolocalisation;

import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import java.util.List;

/**
 * Created by jakub on 17.06.16.
 */
public interface IGeoService {
    List<GeocodingResult> getLocationByAdress(String pAdress);
    GeocodingResult getLocationByPlaceId(String pPlaceId);
    List<GeocodingResult> getLocationByLatLng(LatLng pCoordinates);
}
