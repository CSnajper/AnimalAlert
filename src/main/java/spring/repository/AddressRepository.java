package spring.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import spring.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
