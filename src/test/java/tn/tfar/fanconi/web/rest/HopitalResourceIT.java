package tn.tfar.fanconi.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tn.tfar.fanconi.domain.HopitalAsserts.*;
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
import tn.tfar.fanconi.domain.Hopital;
import tn.tfar.fanconi.repository.HopitalRepository;

/**
 * Integration tests for the {@link HopitalResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class HopitalResourceIT {

    private static final Integer DEFAULT_CODE_HOPITAL = 1;
    private static final Integer UPDATED_CODE_HOPITAL = 2;

    private static final String DEFAULT_NOM_HOPITAL = "AAAAAAAAAA";
    private static final String UPDATED_NOM_HOPITAL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/hopitals";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private HopitalRepository hopitalRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restHopitalMockMvc;

    private Hopital hopital;

    private Hopital insertedHopital;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Hopital createEntity() {
        return new Hopital().codeHopital(DEFAULT_CODE_HOPITAL).nomHopital(DEFAULT_NOM_HOPITAL);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Hopital createUpdatedEntity() {
        return new Hopital().codeHopital(UPDATED_CODE_HOPITAL).nomHopital(UPDATED_NOM_HOPITAL);
    }

    @BeforeEach
    public void initTest() {
        hopital = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedHopital != null) {
            hopitalRepository.delete(insertedHopital);
            insertedHopital = null;
        }
    }

    @Test
    @Transactional
    void createHopital() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Hopital
        var returnedHopital = om.readValue(
            restHopitalMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hopital)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Hopital.class
        );

        // Validate the Hopital in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertHopitalUpdatableFieldsEquals(returnedHopital, getPersistedHopital(returnedHopital));

        insertedHopital = returnedHopital;
    }

    @Test
    @Transactional
    void createHopitalWithExistingId() throws Exception {
        // Create the Hopital with an existing ID
        hopital.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restHopitalMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hopital)))
            .andExpect(status().isBadRequest());

        // Validate the Hopital in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllHopitals() throws Exception {
        // Initialize the database
        insertedHopital = hopitalRepository.saveAndFlush(hopital);

        // Get all the hopitalList
        restHopitalMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(hopital.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeHopital").value(hasItem(DEFAULT_CODE_HOPITAL)))
            .andExpect(jsonPath("$.[*].nomHopital").value(hasItem(DEFAULT_NOM_HOPITAL)));
    }

    @Test
    @Transactional
    void getHopital() throws Exception {
        // Initialize the database
        insertedHopital = hopitalRepository.saveAndFlush(hopital);

        // Get the hopital
        restHopitalMockMvc
            .perform(get(ENTITY_API_URL_ID, hopital.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(hopital.getId().intValue()))
            .andExpect(jsonPath("$.codeHopital").value(DEFAULT_CODE_HOPITAL))
            .andExpect(jsonPath("$.nomHopital").value(DEFAULT_NOM_HOPITAL));
    }

    @Test
    @Transactional
    void getNonExistingHopital() throws Exception {
        // Get the hopital
        restHopitalMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingHopital() throws Exception {
        // Initialize the database
        insertedHopital = hopitalRepository.saveAndFlush(hopital);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hopital
        Hopital updatedHopital = hopitalRepository.findById(hopital.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedHopital are not directly saved in db
        em.detach(updatedHopital);
        updatedHopital.codeHopital(UPDATED_CODE_HOPITAL).nomHopital(UPDATED_NOM_HOPITAL);

        restHopitalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedHopital.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedHopital))
            )
            .andExpect(status().isOk());

        // Validate the Hopital in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedHopitalToMatchAllProperties(updatedHopital);
    }

    @Test
    @Transactional
    void putNonExistingHopital() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hopital.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHopitalMockMvc
            .perform(put(ENTITY_API_URL_ID, hopital.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hopital)))
            .andExpect(status().isBadRequest());

        // Validate the Hopital in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchHopital() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hopital.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHopitalMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(hopital))
            )
            .andExpect(status().isBadRequest());

        // Validate the Hopital in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamHopital() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hopital.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHopitalMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(hopital)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Hopital in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateHopitalWithPatch() throws Exception {
        // Initialize the database
        insertedHopital = hopitalRepository.saveAndFlush(hopital);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hopital using partial update
        Hopital partialUpdatedHopital = new Hopital();
        partialUpdatedHopital.setId(hopital.getId());

        partialUpdatedHopital.nomHopital(UPDATED_NOM_HOPITAL);

        restHopitalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHopital.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedHopital))
            )
            .andExpect(status().isOk());

        // Validate the Hopital in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertHopitalUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedHopital, hopital), getPersistedHopital(hopital));
    }

    @Test
    @Transactional
    void fullUpdateHopitalWithPatch() throws Exception {
        // Initialize the database
        insertedHopital = hopitalRepository.saveAndFlush(hopital);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the hopital using partial update
        Hopital partialUpdatedHopital = new Hopital();
        partialUpdatedHopital.setId(hopital.getId());

        partialUpdatedHopital.codeHopital(UPDATED_CODE_HOPITAL).nomHopital(UPDATED_NOM_HOPITAL);

        restHopitalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedHopital.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedHopital))
            )
            .andExpect(status().isOk());

        // Validate the Hopital in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertHopitalUpdatableFieldsEquals(partialUpdatedHopital, getPersistedHopital(partialUpdatedHopital));
    }

    @Test
    @Transactional
    void patchNonExistingHopital() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hopital.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHopitalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, hopital.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(hopital))
            )
            .andExpect(status().isBadRequest());

        // Validate the Hopital in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchHopital() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hopital.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHopitalMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(hopital))
            )
            .andExpect(status().isBadRequest());

        // Validate the Hopital in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamHopital() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        hopital.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restHopitalMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(hopital)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Hopital in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteHopital() throws Exception {
        // Initialize the database
        insertedHopital = hopitalRepository.saveAndFlush(hopital);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the hopital
        restHopitalMockMvc
            .perform(delete(ENTITY_API_URL_ID, hopital.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return hopitalRepository.count();
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

    protected Hopital getPersistedHopital(Hopital hopital) {
        return hopitalRepository.findById(hopital.getId()).orElseThrow();
    }

    protected void assertPersistedHopitalToMatchAllProperties(Hopital expectedHopital) {
        assertHopitalAllPropertiesEquals(expectedHopital, getPersistedHopital(expectedHopital));
    }

    protected void assertPersistedHopitalToMatchUpdatableProperties(Hopital expectedHopital) {
        assertHopitalAllUpdatablePropertiesEquals(expectedHopital, getPersistedHopital(expectedHopital));
    }
}
