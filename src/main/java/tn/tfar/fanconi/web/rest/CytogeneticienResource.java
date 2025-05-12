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
import tn.tfar.fanconi.domain.Cytogeneticien;
import tn.tfar.fanconi.repository.CytogeneticienRepository;
import tn.tfar.fanconi.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.tfar.fanconi.domain.Cytogeneticien}.
 */
@RestController
@RequestMapping("/api/cytogeneticiens")
@Transactional
public class CytogeneticienResource {

    private static final Logger LOG = LoggerFactory.getLogger(CytogeneticienResource.class);

    private static final String ENTITY_NAME = "cytogeneticien";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CytogeneticienRepository cytogeneticienRepository;

    public CytogeneticienResource(CytogeneticienRepository cytogeneticienRepository) {
        this.cytogeneticienRepository = cytogeneticienRepository;
    }

    /**
     * {@code POST  /cytogeneticiens} : Create a new cytogeneticien.
     *
     * @param cytogeneticien the cytogeneticien to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cytogeneticien, or with status {@code 400 (Bad Request)} if the cytogeneticien has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Cytogeneticien> createCytogeneticien(@RequestBody Cytogeneticien cytogeneticien) throws URISyntaxException {
        LOG.debug("REST request to save Cytogeneticien : {}", cytogeneticien);
        if (cytogeneticien.getId() != null) {
            throw new BadRequestAlertException("A new cytogeneticien cannot already have an ID", ENTITY_NAME, "idexists");
        }
        cytogeneticien = cytogeneticienRepository.save(cytogeneticien);
        return ResponseEntity.created(new URI("/api/cytogeneticiens/" + cytogeneticien.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, cytogeneticien.getId().toString()))
            .body(cytogeneticien);
    }

    /**
     * {@code PUT  /cytogeneticiens/:id} : Updates an existing cytogeneticien.
     *
     * @param id the id of the cytogeneticien to save.
     * @param cytogeneticien the cytogeneticien to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cytogeneticien,
     * or with status {@code 400 (Bad Request)} if the cytogeneticien is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cytogeneticien couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cytogeneticien> updateCytogeneticien(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Cytogeneticien cytogeneticien
    ) throws URISyntaxException {
        LOG.debug("REST request to update Cytogeneticien : {}, {}", id, cytogeneticien);
        if (cytogeneticien.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cytogeneticien.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cytogeneticienRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        cytogeneticien = cytogeneticienRepository.save(cytogeneticien);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cytogeneticien.getId().toString()))
            .body(cytogeneticien);
    }

    /**
     * {@code PATCH  /cytogeneticiens/:id} : Partial updates given fields of an existing cytogeneticien, field will ignore if it is null
     *
     * @param id the id of the cytogeneticien to save.
     * @param cytogeneticien the cytogeneticien to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cytogeneticien,
     * or with status {@code 400 (Bad Request)} if the cytogeneticien is not valid,
     * or with status {@code 404 (Not Found)} if the cytogeneticien is not found,
     * or with status {@code 500 (Internal Server Error)} if the cytogeneticien couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Cytogeneticien> partialUpdateCytogeneticien(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Cytogeneticien cytogeneticien
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Cytogeneticien partially : {}, {}", id, cytogeneticien);
        if (cytogeneticien.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cytogeneticien.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cytogeneticienRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Cytogeneticien> result = cytogeneticienRepository
            .findById(cytogeneticien.getId())
            .map(existingCytogeneticien -> {
                if (cytogeneticien.getCode() != null) {
                    existingCytogeneticien.setCode(cytogeneticien.getCode());
                }
                if (cytogeneticien.getNom() != null) {
                    existingCytogeneticien.setNom(cytogeneticien.getNom());
                }
                if (cytogeneticien.getPrenom() != null) {
                    existingCytogeneticien.setPrenom(cytogeneticien.getPrenom());
                }
                if (cytogeneticien.getService() != null) {
                    existingCytogeneticien.setService(cytogeneticien.getService());
                }
                if (cytogeneticien.getEtab() != null) {
                    existingCytogeneticien.setEtab(cytogeneticien.getEtab());
                }
                if (cytogeneticien.getAdresse() != null) {
                    existingCytogeneticien.setAdresse(cytogeneticien.getAdresse());
                }
                if (cytogeneticien.getTel() != null) {
                    existingCytogeneticien.setTel(cytogeneticien.getTel());
                }
                if (cytogeneticien.getPoste() != null) {
                    existingCytogeneticien.setPoste(cytogeneticien.getPoste());
                }
                if (cytogeneticien.getFax() != null) {
                    existingCytogeneticien.setFax(cytogeneticien.getFax());
                }
                if (cytogeneticien.getEmail() != null) {
                    existingCytogeneticien.setEmail(cytogeneticien.getEmail());
                }
                if (cytogeneticien.getPhoto() != null) {
                    existingCytogeneticien.setPhoto(cytogeneticien.getPhoto());
                }
                if (cytogeneticien.getPhotoContentType() != null) {
                    existingCytogeneticien.setPhotoContentType(cytogeneticien.getPhotoContentType());
                }
                if (cytogeneticien.getType() != null) {
                    existingCytogeneticien.setType(cytogeneticien.getType());
                }
                if (cytogeneticien.getLogin() != null) {
                    existingCytogeneticien.setLogin(cytogeneticien.getLogin());
                }
                if (cytogeneticien.getPasswd() != null) {
                    existingCytogeneticien.setPasswd(cytogeneticien.getPasswd());
                }
                if (cytogeneticien.getuRL() != null) {
                    existingCytogeneticien.setuRL(cytogeneticien.getuRL());
                }

                return existingCytogeneticien;
            })
            .map(cytogeneticienRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cytogeneticien.getId().toString())
        );
    }

    /**
     * {@code GET  /cytogeneticiens} : get all the cytogeneticiens.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cytogeneticiens in body.
     */
    @GetMapping("")
    public List<Cytogeneticien> getAllCytogeneticiens() {
        LOG.debug("REST request to get all Cytogeneticiens");
        return cytogeneticienRepository.findAll();
    }

    /**
     * {@code GET  /cytogeneticiens/:id} : get the "id" cytogeneticien.
     *
     * @param id the id of the cytogeneticien to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cytogeneticien, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cytogeneticien> getCytogeneticien(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Cytogeneticien : {}", id);
        Optional<Cytogeneticien> cytogeneticien = cytogeneticienRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(cytogeneticien);
    }

    /**
     * {@code DELETE  /cytogeneticiens/:id} : delete the "id" cytogeneticien.
     *
     * @param id the id of the cytogeneticien to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCytogeneticien(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Cytogeneticien : {}", id);
        cytogeneticienRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
