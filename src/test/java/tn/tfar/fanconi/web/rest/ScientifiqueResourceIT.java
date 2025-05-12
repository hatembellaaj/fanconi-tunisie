package tn.tfar.fanconi.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tn.tfar.fanconi.domain.ScientifiqueAsserts.*;
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
import tn.tfar.fanconi.domain.Scientifique;
import tn.tfar.fanconi.repository.ScientifiqueRepository;

/**
 * Integration tests for the {@link ScientifiqueResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ScientifiqueResourceIT {

    private static final Integer DEFAULT_CODE_SC = 1;
    private static final Integer UPDATED_CODE_SC = 2;

    private static final String DEFAULT_NOM_SC = "AAAAAAAAAA";
    private static final String UPDATED_NOM_SC = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM_SC = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM_SC = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_SC = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_SC = "BBBBBBBBBB";

    private static final String DEFAULT_CENTRE_SC = "AAAAAAAAAA";
    private static final String UPDATED_CENTRE_SC = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE_SC = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE_SC = "BBBBBBBBBB";

    private static final String DEFAULT_TEL_SC = "AAAAAAAAAA";
    private static final String UPDATED_TEL_SC = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL_SC = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL_SC = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PHOTO_SC = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PHOTO_SC = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PHOTO_SC_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_SC_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_TYPE_SC = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_SC = "BBBBBBBBBB";

    private static final String DEFAULT_LOGIN_SC = "AAAAAAAAAA";
    private static final String UPDATED_LOGIN_SC = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWD_SC = "AAAAAAAAAA";
    private static final String UPDATED_PASSWD_SC = "BBBBBBBBBB";

    private static final String DEFAULT_U_RL = "AAAAAAAAAA";
    private static final String UPDATED_U_RL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/scientifiques";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private ScientifiqueRepository scientifiqueRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restScientifiqueMockMvc;

    private Scientifique scientifique;

    private Scientifique insertedScientifique;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Scientifique createEntity() {
        return new Scientifique()
            .codeSC(DEFAULT_CODE_SC)
            .nomSC(DEFAULT_NOM_SC)
            .prenomSC(DEFAULT_PRENOM_SC)
            .serviceSC(DEFAULT_SERVICE_SC)
            .centreSC(DEFAULT_CENTRE_SC)
            .adresseSC(DEFAULT_ADRESSE_SC)
            .telSC(DEFAULT_TEL_SC)
            .emailSC(DEFAULT_EMAIL_SC)
            .photoSC(DEFAULT_PHOTO_SC)
            .photoSCContentType(DEFAULT_PHOTO_SC_CONTENT_TYPE)
            .typeSC(DEFAULT_TYPE_SC)
            .loginSC(DEFAULT_LOGIN_SC)
            .passwdSC(DEFAULT_PASSWD_SC)
            .uRL(DEFAULT_U_RL);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Scientifique createUpdatedEntity() {
        return new Scientifique()
            .codeSC(UPDATED_CODE_SC)
            .nomSC(UPDATED_NOM_SC)
            .prenomSC(UPDATED_PRENOM_SC)
            .serviceSC(UPDATED_SERVICE_SC)
            .centreSC(UPDATED_CENTRE_SC)
            .adresseSC(UPDATED_ADRESSE_SC)
            .telSC(UPDATED_TEL_SC)
            .emailSC(UPDATED_EMAIL_SC)
            .photoSC(UPDATED_PHOTO_SC)
            .photoSCContentType(UPDATED_PHOTO_SC_CONTENT_TYPE)
            .typeSC(UPDATED_TYPE_SC)
            .loginSC(UPDATED_LOGIN_SC)
            .passwdSC(UPDATED_PASSWD_SC)
            .uRL(UPDATED_U_RL);
    }

    @BeforeEach
    public void initTest() {
        scientifique = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedScientifique != null) {
            scientifiqueRepository.delete(insertedScientifique);
            insertedScientifique = null;
        }
    }

    @Test
    @Transactional
    void createScientifique() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Scientifique
        var returnedScientifique = om.readValue(
            restScientifiqueMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(scientifique)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Scientifique.class
        );

        // Validate the Scientifique in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertScientifiqueUpdatableFieldsEquals(returnedScientifique, getPersistedScientifique(returnedScientifique));

        insertedScientifique = returnedScientifique;
    }

    @Test
    @Transactional
    void createScientifiqueWithExistingId() throws Exception {
        // Create the Scientifique with an existing ID
        scientifique.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restScientifiqueMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(scientifique)))
            .andExpect(status().isBadRequest());

        // Validate the Scientifique in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllScientifiques() throws Exception {
        // Initialize the database
        insertedScientifique = scientifiqueRepository.saveAndFlush(scientifique);

        // Get all the scientifiqueList
        restScientifiqueMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(scientifique.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeSC").value(hasItem(DEFAULT_CODE_SC)))
            .andExpect(jsonPath("$.[*].nomSC").value(hasItem(DEFAULT_NOM_SC)))
            .andExpect(jsonPath("$.[*].prenomSC").value(hasItem(DEFAULT_PRENOM_SC)))
            .andExpect(jsonPath("$.[*].serviceSC").value(hasItem(DEFAULT_SERVICE_SC)))
            .andExpect(jsonPath("$.[*].centreSC").value(hasItem(DEFAULT_CENTRE_SC)))
            .andExpect(jsonPath("$.[*].adresseSC").value(hasItem(DEFAULT_ADRESSE_SC)))
            .andExpect(jsonPath("$.[*].telSC").value(hasItem(DEFAULT_TEL_SC)))
            .andExpect(jsonPath("$.[*].emailSC").value(hasItem(DEFAULT_EMAIL_SC)))
            .andExpect(jsonPath("$.[*].photoSCContentType").value(hasItem(DEFAULT_PHOTO_SC_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photoSC").value(hasItem(Base64.getEncoder().encodeToString(DEFAULT_PHOTO_SC))))
            .andExpect(jsonPath("$.[*].typeSC").value(hasItem(DEFAULT_TYPE_SC)))
            .andExpect(jsonPath("$.[*].loginSC").value(hasItem(DEFAULT_LOGIN_SC)))
            .andExpect(jsonPath("$.[*].passwdSC").value(hasItem(DEFAULT_PASSWD_SC)))
            .andExpect(jsonPath("$.[*].uRL").value(hasItem(DEFAULT_U_RL)));
    }

    @Test
    @Transactional
    void getScientifique() throws Exception {
        // Initialize the database
        insertedScientifique = scientifiqueRepository.saveAndFlush(scientifique);

        // Get the scientifique
        restScientifiqueMockMvc
            .perform(get(ENTITY_API_URL_ID, scientifique.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(scientifique.getId().intValue()))
            .andExpect(jsonPath("$.codeSC").value(DEFAULT_CODE_SC))
            .andExpect(jsonPath("$.nomSC").value(DEFAULT_NOM_SC))
            .andExpect(jsonPath("$.prenomSC").value(DEFAULT_PRENOM_SC))
            .andExpect(jsonPath("$.serviceSC").value(DEFAULT_SERVICE_SC))
            .andExpect(jsonPath("$.centreSC").value(DEFAULT_CENTRE_SC))
            .andExpect(jsonPath("$.adresseSC").value(DEFAULT_ADRESSE_SC))
            .andExpect(jsonPath("$.telSC").value(DEFAULT_TEL_SC))
            .andExpect(jsonPath("$.emailSC").value(DEFAULT_EMAIL_SC))
            .andExpect(jsonPath("$.photoSCContentType").value(DEFAULT_PHOTO_SC_CONTENT_TYPE))
            .andExpect(jsonPath("$.photoSC").value(Base64.getEncoder().encodeToString(DEFAULT_PHOTO_SC)))
            .andExpect(jsonPath("$.typeSC").value(DEFAULT_TYPE_SC))
            .andExpect(jsonPath("$.loginSC").value(DEFAULT_LOGIN_SC))
            .andExpect(jsonPath("$.passwdSC").value(DEFAULT_PASSWD_SC))
            .andExpect(jsonPath("$.uRL").value(DEFAULT_U_RL));
    }

    @Test
    @Transactional
    void getNonExistingScientifique() throws Exception {
        // Get the scientifique
        restScientifiqueMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingScientifique() throws Exception {
        // Initialize the database
        insertedScientifique = scientifiqueRepository.saveAndFlush(scientifique);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the scientifique
        Scientifique updatedScientifique = scientifiqueRepository.findById(scientifique.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedScientifique are not directly saved in db
        em.detach(updatedScientifique);
        updatedScientifique
            .codeSC(UPDATED_CODE_SC)
            .nomSC(UPDATED_NOM_SC)
            .prenomSC(UPDATED_PRENOM_SC)
            .serviceSC(UPDATED_SERVICE_SC)
            .centreSC(UPDATED_CENTRE_SC)
            .adresseSC(UPDATED_ADRESSE_SC)
            .telSC(UPDATED_TEL_SC)
            .emailSC(UPDATED_EMAIL_SC)
            .photoSC(UPDATED_PHOTO_SC)
            .photoSCContentType(UPDATED_PHOTO_SC_CONTENT_TYPE)
            .typeSC(UPDATED_TYPE_SC)
            .loginSC(UPDATED_LOGIN_SC)
            .passwdSC(UPDATED_PASSWD_SC)
            .uRL(UPDATED_U_RL);

        restScientifiqueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedScientifique.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedScientifique))
            )
            .andExpect(status().isOk());

        // Validate the Scientifique in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedScientifiqueToMatchAllProperties(updatedScientifique);
    }

    @Test
    @Transactional
    void putNonExistingScientifique() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        scientifique.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restScientifiqueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, scientifique.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(scientifique))
            )
            .andExpect(status().isBadRequest());

        // Validate the Scientifique in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchScientifique() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        scientifique.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restScientifiqueMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(scientifique))
            )
            .andExpect(status().isBadRequest());

        // Validate the Scientifique in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamScientifique() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        scientifique.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restScientifiqueMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(scientifique)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Scientifique in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateScientifiqueWithPatch() throws Exception {
        // Initialize the database
        insertedScientifique = scientifiqueRepository.saveAndFlush(scientifique);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the scientifique using partial update
        Scientifique partialUpdatedScientifique = new Scientifique();
        partialUpdatedScientifique.setId(scientifique.getId());

        partialUpdatedScientifique
            .codeSC(UPDATED_CODE_SC)
            .nomSC(UPDATED_NOM_SC)
            .prenomSC(UPDATED_PRENOM_SC)
            .serviceSC(UPDATED_SERVICE_SC)
            .telSC(UPDATED_TEL_SC)
            .emailSC(UPDATED_EMAIL_SC)
            .photoSC(UPDATED_PHOTO_SC)
            .photoSCContentType(UPDATED_PHOTO_SC_CONTENT_TYPE)
            .typeSC(UPDATED_TYPE_SC);

        restScientifiqueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedScientifique.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedScientifique))
            )
            .andExpect(status().isOk());

        // Validate the Scientifique in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertScientifiqueUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedScientifique, scientifique),
            getPersistedScientifique(scientifique)
        );
    }

    @Test
    @Transactional
    void fullUpdateScientifiqueWithPatch() throws Exception {
        // Initialize the database
        insertedScientifique = scientifiqueRepository.saveAndFlush(scientifique);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the scientifique using partial update
        Scientifique partialUpdatedScientifique = new Scientifique();
        partialUpdatedScientifique.setId(scientifique.getId());

        partialUpdatedScientifique
            .codeSC(UPDATED_CODE_SC)
            .nomSC(UPDATED_NOM_SC)
            .prenomSC(UPDATED_PRENOM_SC)
            .serviceSC(UPDATED_SERVICE_SC)
            .centreSC(UPDATED_CENTRE_SC)
            .adresseSC(UPDATED_ADRESSE_SC)
            .telSC(UPDATED_TEL_SC)
            .emailSC(UPDATED_EMAIL_SC)
            .photoSC(UPDATED_PHOTO_SC)
            .photoSCContentType(UPDATED_PHOTO_SC_CONTENT_TYPE)
            .typeSC(UPDATED_TYPE_SC)
            .loginSC(UPDATED_LOGIN_SC)
            .passwdSC(UPDATED_PASSWD_SC)
            .uRL(UPDATED_U_RL);

        restScientifiqueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedScientifique.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedScientifique))
            )
            .andExpect(status().isOk());

        // Validate the Scientifique in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertScientifiqueUpdatableFieldsEquals(partialUpdatedScientifique, getPersistedScientifique(partialUpdatedScientifique));
    }

    @Test
    @Transactional
    void patchNonExistingScientifique() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        scientifique.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restScientifiqueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, scientifique.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(scientifique))
            )
            .andExpect(status().isBadRequest());

        // Validate the Scientifique in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchScientifique() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        scientifique.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restScientifiqueMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(scientifique))
            )
            .andExpect(status().isBadRequest());

        // Validate the Scientifique in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamScientifique() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        scientifique.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restScientifiqueMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(scientifique)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Scientifique in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteScientifique() throws Exception {
        // Initialize the database
        insertedScientifique = scientifiqueRepository.saveAndFlush(scientifique);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the scientifique
        restScientifiqueMockMvc
            .perform(delete(ENTITY_API_URL_ID, scientifique.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return scientifiqueRepository.count();
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

    protected Scientifique getPersistedScientifique(Scientifique scientifique) {
        return scientifiqueRepository.findById(scientifique.getId()).orElseThrow();
    }

    protected void assertPersistedScientifiqueToMatchAllProperties(Scientifique expectedScientifique) {
        assertScientifiqueAllPropertiesEquals(expectedScientifique, getPersistedScientifique(expectedScientifique));
    }

    protected void assertPersistedScientifiqueToMatchUpdatableProperties(Scientifique expectedScientifique) {
        assertScientifiqueAllUpdatablePropertiesEquals(expectedScientifique, getPersistedScientifique(expectedScientifique));
    }
}
