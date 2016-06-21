package spring.rest;

import com.google.maps.model.GeocodingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @RequestMapping(value = "map/getAdress", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<List<GeocodingResult>> getAdresses(@RequestBody String adress) {

        return new ResponseEntity<>(iGeoService.getLocationByAdress(adress), HttpStatus.OK);
    }
}
