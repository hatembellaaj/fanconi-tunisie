package tn.tfar.fanconi.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tn.tfar.fanconi.domain.Hopital;

/**
 * Spring Data JPA repository for the Hopital entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HopitalRepository extends JpaRepository<Hopital, Long> {}
