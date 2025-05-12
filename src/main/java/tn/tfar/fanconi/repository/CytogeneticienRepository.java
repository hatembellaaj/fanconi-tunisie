package tn.tfar.fanconi.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tn.tfar.fanconi.domain.Cytogeneticien;

/**
 * Spring Data JPA repository for the Cytogeneticien entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CytogeneticienRepository extends JpaRepository<Cytogeneticien, Long> {}
