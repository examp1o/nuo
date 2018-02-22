package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Investment;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Investment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvestmentRepository extends JpaRepository<Investment, Long> {

}
