package tn.tfar.fanconi.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tn.tfar.fanconi.domain.AndrogeneAsserts.*;
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
import tn.tfar.fanconi.domain.Androgene;
import tn.tfar.fanconi.repository.AndrogeneRepository;

/**
 * Integration tests for the {@link AndrogeneResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AndrogeneResourceIT {

    private static final String DEFAULT_N_DOSSIER_PA = "AAAAAAAAAA";
    private static final String UPDATED_N_DOSSIER_PA = "BBBBBBBBBB";

    private static final Integer DEFAULT_MOIS = 1;
    private static final Integer UPDATED_MOIS = 2;

    private static final String DEFAULT_REPONSE = "AAAAAAAAAA";
    private static final String UPDATED_REPONSE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/androgenes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private AndrogeneRepository androgeneRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAndrogeneMockMvc;

    private Androgene androgene;

    private Androgene insertedAndrogene;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Androgene createEntity() {
        return new Androgene().nDossierPa(DEFAULT_N_DOSSIER_PA).mois(DEFAULT_MOIS).reponse(DEFAULT_REPONSE);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Androgene createUpdatedEntity() {
        return new Androgene().nDossierPa(UPDATED_N_DOSSIER_PA).mois(UPDATED_MOIS).reponse(UPDATED_REPONSE);
    }

    @BeforeEach
    public void initTest() {
        androgene = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedAndrogene != null) {
            androgeneRepository.delete(insertedAndrogene);
            insertedAndrogene = null;
        }
    }

    @Test
    @Transactional
    void createAndrogene() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Androgene
        var returnedAndrogene = om.readValue(
            restAndrogeneMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(androgene)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Androgene.class
        );

        // Validate the Androgene in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertAndrogeneUpdatableFieldsEquals(returnedAndrogene, getPersistedAndrogene(returnedAndrogene));

        insertedAndrogene = returnedAndrogene;
    }

    @Test
    @Transactional
    void createAndrogeneWithExistingId() throws Exception {
        // Create the Androgene with an existing ID
        androgene.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAndrogeneMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(androgene)))
            .andExpect(status().isBadRequest());

        // Validate the Androgene in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAndrogenes() throws Exception {
        // Initialize the database
        insertedAndrogene = androgeneRepository.saveAndFlush(androgene);

        // Get all the androgeneList
        restAndrogeneMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(androgene.getId().intValue())))
            .andExpect(jsonPath("$.[*].nDossierPa").value(hasItem(DEFAULT_N_DOSSIER_PA)))
            .andExpect(jsonPath("$.[*].mois").value(hasItem(DEFAULT_MOIS)))
            .andExpect(jsonPath("$.[*].reponse").value(hasItem(DEFAULT_REPONSE)));
    }

    @Test
    @Transactional
    void getAndrogene() throws Exception {
        // Initialize the database
        insertedAndrogene = androgeneRepository.saveAndFlush(androgene);

        // Get the androgene
        restAndrogeneMockMvc
            .perform(get(ENTITY_API_URL_ID, androgene.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(androgene.getId().intValue()))
            .andExpect(jsonPath("$.nDossierPa").value(DEFAULT_N_DOSSIER_PA))
            .andExpect(jsonPath("$.mois").value(DEFAULT_MOIS))
            .andExpect(jsonPath("$.reponse").value(DEFAULT_REPONSE));
    }

    @Test
    @Transactional
    void getNonExistingAndrogene() throws Exception {
        // Get the androgene
        restAndrogeneMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAndrogene() throws Exception {
        // Initialize the database
        insertedAndrogene = androgeneRepository.saveAndFlush(androgene);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the androgene
        Androgene updatedAndrogene = androgeneRepository.findById(androgene.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedAndrogene are not directly saved in db
        em.detach(updatedAndrogene);
        updatedAndrogene.nDossierPa(UPDATED_N_DOSSIER_PA).mois(UPDATED_MOIS).reponse(UPDATED_REPONSE);

        restAndrogeneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedAndrogene.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedAndrogene))
            )
            .andExpect(status().isOk());

        // Validate the Androgene in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedAndrogeneToMatchAllProperties(updatedAndrogene);
    }

    @Test
    @Transactional
    void putNonExistingAndrogene() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        androgene.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAndrogeneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, androgene.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(androgene))
            )
            .andExpect(status().isBadRequest());

        // Validate the Androgene in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAndrogene() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        androgene.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAndrogeneMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(androgene))
            )
            .andExpect(status().isBadRequest());

        // Validate the Androgene in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAndrogene() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        androgene.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAndrogeneMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(androgene)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Androgene in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAndrogeneWithPatch() throws Exception {
        // Initialize the database
        insertedAndrogene = androgeneRepository.saveAndFlush(androgene);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the androgene using partial update
        Androgene partialUpdatedAndrogene = new Androgene();
        partialUpdatedAndrogene.setId(androgene.getId());

        partialUpdatedAndrogene.reponse(UPDATED_REPONSE);

        restAndrogeneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAndrogene.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAndrogene))
            )
            .andExpect(status().isOk());

        // Validate the Androgene in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAndrogeneUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedAndrogene, androgene),
            getPersistedAndrogene(androgene)
        );
    }

    @Test
    @Transactional
    void fullUpdateAndrogeneWithPatch() throws Exception {
        // Initialize the database
        insertedAndrogene = androgeneRepository.saveAndFlush(androgene);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the androgene using partial update
        Androgene partialUpdatedAndrogene = new Androgene();
        partialUpdatedAndrogene.setId(androgene.getId());

        partialUpdatedAndrogene.nDossierPa(UPDATED_N_DOSSIER_PA).mois(UPDATED_MOIS).reponse(UPDATED_REPONSE);

        restAndrogeneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAndrogene.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedAndrogene))
            )
            .andExpect(status().isOk());

        // Validate the Androgene in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertAndrogeneUpdatableFieldsEquals(partialUpdatedAndrogene, getPersistedAndrogene(partialUpdatedAndrogene));
    }

    @Test
    @Transactional
    void patchNonExistingAndrogene() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        androgene.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAndrogeneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, androgene.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(androgene))
            )
            .andExpect(status().isBadRequest());

        // Validate the Androgene in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAndrogene() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        androgene.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAndrogeneMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(androgene))
            )
            .andExpect(status().isBadRequest());

        // Validate the Androgene in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAndrogene() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        androgene.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAndrogeneMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(androgene)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Androgene in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAndrogene() throws Exception {
        // Initialize the database
        insertedAndrogene = androgeneRepository.saveAndFlush(androgene);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the androgene
        restAndrogeneMockMvc
            .perform(delete(ENTITY_API_URL_ID, androgene.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return androgeneRepository.count();
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

    protected Androgene getPersistedAndrogene(Androgene androgene) {
        return androgeneRepository.findById(androgene.getId()).orElseThrow();
    }

    protected void assertPersistedAndrogeneToMatchAllProperties(Androgene expectedAndrogene) {
        assertAndrogeneAllPropertiesEquals(expectedAndrogene, getPersistedAndrogene(expectedAndrogene));
    }

    protected void assertPersistedAndrogeneToMatchUpdatableProperties(Androgene expectedAndrogene) {
        assertAndrogeneAllUpdatablePropertiesEquals(expectedAndrogene, getPersistedAndrogene(expectedAndrogene));
    }
}
