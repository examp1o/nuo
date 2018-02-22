package io.github.jhipster.application.service;

import io.github.jhipster.application.service.dto.CrowdfundingWalletDTO;
import java.util.List;

/**
 * Service Interface for managing CrowdfundingWallet.
 */
public interface CrowdfundingWalletService {

    /**
     * Save a crowdfundingWallet.
     *
     * @param crowdfundingWalletDTO the entity to save
     * @return the persisted entity
     */
    CrowdfundingWalletDTO save(CrowdfundingWalletDTO crowdfundingWalletDTO);

    /**
     * Get all the crowdfundingWallets.
     *
     * @return the list of entities
     */
    List<CrowdfundingWalletDTO> findAll();

    /**
     * Get the "id" crowdfundingWallet.
     *
     * @param id the id of the entity
     * @return the entity
     */
    CrowdfundingWalletDTO findOne(Long id);

    /**
     * Delete the "id" crowdfundingWallet.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the crowdfundingWallet corresponding to the query.
     *
     * @param query the query of the search
     * 
     * @return the list of entities
     */
    List<CrowdfundingWalletDTO> search(String query);
}
