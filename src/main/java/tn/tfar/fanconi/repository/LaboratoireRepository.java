package tn.tfar.fanconi.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tn.tfar.fanconi.domain.Laboratoire;

/**
 * Spring Data JPA repository for the Laboratoire entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LaboratoireRepository extends JpaRepository<Laboratoire, Integer> {}
