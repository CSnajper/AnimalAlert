package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.domain.geo.Geolocalization;

/**
 * Created by jakub on 29.06.16.
 */
@Repository
public interface GeoRepository extends JpaRepository<Geolocalization,String> {
}
