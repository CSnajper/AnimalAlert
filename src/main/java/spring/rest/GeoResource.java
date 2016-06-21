package spring.rest;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jakub on 21.06.16.
 */
@RestController
@RequestMapping("/api")
public class GeoResource {
    private final Logger log = LoggerFactory.getLogger(OrganisationResource.class);
    @RequestMapping(value = "map/getAdress", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<GeocodingResult[]> getAdress(@RequestBody String adress) {
        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyBjj7yNfJBcv1bykewK9k5dCCf9skcaVvc");
        GeocodingResult[] results = new GeocodingResult[0];
        try {
            results = GeocodingApi.geocode(context,adress).await();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}
