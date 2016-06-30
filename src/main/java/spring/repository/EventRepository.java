package spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.domain.Event;

import java.util.List;
import java.util.Optional;
public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findOneByName(String name);
    Optional<Event> findOneById(long id);
    List<Event> findByName(String name);
    List<Event> findByAuthor(String author);
}
