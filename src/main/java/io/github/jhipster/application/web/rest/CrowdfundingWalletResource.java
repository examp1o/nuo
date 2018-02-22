package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.service.CrowdfundingWalletService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.service.dto.CrowdfundingWalletDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing CrowdfundingWallet.
 */
@RestController
@RequestMapping("/api")
public class CrowdfundingWalletResource {

    private final Logger log = LoggerFactory.getLogger(CrowdfundingWalletResource.class);

    private static final String ENTITY_NAME = "crowdfundingWallet";

    private final CrowdfundingWalletService crowdfundingWalletService;

    public CrowdfundingWalletResource(CrowdfundingWalletService crowdfundingWalletService) {
        this.crowdfundingWalletService = crowdfundingWalletService;
    }

    /**
     * POST  /crowdfunding-wallets : Create a new crowdfundingWallet.
     *
     * @param crowdfundingWalletDTO the crowdfundingWalletDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new crowdfundingWalletDTO, or with status 400 (Bad Request) if the crowdfundingWallet has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/crowdfunding-wallets")
    @Timed
    public ResponseEntity<CrowdfundingWalletDTO> createCrowdfundingWallet(@RequestBody CrowdfundingWalletDTO crowdfundingWalletDTO) throws URISyntaxException {
        log.debug("REST request to save CrowdfundingWallet : {}", crowdfundingWalletDTO);
        if (crowdfundingWalletDTO.getId() != null) {
            throw new BadRequestAlertException("A new crowdfundingWallet cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CrowdfundingWalletDTO result = crowdfundingWalletService.save(crowdfundingWalletDTO);
        return ResponseEntity.created(new URI("/api/crowdfunding-wallets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /crowdfunding-wallets : Updates an existing crowdfundingWallet.
     *
     * @param crowdfundingWalletDTO the crowdfundingWalletDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated crowdfundingWalletDTO,
     * or with status 400 (Bad Request) if the crowdfundingWalletDTO is not valid,
     * or with status 500 (Internal Server Error) if the crowdfundingWalletDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/crowdfunding-wallets")
    @Timed
    public ResponseEntity<CrowdfundingWalletDTO> updateCrowdfundingWallet(@RequestBody CrowdfundingWalletDTO crowdfundingWalletDTO) throws URISyntaxException {
        log.debug("REST request to update CrowdfundingWallet : {}", crowdfundingWalletDTO);
        if (crowdfundingWalletDTO.getId() == null) {
            return createCrowdfundingWallet(crowdfundingWalletDTO);
        }
        CrowdfundingWalletDTO result = crowdfundingWalletService.save(crowdfundingWalletDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, crowdfundingWalletDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /crowdfunding-wallets : get all the crowdfundingWallets.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of crowdfundingWallets in body
     */
    @GetMapping("/crowdfunding-wallets")
    @Timed
    public List<CrowdfundingWalletDTO> getAllCrowdfundingWallets() {
        log.debug("REST request to get all CrowdfundingWallets");
        return crowdfundingWalletService.findAll();
        }

    /**
     * GET  /crowdfunding-wallets/:id : get the "id" crowdfundingWallet.
     *
     * @param id the id of the crowdfundingWalletDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the crowdfundingWalletDTO, or with status 404 (Not Found)
     */
    @GetMapping("/crowdfunding-wallets/{id}")
    @Timed
    public ResponseEntity<CrowdfundingWalletDTO> getCrowdfundingWallet(@PathVariable Long id) {
        log.debug("REST request to get CrowdfundingWallet : {}", id);
        CrowdfundingWalletDTO crowdfundingWalletDTO = crowdfundingWalletService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(crowdfundingWalletDTO));
    }

    /**
     * DELETE  /crowdfunding-wallets/:id : delete the "id" crowdfundingWallet.
     *
     * @param id the id of the crowdfundingWalletDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/crowdfunding-wallets/{id}")
    @Timed
    public ResponseEntity<Void> deleteCrowdfundingWallet(@PathVariable Long id) {
        log.debug("REST request to delete CrowdfundingWallet : {}", id);
        crowdfundingWalletService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/crowdfunding-wallets?query=:query : search for the crowdfundingWallet corresponding
     * to the query.
     *
     * @param query the query of the crowdfundingWallet search
     * @return the result of the search
     */
    @GetMapping("/_search/crowdfunding-wallets")
    @Timed
    public List<CrowdfundingWalletDTO> searchCrowdfundingWallets(@RequestParam String query) {
        log.debug("REST request to search CrowdfundingWallets for query {}", query);
        return crowdfundingWalletService.search(query);
    }

}
