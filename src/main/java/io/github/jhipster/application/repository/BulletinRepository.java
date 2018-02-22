package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Bulletin;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Bulletin entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BulletinRepository extends JpaRepository<Bulletin, Long> {

}
