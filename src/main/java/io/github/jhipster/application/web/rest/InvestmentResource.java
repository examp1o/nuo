package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Investment;

import io.github.jhipster.application.repository.InvestmentRepository;
import io.github.jhipster.application.repository.search.InvestmentSearchRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.InvestmentDTO;
import io.github.jhipster.application.service.mapper.InvestmentMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Investment.
 */
@RestController
@RequestMapping("/api")
public class InvestmentResource {

    private final Logger log = LoggerFactory.getLogger(InvestmentResource.class);

    private static final String ENTITY_NAME = "investment";

    private final InvestmentRepository investmentRepository;

    private final InvestmentMapper investmentMapper;

    private final InvestmentSearchRepository investmentSearchRepository;

    public InvestmentResource(InvestmentRepository investmentRepository, InvestmentMapper investmentMapper, InvestmentSearchRepository investmentSearchRepository) {
        this.investmentRepository = investmentRepository;
        this.investmentMapper = investmentMapper;
        this.investmentSearchRepository = investmentSearchRepository;
    }

    /**
     * POST  /investments : Create a new investment.
     *
     * @param investmentDTO the investmentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new investmentDTO, or with status 400 (Bad Request) if the investment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/investments")
    @Timed
    public ResponseEntity<InvestmentDTO> createInvestment(@RequestBody InvestmentDTO investmentDTO) throws URISyntaxException {
        log.debug("REST request to save Investment : {}", investmentDTO);
        if (investmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new investment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Investment investment = investmentMapper.toEntity(investmentDTO);
        investment = investmentRepository.save(investment);
        InvestmentDTO result = investmentMapper.toDto(investment);
        investmentSearchRepository.save(investment);
        return ResponseEntity.created(new URI("/api/investments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /investments : Updates an existing investment.
     *
     * @param investmentDTO the investmentDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated investmentDTO,
     * or with status 400 (Bad Request) if the investmentDTO is not valid,
     * or with status 500 (Internal Server Error) if the investmentDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/investments")
    @Timed
    public ResponseEntity<InvestmentDTO> updateInvestment(@RequestBody InvestmentDTO investmentDTO) throws URISyntaxException {
        log.debug("REST request to update Investment : {}", investmentDTO);
        if (investmentDTO.getId() == null) {
            return createInvestment(investmentDTO);
        }
        Investment investment = investmentMapper.toEntity(investmentDTO);
        investment = investmentRepository.save(investment);
        InvestmentDTO result = investmentMapper.toDto(investment);
        investmentSearchRepository.save(investment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, investmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /investments : get all the investments.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of investments in body
     */
    @GetMapping("/investments")
    @Timed
    public ResponseEntity<List<InvestmentDTO>> getAllInvestments(Pageable pageable) {
        log.debug("REST request to get a page of Investments");
        Page<Investment> page = investmentRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/investments");
        return new ResponseEntity<>(investmentMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /investments/:id : get the "id" investment.
     *
     * @param id the id of the investmentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the investmentDTO, or with status 404 (Not Found)
     */
    @GetMapping("/investments/{id}")
    @Timed
    public ResponseEntity<InvestmentDTO> getInvestment(@PathVariable Long id) {
        log.debug("REST request to get Investment : {}", id);
        Investment investment = investmentRepository.findOne(id);
        InvestmentDTO investmentDTO = investmentMapper.toDto(investment);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(investmentDTO));
    }

    /**
     * DELETE  /investments/:id : delete the "id" investment.
     *
     * @param id the id of the investmentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/investments/{id}")
    @Timed
    public ResponseEntity<Void> deleteInvestment(@PathVariable Long id) {
        log.debug("REST request to delete Investment : {}", id);
        investmentRepository.delete(id);
        investmentSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/investments?query=:query : search for the investment corresponding
     * to the query.
     *
     * @param query the query of the investment search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/investments")
    @Timed
    public ResponseEntity<List<InvestmentDTO>> searchInvestments(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Investments for query {}", query);
        Page<Investment> page = investmentSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/investments");
        return new ResponseEntity<>(investmentMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

}
