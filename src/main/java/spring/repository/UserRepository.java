package spring.repository;

import spring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByUsername(String username);
    Optional<User> findByUsername(String username);
    Optional<User> findOneByEmail(String email);
    Optional<User> findOneById(long id);
    Optional<User> findOneByActivationKey(String activationKey);
}
