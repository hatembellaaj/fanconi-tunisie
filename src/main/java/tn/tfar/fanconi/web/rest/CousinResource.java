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
import tn.tfar.fanconi.domain.Cousin;
import tn.tfar.fanconi.repository.CousinRepository;
import tn.tfar.fanconi.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.tfar.fanconi.domain.Cousin}.
 */
@RestController
@RequestMapping("/api/cousins")
@Transactional
public class CousinResource {

    private static final Logger LOG = LoggerFactory.getLogger(CousinResource.class);

    private static final String ENTITY_NAME = "cousin";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CousinRepository cousinRepository;

    public CousinResource(CousinRepository cousinRepository) {
        this.cousinRepository = cousinRepository;
    }

    /**
     * {@code POST  /cousins} : Create a new cousin.
     *
     * @param cousin the cousin to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cousin, or with status {@code 400 (Bad Request)} if the cousin has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Cousin> createCousin(@RequestBody Cousin cousin) throws URISyntaxException {
        LOG.debug("REST request to save Cousin : {}", cousin);
        if (cousin.getId() != null) {
            throw new BadRequestAlertException("A new cousin cannot already have an ID", ENTITY_NAME, "idexists");
        }
        cousin = cousinRepository.save(cousin);
        return ResponseEntity.created(new URI("/api/cousins/" + cousin.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, cousin.getId().toString()))
            .body(cousin);
    }

    /**
     * {@code PUT  /cousins/:id} : Updates an existing cousin.
     *
     * @param id the id of the cousin to save.
     * @param cousin the cousin to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cousin,
     * or with status {@code 400 (Bad Request)} if the cousin is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cousin couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cousin> updateCousin(@PathVariable(value = "id", required = false) final Long id, @RequestBody Cousin cousin)
        throws URISyntaxException {
        LOG.debug("REST request to update Cousin : {}, {}", id, cousin);
        if (cousin.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cousin.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cousinRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        cousin = cousinRepository.save(cousin);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cousin.getId().toString()))
            .body(cousin);
    }

    /**
     * {@code PATCH  /cousins/:id} : Partial updates given fields of an existing cousin, field will ignore if it is null
     *
     * @param id the id of the cousin to save.
     * @param cousin the cousin to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cousin,
     * or with status {@code 400 (Bad Request)} if the cousin is not valid,
     * or with status {@code 404 (Not Found)} if the cousin is not found,
     * or with status {@code 500 (Internal Server Error)} if the cousin couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Cousin> partialUpdateCousin(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Cousin cousin
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Cousin partially : {}, {}", id, cousin);
        if (cousin.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cousin.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cousinRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Cousin> result = cousinRepository
            .findById(cousin.getId())
            .map(existingCousin -> {
                if (cousin.getNdossierC() != null) {
                    existingCousin.setNdossierC(cousin.getNdossierC());
                }
                if (cousin.getIdCousin() != null) {
                    existingCousin.setIdCousin(cousin.getIdCousin());
                }
                if (cousin.getPrenomCousin() != null) {
                    existingCousin.setPrenomCousin(cousin.getPrenomCousin());
                }
                if (cousin.getPlaceCousin() != null) {
                    existingCousin.setPlaceCousin(cousin.getPlaceCousin());
                }
                if (cousin.getSexeC() != null) {
                    existingCousin.setSexeC(cousin.getSexeC());
                }

                return existingCousin;
            })
            .map(cousinRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cousin.getId().toString())
        );
    }

    /**
     * {@code GET  /cousins} : get all the cousins.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cousins in body.
     */
    @GetMapping("")
    public List<Cousin> getAllCousins() {
        LOG.debug("REST request to get all Cousins");
        return cousinRepository.findAll();
    }

    /**
     * {@code GET  /cousins/:id} : get the "id" cousin.
     *
     * @param id the id of the cousin to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cousin, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cousin> getCousin(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Cousin : {}", id);
        Optional<Cousin> cousin = cousinRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(cousin);
    }

    /**
     * {@code DELETE  /cousins/:id} : delete the "id" cousin.
     *
     * @param id the id of the cousin to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCousin(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Cousin : {}", id);
        cousinRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
