package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NuoApp;

import io.github.jhipster.application.domain.Bulletin;
import io.github.jhipster.application.repository.BulletinRepository;
import io.github.jhipster.application.repository.search.BulletinSearchRepository;
import io.github.jhipster.application.service.dto.BulletinDTO;
import io.github.jhipster.application.service.mapper.BulletinMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BulletinResource REST controller.
 *
 * @see BulletinResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NuoApp.class)
public class BulletinResourceIntTest {

    private static final String DEFAULT_CONTENT = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT = "BBBBBBBBBB";

    private static final Long DEFAULT_USER = 1L;
    private static final Long UPDATED_USER = 2L;

    private static final Long DEFAULT_CREATE_TIME = 1L;
    private static final Long UPDATED_CREATE_TIME = 2L;

    @Autowired
    private BulletinRepository bulletinRepository;

    @Autowired
    private BulletinMapper bulletinMapper;

    @Autowired
    private BulletinSearchRepository bulletinSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBulletinMockMvc;

    private Bulletin bulletin;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BulletinResource bulletinResource = new BulletinResource(bulletinRepository, bulletinMapper, bulletinSearchRepository);
        this.restBulletinMockMvc = MockMvcBuilders.standaloneSetup(bulletinResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Bulletin createEntity(EntityManager em) {
        Bulletin bulletin = new Bulletin()
            .content(DEFAULT_CONTENT)
            .user(DEFAULT_USER)
            .createTime(DEFAULT_CREATE_TIME);
        return bulletin;
    }

    @Before
    public void initTest() {
        bulletinSearchRepository.deleteAll();
        bulletin = createEntity(em);
    }

    @Test
    @Transactional
    public void createBulletin() throws Exception {
        int databaseSizeBeforeCreate = bulletinRepository.findAll().size();

        // Create the Bulletin
        BulletinDTO bulletinDTO = bulletinMapper.toDto(bulletin);
        restBulletinMockMvc.perform(post("/api/bulletins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bulletinDTO)))
            .andExpect(status().isCreated());

        // Validate the Bulletin in the database
        List<Bulletin> bulletinList = bulletinRepository.findAll();
        assertThat(bulletinList).hasSize(databaseSizeBeforeCreate + 1);
        Bulletin testBulletin = bulletinList.get(bulletinList.size() - 1);
        assertThat(testBulletin.getContent()).isEqualTo(DEFAULT_CONTENT);
        assertThat(testBulletin.getUser()).isEqualTo(DEFAULT_USER);
        assertThat(testBulletin.getCreateTime()).isEqualTo(DEFAULT_CREATE_TIME);

        // Validate the Bulletin in Elasticsearch
        Bulletin bulletinEs = bulletinSearchRepository.findOne(testBulletin.getId());
        assertThat(bulletinEs).isEqualToIgnoringGivenFields(testBulletin);
    }

    @Test
    @Transactional
    public void createBulletinWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = bulletinRepository.findAll().size();

        // Create the Bulletin with an existing ID
        bulletin.setId(1L);
        BulletinDTO bulletinDTO = bulletinMapper.toDto(bulletin);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBulletinMockMvc.perform(post("/api/bulletins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bulletinDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Bulletin in the database
        List<Bulletin> bulletinList = bulletinRepository.findAll();
        assertThat(bulletinList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBulletins() throws Exception {
        // Initialize the database
        bulletinRepository.saveAndFlush(bulletin);

        // Get all the bulletinList
        restBulletinMockMvc.perform(get("/api/bulletins?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bulletin.getId().intValue())))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER.intValue())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.intValue())));
    }

    @Test
    @Transactional
    public void getBulletin() throws Exception {
        // Initialize the database
        bulletinRepository.saveAndFlush(bulletin);

        // Get the bulletin
        restBulletinMockMvc.perform(get("/api/bulletins/{id}", bulletin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(bulletin.getId().intValue()))
            .andExpect(jsonPath("$.content").value(DEFAULT_CONTENT.toString()))
            .andExpect(jsonPath("$.user").value(DEFAULT_USER.intValue()))
            .andExpect(jsonPath("$.createTime").value(DEFAULT_CREATE_TIME.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingBulletin() throws Exception {
        // Get the bulletin
        restBulletinMockMvc.perform(get("/api/bulletins/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBulletin() throws Exception {
        // Initialize the database
        bulletinRepository.saveAndFlush(bulletin);
        bulletinSearchRepository.save(bulletin);
        int databaseSizeBeforeUpdate = bulletinRepository.findAll().size();

        // Update the bulletin
        Bulletin updatedBulletin = bulletinRepository.findOne(bulletin.getId());
        // Disconnect from session so that the updates on updatedBulletin are not directly saved in db
        em.detach(updatedBulletin);
        updatedBulletin
            .content(UPDATED_CONTENT)
            .user(UPDATED_USER)
            .createTime(UPDATED_CREATE_TIME);
        BulletinDTO bulletinDTO = bulletinMapper.toDto(updatedBulletin);

        restBulletinMockMvc.perform(put("/api/bulletins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bulletinDTO)))
            .andExpect(status().isOk());

        // Validate the Bulletin in the database
        List<Bulletin> bulletinList = bulletinRepository.findAll();
        assertThat(bulletinList).hasSize(databaseSizeBeforeUpdate);
        Bulletin testBulletin = bulletinList.get(bulletinList.size() - 1);
        assertThat(testBulletin.getContent()).isEqualTo(UPDATED_CONTENT);
        assertThat(testBulletin.getUser()).isEqualTo(UPDATED_USER);
        assertThat(testBulletin.getCreateTime()).isEqualTo(UPDATED_CREATE_TIME);

        // Validate the Bulletin in Elasticsearch
        Bulletin bulletinEs = bulletinSearchRepository.findOne(testBulletin.getId());
        assertThat(bulletinEs).isEqualToIgnoringGivenFields(testBulletin);
    }

    @Test
    @Transactional
    public void updateNonExistingBulletin() throws Exception {
        int databaseSizeBeforeUpdate = bulletinRepository.findAll().size();

        // Create the Bulletin
        BulletinDTO bulletinDTO = bulletinMapper.toDto(bulletin);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBulletinMockMvc.perform(put("/api/bulletins")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(bulletinDTO)))
            .andExpect(status().isCreated());

        // Validate the Bulletin in the database
        List<Bulletin> bulletinList = bulletinRepository.findAll();
        assertThat(bulletinList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBulletin() throws Exception {
        // Initialize the database
        bulletinRepository.saveAndFlush(bulletin);
        bulletinSearchRepository.save(bulletin);
        int databaseSizeBeforeDelete = bulletinRepository.findAll().size();

        // Get the bulletin
        restBulletinMockMvc.perform(delete("/api/bulletins/{id}", bulletin.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean bulletinExistsInEs = bulletinSearchRepository.exists(bulletin.getId());
        assertThat(bulletinExistsInEs).isFalse();

        // Validate the database is empty
        List<Bulletin> bulletinList = bulletinRepository.findAll();
        assertThat(bulletinList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchBulletin() throws Exception {
        // Initialize the database
        bulletinRepository.saveAndFlush(bulletin);
        bulletinSearchRepository.save(bulletin);

        // Search the bulletin
        restBulletinMockMvc.perform(get("/api/_search/bulletins?query=id:" + bulletin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(bulletin.getId().intValue())))
            .andExpect(jsonPath("$.[*].content").value(hasItem(DEFAULT_CONTENT.toString())))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER.intValue())))
            .andExpect(jsonPath("$.[*].createTime").value(hasItem(DEFAULT_CREATE_TIME.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Bulletin.class);
        Bulletin bulletin1 = new Bulletin();
        bulletin1.setId(1L);
        Bulletin bulletin2 = new Bulletin();
        bulletin2.setId(bulletin1.getId());
        assertThat(bulletin1).isEqualTo(bulletin2);
        bulletin2.setId(2L);
        assertThat(bulletin1).isNotEqualTo(bulletin2);
        bulletin1.setId(null);
        assertThat(bulletin1).isNotEqualTo(bulletin2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(BulletinDTO.class);
        BulletinDTO bulletinDTO1 = new BulletinDTO();
        bulletinDTO1.setId(1L);
        BulletinDTO bulletinDTO2 = new BulletinDTO();
        assertThat(bulletinDTO1).isNotEqualTo(bulletinDTO2);
        bulletinDTO2.setId(bulletinDTO1.getId());
        assertThat(bulletinDTO1).isEqualTo(bulletinDTO2);
        bulletinDTO2.setId(2L);
        assertThat(bulletinDTO1).isNotEqualTo(bulletinDTO2);
        bulletinDTO1.setId(null);
        assertThat(bulletinDTO1).isNotEqualTo(bulletinDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(bulletinMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(bulletinMapper.fromId(null)).isNull();
    }
}
