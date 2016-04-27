package spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import spring.domain.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
