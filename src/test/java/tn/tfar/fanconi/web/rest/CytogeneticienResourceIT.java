package tn.tfar.fanconi.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tn.tfar.fanconi.domain.CytogeneticienAsserts.*;
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
import tn.tfar.fanconi.domain.Cytogeneticien;
import tn.tfar.fanconi.repository.CytogeneticienRepository;

/**
 * Integration tests for the {@link CytogeneticienResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CytogeneticienResourceIT {

    private static final Integer DEFAULT_CODE = 1;
    private static final Integer UPDATED_CODE = 2;

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE = "BBBBBBBBBB";

    private static final String DEFAULT_ETAB = "AAAAAAAAAA";
    private static final String UPDATED_ETAB = "BBBBBBBBBB";

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_POSTE = "AAAAAAAAAA";
    private static final String UPDATED_POSTE = "BBBBBBBBBB";

    private static final String DEFAULT_FAX = "AAAAAAAAAA";
    private static final String UPDATED_FAX = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final byte[] DEFAULT_PHOTO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_PHOTO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_PHOTO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_PHOTO_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_LOGIN = "AAAAAAAAAA";
    private static final String UPDATED_LOGIN = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWD = "BBBBBBBBBB";

    private static final String DEFAULT_U_RL = "AAAAAAAAAA";
    private static final String UPDATED_U_RL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/cytogeneticiens";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private CytogeneticienRepository cytogeneticienRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCytogeneticienMockMvc;

    private Cytogeneticien cytogeneticien;

    private Cytogeneticien insertedCytogeneticien;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Cytogeneticien createEntity() {
        return new Cytogeneticien()
            .code(DEFAULT_CODE)
            .nom(DEFAULT_NOM)
            .prenom(DEFAULT_PRENOM)
            .service(DEFAULT_SERVICE)
            .etab(DEFAULT_ETAB)
            .adresse(DEFAULT_ADRESSE)
            .tel(DEFAULT_TEL)
            .poste(DEFAULT_POSTE)
            .fax(DEFAULT_FAX)
            .email(DEFAULT_EMAIL)
            .photo(DEFAULT_PHOTO)
            .photoContentType(DEFAULT_PHOTO_CONTENT_TYPE)
            .type(DEFAULT_TYPE)
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
    public static Cytogeneticien createUpdatedEntity() {
        return new Cytogeneticien()
            .code(UPDATED_CODE)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .service(UPDATED_SERVICE)
            .etab(UPDATED_ETAB)
            .adresse(UPDATED_ADRESSE)
            .tel(UPDATED_TEL)
            .poste(UPDATED_POSTE)
            .fax(UPDATED_FAX)
            .email(UPDATED_EMAIL)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .type(UPDATED_TYPE)
            .login(UPDATED_LOGIN)
            .passwd(UPDATED_PASSWD)
            .uRL(UPDATED_U_RL);
    }

    @BeforeEach
    public void initTest() {
        cytogeneticien = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedCytogeneticien != null) {
            cytogeneticienRepository.delete(insertedCytogeneticien);
            insertedCytogeneticien = null;
        }
    }

    @Test
    @Transactional
    void createCytogeneticien() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Cytogeneticien
        var returnedCytogeneticien = om.readValue(
            restCytogeneticienMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cytogeneticien)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Cytogeneticien.class
        );

        // Validate the Cytogeneticien in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertCytogeneticienUpdatableFieldsEquals(returnedCytogeneticien, getPersistedCytogeneticien(returnedCytogeneticien));

        insertedCytogeneticien = returnedCytogeneticien;
    }

    @Test
    @Transactional
    void createCytogeneticienWithExistingId() throws Exception {
        // Create the Cytogeneticien with an existing ID
        cytogeneticien.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCytogeneticienMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cytogeneticien)))
            .andExpect(status().isBadRequest());

        // Validate the Cytogeneticien in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCytogeneticiens() throws Exception {
        // Initialize the database
        insertedCytogeneticien = cytogeneticienRepository.saveAndFlush(cytogeneticien);

        // Get all the cytogeneticienList
        restCytogeneticienMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(cytogeneticien.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].service").value(hasItem(DEFAULT_SERVICE)))
            .andExpect(jsonPath("$.[*].etab").value(hasItem(DEFAULT_ETAB)))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].tel").value(hasItem(DEFAULT_TEL)))
            .andExpect(jsonPath("$.[*].poste").value(hasItem(DEFAULT_POSTE)))
            .andExpect(jsonPath("$.[*].fax").value(hasItem(DEFAULT_FAX)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].photoContentType").value(hasItem(DEFAULT_PHOTO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].photo").value(hasItem(Base64.getEncoder().encodeToString(DEFAULT_PHOTO))))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].login").value(hasItem(DEFAULT_LOGIN)))
            .andExpect(jsonPath("$.[*].passwd").value(hasItem(DEFAULT_PASSWD)))
            .andExpect(jsonPath("$.[*].uRL").value(hasItem(DEFAULT_U_RL)));
    }

    @Test
    @Transactional
    void getCytogeneticien() throws Exception {
        // Initialize the database
        insertedCytogeneticien = cytogeneticienRepository.saveAndFlush(cytogeneticien);

        // Get the cytogeneticien
        restCytogeneticienMockMvc
            .perform(get(ENTITY_API_URL_ID, cytogeneticien.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(cytogeneticien.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.service").value(DEFAULT_SERVICE))
            .andExpect(jsonPath("$.etab").value(DEFAULT_ETAB))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE))
            .andExpect(jsonPath("$.tel").value(DEFAULT_TEL))
            .andExpect(jsonPath("$.poste").value(DEFAULT_POSTE))
            .andExpect(jsonPath("$.fax").value(DEFAULT_FAX))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.photoContentType").value(DEFAULT_PHOTO_CONTENT_TYPE))
            .andExpect(jsonPath("$.photo").value(Base64.getEncoder().encodeToString(DEFAULT_PHOTO)))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.login").value(DEFAULT_LOGIN))
            .andExpect(jsonPath("$.passwd").value(DEFAULT_PASSWD))
            .andExpect(jsonPath("$.uRL").value(DEFAULT_U_RL));
    }

    @Test
    @Transactional
    void getNonExistingCytogeneticien() throws Exception {
        // Get the cytogeneticien
        restCytogeneticienMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCytogeneticien() throws Exception {
        // Initialize the database
        insertedCytogeneticien = cytogeneticienRepository.saveAndFlush(cytogeneticien);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cytogeneticien
        Cytogeneticien updatedCytogeneticien = cytogeneticienRepository.findById(cytogeneticien.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedCytogeneticien are not directly saved in db
        em.detach(updatedCytogeneticien);
        updatedCytogeneticien
            .code(UPDATED_CODE)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .service(UPDATED_SERVICE)
            .etab(UPDATED_ETAB)
            .adresse(UPDATED_ADRESSE)
            .tel(UPDATED_TEL)
            .poste(UPDATED_POSTE)
            .fax(UPDATED_FAX)
            .email(UPDATED_EMAIL)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .type(UPDATED_TYPE)
            .login(UPDATED_LOGIN)
            .passwd(UPDATED_PASSWD)
            .uRL(UPDATED_U_RL);

        restCytogeneticienMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedCytogeneticien.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedCytogeneticien))
            )
            .andExpect(status().isOk());

        // Validate the Cytogeneticien in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedCytogeneticienToMatchAllProperties(updatedCytogeneticien);
    }

    @Test
    @Transactional
    void putNonExistingCytogeneticien() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cytogeneticien.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCytogeneticienMockMvc
            .perform(
                put(ENTITY_API_URL_ID, cytogeneticien.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(cytogeneticien))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cytogeneticien in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCytogeneticien() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cytogeneticien.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCytogeneticienMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(cytogeneticien))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cytogeneticien in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCytogeneticien() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cytogeneticien.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCytogeneticienMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(cytogeneticien)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Cytogeneticien in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCytogeneticienWithPatch() throws Exception {
        // Initialize the database
        insertedCytogeneticien = cytogeneticienRepository.saveAndFlush(cytogeneticien);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cytogeneticien using partial update
        Cytogeneticien partialUpdatedCytogeneticien = new Cytogeneticien();
        partialUpdatedCytogeneticien.setId(cytogeneticien.getId());

        partialUpdatedCytogeneticien
            .code(UPDATED_CODE)
            .etab(UPDATED_ETAB)
            .fax(UPDATED_FAX)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .passwd(UPDATED_PASSWD)
            .uRL(UPDATED_U_RL);

        restCytogeneticienMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCytogeneticien.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCytogeneticien))
            )
            .andExpect(status().isOk());

        // Validate the Cytogeneticien in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCytogeneticienUpdatableFieldsEquals(
            createUpdateProxyForBean(partialUpdatedCytogeneticien, cytogeneticien),
            getPersistedCytogeneticien(cytogeneticien)
        );
    }

    @Test
    @Transactional
    void fullUpdateCytogeneticienWithPatch() throws Exception {
        // Initialize the database
        insertedCytogeneticien = cytogeneticienRepository.saveAndFlush(cytogeneticien);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the cytogeneticien using partial update
        Cytogeneticien partialUpdatedCytogeneticien = new Cytogeneticien();
        partialUpdatedCytogeneticien.setId(cytogeneticien.getId());

        partialUpdatedCytogeneticien
            .code(UPDATED_CODE)
            .nom(UPDATED_NOM)
            .prenom(UPDATED_PRENOM)
            .service(UPDATED_SERVICE)
            .etab(UPDATED_ETAB)
            .adresse(UPDATED_ADRESSE)
            .tel(UPDATED_TEL)
            .poste(UPDATED_POSTE)
            .fax(UPDATED_FAX)
            .email(UPDATED_EMAIL)
            .photo(UPDATED_PHOTO)
            .photoContentType(UPDATED_PHOTO_CONTENT_TYPE)
            .type(UPDATED_TYPE)
            .login(UPDATED_LOGIN)
            .passwd(UPDATED_PASSWD)
            .uRL(UPDATED_U_RL);

        restCytogeneticienMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCytogeneticien.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedCytogeneticien))
            )
            .andExpect(status().isOk());

        // Validate the Cytogeneticien in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertCytogeneticienUpdatableFieldsEquals(partialUpdatedCytogeneticien, getPersistedCytogeneticien(partialUpdatedCytogeneticien));
    }

    @Test
    @Transactional
    void patchNonExistingCytogeneticien() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cytogeneticien.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCytogeneticienMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, cytogeneticien.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(cytogeneticien))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cytogeneticien in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCytogeneticien() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cytogeneticien.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCytogeneticienMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(cytogeneticien))
            )
            .andExpect(status().isBadRequest());

        // Validate the Cytogeneticien in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCytogeneticien() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        cytogeneticien.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCytogeneticienMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(cytogeneticien)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Cytogeneticien in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCytogeneticien() throws Exception {
        // Initialize the database
        insertedCytogeneticien = cytogeneticienRepository.saveAndFlush(cytogeneticien);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the cytogeneticien
        restCytogeneticienMockMvc
            .perform(delete(ENTITY_API_URL_ID, cytogeneticien.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return cytogeneticienRepository.count();
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

    protected Cytogeneticien getPersistedCytogeneticien(Cytogeneticien cytogeneticien) {
        return cytogeneticienRepository.findById(cytogeneticien.getId()).orElseThrow();
    }

    protected void assertPersistedCytogeneticienToMatchAllProperties(Cytogeneticien expectedCytogeneticien) {
        assertCytogeneticienAllPropertiesEquals(expectedCytogeneticien, getPersistedCytogeneticien(expectedCytogeneticien));
    }

    protected void assertPersistedCytogeneticienToMatchUpdatableProperties(Cytogeneticien expectedCytogeneticien) {
        assertCytogeneticienAllUpdatablePropertiesEquals(expectedCytogeneticien, getPersistedCytogeneticien(expectedCytogeneticien));
    }
}
