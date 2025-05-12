package tn.tfar.fanconi.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tn.tfar.fanconi.domain.Cousin;

/**
 * Spring Data JPA repository for the Cousin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CousinRepository extends JpaRepository<Cousin, Long> {}
