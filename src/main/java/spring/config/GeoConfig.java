package spring.config;

import com.google.maps.GeoApiContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jakub on 21.06.16.
 */

@Configuration
public class GeoConfig {
    @Bean
    public GeoApiContext geoApiContext(){
        GeoApiContext geoApiContext = new GeoApiContext();
        geoApiContext.setApiKey("AIzaSyBjj7yNfJBcv1bykewK9k5dCCf9skcaVvc");
        return  geoApiContext;
    }
}
