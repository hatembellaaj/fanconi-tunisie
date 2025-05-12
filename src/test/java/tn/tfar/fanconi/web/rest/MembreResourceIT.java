package tn.tfar.fanconi.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tn.tfar.fanconi.domain.MembreAsserts.*;
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
import tn.tfar.fanconi.domain.Membre;
import tn.tfar.fanconi.repository.MembreRepository;

/**
 * Integration tests for the {@link MembreResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MembreResourceIT {

    private static final String DEFAULT_N_DOSSIER_M = "AAAAAAAAAA";
    private static final String UPDATED_N_DOSSIER_M = "BBBBBBBBBB";

    private static final Integer DEFAULT_ID_MEMBRE = 1;
    private static final Integer UPDATED_ID_MEMBRE = 2;

    private static final String DEFAULT_PRENOM_M = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM_M = "BBBBBBBBBB";

    private static final String DEFAULT_LIEN_PARENTE = "AAAAAAAAAA";
    private static final String UPDATED_LIEN_PARENTE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_CANCER_M = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_CANCER_M = "BBBBBBBBBB";

    private static final Integer DEFAULT_AGE_DECOUVERTE_M = 1;
    private static final Integer UPDATED_AGE_DECOUVERTE_M = 2;

    private static final String ENTITY_API_URL = "/api/membres";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MembreRepository membreRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMembreMockMvc;

    private Membre membre;

    private Membre insertedMembre;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Membre createEntity() {
        return new Membre()
            .nDossierM(DEFAULT_N_DOSSIER_M)
            .idMembre(DEFAULT_ID_MEMBRE)
            .prenomM(DEFAULT_PRENOM_M)
            .lienParente(DEFAULT_LIEN_PARENTE)
            .typeCancerM(DEFAULT_TYPE_CANCER_M)
            .ageDecouverteM(DEFAULT_AGE_DECOUVERTE_M);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Membre createUpdatedEntity() {
        return new Membre()
            .nDossierM(UPDATED_N_DOSSIER_M)
            .idMembre(UPDATED_ID_MEMBRE)
            .prenomM(UPDATED_PRENOM_M)
            .lienParente(UPDATED_LIEN_PARENTE)
            .typeCancerM(UPDATED_TYPE_CANCER_M)
            .ageDecouverteM(UPDATED_AGE_DECOUVERTE_M);
    }

    @BeforeEach
    public void initTest() {
        membre = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedMembre != null) {
            membreRepository.delete(insertedMembre);
            insertedMembre = null;
        }
    }

    @Test
    @Transactional
    void createMembre() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Membre
        var returnedMembre = om.readValue(
            restMembreMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(membre)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Membre.class
        );

        // Validate the Membre in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertMembreUpdatableFieldsEquals(returnedMembre, getPersistedMembre(returnedMembre));

        insertedMembre = returnedMembre;
    }

    @Test
    @Transactional
    void createMembreWithExistingId() throws Exception {
        // Create the Membre with an existing ID
        membre.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMembreMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(membre)))
            .andExpect(status().isBadRequest());

        // Validate the Membre in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllMembres() throws Exception {
        // Initialize the database
        insertedMembre = membreRepository.saveAndFlush(membre);

        // Get all the membreList
        restMembreMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(membre.getId().intValue())))
            .andExpect(jsonPath("$.[*].nDossierM").value(hasItem(DEFAULT_N_DOSSIER_M)))
            .andExpect(jsonPath("$.[*].idMembre").value(hasItem(DEFAULT_ID_MEMBRE)))
            .andExpect(jsonPath("$.[*].prenomM").value(hasItem(DEFAULT_PRENOM_M)))
            .andExpect(jsonPath("$.[*].lienParente").value(hasItem(DEFAULT_LIEN_PARENTE)))
            .andExpect(jsonPath("$.[*].typeCancerM").value(hasItem(DEFAULT_TYPE_CANCER_M)))
            .andExpect(jsonPath("$.[*].ageDecouverteM").value(hasItem(DEFAULT_AGE_DECOUVERTE_M)));
    }

    @Test
    @Transactional
    void getMembre() throws Exception {
        // Initialize the database
        insertedMembre = membreRepository.saveAndFlush(membre);

        // Get the membre
        restMembreMockMvc
            .perform(get(ENTITY_API_URL_ID, membre.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(membre.getId().intValue()))
            .andExpect(jsonPath("$.nDossierM").value(DEFAULT_N_DOSSIER_M))
            .andExpect(jsonPath("$.idMembre").value(DEFAULT_ID_MEMBRE))
            .andExpect(jsonPath("$.prenomM").value(DEFAULT_PRENOM_M))
            .andExpect(jsonPath("$.lienParente").value(DEFAULT_LIEN_PARENTE))
            .andExpect(jsonPath("$.typeCancerM").value(DEFAULT_TYPE_CANCER_M))
            .andExpect(jsonPath("$.ageDecouverteM").value(DEFAULT_AGE_DECOUVERTE_M));
    }

    @Test
    @Transactional
    void getNonExistingMembre() throws Exception {
        // Get the membre
        restMembreMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingMembre() throws Exception {
        // Initialize the database
        insertedMembre = membreRepository.saveAndFlush(membre);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the membre
        Membre updatedMembre = membreRepository.findById(membre.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedMembre are not directly saved in db
        em.detach(updatedMembre);
        updatedMembre
            .nDossierM(UPDATED_N_DOSSIER_M)
            .idMembre(UPDATED_ID_MEMBRE)
            .prenomM(UPDATED_PRENOM_M)
            .lienParente(UPDATED_LIEN_PARENTE)
            .typeCancerM(UPDATED_TYPE_CANCER_M)
            .ageDecouverteM(UPDATED_AGE_DECOUVERTE_M);

        restMembreMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMembre.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedMembre))
            )
            .andExpect(status().isOk());

        // Validate the Membre in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedMembreToMatchAllProperties(updatedMembre);
    }

    @Test
    @Transactional
    void putNonExistingMembre() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        membre.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMembreMockMvc
            .perform(put(ENTITY_API_URL_ID, membre.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(membre)))
            .andExpect(status().isBadRequest());

        // Validate the Membre in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMembre() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        membre.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMembreMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(membre))
            )
            .andExpect(status().isBadRequest());

        // Validate the Membre in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMembre() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        membre.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMembreMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(membre)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Membre in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMembreWithPatch() throws Exception {
        // Initialize the database
        insertedMembre = membreRepository.saveAndFlush(membre);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the membre using partial update
        Membre partialUpdatedMembre = new Membre();
        partialUpdatedMembre.setId(membre.getId());

        partialUpdatedMembre.idMembre(UPDATED_ID_MEMBRE).typeCancerM(UPDATED_TYPE_CANCER_M).ageDecouverteM(UPDATED_AGE_DECOUVERTE_M);

        restMembreMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMembre.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMembre))
            )
            .andExpect(status().isOk());

        // Validate the Membre in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMembreUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedMembre, membre), getPersistedMembre(membre));
    }

    @Test
    @Transactional
    void fullUpdateMembreWithPatch() throws Exception {
        // Initialize the database
        insertedMembre = membreRepository.saveAndFlush(membre);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the membre using partial update
        Membre partialUpdatedMembre = new Membre();
        partialUpdatedMembre.setId(membre.getId());

        partialUpdatedMembre
            .nDossierM(UPDATED_N_DOSSIER_M)
            .idMembre(UPDATED_ID_MEMBRE)
            .prenomM(UPDATED_PRENOM_M)
            .lienParente(UPDATED_LIEN_PARENTE)
            .typeCancerM(UPDATED_TYPE_CANCER_M)
            .ageDecouverteM(UPDATED_AGE_DECOUVERTE_M);

        restMembreMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMembre.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMembre))
            )
            .andExpect(status().isOk());

        // Validate the Membre in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMembreUpdatableFieldsEquals(partialUpdatedMembre, getPersistedMembre(partialUpdatedMembre));
    }

    @Test
    @Transactional
    void patchNonExistingMembre() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        membre.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMembreMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, membre.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(membre))
            )
            .andExpect(status().isBadRequest());

        // Validate the Membre in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMembre() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        membre.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMembreMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(membre))
            )
            .andExpect(status().isBadRequest());

        // Validate the Membre in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMembre() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        membre.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMembreMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(membre)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Membre in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMembre() throws Exception {
        // Initialize the database
        insertedMembre = membreRepository.saveAndFlush(membre);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the membre
        restMembreMockMvc
            .perform(delete(ENTITY_API_URL_ID, membre.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return membreRepository.count();
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

    protected Membre getPersistedMembre(Membre membre) {
        return membreRepository.findById(membre.getId()).orElseThrow();
    }

    protected void assertPersistedMembreToMatchAllProperties(Membre expectedMembre) {
        assertMembreAllPropertiesEquals(expectedMembre, getPersistedMembre(expectedMembre));
    }

    protected void assertPersistedMembreToMatchUpdatableProperties(Membre expectedMembre) {
        assertMembreAllUpdatablePropertiesEquals(expectedMembre, getPersistedMembre(expectedMembre));
    }
}
