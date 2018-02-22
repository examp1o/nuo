package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NuoApp;

import io.github.jhipster.application.domain.Project;
import io.github.jhipster.application.repository.ProjectRepository;
import io.github.jhipster.application.repository.search.ProjectSearchRepository;
import io.github.jhipster.application.service.dto.ProjectDTO;
import io.github.jhipster.application.service.mapper.ProjectMapper;
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

import io.github.jhipster.application.domain.enumeration.ProjectLevel;
/**
 * Test class for the ProjectResource REST controller.
 *
 * @see ProjectResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NuoApp.class)
public class ProjectResourceIntTest {

    private static final Long DEFAULT_TOTAL_AMOUNT = 1L;
    private static final Long UPDATED_TOTAL_AMOUNT = 2L;

    private static final Long DEFAULT_TOKENS_AMOUT = 1L;
    private static final Long UPDATED_TOKENS_AMOUT = 2L;

    private static final Long DEFAULT_TOKENS_HARD_TOP = 1L;
    private static final Long UPDATED_TOKENS_HARD_TOP = 2L;

    private static final String DEFAULT_TOKENS_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TOKENS_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EXCHANGE = "AAAAAAAAAA";
    private static final String UPDATED_EXCHANGE = "BBBBBBBBBB";

    private static final String DEFAULT_CONCEPT = "AAAAAAAAAA";
    private static final String UPDATED_CONCEPT = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final ProjectLevel DEFAULT_CROWDFUNDING_LEVEL = ProjectLevel.AAA;
    private static final ProjectLevel UPDATED_CROWDFUNDING_LEVEL = ProjectLevel.AA;

    private static final Integer DEFAULT_CROWDFUNDING_TARGET = 1;
    private static final Integer UPDATED_CROWDFUNDING_TARGET = 2;

    private static final Integer DEFAULT_DEPOT_LOCK = 1;
    private static final Integer UPDATED_DEPOT_LOCK = 2;

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

    private static final String DEFAULT_ILLUSTRATION = "AAAAAAAAAA";
    private static final String UPDATED_ILLUSTRATION = "BBBBBBBBBB";

    private static final Integer DEFAULT_GRADE = 1;
    private static final Integer UPDATED_GRADE = 2;

    private static final String DEFAULT_CROWDFUNDING_PLATFORM = "AAAAAAAAAA";
    private static final String UPDATED_CROWDFUNDING_PLATFORM = "BBBBBBBBBB";

    private static final Long DEFAULT_ISSUE_DATE = 1L;
    private static final Long UPDATED_ISSUE_DATE = 2L;

    private static final Long DEFAULT_ISSUE_END_DATE = 1L;
    private static final Long UPDATED_ISSUE_END_DATE = 2L;

    private static final Integer DEFAULT_SERVICE_CHARGE = 1;
    private static final Integer UPDATED_SERVICE_CHARGE = 2;

    private static final Long DEFAULT_MIN_CROWDFUNDING_TARGET = 1L;
    private static final Long UPDATED_MIN_CROWDFUNDING_TARGET = 2L;

    private static final Long DEFAULT_USER = 1L;
    private static final Long UPDATED_USER = 2L;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProjectSearchRepository projectSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProjectMockMvc;

    private Project project;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProjectResource projectResource = new ProjectResource(projectRepository, projectMapper, projectSearchRepository);
        this.restProjectMockMvc = MockMvcBuilders.standaloneSetup(projectResource)
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
    public static Project createEntity(EntityManager em) {
        Project project = new Project()
            .totalAmount(DEFAULT_TOTAL_AMOUNT)
            .tokensAmout(DEFAULT_TOKENS_AMOUT)
            .tokensHardTop(DEFAULT_TOKENS_HARD_TOP)
            .tokensName(DEFAULT_TOKENS_NAME)
            .exchange(DEFAULT_EXCHANGE)
            .concept(DEFAULT_CONCEPT)
            .code(DEFAULT_CODE)
            .crowdfundingLevel(DEFAULT_CROWDFUNDING_LEVEL)
            .crowdfundingTarget(DEFAULT_CROWDFUNDING_TARGET)
            .depotLock(DEFAULT_DEPOT_LOCK)
            .remarks(DEFAULT_REMARKS)
            .website(DEFAULT_WEBSITE)
            .illustration(DEFAULT_ILLUSTRATION)
            .grade(DEFAULT_GRADE)
            .crowdfundingPlatform(DEFAULT_CROWDFUNDING_PLATFORM)
            .issueDate(DEFAULT_ISSUE_DATE)
            .issueEndDate(DEFAULT_ISSUE_END_DATE)
            .serviceCharge(DEFAULT_SERVICE_CHARGE)
            .minCrowdfundingTarget(DEFAULT_MIN_CROWDFUNDING_TARGET)
            .user(DEFAULT_USER);
        return project;
    }

    @Before
    public void initTest() {
        projectSearchRepository.deleteAll();
        project = createEntity(em);
    }

    @Test
    @Transactional
    public void createProject() throws Exception {
        int databaseSizeBeforeCreate = projectRepository.findAll().size();

        // Create the Project
        ProjectDTO projectDTO = projectMapper.toDto(project);
        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectDTO)))
            .andExpect(status().isCreated());

        // Validate the Project in the database
        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeCreate + 1);
        Project testProject = projectList.get(projectList.size() - 1);
        assertThat(testProject.getTotalAmount()).isEqualTo(DEFAULT_TOTAL_AMOUNT);
        assertThat(testProject.getTokensAmout()).isEqualTo(DEFAULT_TOKENS_AMOUT);
        assertThat(testProject.getTokensHardTop()).isEqualTo(DEFAULT_TOKENS_HARD_TOP);
        assertThat(testProject.getTokensName()).isEqualTo(DEFAULT_TOKENS_NAME);
        assertThat(testProject.getExchange()).isEqualTo(DEFAULT_EXCHANGE);
        assertThat(testProject.getConcept()).isEqualTo(DEFAULT_CONCEPT);
        assertThat(testProject.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testProject.getCrowdfundingLevel()).isEqualTo(DEFAULT_CROWDFUNDING_LEVEL);
        assertThat(testProject.getCrowdfundingTarget()).isEqualTo(DEFAULT_CROWDFUNDING_TARGET);
        assertThat(testProject.getDepotLock()).isEqualTo(DEFAULT_DEPOT_LOCK);
        assertThat(testProject.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testProject.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testProject.getIllustration()).isEqualTo(DEFAULT_ILLUSTRATION);
        assertThat(testProject.getGrade()).isEqualTo(DEFAULT_GRADE);
        assertThat(testProject.getCrowdfundingPlatform()).isEqualTo(DEFAULT_CROWDFUNDING_PLATFORM);
        assertThat(testProject.getIssueDate()).isEqualTo(DEFAULT_ISSUE_DATE);
        assertThat(testProject.getIssueEndDate()).isEqualTo(DEFAULT_ISSUE_END_DATE);
        assertThat(testProject.getServiceCharge()).isEqualTo(DEFAULT_SERVICE_CHARGE);
        assertThat(testProject.getMinCrowdfundingTarget()).isEqualTo(DEFAULT_MIN_CROWDFUNDING_TARGET);
        assertThat(testProject.getUser()).isEqualTo(DEFAULT_USER);

        // Validate the Project in Elasticsearch
        Project projectEs = projectSearchRepository.findOne(testProject.getId());
        assertThat(projectEs).isEqualToIgnoringGivenFields(testProject);
    }

    @Test
    @Transactional
    public void createProjectWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = projectRepository.findAll().size();

        // Create the Project with an existing ID
        project.setId(1L);
        ProjectDTO projectDTO = projectMapper.toDto(project);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProjectMockMvc.perform(post("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Project in the database
        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProjects() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get all the projectList
        restProjectMockMvc.perform(get("/api/projects?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(project.getId().intValue())))
            .andExpect(jsonPath("$.[*].totalAmount").value(hasItem(DEFAULT_TOTAL_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].tokensAmout").value(hasItem(DEFAULT_TOKENS_AMOUT.intValue())))
            .andExpect(jsonPath("$.[*].tokensHardTop").value(hasItem(DEFAULT_TOKENS_HARD_TOP.intValue())))
            .andExpect(jsonPath("$.[*].tokensName").value(hasItem(DEFAULT_TOKENS_NAME.toString())))
            .andExpect(jsonPath("$.[*].exchange").value(hasItem(DEFAULT_EXCHANGE.toString())))
            .andExpect(jsonPath("$.[*].concept").value(hasItem(DEFAULT_CONCEPT.toString())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].crowdfundingLevel").value(hasItem(DEFAULT_CROWDFUNDING_LEVEL.toString())))
            .andExpect(jsonPath("$.[*].crowdfundingTarget").value(hasItem(DEFAULT_CROWDFUNDING_TARGET)))
            .andExpect(jsonPath("$.[*].depotLock").value(hasItem(DEFAULT_DEPOT_LOCK)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE.toString())))
            .andExpect(jsonPath("$.[*].illustration").value(hasItem(DEFAULT_ILLUSTRATION.toString())))
            .andExpect(jsonPath("$.[*].grade").value(hasItem(DEFAULT_GRADE)))
            .andExpect(jsonPath("$.[*].crowdfundingPlatform").value(hasItem(DEFAULT_CROWDFUNDING_PLATFORM.toString())))
            .andExpect(jsonPath("$.[*].issueDate").value(hasItem(DEFAULT_ISSUE_DATE.intValue())))
            .andExpect(jsonPath("$.[*].issueEndDate").value(hasItem(DEFAULT_ISSUE_END_DATE.intValue())))
            .andExpect(jsonPath("$.[*].serviceCharge").value(hasItem(DEFAULT_SERVICE_CHARGE)))
            .andExpect(jsonPath("$.[*].minCrowdfundingTarget").value(hasItem(DEFAULT_MIN_CROWDFUNDING_TARGET.intValue())))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER.intValue())));
    }

    @Test
    @Transactional
    public void getProject() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);

        // Get the project
        restProjectMockMvc.perform(get("/api/projects/{id}", project.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(project.getId().intValue()))
            .andExpect(jsonPath("$.totalAmount").value(DEFAULT_TOTAL_AMOUNT.intValue()))
            .andExpect(jsonPath("$.tokensAmout").value(DEFAULT_TOKENS_AMOUT.intValue()))
            .andExpect(jsonPath("$.tokensHardTop").value(DEFAULT_TOKENS_HARD_TOP.intValue()))
            .andExpect(jsonPath("$.tokensName").value(DEFAULT_TOKENS_NAME.toString()))
            .andExpect(jsonPath("$.exchange").value(DEFAULT_EXCHANGE.toString()))
            .andExpect(jsonPath("$.concept").value(DEFAULT_CONCEPT.toString()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE.toString()))
            .andExpect(jsonPath("$.crowdfundingLevel").value(DEFAULT_CROWDFUNDING_LEVEL.toString()))
            .andExpect(jsonPath("$.crowdfundingTarget").value(DEFAULT_CROWDFUNDING_TARGET))
            .andExpect(jsonPath("$.depotLock").value(DEFAULT_DEPOT_LOCK))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE.toString()))
            .andExpect(jsonPath("$.illustration").value(DEFAULT_ILLUSTRATION.toString()))
            .andExpect(jsonPath("$.grade").value(DEFAULT_GRADE))
            .andExpect(jsonPath("$.crowdfundingPlatform").value(DEFAULT_CROWDFUNDING_PLATFORM.toString()))
            .andExpect(jsonPath("$.issueDate").value(DEFAULT_ISSUE_DATE.intValue()))
            .andExpect(jsonPath("$.issueEndDate").value(DEFAULT_ISSUE_END_DATE.intValue()))
            .andExpect(jsonPath("$.serviceCharge").value(DEFAULT_SERVICE_CHARGE))
            .andExpect(jsonPath("$.minCrowdfundingTarget").value(DEFAULT_MIN_CROWDFUNDING_TARGET.intValue()))
            .andExpect(jsonPath("$.user").value(DEFAULT_USER.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProject() throws Exception {
        // Get the project
        restProjectMockMvc.perform(get("/api/projects/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProject() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);
        projectSearchRepository.save(project);
        int databaseSizeBeforeUpdate = projectRepository.findAll().size();

        // Update the project
        Project updatedProject = projectRepository.findOne(project.getId());
        // Disconnect from session so that the updates on updatedProject are not directly saved in db
        em.detach(updatedProject);
        updatedProject
            .totalAmount(UPDATED_TOTAL_AMOUNT)
            .tokensAmout(UPDATED_TOKENS_AMOUT)
            .tokensHardTop(UPDATED_TOKENS_HARD_TOP)
            .tokensName(UPDATED_TOKENS_NAME)
            .exchange(UPDATED_EXCHANGE)
            .concept(UPDATED_CONCEPT)
            .code(UPDATED_CODE)
            .crowdfundingLevel(UPDATED_CROWDFUNDING_LEVEL)
            .crowdfundingTarget(UPDATED_CROWDFUNDING_TARGET)
            .depotLock(UPDATED_DEPOT_LOCK)
            .remarks(UPDATED_REMARKS)
            .website(UPDATED_WEBSITE)
            .illustration(UPDATED_ILLUSTRATION)
            .grade(UPDATED_GRADE)
            .crowdfundingPlatform(UPDATED_CROWDFUNDING_PLATFORM)
            .issueDate(UPDATED_ISSUE_DATE)
            .issueEndDate(UPDATED_ISSUE_END_DATE)
            .serviceCharge(UPDATED_SERVICE_CHARGE)
            .minCrowdfundingTarget(UPDATED_MIN_CROWDFUNDING_TARGET)
            .user(UPDATED_USER);
        ProjectDTO projectDTO = projectMapper.toDto(updatedProject);

        restProjectMockMvc.perform(put("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectDTO)))
            .andExpect(status().isOk());

        // Validate the Project in the database
        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeUpdate);
        Project testProject = projectList.get(projectList.size() - 1);
        assertThat(testProject.getTotalAmount()).isEqualTo(UPDATED_TOTAL_AMOUNT);
        assertThat(testProject.getTokensAmout()).isEqualTo(UPDATED_TOKENS_AMOUT);
        assertThat(testProject.getTokensHardTop()).isEqualTo(UPDATED_TOKENS_HARD_TOP);
        assertThat(testProject.getTokensName()).isEqualTo(UPDATED_TOKENS_NAME);
        assertThat(testProject.getExchange()).isEqualTo(UPDATED_EXCHANGE);
        assertThat(testProject.getConcept()).isEqualTo(UPDATED_CONCEPT);
        assertThat(testProject.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testProject.getCrowdfundingLevel()).isEqualTo(UPDATED_CROWDFUNDING_LEVEL);
        assertThat(testProject.getCrowdfundingTarget()).isEqualTo(UPDATED_CROWDFUNDING_TARGET);
        assertThat(testProject.getDepotLock()).isEqualTo(UPDATED_DEPOT_LOCK);
        assertThat(testProject.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testProject.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testProject.getIllustration()).isEqualTo(UPDATED_ILLUSTRATION);
        assertThat(testProject.getGrade()).isEqualTo(UPDATED_GRADE);
        assertThat(testProject.getCrowdfundingPlatform()).isEqualTo(UPDATED_CROWDFUNDING_PLATFORM);
        assertThat(testProject.getIssueDate()).isEqualTo(UPDATED_ISSUE_DATE);
        assertThat(testProject.getIssueEndDate()).isEqualTo(UPDATED_ISSUE_END_DATE);
        assertThat(testProject.getServiceCharge()).isEqualTo(UPDATED_SERVICE_CHARGE);
        assertThat(testProject.getMinCrowdfundingTarget()).isEqualTo(UPDATED_MIN_CROWDFUNDING_TARGET);
        assertThat(testProject.getUser()).isEqualTo(UPDATED_USER);

        // Validate the Project in Elasticsearch
        Project projectEs = projectSearchRepository.findOne(testProject.getId());
        assertThat(projectEs).isEqualToIgnoringGivenFields(testProject);
    }

    @Test
    @Transactional
    public void updateNonExistingProject() throws Exception {
        int databaseSizeBeforeUpdate = projectRepository.findAll().size();

        // Create the Project
        ProjectDTO projectDTO = projectMapper.toDto(project);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restProjectMockMvc.perform(put("/api/projects")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(projectDTO)))
            .andExpect(status().isCreated());

        // Validate the Project in the database
        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteProject() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);
        projectSearchRepository.save(project);
        int databaseSizeBeforeDelete = projectRepository.findAll().size();

        // Get the project
        restProjectMockMvc.perform(delete("/api/projects/{id}", project.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean projectExistsInEs = projectSearchRepository.exists(project.getId());
        assertThat(projectExistsInEs).isFalse();

        // Validate the database is empty
        List<Project> projectList = projectRepository.findAll();
        assertThat(projectList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchProject() throws Exception {
        // Initialize the database
        projectRepository.saveAndFlush(project);
        projectSearchRepository.save(project);

        // Search the project
        restProjectMockMvc.perform(get("/api/_search/projects?query=id:" + project.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(project.getId().intValue())))
            .andExpect(jsonPath("$.[*].totalAmount").value(hasItem(DEFAULT_TOTAL_AMOUNT.intValue())))
            .andExpect(jsonPath("$.[*].tokensAmout").value(hasItem(DEFAULT_TOKENS_AMOUT.intValue())))
            .andExpect(jsonPath("$.[*].tokensHardTop").value(hasItem(DEFAULT_TOKENS_HARD_TOP.intValue())))
            .andExpect(jsonPath("$.[*].tokensName").value(hasItem(DEFAULT_TOKENS_NAME.toString())))
            .andExpect(jsonPath("$.[*].exchange").value(hasItem(DEFAULT_EXCHANGE.toString())))
            .andExpect(jsonPath("$.[*].concept").value(hasItem(DEFAULT_CONCEPT.toString())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE.toString())))
            .andExpect(jsonPath("$.[*].crowdfundingLevel").value(hasItem(DEFAULT_CROWDFUNDING_LEVEL.toString())))
            .andExpect(jsonPath("$.[*].crowdfundingTarget").value(hasItem(DEFAULT_CROWDFUNDING_TARGET)))
            .andExpect(jsonPath("$.[*].depotLock").value(hasItem(DEFAULT_DEPOT_LOCK)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE.toString())))
            .andExpect(jsonPath("$.[*].illustration").value(hasItem(DEFAULT_ILLUSTRATION.toString())))
            .andExpect(jsonPath("$.[*].grade").value(hasItem(DEFAULT_GRADE)))
            .andExpect(jsonPath("$.[*].crowdfundingPlatform").value(hasItem(DEFAULT_CROWDFUNDING_PLATFORM.toString())))
            .andExpect(jsonPath("$.[*].issueDate").value(hasItem(DEFAULT_ISSUE_DATE.intValue())))
            .andExpect(jsonPath("$.[*].issueEndDate").value(hasItem(DEFAULT_ISSUE_END_DATE.intValue())))
            .andExpect(jsonPath("$.[*].serviceCharge").value(hasItem(DEFAULT_SERVICE_CHARGE)))
            .andExpect(jsonPath("$.[*].minCrowdfundingTarget").value(hasItem(DEFAULT_MIN_CROWDFUNDING_TARGET.intValue())))
            .andExpect(jsonPath("$.[*].user").value(hasItem(DEFAULT_USER.intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Project.class);
        Project project1 = new Project();
        project1.setId(1L);
        Project project2 = new Project();
        project2.setId(project1.getId());
        assertThat(project1).isEqualTo(project2);
        project2.setId(2L);
        assertThat(project1).isNotEqualTo(project2);
        project1.setId(null);
        assertThat(project1).isNotEqualTo(project2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProjectDTO.class);
        ProjectDTO projectDTO1 = new ProjectDTO();
        projectDTO1.setId(1L);
        ProjectDTO projectDTO2 = new ProjectDTO();
        assertThat(projectDTO1).isNotEqualTo(projectDTO2);
        projectDTO2.setId(projectDTO1.getId());
        assertThat(projectDTO1).isEqualTo(projectDTO2);
        projectDTO2.setId(2L);
        assertThat(projectDTO1).isNotEqualTo(projectDTO2);
        projectDTO1.setId(null);
        assertThat(projectDTO1).isNotEqualTo(projectDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(projectMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(projectMapper.fromId(null)).isNull();
    }
}
