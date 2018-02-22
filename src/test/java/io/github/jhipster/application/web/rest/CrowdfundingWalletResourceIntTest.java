package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NuoApp;

import io.github.jhipster.application.domain.CrowdfundingWallet;
import io.github.jhipster.application.repository.CrowdfundingWalletRepository;
import io.github.jhipster.application.service.CrowdfundingWalletService;
import io.github.jhipster.application.repository.search.CrowdfundingWalletSearchRepository;
import io.github.jhipster.application.service.dto.CrowdfundingWalletDTO;
import io.github.jhipster.application.service.mapper.CrowdfundingWalletMapper;
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

import io.github.jhipster.application.domain.enumeration.TokensType;
/**
 * Test class for the CrowdfundingWalletResource REST controller.
 *
 * @see CrowdfundingWalletResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NuoApp.class)
public class CrowdfundingWalletResourceIntTest {

    private static final TokensType DEFAULT_TOKENS_TYPE = TokensType.ETH;
    private static final TokensType UPDATED_TOKENS_TYPE = TokensType.ETH;

    private static final Long DEFAULT_CROWDFUNDING_AMOUNT = 1L;
    private static final Long UPDATED_CROWDFUNDING_AMOUNT = 2L;

    private static final String DEFAULT_CROWDFUNDING_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_CROWDFUNDING_ADDRESS = "BBBBBBBBBB";

    @Autowired
    private CrowdfundingWalletRepository crowdfundingWalletRepository;

    @Autowired
    private CrowdfundingWalletMapper crowdfundingWalletMapper;

    @Autowired
    private CrowdfundingWalletService crowdfundingWalletService;

    @Autowired
    private CrowdfundingWalletSearchRepository crowdfundingWalletSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCrowdfundingWalletMockMvc;

    private CrowdfundingWallet crowdfundingWallet;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CrowdfundingWalletResource crowdfundingWalletResource = new CrowdfundingWalletResource(crowdfundingWalletService);
        this.restCrowdfundingWalletMockMvc = MockMvcBuilders.standaloneSetup(crowdfundingWalletResource)
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
    public static CrowdfundingWallet createEntity(EntityManager em) {
        CrowdfundingWallet crowdfundingWallet = new CrowdfundingWallet()
            .tokensType(DEFAULT_TOKENS_TYPE)
            .crowdfundingAmount(DEFAULT_CROWDFUNDING_AMOUNT)
            .crowdfundingAddress(DEFAULT_CROWDFUNDING_ADDRESS);
        return crowdfundingWallet;
    }

    @Before
    public void initTest() {
        crowdfundingWalletSearchRepository.deleteAll();
        crowdfundingWallet = createEntity(em);
    }

    @Test
    @Transactional
    public void createCrowdfundingWallet() throws Exception {
        int databaseSizeBeforeCreate = crowdfundingWalletRepository.findAll().size();

        // Create the CrowdfundingWallet
        CrowdfundingWalletDTO crowdfundingWalletDTO = crowdfundingWalletMapper.toDto(crowdfundingWallet);
        restCrowdfundingWalletMockMvc.perform(post("/api/crowdfunding-wallets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(crowdfundingWalletDTO)))
            .andExpect(status().isCreated());

        // Validate the CrowdfundingWallet in the database
        List<CrowdfundingWallet> crowdfundingWalletList = crowdfundingWalletRepository.findAll();
        assertThat(crowdfundingWalletList).hasSize(databaseSizeBeforeCreate + 1);
        CrowdfundingWallet testCrowdfundingWallet = crowdfundingWalletList.get(crowdfundingWalletList.size() - 1);
        assertThat(testCrowdfundingWallet.getTokensType()).isEqualTo(DEFAULT_TOKENS_TYPE);
        assertThat(testCrowdfundingWallet.getCrowdfundingAmount()).isEqualTo(DEFAULT_CROWDFUNDING_AMOUNT);
        assertThat(testCrowdfundingWallet.getCrowdfundingAddress()).isEqualTo(DEFAULT_CROWDFUNDING_ADDRESS);

        // Validate the CrowdfundingWallet in Elasticsearch
        CrowdfundingWallet crowdfundingWalletEs = crowdfundingWalletSearchRepository.findOne(testCrowdfundingWallet.getId());
        assertThat(crowdfundingWalletEs).isEqualToIgnoringGivenFields(testCrowdfundingWallet);
    }

    @Test
    @Transactional
    public void createCrowdfundingWalletWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = crowdfundingWalletRepository.findAll().size();

        // Create the CrowdfundingWallet with an existing ID
        crowdfundingWallet.setId(1L);
        CrowdfundingWalletDTO crowdfundingWalletDTO = crowdfundingWalletMapper.toDto(crowdfundingWallet);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCrowdfundingWalletMockMvc.perform(post("/api/crowdfunding-wallets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(crowdfundingWalletDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CrowdfundingWallet in the database
        List<CrowdfundingWallet> crowdfundingWalletList = crowdfundingWalletRepository.findAll();
        assertThat(crowdfundingWalletList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCrowdfundingWallets() throws Exception {
        // Initialize the database
        crowdfundingWalletRepository.saveAndFlush(crowdfundingWallet);

        // Get all the crowdfundingWalletList
        restCrowdfundingWalletMockMvc.perform(get("/api/crowdfunding-wallets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(crowdfundingWallet.getId().intValue())))
            .andExpect(jsonPath("$.[*].tokensType").value(hasItem(DEFAULT_TOKENS_TYPE.toString())))
            .andExpect(jsonPath("$.[*].crowdfundingAmount").value(hasItem(DEFAULT_CROWDFUNDING_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].crowdfundingAddress").value(hasItem(DEFAULT_CROWDFUNDING_ADDRESS.toString())));
    }

    @Test
    @Transactional
    public void getCrowdfundingWallet() throws Exception {
        // Initialize the database
        crowdfundingWalletRepository.saveAndFlush(crowdfundingWallet);

        // Get the crowdfundingWallet
        restCrowdfundingWalletMockMvc.perform(get("/api/crowdfunding-wallets/{id}", crowdfundingWallet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(crowdfundingWallet.getId().intValue()))
            .andExpect(jsonPath("$.tokensType").value(DEFAULT_TOKENS_TYPE.toString()))
            .andExpect(jsonPath("$.crowdfundingAmount").value(DEFAULT_CROWDFUNDING_AMOUNT.intValue()))
            .andExpect(jsonPath("$.crowdfundingAddress").value(DEFAULT_CROWDFUNDING_ADDRESS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCrowdfundingWallet() throws Exception {
        // Get the crowdfundingWallet
        restCrowdfundingWalletMockMvc.perform(get("/api/crowdfunding-wallets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCrowdfundingWallet() throws Exception {
        // Initialize the database
        crowdfundingWalletRepository.saveAndFlush(crowdfundingWallet);
        crowdfundingWalletSearchRepository.save(crowdfundingWallet);
        int databaseSizeBeforeUpdate = crowdfundingWalletRepository.findAll().size();

        // Update the crowdfundingWallet
        CrowdfundingWallet updatedCrowdfundingWallet = crowdfundingWalletRepository.findOne(crowdfundingWallet.getId());
        // Disconnect from session so that the updates on updatedCrowdfundingWallet are not directly saved in db
        em.detach(updatedCrowdfundingWallet);
        updatedCrowdfundingWallet
            .tokensType(UPDATED_TOKENS_TYPE)
            .crowdfundingAmount(UPDATED_CROWDFUNDING_AMOUNT)
            .crowdfundingAddress(UPDATED_CROWDFUNDING_ADDRESS);
        CrowdfundingWalletDTO crowdfundingWalletDTO = crowdfundingWalletMapper.toDto(updatedCrowdfundingWallet);

        restCrowdfundingWalletMockMvc.perform(put("/api/crowdfunding-wallets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(crowdfundingWalletDTO)))
            .andExpect(status().isOk());

        // Validate the CrowdfundingWallet in the database
        List<CrowdfundingWallet> crowdfundingWalletList = crowdfundingWalletRepository.findAll();
        assertThat(crowdfundingWalletList).hasSize(databaseSizeBeforeUpdate);
        CrowdfundingWallet testCrowdfundingWallet = crowdfundingWalletList.get(crowdfundingWalletList.size() - 1);
        assertThat(testCrowdfundingWallet.getTokensType()).isEqualTo(UPDATED_TOKENS_TYPE);
        assertThat(testCrowdfundingWallet.getCrowdfundingAmount()).isEqualTo(UPDATED_CROWDFUNDING_AMOUNT);
        assertThat(testCrowdfundingWallet.getCrowdfundingAddress()).isEqualTo(UPDATED_CROWDFUNDING_ADDRESS);

        // Validate the CrowdfundingWallet in Elasticsearch
        CrowdfundingWallet crowdfundingWalletEs = crowdfundingWalletSearchRepository.findOne(testCrowdfundingWallet.getId());
        assertThat(crowdfundingWalletEs).isEqualToIgnoringGivenFields(testCrowdfundingWallet);
    }

    @Test
    @Transactional
    public void updateNonExistingCrowdfundingWallet() throws Exception {
        int databaseSizeBeforeUpdate = crowdfundingWalletRepository.findAll().size();

        // Create the CrowdfundingWallet
        CrowdfundingWalletDTO crowdfundingWalletDTO = crowdfundingWalletMapper.toDto(crowdfundingWallet);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restCrowdfundingWalletMockMvc.perform(put("/api/crowdfunding-wallets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(crowdfundingWalletDTO)))
            .andExpect(status().isCreated());

        // Validate the CrowdfundingWallet in the database
        List<CrowdfundingWallet> crowdfundingWalletList = crowdfundingWalletRepository.findAll();
        assertThat(crowdfundingWalletList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteCrowdfundingWallet() throws Exception {
        // Initialize the database
        crowdfundingWalletRepository.saveAndFlush(crowdfundingWallet);
        crowdfundingWalletSearchRepository.save(crowdfundingWallet);
        int databaseSizeBeforeDelete = crowdfundingWalletRepository.findAll().size();

        // Get the crowdfundingWallet
        restCrowdfundingWalletMockMvc.perform(delete("/api/crowdfunding-wallets/{id}", crowdfundingWallet.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean crowdfundingWalletExistsInEs = crowdfundingWalletSearchRepository.exists(crowdfundingWallet.getId());
        assertThat(crowdfundingWalletExistsInEs).isFalse();

        // Validate the database is empty
        List<CrowdfundingWallet> crowdfundingWalletList = crowdfundingWalletRepository.findAll();
        assertThat(crowdfundingWalletList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchCrowdfundingWallet() throws Exception {
        // Initialize the database
        crowdfundingWalletRepository.saveAndFlush(crowdfundingWallet);
        crowdfundingWalletSearchRepository.save(crowdfundingWallet);

        // Search the crowdfundingWallet
        restCrowdfundingWalletMockMvc.perform(get("/api/_search/crowdfunding-wallets?query=id:" + crowdfundingWallet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(crowdfundingWallet.getId().intValue())))
            .andExpect(jsonPath("$.[*].tokensType").value(hasItem(DEFAULT_TOKENS_TYPE.toString())))
            .andExpect(jsonPath("$.[*].crowdfundingAmount").value(hasItem(DEFAULT_CROWDFUNDING_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].crowdfundingAddress").value(hasItem(DEFAULT_CROWDFUNDING_ADDRESS.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CrowdfundingWallet.class);
        CrowdfundingWallet crowdfundingWallet1 = new CrowdfundingWallet();
        crowdfundingWallet1.setId(1L);
        CrowdfundingWallet crowdfundingWallet2 = new CrowdfundingWallet();
        crowdfundingWallet2.setId(crowdfundingWallet1.getId());
        assertThat(crowdfundingWallet1).isEqualTo(crowdfundingWallet2);
        crowdfundingWallet2.setId(2L);
        assertThat(crowdfundingWallet1).isNotEqualTo(crowdfundingWallet2);
        crowdfundingWallet1.setId(null);
        assertThat(crowdfundingWallet1).isNotEqualTo(crowdfundingWallet2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CrowdfundingWalletDTO.class);
        CrowdfundingWalletDTO crowdfundingWalletDTO1 = new CrowdfundingWalletDTO();
        crowdfundingWalletDTO1.setId(1L);
        CrowdfundingWalletDTO crowdfundingWalletDTO2 = new CrowdfundingWalletDTO();
        assertThat(crowdfundingWalletDTO1).isNotEqualTo(crowdfundingWalletDTO2);
        crowdfundingWalletDTO2.setId(crowdfundingWalletDTO1.getId());
        assertThat(crowdfundingWalletDTO1).isEqualTo(crowdfundingWalletDTO2);
        crowdfundingWalletDTO2.setId(2L);
        assertThat(crowdfundingWalletDTO1).isNotEqualTo(crowdfundingWalletDTO2);
        crowdfundingWalletDTO1.setId(null);
        assertThat(crowdfundingWalletDTO1).isNotEqualTo(crowdfundingWalletDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(crowdfundingWalletMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(crowdfundingWalletMapper.fromId(null)).isNull();
    }
}
