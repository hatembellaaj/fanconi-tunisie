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
import tn.tfar.fanconi.domain.Hopital;
import tn.tfar.fanconi.repository.HopitalRepository;
import tn.tfar.fanconi.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.tfar.fanconi.domain.Hopital}.
 */
@RestController
@RequestMapping("/api/hopitals")
@Transactional
public class HopitalResource {

    private static final Logger LOG = LoggerFactory.getLogger(HopitalResource.class);

    private static final String ENTITY_NAME = "hopital";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HopitalRepository hopitalRepository;

    public HopitalResource(HopitalRepository hopitalRepository) {
        this.hopitalRepository = hopitalRepository;
    }

    /**
     * {@code POST  /hopitals} : Create a new hopital.
     *
     * @param hopital the hopital to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new hopital, or with status {@code 400 (Bad Request)} if the hopital has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Hopital> createHopital(@RequestBody Hopital hopital) throws URISyntaxException {
        LOG.debug("REST request to save Hopital : {}", hopital);
        if (hopital.getId() != null) {
            throw new BadRequestAlertException("A new hopital cannot already have an ID", ENTITY_NAME, "idexists");
        }
        hopital = hopitalRepository.save(hopital);
        return ResponseEntity.created(new URI("/api/hopitals/" + hopital.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, hopital.getId().toString()))
            .body(hopital);
    }

    /**
     * {@code PUT  /hopitals/:id} : Updates an existing hopital.
     *
     * @param id the id of the hopital to save.
     * @param hopital the hopital to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hopital,
     * or with status {@code 400 (Bad Request)} if the hopital is not valid,
     * or with status {@code 500 (Internal Server Error)} if the hopital couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Hopital> updateHopital(@PathVariable(value = "id", required = false) final Long id, @RequestBody Hopital hopital)
        throws URISyntaxException {
        LOG.debug("REST request to update Hopital : {}, {}", id, hopital);
        if (hopital.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hopital.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hopitalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        hopital = hopitalRepository.save(hopital);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hopital.getId().toString()))
            .body(hopital);
    }

    /**
     * {@code PATCH  /hopitals/:id} : Partial updates given fields of an existing hopital, field will ignore if it is null
     *
     * @param id the id of the hopital to save.
     * @param hopital the hopital to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated hopital,
     * or with status {@code 400 (Bad Request)} if the hopital is not valid,
     * or with status {@code 404 (Not Found)} if the hopital is not found,
     * or with status {@code 500 (Internal Server Error)} if the hopital couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Hopital> partialUpdateHopital(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Hopital hopital
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Hopital partially : {}, {}", id, hopital);
        if (hopital.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, hopital.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!hopitalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Hopital> result = hopitalRepository
            .findById(hopital.getId())
            .map(existingHopital -> {
                if (hopital.getCodeHopital() != null) {
                    existingHopital.setCodeHopital(hopital.getCodeHopital());
                }
                if (hopital.getNomHopital() != null) {
                    existingHopital.setNomHopital(hopital.getNomHopital());
                }

                return existingHopital;
            })
            .map(hopitalRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, hopital.getId().toString())
        );
    }

    /**
     * {@code GET  /hopitals} : get all the hopitals.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of hopitals in body.
     */
    @GetMapping("")
    public List<Hopital> getAllHopitals() {
        LOG.debug("REST request to get all Hopitals");
        return hopitalRepository.findAll();
    }

    /**
     * {@code GET  /hopitals/:id} : get the "id" hopital.
     *
     * @param id the id of the hopital to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the hopital, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Hopital> getHopital(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Hopital : {}", id);
        Optional<Hopital> hopital = hopitalRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(hopital);
    }

    /**
     * {@code DELETE  /hopitals/:id} : delete the "id" hopital.
     *
     * @param id the id of the hopital to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHopital(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Hopital : {}", id);
        hopitalRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
