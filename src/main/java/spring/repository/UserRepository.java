package spring.repository;

import org.springframework.data.jpa.repository.Query;
import spring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByUsername(String username);
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.userRoles WHERE u.username = ?1")
    Optional<User> findByUsernameWithAuthorities(String username);
    Optional<User> findByUsername(String username);
    Optional<User> findOneByEmail(String email);
    Optional<User> findOneById(long id);
    Optional<User> findOneByActivationKey(String activationKey);
    Optional<User> findOneByResetKey(String key);
    @Query("select p from User p where p.citizen.id=?1")
    Set<User> findAllInLocationByPlaceId(String id);
}
