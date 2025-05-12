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
import tn.tfar.fanconi.domain.Androgene;
import tn.tfar.fanconi.repository.AndrogeneRepository;
import tn.tfar.fanconi.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.tfar.fanconi.domain.Androgene}.
 */
@RestController
@RequestMapping("/api/androgenes")
@Transactional
public class AndrogeneResource {

    private static final Logger LOG = LoggerFactory.getLogger(AndrogeneResource.class);

    private static final String ENTITY_NAME = "androgene";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AndrogeneRepository androgeneRepository;

    public AndrogeneResource(AndrogeneRepository androgeneRepository) {
        this.androgeneRepository = androgeneRepository;
    }

    /**
     * {@code POST  /androgenes} : Create a new androgene.
     *
     * @param androgene the androgene to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new androgene, or with status {@code 400 (Bad Request)} if the androgene has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Androgene> createAndrogene(@RequestBody Androgene androgene) throws URISyntaxException {
        LOG.debug("REST request to save Androgene : {}", androgene);
        if (androgene.getId() != null) {
            throw new BadRequestAlertException("A new androgene cannot already have an ID", ENTITY_NAME, "idexists");
        }
        androgene = androgeneRepository.save(androgene);
        return ResponseEntity.created(new URI("/api/androgenes/" + androgene.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, androgene.getId().toString()))
            .body(androgene);
    }

    /**
     * {@code PUT  /androgenes/:id} : Updates an existing androgene.
     *
     * @param id the id of the androgene to save.
     * @param androgene the androgene to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated androgene,
     * or with status {@code 400 (Bad Request)} if the androgene is not valid,
     * or with status {@code 500 (Internal Server Error)} if the androgene couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Androgene> updateAndrogene(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Androgene androgene
    ) throws URISyntaxException {
        LOG.debug("REST request to update Androgene : {}, {}", id, androgene);
        if (androgene.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, androgene.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!androgeneRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        androgene = androgeneRepository.save(androgene);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, androgene.getId().toString()))
            .body(androgene);
    }

    /**
     * {@code PATCH  /androgenes/:id} : Partial updates given fields of an existing androgene, field will ignore if it is null
     *
     * @param id the id of the androgene to save.
     * @param androgene the androgene to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated androgene,
     * or with status {@code 400 (Bad Request)} if the androgene is not valid,
     * or with status {@code 404 (Not Found)} if the androgene is not found,
     * or with status {@code 500 (Internal Server Error)} if the androgene couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Androgene> partialUpdateAndrogene(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Androgene androgene
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Androgene partially : {}, {}", id, androgene);
        if (androgene.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, androgene.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!androgeneRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Androgene> result = androgeneRepository
            .findById(androgene.getId())
            .map(existingAndrogene -> {
                if (androgene.getnDossierPa() != null) {
                    existingAndrogene.setnDossierPa(androgene.getnDossierPa());
                }
                if (androgene.getMois() != null) {
                    existingAndrogene.setMois(androgene.getMois());
                }
                if (androgene.getReponse() != null) {
                    existingAndrogene.setReponse(androgene.getReponse());
                }

                return existingAndrogene;
            })
            .map(androgeneRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, androgene.getId().toString())
        );
    }

    /**
     * {@code GET  /androgenes} : get all the androgenes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of androgenes in body.
     */
    @GetMapping("")
    public List<Androgene> getAllAndrogenes() {
        LOG.debug("REST request to get all Androgenes");
        return androgeneRepository.findAll();
    }

    /**
     * {@code GET  /androgenes/:id} : get the "id" androgene.
     *
     * @param id the id of the androgene to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the androgene, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Androgene> getAndrogene(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Androgene : {}", id);
        Optional<Androgene> androgene = androgeneRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(androgene);
    }

    /**
     * {@code DELETE  /androgenes/:id} : delete the "id" androgene.
     *
     * @param id the id of the androgene to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAndrogene(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Androgene : {}", id);
        androgeneRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
