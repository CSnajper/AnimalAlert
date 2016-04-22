package eu.programisci.repository;

import eu.programisci.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByUsername(String username);
}
