package spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import spring.domain.Adress;

public interface AdressRepository extends JpaRepository<Adress, Long> {
}
