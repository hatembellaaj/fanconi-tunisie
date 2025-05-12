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
import tn.tfar.fanconi.domain.Membre;
import tn.tfar.fanconi.repository.MembreRepository;
import tn.tfar.fanconi.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.tfar.fanconi.domain.Membre}.
 */
@RestController
@RequestMapping("/api/membres")
@Transactional
public class MembreResource {

    private static final Logger LOG = LoggerFactory.getLogger(MembreResource.class);

    private static final String ENTITY_NAME = "membre";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MembreRepository membreRepository;

    public MembreResource(MembreRepository membreRepository) {
        this.membreRepository = membreRepository;
    }

    /**
     * {@code POST  /membres} : Create a new membre.
     *
     * @param membre the membre to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new membre, or with status {@code 400 (Bad Request)} if the membre has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Membre> createMembre(@RequestBody Membre membre) throws URISyntaxException {
        LOG.debug("REST request to save Membre : {}", membre);
        if (membre.getId() != null) {
            throw new BadRequestAlertException("A new membre cannot already have an ID", ENTITY_NAME, "idexists");
        }
        membre = membreRepository.save(membre);
        return ResponseEntity.created(new URI("/api/membres/" + membre.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, membre.getId().toString()))
            .body(membre);
    }

    /**
     * {@code PUT  /membres/:id} : Updates an existing membre.
     *
     * @param id the id of the membre to save.
     * @param membre the membre to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated membre,
     * or with status {@code 400 (Bad Request)} if the membre is not valid,
     * or with status {@code 500 (Internal Server Error)} if the membre couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Membre> updateMembre(@PathVariable(value = "id", required = false) final Long id, @RequestBody Membre membre)
        throws URISyntaxException {
        LOG.debug("REST request to update Membre : {}, {}", id, membre);
        if (membre.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, membre.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!membreRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        membre = membreRepository.save(membre);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, membre.getId().toString()))
            .body(membre);
    }

    /**
     * {@code PATCH  /membres/:id} : Partial updates given fields of an existing membre, field will ignore if it is null
     *
     * @param id the id of the membre to save.
     * @param membre the membre to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated membre,
     * or with status {@code 400 (Bad Request)} if the membre is not valid,
     * or with status {@code 404 (Not Found)} if the membre is not found,
     * or with status {@code 500 (Internal Server Error)} if the membre couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Membre> partialUpdateMembre(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Membre membre
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Membre partially : {}, {}", id, membre);
        if (membre.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, membre.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!membreRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Membre> result = membreRepository
            .findById(membre.getId())
            .map(existingMembre -> {
                if (membre.getnDossierM() != null) {
                    existingMembre.setnDossierM(membre.getnDossierM());
                }
                if (membre.getIdMembre() != null) {
                    existingMembre.setIdMembre(membre.getIdMembre());
                }
                if (membre.getPrenomM() != null) {
                    existingMembre.setPrenomM(membre.getPrenomM());
                }
                if (membre.getLienParente() != null) {
                    existingMembre.setLienParente(membre.getLienParente());
                }
                if (membre.getTypeCancerM() != null) {
                    existingMembre.setTypeCancerM(membre.getTypeCancerM());
                }
                if (membre.getAgeDecouverteM() != null) {
                    existingMembre.setAgeDecouverteM(membre.getAgeDecouverteM());
                }

                return existingMembre;
            })
            .map(membreRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, membre.getId().toString())
        );
    }

    /**
     * {@code GET  /membres} : get all the membres.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of membres in body.
     */
    @GetMapping("")
    public List<Membre> getAllMembres() {
        LOG.debug("REST request to get all Membres");
        return membreRepository.findAll();
    }

    /**
     * {@code GET  /membres/:id} : get the "id" membre.
     *
     * @param id the id of the membre to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the membre, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Membre> getMembre(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Membre : {}", id);
        Optional<Membre> membre = membreRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(membre);
    }

    /**
     * {@code DELETE  /membres/:id} : delete the "id" membre.
     *
     * @param id the id of the membre to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMembre(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Membre : {}", id);
        membreRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
