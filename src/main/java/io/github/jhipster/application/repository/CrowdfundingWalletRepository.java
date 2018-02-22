package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.CrowdfundingWallet;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the CrowdfundingWallet entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CrowdfundingWalletRepository extends JpaRepository<CrowdfundingWallet, Long> {

}
