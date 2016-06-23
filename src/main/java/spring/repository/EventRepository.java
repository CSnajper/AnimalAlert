package spring.repository;

import spring.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {
    Optional<Event> findOneByName(String name);
    Optional<Event> findOneById(long id);
    List<Event> findByName(String name);
    List<Event> findByAuthor(String author);
}
