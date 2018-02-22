package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NuoApp;

import io.github.jhipster.application.domain.Investment;
import io.github.jhipster.application.repository.InvestmentRepository;
import io.github.jhipster.application.repository.search.InvestmentSearchRepository;
import io.github.jhipster.application.service.dto.InvestmentDTO;
import io.github.jhipster.application.service.mapper.InvestmentMapper;
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
 * Test class for the InvestmentResource REST controller.
 *
 * @see InvestmentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NuoApp.class)
public class InvestmentResourceIntTest {

    private static final Long DEFAULT_AMOUNT = 1L;
    private static final Long UPDATED_AMOUNT = 2L;

    private static final String DEFAULT_SOURCE_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_ADDRESS = "BBBBBBBBBB";

    private static final Long DEFAULT_INVESTMENT_TIME = 1L;
    private static final Long UPDATED_INVESTMENT_TIME = 2L;

    private static final Long DEFAULT_USER = 1L;
    private static final Long UPDATED_USER = 2L;

    @Autowired
    private InvestmentRepository investmentRepository;

    @Autowired
    private InvestmentMapper investmentMapper;

    @Autowired
    private InvestmentSearchRepository investmentSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restInvestmentMockMvc;

    private Investment investment;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final InvestmentResource investmentResource = new InvestmentResource(investmentRepository, investmentMapper, investmentSearchRepository);
        this.restInvestmentMockMvc = MockMvcBuilders.standaloneSetup(investmentResource)
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
    public static Investment createEntity(EntityManager em) {
        Investment investment = new Investment()
            .amount(DEFAULT_AMOUNT)
            .sourceAddress(DEFAULT_SOURCE_ADDRESS)
            .investmentTime(DEFAULT_INVESTMENT_TIME)
            .user(DEFAULT_USER);
        return investment;
    }

    @Before
    public void initTest() {
        investmentSearchRepository.deleteAll();
        investment = createEntity(em);
    }

    @Test
    @Transactional
    public void createInvestment() throws Exception {
        int databaseSizeBeforeCreate = investmentRepository.findAll().size();

        // Create the Investment
        InvestmentDTO investmentDTO = investmentMapper.toDto(investment);
        restInvestmentMockMvc.perform(post("/api/investments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investmentDTO)))
            .andExpect(status().isCreated());

        // Validate the Investment in the database
        List<Investment> investmentList = investmentRepository.findAll();
        assertThat(investmentList).hasSize(databaseSizeBeforeCreate + 1);
        Investment testInvestment = investmentList.get(investmentList.size() - 1);
        assertThat(testInvestment.getAmount()).isEqualTo(DEFAULT_AMOUNT);
        assertThat(testInvestment.getSourceAddress()).isEqualTo(DEFAULT_SOURCE_ADDRESS);
        assertThat(testInvestment.getInvestmentTime()).isEqualTo(DEFAULT_INVESTMENT_TIME);
        assertThat(testInvestment.getUser()).isEqualTo(DEFAULT_USER);

        // Validate the Investment in Elasticsearch
        Investment investmentEs = investmentSearchRepository.findOne(testInvestment.getId());
        assertThat(investmentEs).isEqualToIgnoringGivenFields(testInvestment);
    }

    @Test
    @Transactional
    public void createInvestmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = investmentRepository.findAll().size();

        // Create the Investment with an existing ID
        investment.setId(1L);
        InvestmentDTO investmentDTO = investmentMapper.toDto(investment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restInvestmentMockMvc.perform(post("/api/investments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Investment in the database
        List<Investment> investmentList = investmentRepository.findAll();
        assertThat(investmentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllInvestments() throws Exception {
        // Initialize the database
        investmentRepository.saveAndFlush(investment);

        // Get all the investmentList
        restInvestmentMockMvc.perform(get("/api/investments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(investment.getId().intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].sourceAddress").value(hasItem(DEFAULT_SOURCE_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].investmentTime").value(hasItem(DEFAULT_INVESTMENT_TIME.intValue())))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER.intValue())));
    }

    @Test
    @Transactional
    public void getInvestment() throws Exception {
        // Initialize the database
        investmentRepository.saveAndFlush(investment);

        // Get the investment
        restInvestmentMockMvc.perform(get("/api/investments/{id}", investment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(investment.getId().intValue()))
            .andExpect(jsonPath("$.amount").value(DEFAULT_AMOUNT.intValue()))
            .andExpect(jsonPath("$.sourceAddress").value(DEFAULT_SOURCE_ADDRESS.toString()))
            .andExpect(jsonPath("$.investmentTime").value(DEFAULT_INVESTMENT_TIME.intValue()))
            .andExpect(jsonPath("$.user").value(DEFAULT_USER.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingInvestment() throws Exception {
        // Get the investment
        restInvestmentMockMvc.perform(get("/api/investments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInvestment() throws Exception {
        // Initialize the database
        investmentRepository.saveAndFlush(investment);
        investmentSearchRepository.save(investment);
        int databaseSizeBeforeUpdate = investmentRepository.findAll().size();

        // Update the investment
        Investment updatedInvestment = investmentRepository.findOne(investment.getId());
        // Disconnect from session so that the updates on updatedInvestment are not directly saved in db
        em.detach(updatedInvestment);
        updatedInvestment
            .amount(UPDATED_AMOUNT)
            .sourceAddress(UPDATED_SOURCE_ADDRESS)
            .investmentTime(UPDATED_INVESTMENT_TIME)
            .user(UPDATED_USER);
        InvestmentDTO investmentDTO = investmentMapper.toDto(updatedInvestment);

        restInvestmentMockMvc.perform(put("/api/investments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investmentDTO)))
            .andExpect(status().isOk());

        // Validate the Investment in the database
        List<Investment> investmentList = investmentRepository.findAll();
        assertThat(investmentList).hasSize(databaseSizeBeforeUpdate);
        Investment testInvestment = investmentList.get(investmentList.size() - 1);
        assertThat(testInvestment.getAmount()).isEqualTo(UPDATED_AMOUNT);
        assertThat(testInvestment.getSourceAddress()).isEqualTo(UPDATED_SOURCE_ADDRESS);
        assertThat(testInvestment.getInvestmentTime()).isEqualTo(UPDATED_INVESTMENT_TIME);
        assertThat(testInvestment.getUser()).isEqualTo(UPDATED_USER);

        // Validate the Investment in Elasticsearch
        Investment investmentEs = investmentSearchRepository.findOne(testInvestment.getId());
        assertThat(investmentEs).isEqualToIgnoringGivenFields(testInvestment);
    }

    @Test
    @Transactional
    public void updateNonExistingInvestment() throws Exception {
        int databaseSizeBeforeUpdate = investmentRepository.findAll().size();

        // Create the Investment
        InvestmentDTO investmentDTO = investmentMapper.toDto(investment);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restInvestmentMockMvc.perform(put("/api/investments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(investmentDTO)))
            .andExpect(status().isCreated());

        // Validate the Investment in the database
        List<Investment> investmentList = investmentRepository.findAll();
        assertThat(investmentList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteInvestment() throws Exception {
        // Initialize the database
        investmentRepository.saveAndFlush(investment);
        investmentSearchRepository.save(investment);
        int databaseSizeBeforeDelete = investmentRepository.findAll().size();

        // Get the investment
        restInvestmentMockMvc.perform(delete("/api/investments/{id}", investment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean investmentExistsInEs = investmentSearchRepository.exists(investment.getId());
        assertThat(investmentExistsInEs).isFalse();

        // Validate the database is empty
        List<Investment> investmentList = investmentRepository.findAll();
        assertThat(investmentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchInvestment() throws Exception {
        // Initialize the database
        investmentRepository.saveAndFlush(investment);
        investmentSearchRepository.save(investment);

        // Search the investment
        restInvestmentMockMvc.perform(get("/api/_search/investments?query=id:" + investment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(investment.getId().intValue())))
            .andExpect(jsonPath("$.[*].amount").value(hasItem(DEFAULT_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].sourceAddress").value(hasItem(DEFAULT_SOURCE_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].investmentTime").value(hasItem(DEFAULT_INVESTMENT_TIME.intValue())))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Investment.class);
        Investment investment1 = new Investment();
        investment1.setId(1L);
        Investment investment2 = new Investment();
        investment2.setId(investment1.getId());
        assertThat(investment1).isEqualTo(investment2);
        investment2.setId(2L);
        assertThat(investment1).isNotEqualTo(investment2);
        investment1.setId(null);
        assertThat(investment1).isNotEqualTo(investment2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvestmentDTO.class);
        InvestmentDTO investmentDTO1 = new InvestmentDTO();
        investmentDTO1.setId(1L);
        InvestmentDTO investmentDTO2 = new InvestmentDTO();
        assertThat(investmentDTO1).isNotEqualTo(investmentDTO2);
        investmentDTO2.setId(investmentDTO1.getId());
        assertThat(investmentDTO1).isEqualTo(investmentDTO2);
        investmentDTO2.setId(2L);
        assertThat(investmentDTO1).isNotEqualTo(investmentDTO2);
        investmentDTO1.setId(null);
        assertThat(investmentDTO1).isNotEqualTo(investmentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(investmentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(investmentMapper.fromId(null)).isNull();
    }
}
