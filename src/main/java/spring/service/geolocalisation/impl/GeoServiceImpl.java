package spring.service.geolocalisation.impl;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import spring.domain.geo.Geolocalization;
import spring.repository.GeoRepository;
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
    @Qualifier("geoRepository")
    @Autowired
    private GeoRepository geoRepository;
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
    @Override
    public DistanceMatrix getDistance(LatLng a,LatLng b) {
        DistanceMatrix distanceMatrix = null;
        try {
            distanceMatrix = DistanceMatrixApi.newRequest(context).origins(a).destinations(b).await();
        }catch (Exception e){
            e.printStackTrace();
        }
        return distanceMatrix;
    }

    @Override
    public Geolocalization getLocatationFromSystem(String geolocalization) {
        return geoRepository.findOne(geolocalization);
    }

}
