package tn.tfar.fanconi.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tn.tfar.fanconi.domain.Cytogenetique;

/**
 * Spring Data JPA repository for the Cytogenetique entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CytogenetiqueRepository extends JpaRepository<Cytogenetique, Long> {}
