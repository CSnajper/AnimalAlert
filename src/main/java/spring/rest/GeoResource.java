package spring.rest;

import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.rest.dto.geo.CoordinatsDTO;
import spring.service.geolocalisation.IGeoService;

import java.util.List;

/**
 * Created by jakub on 21.06.16.
 */
@RestController
@RequestMapping("/api")
public class GeoResource {
    @Autowired
    IGeoService iGeoService;
    private final Logger log = LoggerFactory.getLogger(OrganisationResource.class);
    @RequestMapping(value = "map/getLocationByAdress", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<GeocodingResult>> getLocationByAdress(@RequestBody String adress) {

        return new ResponseEntity<>(iGeoService.getLocationByAdress(adress), HttpStatus.OK);
    }
    @RequestMapping(value = "map/getLocationByCoordinats", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<GeocodingResult>> getLocationByCoordinats(@RequestBody LatLng coordinats) {

        return new ResponseEntity<>(iGeoService.getLocationByLatLng(coordinats), HttpStatus.OK);
    }
    @RequestMapping(value = "map/getLocationByPlaceId", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<GeocodingResult>> getLocationByPlaceId(@RequestBody String placeId) {

        return new ResponseEntity<>(iGeoService.getLocationByPlaceId(placeId), HttpStatus.OK);
    }
    @RequestMapping(value = "map/getDistance", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<DistanceMatrix> getDistance(@RequestBody CoordinatsDTO coordinatsDTO) {
        DistanceMatrix distance = null;
        try {
            distance = iGeoService.getDistance(coordinatsDTO.getStart().getLatLong(),coordinatsDTO.getFinish().getLatLong());
        } catch (Exception e) {
            e.printStackTrace();
        }
            return new ResponseEntity<>(distance, HttpStatus.OK);
    }
}
