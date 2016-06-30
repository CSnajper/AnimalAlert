package spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.domain.Organisation;

import java.util.Set;

public interface OrganisationRepository extends JpaRepository<Organisation, Long> {
@Query("select p from Organisation p where p.location.id=?1")
    Set<Organisation> findAllInLocationByPlaceId(String placeId);

}
