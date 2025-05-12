package tn.tfar.fanconi.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tn.tfar.fanconi.domain.MedecinAsserts.*;
import static tn.tfar.fanconi.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.util.Base64;
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
import tn.tfar.fanconi.domain.Medecin;
import tn.tfar.fanconi.repository.MedecinRepository;

/**
 * Integration tests for the {@link MedecinResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class MedecinResourceIT {

    private static final Integer DEFAULT_C_IN = 1;
    private static final Integer UPDATED_C_IN = 2;

    private static final String DEFAULT_NOM_MEDECIN = "AAAAAAAAAA";
    private static final String UPDATED_NOM_MEDECIN = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM_MEDECIN = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM_MEDECIN = "BBBBBBBBBB";

    private static final String DEFAULT_GRADE = "AAAAAAAAAA";
    private static final String UPDATED_GRADE = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_MEDECIN = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_MEDECIN = "BBBBBBBBBB";

    private static final String DEFAULT_GOUVERNORAT_M = "AAAAAAAAAA";
    private static final String UPDATED_GOUVERNORAT_M = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE_M = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE_M = "BBBBBBBBBB";

    private static final String DEFAULT_TEL_M = "AAAAAAAAAA";
    private static final String UPDATED_TEL_M = "BBBBBBBBBB";

    private static final String DEFAULT_POSTE_M = "AAAAAAAAAA";
    private static final String UPDATED_POSTE_M = "BBBBBBBBBB";

    private static final String DEFAULT_FAX_M = "AAAAAAAAAA";
    private static final String UPDATED_FAX_M = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_M = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_M = "BBBBBBBBBB";

    private static final String DEFAULT_HOPITAL = "AAAAAAAAAA";
    private static final String UPDATED_HOPITAL = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE = "BBBBBBBBBB";

    private static final byte[] DEFAULT_IMAGE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_IMAGE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_IMAGE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_IMAGE_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_LOGIN = "AAAAAAAAAA";
    private static final String UPDATED_LOGIN = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWD = "BBBBBBBBBB";

    private static final String DEFAULT_U_RL = "AAAAAAAAAA";
    private static final String UPDATED_U_RL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/medecins";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMedecinMockMvc;

    private Medecin medecin;

    private Medecin insertedMedecin;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Medecin createEntity() {
        return new Medecin()
            .cIN(DEFAULT_C_IN)
            .nomMedecin(DEFAULT_NOM_MEDECIN)
            .prenomMedecin(DEFAULT_PRENOM_MEDECIN)
            .grade(DEFAULT_GRADE)
            .typeMedecin(DEFAULT_TYPE_MEDECIN)
            .gouvernoratM(DEFAULT_GOUVERNORAT_M)
            .adresseM(DEFAULT_ADRESSE_M)
            .telM(DEFAULT_TEL_M)
            .posteM(DEFAULT_POSTE_M)
            .faxM(DEFAULT_FAX_M)
            .emailM(DEFAULT_EMAIL_M)
            .hopital(DEFAULT_HOPITAL)
            .service(DEFAULT_SERVICE)
            .image(DEFAULT_IMAGE)
            .imageContentType(DEFAULT_IMAGE_CONTENT_TYPE)
            .login(DEFAULT_LOGIN)
            .passwd(DEFAULT_PASSWD)
            .uRL(DEFAULT_U_RL);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Medecin createUpdatedEntity() {
        return new Medecin()
            .cIN(UPDATED_C_IN)
            .nomMedecin(UPDATED_NOM_MEDECIN)
            .prenomMedecin(UPDATED_PRENOM_MEDECIN)
            .grade(UPDATED_GRADE)
            .typeMedecin(UPDATED_TYPE_MEDECIN)
            .gouvernoratM(UPDATED_GOUVERNORAT_M)
            .adresseM(UPDATED_ADRESSE_M)
            .telM(UPDATED_TEL_M)
            .posteM(UPDATED_POSTE_M)
            .faxM(UPDATED_FAX_M)
            .emailM(UPDATED_EMAIL_M)
            .hopital(UPDATED_HOPITAL)
            .service(UPDATED_SERVICE)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .login(UPDATED_LOGIN)
            .passwd(UPDATED_PASSWD)
            .uRL(UPDATED_U_RL);
    }

    @BeforeEach
    public void initTest() {
        medecin = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedMedecin != null) {
            medecinRepository.delete(insertedMedecin);
            insertedMedecin = null;
        }
    }

    @Test
    @Transactional
    void createMedecin() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Medecin
        var returnedMedecin = om.readValue(
            restMedecinMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(medecin)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Medecin.class
        );

        // Validate the Medecin in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertMedecinUpdatableFieldsEquals(returnedMedecin, getPersistedMedecin(returnedMedecin));

        insertedMedecin = returnedMedecin;
    }

    @Test
    @Transactional
    void createMedecinWithExistingId() throws Exception {
        // Create the Medecin with an existing ID
        medecin.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedecinMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(medecin)))
            .andExpect(status().isBadRequest());

        // Validate the Medecin in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllMedecins() throws Exception {
        // Initialize the database
        insertedMedecin = medecinRepository.saveAndFlush(medecin);

        // Get all the medecinList
        restMedecinMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medecin.getId().intValue())))
            .andExpect(jsonPath("$.[*].cIN").value(hasItem(DEFAULT_C_IN)))
            .andExpect(jsonPath("$.[*].nomMedecin").value(hasItem(DEFAULT_NOM_MEDECIN)))
            .andExpect(jsonPath("$.[*].prenomMedecin").value(hasItem(DEFAULT_PRENOM_MEDECIN)))
            .andExpect(jsonPath("$.[*].grade").value(hasItem(DEFAULT_GRADE)))
            .andExpect(jsonPath("$.[*].typeMedecin").value(hasItem(DEFAULT_TYPE_MEDECIN)))
            .andExpect(jsonPath("$.[*].gouvernoratM").value(hasItem(DEFAULT_GOUVERNORAT_M)))
            .andExpect(jsonPath("$.[*].adresseM").value(hasItem(DEFAULT_ADRESSE_M)))
            .andExpect(jsonPath("$.[*].telM").value(hasItem(DEFAULT_TEL_M)))
            .andExpect(jsonPath("$.[*].posteM").value(hasItem(DEFAULT_POSTE_M)))
            .andExpect(jsonPath("$.[*].faxM").value(hasItem(DEFAULT_FAX_M)))
            .andExpect(jsonPath("$.[*].emailM").value(hasItem(DEFAULT_EMAIL_M)))
            .andExpect(jsonPath("$.[*].hopital").value(hasItem(DEFAULT_HOPITAL)))
            .andExpect(jsonPath("$.[*].service").value(hasItem(DEFAULT_SERVICE)))
            .andExpect(jsonPath("$.[*].imageContentType").value(hasItem(DEFAULT_IMAGE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].image").value(hasItem(Base64.getEncoder().encodeToString(DEFAULT_IMAGE))))
            .andExpect(jsonPath("$.[*].login").value(hasItem(DEFAULT_LOGIN)))
            .andExpect(jsonPath("$.[*].passwd").value(hasItem(DEFAULT_PASSWD)))
            .andExpect(jsonPath("$.[*].uRL").value(hasItem(DEFAULT_U_RL)));
    }

    @Test
    @Transactional
    void getMedecin() throws Exception {
        // Initialize the database
        insertedMedecin = medecinRepository.saveAndFlush(medecin);

        // Get the medecin
        restMedecinMockMvc
            .perform(get(ENTITY_API_URL_ID, medecin.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(medecin.getId().intValue()))
            .andExpect(jsonPath("$.cIN").value(DEFAULT_C_IN))
            .andExpect(jsonPath("$.nomMedecin").value(DEFAULT_NOM_MEDECIN))
            .andExpect(jsonPath("$.prenomMedecin").value(DEFAULT_PRENOM_MEDECIN))
            .andExpect(jsonPath("$.grade").value(DEFAULT_GRADE))
            .andExpect(jsonPath("$.typeMedecin").value(DEFAULT_TYPE_MEDECIN))
            .andExpect(jsonPath("$.gouvernoratM").value(DEFAULT_GOUVERNORAT_M))
            .andExpect(jsonPath("$.adresseM").value(DEFAULT_ADRESSE_M))
            .andExpect(jsonPath("$.telM").value(DEFAULT_TEL_M))
            .andExpect(jsonPath("$.posteM").value(DEFAULT_POSTE_M))
            .andExpect(jsonPath("$.faxM").value(DEFAULT_FAX_M))
            .andExpect(jsonPath("$.emailM").value(DEFAULT_EMAIL_M))
            .andExpect(jsonPath("$.hopital").value(DEFAULT_HOPITAL))
            .andExpect(jsonPath("$.service").value(DEFAULT_SERVICE))
            .andExpect(jsonPath("$.imageContentType").value(DEFAULT_IMAGE_CONTENT_TYPE))
            .andExpect(jsonPath("$.image").value(Base64.getEncoder().encodeToString(DEFAULT_IMAGE)))
            .andExpect(jsonPath("$.login").value(DEFAULT_LOGIN))
            .andExpect(jsonPath("$.passwd").value(DEFAULT_PASSWD))
            .andExpect(jsonPath("$.uRL").value(DEFAULT_U_RL));
    }

    @Test
    @Transactional
    void getNonExistingMedecin() throws Exception {
        // Get the medecin
        restMedecinMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingMedecin() throws Exception {
        // Initialize the database
        insertedMedecin = medecinRepository.saveAndFlush(medecin);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the medecin
        Medecin updatedMedecin = medecinRepository.findById(medecin.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedMedecin are not directly saved in db
        em.detach(updatedMedecin);
        updatedMedecin
            .cIN(UPDATED_C_IN)
            .nomMedecin(UPDATED_NOM_MEDECIN)
            .prenomMedecin(UPDATED_PRENOM_MEDECIN)
            .grade(UPDATED_GRADE)
            .typeMedecin(UPDATED_TYPE_MEDECIN)
            .gouvernoratM(UPDATED_GOUVERNORAT_M)
            .adresseM(UPDATED_ADRESSE_M)
            .telM(UPDATED_TEL_M)
            .posteM(UPDATED_POSTE_M)
            .faxM(UPDATED_FAX_M)
            .emailM(UPDATED_EMAIL_M)
            .hopital(UPDATED_HOPITAL)
            .service(UPDATED_SERVICE)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .login(UPDATED_LOGIN)
            .passwd(UPDATED_PASSWD)
            .uRL(UPDATED_U_RL);

        restMedecinMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedMedecin.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedMedecin))
            )
            .andExpect(status().isOk());

        // Validate the Medecin in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedMedecinToMatchAllProperties(updatedMedecin);
    }

    @Test
    @Transactional
    void putNonExistingMedecin() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        medecin.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMedecinMockMvc
            .perform(put(ENTITY_API_URL_ID, medecin.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(medecin)))
            .andExpect(status().isBadRequest());

        // Validate the Medecin in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchMedecin() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        medecin.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMedecinMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(medecin))
            )
            .andExpect(status().isBadRequest());

        // Validate the Medecin in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamMedecin() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        medecin.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMedecinMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(medecin)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Medecin in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateMedecinWithPatch() throws Exception {
        // Initialize the database
        insertedMedecin = medecinRepository.saveAndFlush(medecin);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the medecin using partial update
        Medecin partialUpdatedMedecin = new Medecin();
        partialUpdatedMedecin.setId(medecin.getId());

        partialUpdatedMedecin
            .cIN(UPDATED_C_IN)
            .nomMedecin(UPDATED_NOM_MEDECIN)
            .grade(UPDATED_GRADE)
            .typeMedecin(UPDATED_TYPE_MEDECIN)
            .telM(UPDATED_TEL_M)
            .emailM(UPDATED_EMAIL_M)
            .login(UPDATED_LOGIN)
            .passwd(UPDATED_PASSWD);

        restMedecinMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMedecin.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMedecin))
            )
            .andExpect(status().isOk());

        // Validate the Medecin in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMedecinUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedMedecin, medecin), getPersistedMedecin(medecin));
    }

    @Test
    @Transactional
    void fullUpdateMedecinWithPatch() throws Exception {
        // Initialize the database
        insertedMedecin = medecinRepository.saveAndFlush(medecin);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the medecin using partial update
        Medecin partialUpdatedMedecin = new Medecin();
        partialUpdatedMedecin.setId(medecin.getId());

        partialUpdatedMedecin
            .cIN(UPDATED_C_IN)
            .nomMedecin(UPDATED_NOM_MEDECIN)
            .prenomMedecin(UPDATED_PRENOM_MEDECIN)
            .grade(UPDATED_GRADE)
            .typeMedecin(UPDATED_TYPE_MEDECIN)
            .gouvernoratM(UPDATED_GOUVERNORAT_M)
            .adresseM(UPDATED_ADRESSE_M)
            .telM(UPDATED_TEL_M)
            .posteM(UPDATED_POSTE_M)
            .faxM(UPDATED_FAX_M)
            .emailM(UPDATED_EMAIL_M)
            .hopital(UPDATED_HOPITAL)
            .service(UPDATED_SERVICE)
            .image(UPDATED_IMAGE)
            .imageContentType(UPDATED_IMAGE_CONTENT_TYPE)
            .login(UPDATED_LOGIN)
            .passwd(UPDATED_PASSWD)
            .uRL(UPDATED_U_RL);

        restMedecinMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedMedecin.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedMedecin))
            )
            .andExpect(status().isOk());

        // Validate the Medecin in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertMedecinUpdatableFieldsEquals(partialUpdatedMedecin, getPersistedMedecin(partialUpdatedMedecin));
    }

    @Test
    @Transactional
    void patchNonExistingMedecin() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        medecin.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMedecinMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, medecin.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(medecin))
            )
            .andExpect(status().isBadRequest());

        // Validate the Medecin in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchMedecin() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        medecin.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMedecinMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(medecin))
            )
            .andExpect(status().isBadRequest());

        // Validate the Medecin in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamMedecin() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        medecin.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restMedecinMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(medecin)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Medecin in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteMedecin() throws Exception {
        // Initialize the database
        insertedMedecin = medecinRepository.saveAndFlush(medecin);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the medecin
        restMedecinMockMvc
            .perform(delete(ENTITY_API_URL_ID, medecin.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return medecinRepository.count();
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

    protected Medecin getPersistedMedecin(Medecin medecin) {
        return medecinRepository.findById(medecin.getId()).orElseThrow();
    }

    protected void assertPersistedMedecinToMatchAllProperties(Medecin expectedMedecin) {
        assertMedecinAllPropertiesEquals(expectedMedecin, getPersistedMedecin(expectedMedecin));
    }

    protected void assertPersistedMedecinToMatchUpdatableProperties(Medecin expectedMedecin) {
        assertMedecinAllUpdatablePropertiesEquals(expectedMedecin, getPersistedMedecin(expectedMedecin));
    }
}
