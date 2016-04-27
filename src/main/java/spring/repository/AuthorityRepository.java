package spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import spring.domain.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {

}
