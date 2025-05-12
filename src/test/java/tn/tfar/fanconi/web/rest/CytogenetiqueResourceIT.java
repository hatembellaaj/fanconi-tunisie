package tn.tfar.fanconi.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tn.tfar.fanconi.domain.CytogenetiqueAsserts.*;
import static tn.tfar.fanconi.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
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
import tn.tfar.fanconi.domain.Cytogenetique;
import tn.tfar.fanconi.repository.CytogenetiqueRepository;

/**
 * Integration tests for the {@link CytogenetiqueResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CytogenetiqueResourceIT {

    private static final String DEFAULT_N_DOSSIER_PATIENT = "AAAAAAAAAA";
    private static final String UPDATED_N_DOSSIER_PATIENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_N_ETUDE_CYTO = 1;
    private static final Integer UPDATED_N_ETUDE_CYTO = 2;

    private static final String DEFAULT_LYMPHOCYTES = "AAAAAAAAAA";
    private static final String UPDATED_LYMPHOCYTES = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_SANG = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_SANG = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_LABORATOIRE = "AAAAAAAAAA";
    private static final String UPDATED_LABORATOIRE = "BBBBBBBBBB";

    private static final String DEFAULT_AGENT_PONTANT = "AAAAAAAAAA";
    private static final String UPDATED_AGENT_PONTANT = "BBBBBBBBBB";

    private static final String DEFAULT_INSTABILITE = "AAAAAAAAAA";
    private static final String UPDATED_INSTABILITE = "BBBBBBBBBB";

    private static final Float DEFAULT_INSTABILITE_POURCENTAGE = 1F;
    private static final Float UPDATED_INSTABILITE_POURCENTAGE = 2F;

    private static final String DEFAULT_I_R = "AAAAAAAAAA";
    private static final String UPDATED_I_R = "BBBBBBBBBB";

    private static final Float DEFAULT_I_R_POURCENTAGE = 1F;
    private static final Float UPDATED_I_R_POURCENTAGE = 2F;

    private static final String DEFAULT_MOELLE = "AAAAAAAAAA";
    private static final String UPDATED_MOELLE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_MOELLE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_MOELLE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_RESULTAT_MOELLE = "AAAAAAAAAA";
    private static final String UPDATED_RESULTAT_MOELLE = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/cytogenetiques";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CytogenetiqueRepository cytogenetiqueRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCytogenetiqueMockMvc;

    private Cytogenetique cytogenetique;

    private Cytogenetique insertedCytogenetique;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cytogenetique createEntity() {
        return new Cytogenetique()
            .nDossierPatient(DEFAULT_N_DOSSIER_PATIENT)
            .nEtudeCyto(DEFAULT_N_ETUDE_CYTO)
            .lymphocytes(DEFAULT_LYMPHOCYTES)
            .dateSang(DEFAULT_DATE_SANG)
            .laboratoire(DEFAULT_LABORATOIRE)
            .agentPontant(DEFAULT_AGENT_PONTANT)
            .instabilite(DEFAULT_INSTABILITE)
            .instabilitePourcentage(DEFAULT_INSTABILITE_POURCENTAGE)
            .iR(DEFAULT_I_R)
            .iRPourcentage(DEFAULT_I_R_POURCENTAGE)
            .moelle(DEFAULT_MOELLE)
            .dateMoelle(DEFAULT_DATE_MOELLE)
            .resultatMoelle(DEFAULT_RESULTAT_MOELLE);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cytogenetique createUpdatedEntity() {
        return new Cytogenetique()
            .nDossierPatient(UPDATED_N_DOSSIER_PATIENT)
            .nEtudeCyto(UPDATED_N_ETUDE_CYTO)
            .lymphocytes(UPDATED_LYMPHOCYTES)
            .dateSang(UPDATED_DATE_SANG)
            .laboratoire(UPDATED_LABORATOIRE)
            .agentPontant(UPDATED_AGENT_PONTANT)
            .instabilite(UPDATED_INSTABILITE)
            .instabilitePourcentage(UPDATED_INSTABILITE_POURCENTAGE)
            .iR(UPDATED_I_R)
            .iRPourcentage(UPDATED_I_R_POURCENTAGE)
            .moelle(UPDATED_MOELLE)
            .dateMoelle(UPDATED_DATE_MOELLE)
            .resultatMoelle(UPDATED_RESULTAT_MOELLE);
    }

    @BeforeEach
    public void initTest() {
        cytogenetique = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedCytogenetique != null) {
            cytogenetiqueRepository.delete(insertedCytogenetique);
            insertedCytogenetique = null;
        }
    }

    @Test
    @Transactional
    void createCytogenetique() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Cytogenetique
        var returnedCytogenetique = om.readValue(
            restCytogenetiqueMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cytogenetique)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Cytogenetique.class
        );

        // Validate the Cytogenetique in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCytogenetiqueUpdatableFieldsEquals(returnedCytogenetique, getPersistedCytogenetique(returnedCytogenetique));

        insertedCytogenetique = returnedCytogenetique;
    }

    @Test
    @Transactional
    void createCytogenetiqueWithExistingId() throws Exception {
        // Create the Cytogenetique with an existing ID
        cytogenetique.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCytogenetiqueMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cytogenetique)))
            .andExpect(status().isBadRequest());

        // Validate the Cytogenetique in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCytogenetiques() throws Exception {
        // Initialize the database
        insertedCytogenetique = cytogenetiqueRepository.saveAndFlush(cytogenetique);

        // Get all the cytogenetiqueList
        restCytogenetiqueMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cytogenetique.getId().intValue())))
            .andExpect(jsonPath("$.[*].nDossierPatient").value(hasItem(DEFAULT_N_DOSSIER_PATIENT)))
            .andExpect(jsonPath("$.[*].nEtudeCyto").value(hasItem(DEFAULT_N_ETUDE_CYTO)))
            .andExpect(jsonPath("$.[*].lymphocytes").value(hasItem(DEFAULT_LYMPHOCYTES)))
            .andExpect(jsonPath("$.[*].dateSang").value(hasItem(DEFAULT_DATE_SANG.toString())))
            .andExpect(jsonPath("$.[*].laboratoire").value(hasItem(DEFAULT_LABORATOIRE)))
            .andExpect(jsonPath("$.[*].agentPontant").value(hasItem(DEFAULT_AGENT_PONTANT)))
            .andExpect(jsonPath("$.[*].instabilite").value(hasItem(DEFAULT_INSTABILITE)))
            .andExpect(jsonPath("$.[*].instabilitePourcentage").value(hasItem(DEFAULT_INSTABILITE_POURCENTAGE.doubleValue())))
            .andExpect(jsonPath("$.[*].iR").value(hasItem(DEFAULT_I_R)))
            .andExpect(jsonPath("$.[*].iRPourcentage").value(hasItem(DEFAULT_I_R_POURCENTAGE.doubleValue())))
            .andExpect(jsonPath("$.[*].moelle").value(hasItem(DEFAULT_MOELLE)))
            .andExpect(jsonPath("$.[*].dateMoelle").value(hasItem(DEFAULT_DATE_MOELLE.toString())))
            .andExpect(jsonPath("$.[*].resultatMoelle").value(hasItem(DEFAULT_RESULTAT_MOELLE)));
    }

    @Test
    @Transactional
    void getCytogenetique() throws Exception {
        // Initialize the database
        insertedCytogenetique = cytogenetiqueRepository.saveAndFlush(cytogenetique);

        // Get the cytogenetique
        restCytogenetiqueMockMvc
            .perform(get(ENTITY_API_URL_ID, cytogenetique.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cytogenetique.getId().intValue()))
            .andExpect(jsonPath("$.nDossierPatient").value(DEFAULT_N_DOSSIER_PATIENT))
            .andExpect(jsonPath("$.nEtudeCyto").value(DEFAULT_N_ETUDE_CYTO))
            .andExpect(jsonPath("$.lymphocytes").value(DEFAULT_LYMPHOCYTES))
            .andExpect(jsonPath("$.dateSang").value(DEFAULT_DATE_SANG.toString()))
            .andExpect(jsonPath("$.laboratoire").value(DEFAULT_LABORATOIRE))
            .andExpect(jsonPath("$.agentPontant").value(DEFAULT_AGENT_PONTANT))
            .andExpect(jsonPath("$.instabilite").value(DEFAULT_INSTABILITE))
            .andExpect(jsonPath("$.instabilitePourcentage").value(DEFAULT_INSTABILITE_POURCENTAGE.doubleValue()))
            .andExpect(jsonPath("$.iR").value(DEFAULT_I_R))
            .andExpect(jsonPath("$.iRPourcentage").value(DEFAULT_I_R_POURCENTAGE.doubleValue()))
            .andExpect(jsonPath("$.moelle").value(DEFAULT_MOELLE))
            .andExpect(jsonPath("$.dateMoelle").value(DEFAULT_DATE_MOELLE.toString()))
            .andExpect(jsonPath("$.resultatMoelle").value(DEFAULT_RESULTAT_MOELLE));
    }

    @Test
    @Transactional
    void getNonExistingCytogenetique() throws Exception {
        // Get the cytogenetique
        restCytogenetiqueMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCytogenetique() throws Exception {
        // Initialize the database
        insertedCytogenetique = cytogenetiqueRepository.saveAndFlush(cytogenetique);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cytogenetique
        Cytogenetique updatedCytogenetique = cytogenetiqueRepository.findById(cytogenetique.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCytogenetique are not directly saved in db
        em.detach(updatedCytogenetique);
        updatedCytogenetique
            .nDossierPatient(UPDATED_N_DOSSIER_PATIENT)
            .nEtudeCyto(UPDATED_N_ETUDE_CYTO)
            .lymphocytes(UPDATED_LYMPHOCYTES)
            .dateSang(UPDATED_DATE_SANG)
            .laboratoire(UPDATED_LABORATOIRE)
            .agentPontant(UPDATED_AGENT_PONTANT)
            .instabilite(UPDATED_INSTABILITE)
            .instabilitePourcentage(UPDATED_INSTABILITE_POURCENTAGE)
            .iR(UPDATED_I_R)
            .iRPourcentage(UPDATED_I_R_POURCENTAGE)
            .moelle(UPDATED_MOELLE)
            .dateMoelle(UPDATED_DATE_MOELLE)
            .resultatMoelle(UPDATED_RESULTAT_MOELLE);

        restCytogenetiqueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCytogenetique.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCytogenetique))
            )
            .andExpect(status().isOk());

        // Validate the Cytogenetique in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCytogenetiqueToMatchAllProperties(updatedCytogenetique);
    }

    @Test
    @Transactional
    void putNonExistingCytogenetique() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cytogenetique.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCytogenetiqueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cytogenetique.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(cytogenetique))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cytogenetique in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCytogenetique() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cytogenetique.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCytogenetiqueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(cytogenetique))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cytogenetique in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCytogenetique() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cytogenetique.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCytogenetiqueMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cytogenetique)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Cytogenetique in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCytogenetiqueWithPatch() throws Exception {
        // Initialize the database
        insertedCytogenetique = cytogenetiqueRepository.saveAndFlush(cytogenetique);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cytogenetique using partial update
        Cytogenetique partialUpdatedCytogenetique = new Cytogenetique();
        partialUpdatedCytogenetique.setId(cytogenetique.getId());

        partialUpdatedCytogenetique
            .nEtudeCyto(UPDATED_N_ETUDE_CYTO)
            .lymphocytes(UPDATED_LYMPHOCYTES)
            .dateSang(UPDATED_DATE_SANG)
            .instabilite(UPDATED_INSTABILITE)
            .instabilitePourcentage(UPDATED_INSTABILITE_POURCENTAGE)
            .iR(UPDATED_I_R)
            .iRPourcentage(UPDATED_I_R_POURCENTAGE)
            .moelle(UPDATED_MOELLE)
            .dateMoelle(UPDATED_DATE_MOELLE);

        restCytogenetiqueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCytogenetique.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCytogenetique))
            )
            .andExpect(status().isOk());

        // Validate the Cytogenetique in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCytogenetiqueUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCytogenetique, cytogenetique),
            getPersistedCytogenetique(cytogenetique)
        );
    }

    @Test
    @Transactional
    void fullUpdateCytogenetiqueWithPatch() throws Exception {
        // Initialize the database
        insertedCytogenetique = cytogenetiqueRepository.saveAndFlush(cytogenetique);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cytogenetique using partial update
        Cytogenetique partialUpdatedCytogenetique = new Cytogenetique();
        partialUpdatedCytogenetique.setId(cytogenetique.getId());

        partialUpdatedCytogenetique
            .nDossierPatient(UPDATED_N_DOSSIER_PATIENT)
            .nEtudeCyto(UPDATED_N_ETUDE_CYTO)
            .lymphocytes(UPDATED_LYMPHOCYTES)
            .dateSang(UPDATED_DATE_SANG)
            .laboratoire(UPDATED_LABORATOIRE)
            .agentPontant(UPDATED_AGENT_PONTANT)
            .instabilite(UPDATED_INSTABILITE)
            .instabilitePourcentage(UPDATED_INSTABILITE_POURCENTAGE)
            .iR(UPDATED_I_R)
            .iRPourcentage(UPDATED_I_R_POURCENTAGE)
            .moelle(UPDATED_MOELLE)
            .dateMoelle(UPDATED_DATE_MOELLE)
            .resultatMoelle(UPDATED_RESULTAT_MOELLE);

        restCytogenetiqueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCytogenetique.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCytogenetique))
            )
            .andExpect(status().isOk());

        // Validate the Cytogenetique in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCytogenetiqueUpdatableFieldsEquals(partialUpdatedCytogenetique, getPersistedCytogenetique(partialUpdatedCytogenetique));
    }

    @Test
    @Transactional
    void patchNonExistingCytogenetique() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cytogenetique.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCytogenetiqueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, cytogenetique.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(cytogenetique))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cytogenetique in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCytogenetique() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cytogenetique.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCytogenetiqueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(cytogenetique))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cytogenetique in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCytogenetique() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cytogenetique.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCytogenetiqueMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(cytogenetique)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Cytogenetique in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCytogenetique() throws Exception {
        // Initialize the database
        insertedCytogenetique = cytogenetiqueRepository.saveAndFlush(cytogenetique);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the cytogenetique
        restCytogenetiqueMockMvc
            .perform(delete(ENTITY_API_URL_ID, cytogenetique.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return cytogenetiqueRepository.count();
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

    protected Cytogenetique getPersistedCytogenetique(Cytogenetique cytogenetique) {
        return cytogenetiqueRepository.findById(cytogenetique.getId()).orElseThrow();
    }

    protected void assertPersistedCytogenetiqueToMatchAllProperties(Cytogenetique expectedCytogenetique) {
        assertCytogenetiqueAllPropertiesEquals(expectedCytogenetique, getPersistedCytogenetique(expectedCytogenetique));
    }

    protected void assertPersistedCytogenetiqueToMatchUpdatableProperties(Cytogenetique expectedCytogenetique) {
        assertCytogenetiqueAllUpdatablePropertiesEquals(expectedCytogenetique, getPersistedCytogenetique(expectedCytogenetique));
    }
}
