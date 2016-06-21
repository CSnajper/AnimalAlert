package spring.service.geolocalisation.impl;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.service.geolocalisation.IGeoService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jakub on 17.06.16.
 */
@Service
public class GeoServiceImpl implements IGeoService {
    @Autowired
    private GeoApiContext context;
    @Override
    public List<GeocodingResult> getLocationByAdress(String pAdress) {
        List<GeocodingResult> results = new ArrayList<>();
        try {
            results = Arrays.asList(GeocodingApi.geocode(context,pAdress).await());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;

    }

    @Override
    public GeocodingResult getLocationByPlaceId(String pPlaceId) {
        return null;
    }

    @Override
    public List<GeocodingResult> getLocationByLatLng(LatLng pCoordinates) {
        return null;
    }
}
