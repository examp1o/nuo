package io.github.jhipster.application.service.impl;

import io.github.jhipster.application.service.CrowdfundingWalletService;
import io.github.jhipster.application.domain.CrowdfundingWallet;
import io.github.jhipster.application.repository.CrowdfundingWalletRepository;
import io.github.jhipster.application.repository.search.CrowdfundingWalletSearchRepository;
import io.github.jhipster.application.service.dto.CrowdfundingWalletDTO;
import io.github.jhipster.application.service.mapper.CrowdfundingWalletMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing CrowdfundingWallet.
 */
@Service
@Transactional
public class CrowdfundingWalletServiceImpl implements CrowdfundingWalletService {

    private final Logger log = LoggerFactory.getLogger(CrowdfundingWalletServiceImpl.class);

    private final CrowdfundingWalletRepository crowdfundingWalletRepository;

    private final CrowdfundingWalletMapper crowdfundingWalletMapper;

    private final CrowdfundingWalletSearchRepository crowdfundingWalletSearchRepository;

    public CrowdfundingWalletServiceImpl(CrowdfundingWalletRepository crowdfundingWalletRepository, CrowdfundingWalletMapper crowdfundingWalletMapper, CrowdfundingWalletSearchRepository crowdfundingWalletSearchRepository) {
        this.crowdfundingWalletRepository = crowdfundingWalletRepository;
        this.crowdfundingWalletMapper = crowdfundingWalletMapper;
        this.crowdfundingWalletSearchRepository = crowdfundingWalletSearchRepository;
    }

    /**
     * Save a crowdfundingWallet.
     *
     * @param crowdfundingWalletDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CrowdfundingWalletDTO save(CrowdfundingWalletDTO crowdfundingWalletDTO) {
        log.debug("Request to save CrowdfundingWallet : {}", crowdfundingWalletDTO);
        CrowdfundingWallet crowdfundingWallet = crowdfundingWalletMapper.toEntity(crowdfundingWalletDTO);
        crowdfundingWallet = crowdfundingWalletRepository.save(crowdfundingWallet);
        CrowdfundingWalletDTO result = crowdfundingWalletMapper.toDto(crowdfundingWallet);
        crowdfundingWalletSearchRepository.save(crowdfundingWallet);
        return result;
    }

    /**
     * Get all the crowdfundingWallets.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CrowdfundingWalletDTO> findAll() {
        log.debug("Request to get all CrowdfundingWallets");
        return crowdfundingWalletRepository.findAll().stream()
            .map(crowdfundingWalletMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one crowdfundingWallet by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public CrowdfundingWalletDTO findOne(Long id) {
        log.debug("Request to get CrowdfundingWallet : {}", id);
        CrowdfundingWallet crowdfundingWallet = crowdfundingWalletRepository.findOne(id);
        return crowdfundingWalletMapper.toDto(crowdfundingWallet);
    }

    /**
     * Delete the crowdfundingWallet by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CrowdfundingWallet : {}", id);
        crowdfundingWalletRepository.delete(id);
        crowdfundingWalletSearchRepository.delete(id);
    }

    /**
     * Search for the crowdfundingWallet corresponding to the query.
     *
     * @param query the query of the search
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<CrowdfundingWalletDTO> search(String query) {
        log.debug("Request to search CrowdfundingWallets for query {}", query);
        return StreamSupport
            .stream(crowdfundingWalletSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .map(crowdfundingWalletMapper::toDto)
            .collect(Collectors.toList());
    }
}
