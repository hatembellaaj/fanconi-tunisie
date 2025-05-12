package tn.tfar.fanconi.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tn.tfar.fanconi.domain.CousinAsserts.*;
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
import tn.tfar.fanconi.domain.Cousin;
import tn.tfar.fanconi.repository.CousinRepository;

/**
 * Integration tests for the {@link CousinResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CousinResourceIT {

    private static final String DEFAULT_NDOSSIER_C = "AAAAAAAAAA";
    private static final String UPDATED_NDOSSIER_C = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_COUSIN = 1;
    private static final Integer UPDATED_ID_COUSIN = 2;

    private static final String DEFAULT_PRENOM_COUSIN = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM_COUSIN = "BBBBBBBBBB";

    private static final String DEFAULT_PLACE_COUSIN = "AAAAAAAAAA";
    private static final String UPDATED_PLACE_COUSIN = "BBBBBBBBBB";

    private static final String DEFAULT_SEXE_C = "AAAAAAAAAA";
    private static final String UPDATED_SEXE_C = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/cousins";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CousinRepository cousinRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCousinMockMvc;

    private Cousin cousin;

    private Cousin insertedCousin;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cousin createEntity() {
        return new Cousin()
            .ndossierC(DEFAULT_NDOSSIER_C)
            .idCousin(DEFAULT_ID_COUSIN)
            .prenomCousin(DEFAULT_PRENOM_COUSIN)
            .placeCousin(DEFAULT_PLACE_COUSIN)
            .sexeC(DEFAULT_SEXE_C);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cousin createUpdatedEntity() {
        return new Cousin()
            .ndossierC(UPDATED_NDOSSIER_C)
            .idCousin(UPDATED_ID_COUSIN)
            .prenomCousin(UPDATED_PRENOM_COUSIN)
            .placeCousin(UPDATED_PLACE_COUSIN)
            .sexeC(UPDATED_SEXE_C);
    }

    @BeforeEach
    public void initTest() {
        cousin = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedCousin != null) {
            cousinRepository.delete(insertedCousin);
            insertedCousin = null;
        }
    }

    @Test
    @Transactional
    void createCousin() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Cousin
        var returnedCousin = om.readValue(
            restCousinMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cousin)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Cousin.class
        );

        // Validate the Cousin in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCousinUpdatableFieldsEquals(returnedCousin, getPersistedCousin(returnedCousin));

        insertedCousin = returnedCousin;
    }

    @Test
    @Transactional
    void createCousinWithExistingId() throws Exception {
        // Create the Cousin with an existing ID
        cousin.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCousinMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cousin)))
            .andExpect(status().isBadRequest());

        // Validate the Cousin in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCousins() throws Exception {
        // Initialize the database
        insertedCousin = cousinRepository.saveAndFlush(cousin);

        // Get all the cousinList
        restCousinMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cousin.getId().intValue())))
            .andExpect(jsonPath("$.[*].ndossierC").value(hasItem(DEFAULT_NDOSSIER_C)))
            .andExpect(jsonPath("$.[*].idCousin").value(hasItem(DEFAULT_ID_COUSIN)))
            .andExpect(jsonPath("$.[*].prenomCousin").value(hasItem(DEFAULT_PRENOM_COUSIN)))
            .andExpect(jsonPath("$.[*].placeCousin").value(hasItem(DEFAULT_PLACE_COUSIN)))
            .andExpect(jsonPath("$.[*].sexeC").value(hasItem(DEFAULT_SEXE_C)));
    }

    @Test
    @Transactional
    void getCousin() throws Exception {
        // Initialize the database
        insertedCousin = cousinRepository.saveAndFlush(cousin);

        // Get the cousin
        restCousinMockMvc
            .perform(get(ENTITY_API_URL_ID, cousin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cousin.getId().intValue()))
            .andExpect(jsonPath("$.ndossierC").value(DEFAULT_NDOSSIER_C))
            .andExpect(jsonPath("$.idCousin").value(DEFAULT_ID_COUSIN))
            .andExpect(jsonPath("$.prenomCousin").value(DEFAULT_PRENOM_COUSIN))
            .andExpect(jsonPath("$.placeCousin").value(DEFAULT_PLACE_COUSIN))
            .andExpect(jsonPath("$.sexeC").value(DEFAULT_SEXE_C));
    }

    @Test
    @Transactional
    void getNonExistingCousin() throws Exception {
        // Get the cousin
        restCousinMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCousin() throws Exception {
        // Initialize the database
        insertedCousin = cousinRepository.saveAndFlush(cousin);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cousin
        Cousin updatedCousin = cousinRepository.findById(cousin.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCousin are not directly saved in db
        em.detach(updatedCousin);
        updatedCousin
            .ndossierC(UPDATED_NDOSSIER_C)
            .idCousin(UPDATED_ID_COUSIN)
            .prenomCousin(UPDATED_PRENOM_COUSIN)
            .placeCousin(UPDATED_PLACE_COUSIN)
            .sexeC(UPDATED_SEXE_C);

        restCousinMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCousin.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCousin))
            )
            .andExpect(status().isOk());

        // Validate the Cousin in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCousinToMatchAllProperties(updatedCousin);
    }

    @Test
    @Transactional
    void putNonExistingCousin() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cousin.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCousinMockMvc
            .perform(put(ENTITY_API_URL_ID, cousin.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cousin)))
            .andExpect(status().isBadRequest());

        // Validate the Cousin in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCousin() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cousin.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCousinMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(cousin))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cousin in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCousin() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cousin.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCousinMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cousin)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Cousin in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCousinWithPatch() throws Exception {
        // Initialize the database
        insertedCousin = cousinRepository.saveAndFlush(cousin);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cousin using partial update
        Cousin partialUpdatedCousin = new Cousin();
        partialUpdatedCousin.setId(cousin.getId());

        partialUpdatedCousin.idCousin(UPDATED_ID_COUSIN);

        restCousinMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCousin.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCousin))
            )
            .andExpect(status().isOk());

        // Validate the Cousin in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCousinUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedCousin, cousin), getPersistedCousin(cousin));
    }

    @Test
    @Transactional
    void fullUpdateCousinWithPatch() throws Exception {
        // Initialize the database
        insertedCousin = cousinRepository.saveAndFlush(cousin);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cousin using partial update
        Cousin partialUpdatedCousin = new Cousin();
        partialUpdatedCousin.setId(cousin.getId());

        partialUpdatedCousin
            .ndossierC(UPDATED_NDOSSIER_C)
            .idCousin(UPDATED_ID_COUSIN)
            .prenomCousin(UPDATED_PRENOM_COUSIN)
            .placeCousin(UPDATED_PLACE_COUSIN)
            .sexeC(UPDATED_SEXE_C);

        restCousinMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCousin.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCousin))
            )
            .andExpect(status().isOk());

        // Validate the Cousin in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCousinUpdatableFieldsEquals(partialUpdatedCousin, getPersistedCousin(partialUpdatedCousin));
    }

    @Test
    @Transactional
    void patchNonExistingCousin() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cousin.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCousinMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, cousin.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(cousin))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cousin in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCousin() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cousin.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCousinMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(cousin))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cousin in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCousin() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cousin.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCousinMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(cousin)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Cousin in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCousin() throws Exception {
        // Initialize the database
        insertedCousin = cousinRepository.saveAndFlush(cousin);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the cousin
        restCousinMockMvc
            .perform(delete(ENTITY_API_URL_ID, cousin.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return cousinRepository.count();
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

    protected Cousin getPersistedCousin(Cousin cousin) {
        return cousinRepository.findById(cousin.getId()).orElseThrow();
    }

    protected void assertPersistedCousinToMatchAllProperties(Cousin expectedCousin) {
        assertCousinAllPropertiesEquals(expectedCousin, getPersistedCousin(expectedCousin));
    }

    protected void assertPersistedCousinToMatchUpdatableProperties(Cousin expectedCousin) {
        assertCousinAllUpdatablePropertiesEquals(expectedCousin, getPersistedCousin(expectedCousin));
    }
}
