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
import tn.tfar.fanconi.domain.Scientifique;
import tn.tfar.fanconi.repository.ScientifiqueRepository;
import tn.tfar.fanconi.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.tfar.fanconi.domain.Scientifique}.
 */
@RestController
@RequestMapping("/api/scientifiques")
@Transactional
public class ScientifiqueResource {

    private static final Logger LOG = LoggerFactory.getLogger(ScientifiqueResource.class);

    private static final String ENTITY_NAME = "scientifique";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ScientifiqueRepository scientifiqueRepository;

    public ScientifiqueResource(ScientifiqueRepository scientifiqueRepository) {
        this.scientifiqueRepository = scientifiqueRepository;
    }

    /**
     * {@code POST  /scientifiques} : Create a new scientifique.
     *
     * @param scientifique the scientifique to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new scientifique, or with status {@code 400 (Bad Request)} if the scientifique has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Scientifique> createScientifique(@RequestBody Scientifique scientifique) throws URISyntaxException {
        LOG.debug("REST request to save Scientifique : {}", scientifique);
        if (scientifique.getId() != null) {
            throw new BadRequestAlertException("A new scientifique cannot already have an ID", ENTITY_NAME, "idexists");
        }
        scientifique = scientifiqueRepository.save(scientifique);
        return ResponseEntity.created(new URI("/api/scientifiques/" + scientifique.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, scientifique.getId().toString()))
            .body(scientifique);
    }

    /**
     * {@code PUT  /scientifiques/:id} : Updates an existing scientifique.
     *
     * @param id the id of the scientifique to save.
     * @param scientifique the scientifique to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated scientifique,
     * or with status {@code 400 (Bad Request)} if the scientifique is not valid,
     * or with status {@code 500 (Internal Server Error)} if the scientifique couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Scientifique> updateScientifique(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Scientifique scientifique
    ) throws URISyntaxException {
        LOG.debug("REST request to update Scientifique : {}, {}", id, scientifique);
        if (scientifique.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, scientifique.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!scientifiqueRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        scientifique = scientifiqueRepository.save(scientifique);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, scientifique.getId().toString()))
            .body(scientifique);
    }

    /**
     * {@code PATCH  /scientifiques/:id} : Partial updates given fields of an existing scientifique, field will ignore if it is null
     *
     * @param id the id of the scientifique to save.
     * @param scientifique the scientifique to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated scientifique,
     * or with status {@code 400 (Bad Request)} if the scientifique is not valid,
     * or with status {@code 404 (Not Found)} if the scientifique is not found,
     * or with status {@code 500 (Internal Server Error)} if the scientifique couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Scientifique> partialUpdateScientifique(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Scientifique scientifique
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Scientifique partially : {}, {}", id, scientifique);
        if (scientifique.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, scientifique.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!scientifiqueRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Scientifique> result = scientifiqueRepository
            .findById(scientifique.getId())
            .map(existingScientifique -> {
                if (scientifique.getCodeSC() != null) {
                    existingScientifique.setCodeSC(scientifique.getCodeSC());
                }
                if (scientifique.getNomSC() != null) {
                    existingScientifique.setNomSC(scientifique.getNomSC());
                }
                if (scientifique.getPrenomSC() != null) {
                    existingScientifique.setPrenomSC(scientifique.getPrenomSC());
                }
                if (scientifique.getServiceSC() != null) {
                    existingScientifique.setServiceSC(scientifique.getServiceSC());
                }
                if (scientifique.getCentreSC() != null) {
                    existingScientifique.setCentreSC(scientifique.getCentreSC());
                }
                if (scientifique.getAdresseSC() != null) {
                    existingScientifique.setAdresseSC(scientifique.getAdresseSC());
                }
                if (scientifique.getTelSC() != null) {
                    existingScientifique.setTelSC(scientifique.getTelSC());
                }
                if (scientifique.getEmailSC() != null) {
                    existingScientifique.setEmailSC(scientifique.getEmailSC());
                }
                if (scientifique.getPhotoSC() != null) {
                    existingScientifique.setPhotoSC(scientifique.getPhotoSC());
                }
                if (scientifique.getPhotoSCContentType() != null) {
                    existingScientifique.setPhotoSCContentType(scientifique.getPhotoSCContentType());
                }
                if (scientifique.getTypeSC() != null) {
                    existingScientifique.setTypeSC(scientifique.getTypeSC());
                }
                if (scientifique.getLoginSC() != null) {
                    existingScientifique.setLoginSC(scientifique.getLoginSC());
                }
                if (scientifique.getPasswdSC() != null) {
                    existingScientifique.setPasswdSC(scientifique.getPasswdSC());
                }
                if (scientifique.getuRL() != null) {
                    existingScientifique.setuRL(scientifique.getuRL());
                }

                return existingScientifique;
            })
            .map(scientifiqueRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, scientifique.getId().toString())
        );
    }

    /**
     * {@code GET  /scientifiques} : get all the scientifiques.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of scientifiques in body.
     */
    @GetMapping("")
    public List<Scientifique> getAllScientifiques() {
        LOG.debug("REST request to get all Scientifiques");
        return scientifiqueRepository.findAll();
    }

    /**
     * {@code GET  /scientifiques/:id} : get the "id" scientifique.
     *
     * @param id the id of the scientifique to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the scientifique, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Scientifique> getScientifique(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Scientifique : {}", id);
        Optional<Scientifique> scientifique = scientifiqueRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(scientifique);
    }

    /**
     * {@code DELETE  /scientifiques/:id} : delete the "id" scientifique.
     *
     * @param id the id of the scientifique to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScientifique(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Scientifique : {}", id);
        scientifiqueRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
