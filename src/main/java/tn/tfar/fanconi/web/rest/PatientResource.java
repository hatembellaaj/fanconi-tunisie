package tn.tfar.fanconi.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;
import tn.tfar.fanconi.domain.Patient;
import tn.tfar.fanconi.repository.PatientRepository;
import tn.tfar.fanconi.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.tfar.fanconi.domain.Patient}.
 */
@RestController
@RequestMapping("/api/patients")
@Transactional
public class PatientResource {

    private static final Logger LOG = LoggerFactory.getLogger(PatientResource.class);

    private static final String ENTITY_NAME = "patient";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PatientRepository patientRepository;

    public PatientResource(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * {@code POST  /patients} : Create a new patient.
     *
     * @param patient the patient to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new patient, or with status {@code 400 (Bad Request)} if the patient has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) throws URISyntaxException {
        LOG.debug("REST request to save Patient : {}", patient);
        if (patient.getId() != null) {
            throw new BadRequestAlertException("A new patient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        patient = patientRepository.save(patient);
        return ResponseEntity.created(new URI("/api/patients/" + patient.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, patient.getId().toString()))
            .body(patient);
    }

    /**
     * {@code PUT  /patients/:id} : Updates an existing patient.
     *
     * @param id the id of the patient to save.
     * @param patient the patient to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patient,
     * or with status {@code 400 (Bad Request)} if the patient is not valid,
     * or with status {@code 500 (Internal Server Error)} if the patient couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable(value = "id", required = false) final Long id, @RequestBody Patient patient)
        throws URISyntaxException {
        LOG.debug("REST request to update Patient : {}, {}", id, patient);
        if (patient.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, patient.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!patientRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        patient = patientRepository.save(patient);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, patient.getId().toString()))
            .body(patient);
    }

    /**
     * {@code PATCH  /patients/:id} : Partial updates given fields of an existing patient, field will ignore if it is null
     *
     * @param id the id of the patient to save.
     * @param patient the patient to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated patient,
     * or with status {@code 400 (Bad Request)} if the patient is not valid,
     * or with status {@code 404 (Not Found)} if the patient is not found,
     * or with status {@code 500 (Internal Server Error)} if the patient couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Patient> partialUpdatePatient(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Patient patient
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Patient partially : {}, {}", id, patient);
        if (patient.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, patient.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!patientRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Patient> result = patientRepository
            .findById(patient.getId())
            .map(existingPatient -> {
                if (patient.getNdossierP() != null) {
                    existingPatient.setNdossierP(patient.getNdossierP());
                }
                if (patient.getNom() != null) {
                    existingPatient.setNom(patient.getNom());
                }
                if (patient.getPrenom() != null) {
                    existingPatient.setPrenom(patient.getPrenom());
                }
                if (patient.getDateNaissance() != null) {
                    existingPatient.setDateNaissance(patient.getDateNaissance());
                }
                if (patient.getLieuNaissance() != null) {
                    existingPatient.setLieuNaissance(patient.getLieuNaissance());
                }
                if (patient.getSexe() != null) {
                    existingPatient.setSexe(patient.getSexe());
                }
                if (patient.getGouvernorat() != null) {
                    existingPatient.setGouvernorat(patient.getGouvernorat());
                }
                if (patient.getAdresse() != null) {
                    existingPatient.setAdresse(patient.getAdresse());
                }
                if (patient.getReperes() != null) {
                    existingPatient.setReperes(patient.getReperes());
                }
                if (patient.getTel() != null) {
                    existingPatient.setTel(patient.getTel());
                }
                if (patient.getPrenomPere() != null) {
                    existingPatient.setPrenomPere(patient.getPrenomPere());
                }
                if (patient.getNomMere() != null) {
                    existingPatient.setNomMere(patient.getNomMere());
                }
                if (patient.getPrenomMere() != null) {
                    existingPatient.setPrenomMere(patient.getPrenomMere());
                }
                if (patient.getNomGMP() != null) {
                    existingPatient.setNomGMP(patient.getNomGMP());
                }
                if (patient.getNomGMM() != null) {
                    existingPatient.setNomGMM(patient.getNomGMM());
                }
                if (patient.getAge() != null) {
                    existingPatient.setAge(patient.getAge());
                }

                return existingPatient;
            })
            .map(patientRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, patient.getId().toString())
        );
    }

    /**
     * {@code GET  /patients} : get all the patients.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of patients in body.
     */
    @GetMapping("")
    public List<Patient> getAllPatients() {
        LOG.debug("REST request to get all Patients");
        return patientRepository.findAll();
    }

    /**
     * {@code GET  /patients/:id} : get the "id" patient.
     *
     * @param id the id of the patient to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the patient, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Patient : {}", id);
        Optional<Patient> patient = patientRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(patient);
    }

    /**
     * {@code DELETE  /patients/:id} : delete the "id" patient.
     *
     * @param id the id of the patient to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Patient : {}", id);
        patientRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
