package tn.tfar.fanconi.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tn.tfar.fanconi.domain.LaboratoireAsserts.*;
import static tn.tfar.fanconi.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
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
import tn.tfar.fanconi.domain.Laboratoire;
import tn.tfar.fanconi.repository.LaboratoireRepository;

/**
 * Integration tests for the {@link LaboratoireResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LaboratoireResourceIT {

    private static final String DEFAULT_NOM_LABORATOIRE = "AAAAAAAAAA";
    private static final String UPDATED_NOM_LABORATOIRE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/laboratoires";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private LaboratoireRepository laboratoireRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLaboratoireMockMvc;

    private Laboratoire laboratoire;

    private Laboratoire insertedLaboratoire;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Laboratoire createEntity() {
        return new Laboratoire().nomLaboratoire(DEFAULT_NOM_LABORATOIRE);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Laboratoire createUpdatedEntity() {
        return new Laboratoire().nomLaboratoire(UPDATED_NOM_LABORATOIRE);
    }

    @BeforeEach
    public void initTest() {
        laboratoire = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedLaboratoire != null) {
            laboratoireRepository.delete(insertedLaboratoire);
            insertedLaboratoire = null;
        }
    }

    @Test
    @Transactional
    void createLaboratoire() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Laboratoire
        var returnedLaboratoire = om.readValue(
            restLaboratoireMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(laboratoire)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Laboratoire.class
        );

        // Validate the Laboratoire in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertLaboratoireUpdatableFieldsEquals(returnedLaboratoire, getPersistedLaboratoire(returnedLaboratoire));

        insertedLaboratoire = returnedLaboratoire;
    }

    @Test
    @Transactional
    void createLaboratoireWithExistingId() throws Exception {
        // Create the Laboratoire with an existing ID
        laboratoire.setId(1);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLaboratoireMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(laboratoire)))
            .andExpect(status().isBadRequest());

        // Validate the Laboratoire in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllLaboratoires() throws Exception {
        // Initialize the database
        insertedLaboratoire = laboratoireRepository.saveAndFlush(laboratoire);

        // Get all the laboratoireList
        restLaboratoireMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(laboratoire.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomLaboratoire").value(hasItem(DEFAULT_NOM_LABORATOIRE)));
    }

    @Test
    @Transactional
    void getLaboratoire() throws Exception {
        // Initialize the database
        insertedLaboratoire = laboratoireRepository.saveAndFlush(laboratoire);

        // Get the laboratoire
        restLaboratoireMockMvc
            .perform(get(ENTITY_API_URL_ID, laboratoire.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(laboratoire.getId().intValue()))
            .andExpect(jsonPath("$.nomLaboratoire").value(DEFAULT_NOM_LABORATOIRE));
    }

    @Test
    @Transactional
    void getNonExistingLaboratoire() throws Exception {
        // Get the laboratoire
        restLaboratoireMockMvc.perform(get(ENTITY_API_URL_ID, Integer.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingLaboratoire() throws Exception {
        // Initialize the database
        insertedLaboratoire = laboratoireRepository.saveAndFlush(laboratoire);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the laboratoire
        Laboratoire updatedLaboratoire = laboratoireRepository.findById(laboratoire.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedLaboratoire are not directly saved in db
        em.detach(updatedLaboratoire);
        updatedLaboratoire.nomLaboratoire(UPDATED_NOM_LABORATOIRE);

        restLaboratoireMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedLaboratoire.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedLaboratoire))
            )
            .andExpect(status().isOk());

        // Validate the Laboratoire in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedLaboratoireToMatchAllProperties(updatedLaboratoire);
    }

    @Test
    @Transactional
    void putNonExistingLaboratoire() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        laboratoire.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLaboratoireMockMvc
            .perform(
                put(ENTITY_API_URL_ID, laboratoire.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(laboratoire))
            )
            .andExpect(status().isBadRequest());

        // Validate the Laboratoire in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLaboratoire() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        laboratoire.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLaboratoireMockMvc
            .perform(
                put(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(laboratoire))
            )
            .andExpect(status().isBadRequest());

        // Validate the Laboratoire in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLaboratoire() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        laboratoire.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLaboratoireMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(laboratoire)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Laboratoire in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLaboratoireWithPatch() throws Exception {
        // Initialize the database
        insertedLaboratoire = laboratoireRepository.saveAndFlush(laboratoire);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the laboratoire using partial update
        Laboratoire partialUpdatedLaboratoire = new Laboratoire();
        partialUpdatedLaboratoire.setId(laboratoire.getId());

        restLaboratoireMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLaboratoire.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLaboratoire))
            )
            .andExpect(status().isOk());

        // Validate the Laboratoire in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLaboratoireUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedLaboratoire, laboratoire),
            getPersistedLaboratoire(laboratoire)
        );
    }

    @Test
    @Transactional
    void fullUpdateLaboratoireWithPatch() throws Exception {
        // Initialize the database
        insertedLaboratoire = laboratoireRepository.saveAndFlush(laboratoire);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the laboratoire using partial update
        Laboratoire partialUpdatedLaboratoire = new Laboratoire();
        partialUpdatedLaboratoire.setId(laboratoire.getId());

        partialUpdatedLaboratoire.nomLaboratoire(UPDATED_NOM_LABORATOIRE);

        restLaboratoireMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLaboratoire.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedLaboratoire))
            )
            .andExpect(status().isOk());

        // Validate the Laboratoire in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertLaboratoireUpdatableFieldsEquals(partialUpdatedLaboratoire, getPersistedLaboratoire(partialUpdatedLaboratoire));
    }

    @Test
    @Transactional
    void patchNonExistingLaboratoire() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        laboratoire.setId(intCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLaboratoireMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, laboratoire.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(laboratoire))
            )
            .andExpect(status().isBadRequest());

        // Validate the Laboratoire in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLaboratoire() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        laboratoire.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLaboratoireMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, intCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(laboratoire))
            )
            .andExpect(status().isBadRequest());

        // Validate the Laboratoire in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLaboratoire() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        laboratoire.setId(intCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLaboratoireMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(laboratoire)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Laboratoire in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLaboratoire() throws Exception {
        // Initialize the database
        insertedLaboratoire = laboratoireRepository.saveAndFlush(laboratoire);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the laboratoire
        restLaboratoireMockMvc
            .perform(delete(ENTITY_API_URL_ID, laboratoire.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return laboratoireRepository.count();
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

    protected Laboratoire getPersistedLaboratoire(Laboratoire laboratoire) {
        return laboratoireRepository.findById(laboratoire.getId()).orElseThrow();
    }

    protected void assertPersistedLaboratoireToMatchAllProperties(Laboratoire expectedLaboratoire) {
        assertLaboratoireAllPropertiesEquals(expectedLaboratoire, getPersistedLaboratoire(expectedLaboratoire));
    }

    protected void assertPersistedLaboratoireToMatchUpdatableProperties(Laboratoire expectedLaboratoire) {
        assertLaboratoireAllUpdatablePropertiesEquals(expectedLaboratoire, getPersistedLaboratoire(expectedLaboratoire));
    }
}
