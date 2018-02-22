package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.Bulletin;

import io.github.jhipster.application.repository.BulletinRepository;
import io.github.jhipster.application.repository.search.BulletinSearchRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.BulletinDTO;
import io.github.jhipster.application.service.mapper.BulletinMapper;
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
 * REST controller for managing Bulletin.
 */
@RestController
@RequestMapping("/api")
public class BulletinResource {

    private final Logger log = LoggerFactory.getLogger(BulletinResource.class);

    private static final String ENTITY_NAME = "bulletin";

    private final BulletinRepository bulletinRepository;

    private final BulletinMapper bulletinMapper;

    private final BulletinSearchRepository bulletinSearchRepository;

    public BulletinResource(BulletinRepository bulletinRepository, BulletinMapper bulletinMapper, BulletinSearchRepository bulletinSearchRepository) {
        this.bulletinRepository = bulletinRepository;
        this.bulletinMapper = bulletinMapper;
        this.bulletinSearchRepository = bulletinSearchRepository;
    }

    /**
     * POST  /bulletins : Create a new bulletin.
     *
     * @param bulletinDTO the bulletinDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new bulletinDTO, or with status 400 (Bad Request) if the bulletin has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/bulletins")
    @Timed
    public ResponseEntity<BulletinDTO> createBulletin(@RequestBody BulletinDTO bulletinDTO) throws URISyntaxException {
        log.debug("REST request to save Bulletin : {}", bulletinDTO);
        if (bulletinDTO.getId() != null) {
            throw new BadRequestAlertException("A new bulletin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Bulletin bulletin = bulletinMapper.toEntity(bulletinDTO);
        bulletin = bulletinRepository.save(bulletin);
        BulletinDTO result = bulletinMapper.toDto(bulletin);
        bulletinSearchRepository.save(bulletin);
        return ResponseEntity.created(new URI("/api/bulletins/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /bulletins : Updates an existing bulletin.
     *
     * @param bulletinDTO the bulletinDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated bulletinDTO,
     * or with status 400 (Bad Request) if the bulletinDTO is not valid,
     * or with status 500 (Internal Server Error) if the bulletinDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/bulletins")
    @Timed
    public ResponseEntity<BulletinDTO> updateBulletin(@RequestBody BulletinDTO bulletinDTO) throws URISyntaxException {
        log.debug("REST request to update Bulletin : {}", bulletinDTO);
        if (bulletinDTO.getId() == null) {
            return createBulletin(bulletinDTO);
        }
        Bulletin bulletin = bulletinMapper.toEntity(bulletinDTO);
        bulletin = bulletinRepository.save(bulletin);
        BulletinDTO result = bulletinMapper.toDto(bulletin);
        bulletinSearchRepository.save(bulletin);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, bulletinDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /bulletins : get all the bulletins.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of bulletins in body
     */
    @GetMapping("/bulletins")
    @Timed
    public ResponseEntity<List<BulletinDTO>> getAllBulletins(Pageable pageable) {
        log.debug("REST request to get a page of Bulletins");
        Page<Bulletin> page = bulletinRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/bulletins");
        return new ResponseEntity<>(bulletinMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /bulletins/:id : get the "id" bulletin.
     *
     * @param id the id of the bulletinDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the bulletinDTO, or with status 404 (Not Found)
     */
    @GetMapping("/bulletins/{id}")
    @Timed
    public ResponseEntity<BulletinDTO> getBulletin(@PathVariable Long id) {
        log.debug("REST request to get Bulletin : {}", id);
        Bulletin bulletin = bulletinRepository.findOne(id);
        BulletinDTO bulletinDTO = bulletinMapper.toDto(bulletin);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(bulletinDTO));
    }

    /**
     * DELETE  /bulletins/:id : delete the "id" bulletin.
     *
     * @param id the id of the bulletinDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/bulletins/{id}")
    @Timed
    public ResponseEntity<Void> deleteBulletin(@PathVariable Long id) {
        log.debug("REST request to delete Bulletin : {}", id);
        bulletinRepository.delete(id);
        bulletinSearchRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/bulletins?query=:query : search for the bulletin corresponding
     * to the query.
     *
     * @param query the query of the bulletin search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/bulletins")
    @Timed
    public ResponseEntity<List<BulletinDTO>> searchBulletins(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of Bulletins for query {}", query);
        Page<Bulletin> page = bulletinSearchRepository.search(queryStringQuery(query), pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/bulletins");
        return new ResponseEntity<>(bulletinMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

}
