package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.domain.Event;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findOneByName(String name);
    Optional<Event> findOneById(long id);
    List<Event> findByName(String name);
    List<Event> findByAuthor(String author);
    @Query("select e from Event e where e.geolocalization.id=?1")
    Set<Event> findByLocation(String id);
    @Query("select e from Event e where e.geolocalization.lat=?1 and e.geolocalization.lng=?2")
    Set<Event> findByLocation(Double lat, Double lng);
}
