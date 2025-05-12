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
import tn.tfar.fanconi.domain.Frere;
import tn.tfar.fanconi.repository.FrereRepository;
import tn.tfar.fanconi.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.tfar.fanconi.domain.Frere}.
 */
@RestController
@RequestMapping("/api/freres")
@Transactional
public class FrereResource {

    private static final Logger LOG = LoggerFactory.getLogger(FrereResource.class);

    private static final String ENTITY_NAME = "frere";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FrereRepository frereRepository;

    public FrereResource(FrereRepository frereRepository) {
        this.frereRepository = frereRepository;
    }

    /**
     * {@code POST  /freres} : Create a new frere.
     *
     * @param frere the frere to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new frere, or with status {@code 400 (Bad Request)} if the frere has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Frere> createFrere(@RequestBody Frere frere) throws URISyntaxException {
        LOG.debug("REST request to save Frere : {}", frere);
        if (frere.getId() != null) {
            throw new BadRequestAlertException("A new frere cannot already have an ID", ENTITY_NAME, "idexists");
        }
        frere = frereRepository.save(frere);
        return ResponseEntity.created(new URI("/api/freres/" + frere.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, frere.getId().toString()))
            .body(frere);
    }

    /**
     * {@code PUT  /freres/:id} : Updates an existing frere.
     *
     * @param id the id of the frere to save.
     * @param frere the frere to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated frere,
     * or with status {@code 400 (Bad Request)} if the frere is not valid,
     * or with status {@code 500 (Internal Server Error)} if the frere couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Frere> updateFrere(@PathVariable(value = "id", required = false) final Long id, @RequestBody Frere frere)
        throws URISyntaxException {
        LOG.debug("REST request to update Frere : {}, {}", id, frere);
        if (frere.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, frere.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!frereRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        frere = frereRepository.save(frere);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, frere.getId().toString()))
            .body(frere);
    }

    /**
     * {@code PATCH  /freres/:id} : Partial updates given fields of an existing frere, field will ignore if it is null
     *
     * @param id the id of the frere to save.
     * @param frere the frere to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated frere,
     * or with status {@code 400 (Bad Request)} if the frere is not valid,
     * or with status {@code 404 (Not Found)} if the frere is not found,
     * or with status {@code 500 (Internal Server Error)} if the frere couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Frere> partialUpdateFrere(@PathVariable(value = "id", required = false) final Long id, @RequestBody Frere frere)
        throws URISyntaxException {
        LOG.debug("REST request to partial update Frere partially : {}, {}", id, frere);
        if (frere.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, frere.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!frereRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Frere> result = frereRepository
            .findById(frere.getId())
            .map(existingFrere -> {
                if (frere.getnDossierF() != null) {
                    existingFrere.setnDossierF(frere.getnDossierF());
                }
                if (frere.getIdFrere() != null) {
                    existingFrere.setIdFrere(frere.getIdFrere());
                }
                if (frere.getPrenomFrere() != null) {
                    existingFrere.setPrenomFrere(frere.getPrenomFrere());
                }
                if (frere.getAtteint() != null) {
                    existingFrere.setAtteint(frere.getAtteint());
                }
                if (frere.getPlacefratrie() != null) {
                    existingFrere.setPlacefratrie(frere.getPlacefratrie());
                }
                if (frere.getSexeF() != null) {
                    existingFrere.setSexeF(frere.getSexeF());
                }
                if (frere.getDecedes() != null) {
                    existingFrere.setDecedes(frere.getDecedes());
                }
                if (frere.getAge() != null) {
                    existingFrere.setAge(frere.getAge());
                }

                return existingFrere;
            })
            .map(frereRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, frere.getId().toString())
        );
    }

    /**
     * {@code GET  /freres} : get all the freres.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of freres in body.
     */
    @GetMapping("")
    public List<Frere> getAllFreres() {
        LOG.debug("REST request to get all Freres");
        return frereRepository.findAll();
    }

    /**
     * {@code GET  /freres/:id} : get the "id" frere.
     *
     * @param id the id of the frere to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the frere, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Frere> getFrere(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Frere : {}", id);
        Optional<Frere> frere = frereRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(frere);
    }

    /**
     * {@code DELETE  /freres/:id} : delete the "id" frere.
     *
     * @param id the id of the frere to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFrere(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Frere : {}", id);
        frereRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
