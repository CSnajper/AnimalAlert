package eu.programisci.repository;


import eu.programisci.domain.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganisationRepository extends JpaRepository<Organisation, Long> {

}
