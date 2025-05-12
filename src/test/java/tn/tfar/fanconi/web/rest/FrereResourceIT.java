package tn.tfar.fanconi.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tn.tfar.fanconi.domain.FrereAsserts.*;
import static tn.tfar.fanconi.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tn.tfar.fanconi.IntegrationTest;
import tn.tfar.fanconi.domain.Frere;
import tn.tfar.fanconi.repository.FrereRepository;

/**
 * Integration tests for the {@link FrereResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FrereResourceIT {

    private static final String DEFAULT_N_DOSSIER_F = "AAAAAAAAAA";
    private static final String UPDATED_N_DOSSIER_F = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_FRERE = 1;
    private static final Integer UPDATED_ID_FRERE = 2;

    private static final String DEFAULT_PRENOM_FRERE = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM_FRERE = "BBBBBBBBBB";

    private static final String DEFAULT_ATTEINT = "AAAAAAAAAA";
    private static final String UPDATED_ATTEINT = "BBBBBBBBBB";

    private static final Integer DEFAULT_PLACEFRATRIE = 1;
    private static final Integer UPDATED_PLACEFRATRIE = 2;

    private static final String DEFAULT_SEXE_F = "AAAAAAAAAA";
    private static final String UPDATED_SEXE_F = "BBBBBBBBBB";

    private static final String DEFAULT_DECEDES = "AAAAAAAAAA";
    private static final String UPDATED_DECEDES = "BBBBBBBBBB";

    private static final Integer DEFAULT_AGE = 1;
    private static final Integer UPDATED_AGE = 2;

    private static final String ENTITY_API_URL = "/api/freres";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private FrereRepository frereRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFrereMockMvc;

    private Frere frere;

    private Frere insertedFrere;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Frere createEntity() {
        return new Frere()
            .nDossierF(DEFAULT_N_DOSSIER_F)
            .idFrere(DEFAULT_ID_FRERE)
            .prenomFrere(DEFAULT_PRENOM_FRERE)
            .atteint(DEFAULT_ATTEINT)
            .placefratrie(DEFAULT_PLACEFRATRIE)
            .sexeF(DEFAULT_SEXE_F)
            .decedes(DEFAULT_DECEDES)
            .age(DEFAULT_AGE);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Frere createUpdatedEntity() {
        return new Frere()
            .nDossierF(UPDATED_N_DOSSIER_F)
            .idFrere(UPDATED_ID_FRERE)
            .prenomFrere(UPDATED_PRENOM_FRERE)
            .atteint(UPDATED_ATTEINT)
            .placefratrie(UPDATED_PLACEFRATRIE)
            .sexeF(UPDATED_SEXE_F)
            .decedes(UPDATED_DECEDES)
            .age(UPDATED_AGE);
    }

    @BeforeEach
    public void initTest() {
        frere = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedFrere != null) {
            frereRepository.delete(insertedFrere);
            insertedFrere = null;
        }
    }

    @Test
    @Transactional
    void createFrere() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Frere
        var returnedFrere = om.readValue(
            restFrereMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(frere)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Frere.class
        );

        // Validate the Frere in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertFrereUpdatableFieldsEquals(returnedFrere, getPersistedFrere(returnedFrere));

        insertedFrere = returnedFrere;
    }

    @Test
    @Transactional
    void createFrereWithExistingId() throws Exception {
        // Create the Frere with an existing ID
        frere.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFrereMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(frere)))
            .andExpect(status().isBadRequest());

        // Validate the Frere in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllFreres() throws Exception {
        // Initialize the database
        insertedFrere = frereRepository.saveAndFlush(frere);

        // Get all the frereList
        restFrereMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(frere.getId().intValue())))
            .andExpect(jsonPath("$.[*].nDossierF").value(hasItem(DEFAULT_N_DOSSIER_F)))
            .andExpect(jsonPath("$.[*].idFrere").value(hasItem(DEFAULT_ID_FRERE)))
            .andExpect(jsonPath("$.[*].prenomFrere").value(hasItem(DEFAULT_PRENOM_FRERE)))
            .andExpect(jsonPath("$.[*].atteint").value(hasItem(DEFAULT_ATTEINT)))
            .andExpect(jsonPath("$.[*].placefratrie").value(hasItem(DEFAULT_PLACEFRATRIE)))
            .andExpect(jsonPath("$.[*].sexeF").value(hasItem(DEFAULT_SEXE_F)))
            .andExpect(jsonPath("$.[*].decedes").value(hasItem(DEFAULT_DECEDES)))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)));
    }

    @Test
    @Transactional
    void getFrere() throws Exception {
        // Initialize the database
        insertedFrere = frereRepository.saveAndFlush(frere);

        // Get the frere
        restFrereMockMvc
            .perform(get(ENTITY_API_URL_ID, frere.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(frere.getId().intValue()))
            .andExpect(jsonPath("$.nDossierF").value(DEFAULT_N_DOSSIER_F))
            .andExpect(jsonPath("$.idFrere").value(DEFAULT_ID_FRERE))
            .andExpect(jsonPath("$.prenomFrere").value(DEFAULT_PRENOM_FRERE))
            .andExpect(jsonPath("$.atteint").value(DEFAULT_ATTEINT))
            .andExpect(jsonPath("$.placefratrie").value(DEFAULT_PLACEFRATRIE))
            .andExpect(jsonPath("$.sexeF").value(DEFAULT_SEXE_F))
            .andExpect(jsonPath("$.decedes").value(DEFAULT_DECEDES))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE));
    }

    @Test
    @Transactional
    void getNonExistingFrere() throws Exception {
        // Get the frere
        restFrereMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFrere() throws Exception {
        // Initialize the database
        insertedFrere = frereRepository.saveAndFlush(frere);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the frere
        Frere updatedFrere = frereRepository.findById(frere.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedFrere are not directly saved in db
        em.detach(updatedFrere);
        updatedFrere
            .nDossierF(UPDATED_N_DOSSIER_F)
            .idFrere(UPDATED_ID_FRERE)
            .prenomFrere(UPDATED_PRENOM_FRERE)
            .atteint(UPDATED_ATTEINT)
            .placefratrie(UPDATED_PLACEFRATRIE)
            .sexeF(UPDATED_SEXE_F)
            .decedes(UPDATED_DECEDES)
            .age(UPDATED_AGE);

        restFrereMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedFrere.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedFrere))
            )
            .andExpect(status().isOk());

        // Validate the Frere in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedFrereToMatchAllProperties(updatedFrere);
    }

    @Test
    @Transactional
    void putNonExistingFrere() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        frere.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFrereMockMvc
            .perform(put(ENTITY_API_URL_ID, frere.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(frere)))
            .andExpect(status().isBadRequest());

        // Validate the Frere in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFrere() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        frere.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFrereMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(frere))
            )
            .andExpect(status().isBadRequest());

        // Validate the Frere in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFrere() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        frere.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFrereMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(frere)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Frere in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFrereWithPatch() throws Exception {
        // Initialize the database
        insertedFrere = frereRepository.saveAndFlush(frere);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the frere using partial update
        Frere partialUpdatedFrere = new Frere();
        partialUpdatedFrere.setId(frere.getId());

        partialUpdatedFrere.nDossierF(UPDATED_N_DOSSIER_F).idFrere(UPDATED_ID_FRERE).prenomFrere(UPDATED_PRENOM_FRERE);

        restFrereMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFrere.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFrere))
            )
            .andExpect(status().isOk());

        // Validate the Frere in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFrereUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedFrere, frere), getPersistedFrere(frere));
    }

    @Test
    @Transactional
    void fullUpdateFrereWithPatch() throws Exception {
        // Initialize the database
        insertedFrere = frereRepository.saveAndFlush(frere);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the frere using partial update
        Frere partialUpdatedFrere = new Frere();
        partialUpdatedFrere.setId(frere.getId());

        partialUpdatedFrere
            .nDossierF(UPDATED_N_DOSSIER_F)
            .idFrere(UPDATED_ID_FRERE)
            .prenomFrere(UPDATED_PRENOM_FRERE)
            .atteint(UPDATED_ATTEINT)
            .placefratrie(UPDATED_PLACEFRATRIE)
            .sexeF(UPDATED_SEXE_F)
            .decedes(UPDATED_DECEDES)
            .age(UPDATED_AGE);

        restFrereMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFrere.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFrere))
            )
            .andExpect(status().isOk());

        // Validate the Frere in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFrereUpdatableFieldsEquals(partialUpdatedFrere, getPersistedFrere(partialUpdatedFrere));
    }

    @Test
    @Transactional
    void patchNonExistingFrere() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        frere.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFrereMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, frere.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(frere))
            )
            .andExpect(status().isBadRequest());

        // Validate the Frere in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFrere() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        frere.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFrereMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(frere))
            )
            .andExpect(status().isBadRequest());

        // Validate the Frere in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFrere() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        frere.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFrereMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(frere)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Frere in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFrere() throws Exception {
        // Initialize the database
        insertedFrere = frereRepository.saveAndFlush(frere);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the frere
        restFrereMockMvc
            .perform(delete(ENTITY_API_URL_ID, frere.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return frereRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Frere getPersistedFrere(Frere frere) {
        return frereRepository.findById(frere.getId()).orElseThrow();
    }

    protected void assertPersistedFrereToMatchAllProperties(Frere expectedFrere) {
        assertFrereAllPropertiesEquals(expectedFrere, getPersistedFrere(expectedFrere));
    }

    protected void assertPersistedFrereToMatchUpdatableProperties(Frere expectedFrere) {
        assertFrereAllUpdatablePropertiesEquals(expectedFrere, getPersistedFrere(expectedFrere));
    }
}
