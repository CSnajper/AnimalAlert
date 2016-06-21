package spring.service.geolocalisation.impl;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.service.geolocalisation.IGeoService;

import java.util.Arrays;
import java.util.List;

/**
 * Created by jakub on 17.06.16.
 */
@Service
public class GeoServiceImpl implements IGeoService {
    @Autowired
    private GeoApiContext context;
    private List<GeocodingResult> results;
    @Override
    public List<GeocodingResult> getLocationByAdress(String pAdress) {
        try {
            results = Arrays.asList(GeocodingApi.geocode(context,pAdress).await());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;

    }

    @Override
    public List<GeocodingResult> getLocationByPlaceId(String pPlaceId) {
        try {
            results = Arrays.asList(GeocodingApi.newRequest(context).place(pPlaceId).await());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public List<GeocodingResult> getLocationByLatLng(LatLng pCoordinates) {
        try {
            results = Arrays.asList(GeocodingApi.reverseGeocode(context,pCoordinates).await());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
}
