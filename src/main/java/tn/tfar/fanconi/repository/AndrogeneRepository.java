package tn.tfar.fanconi.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import tn.tfar.fanconi.domain.Androgene;

/**
 * Spring Data JPA repository for the Androgene entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AndrogeneRepository extends JpaRepository<Androgene, Long> {}
