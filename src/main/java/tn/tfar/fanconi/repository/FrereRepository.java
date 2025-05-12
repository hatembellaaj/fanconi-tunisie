package tn.tfar.fanconi.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tn.tfar.fanconi.domain.Frere;

/**
 * Spring Data JPA repository for the Frere entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FrereRepository extends JpaRepository<Frere, Long> {}
