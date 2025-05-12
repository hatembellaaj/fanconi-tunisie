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
import tn.tfar.fanconi.domain.Cytogenetique;
import tn.tfar.fanconi.repository.CytogenetiqueRepository;
import tn.tfar.fanconi.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.tfar.fanconi.domain.Cytogenetique}.
 */
@RestController
@RequestMapping("/api/cytogenetiques")
@Transactional
public class CytogenetiqueResource {

    private static final Logger LOG = LoggerFactory.getLogger(CytogenetiqueResource.class);

    private static final String ENTITY_NAME = "cytogenetique";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CytogenetiqueRepository cytogenetiqueRepository;

    public CytogenetiqueResource(CytogenetiqueRepository cytogenetiqueRepository) {
        this.cytogenetiqueRepository = cytogenetiqueRepository;
    }

    /**
     * {@code POST  /cytogenetiques} : Create a new cytogenetique.
     *
     * @param cytogenetique the cytogenetique to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cytogenetique, or with status {@code 400 (Bad Request)} if the cytogenetique has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Cytogenetique> createCytogenetique(@RequestBody Cytogenetique cytogenetique) throws URISyntaxException {
        LOG.debug("REST request to save Cytogenetique : {}", cytogenetique);
        if (cytogenetique.getId() != null) {
            throw new BadRequestAlertException("A new cytogenetique cannot already have an ID", ENTITY_NAME, "idexists");
        }
        cytogenetique = cytogenetiqueRepository.save(cytogenetique);
        return ResponseEntity.created(new URI("/api/cytogenetiques/" + cytogenetique.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, cytogenetique.getId().toString()))
            .body(cytogenetique);
    }

    /**
     * {@code PUT  /cytogenetiques/:id} : Updates an existing cytogenetique.
     *
     * @param id the id of the cytogenetique to save.
     * @param cytogenetique the cytogenetique to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cytogenetique,
     * or with status {@code 400 (Bad Request)} if the cytogenetique is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cytogenetique couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cytogenetique> updateCytogenetique(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Cytogenetique cytogenetique
    ) throws URISyntaxException {
        LOG.debug("REST request to update Cytogenetique : {}, {}", id, cytogenetique);
        if (cytogenetique.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cytogenetique.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cytogenetiqueRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        cytogenetique = cytogenetiqueRepository.save(cytogenetique);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cytogenetique.getId().toString()))
            .body(cytogenetique);
    }

    /**
     * {@code PATCH  /cytogenetiques/:id} : Partial updates given fields of an existing cytogenetique, field will ignore if it is null
     *
     * @param id the id of the cytogenetique to save.
     * @param cytogenetique the cytogenetique to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cytogenetique,
     * or with status {@code 400 (Bad Request)} if the cytogenetique is not valid,
     * or with status {@code 404 (Not Found)} if the cytogenetique is not found,
     * or with status {@code 500 (Internal Server Error)} if the cytogenetique couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Cytogenetique> partialUpdateCytogenetique(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Cytogenetique cytogenetique
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Cytogenetique partially : {}, {}", id, cytogenetique);
        if (cytogenetique.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cytogenetique.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cytogenetiqueRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Cytogenetique> result = cytogenetiqueRepository
            .findById(cytogenetique.getId())
            .map(existingCytogenetique -> {
                if (cytogenetique.getnDossierPatient() != null) {
                    existingCytogenetique.setnDossierPatient(cytogenetique.getnDossierPatient());
                }
                if (cytogenetique.getnEtudeCyto() != null) {
                    existingCytogenetique.setnEtudeCyto(cytogenetique.getnEtudeCyto());
                }
                if (cytogenetique.getLymphocytes() != null) {
                    existingCytogenetique.setLymphocytes(cytogenetique.getLymphocytes());
                }
                if (cytogenetique.getDateSang() != null) {
                    existingCytogenetique.setDateSang(cytogenetique.getDateSang());
                }
                if (cytogenetique.getLaboratoire() != null) {
                    existingCytogenetique.setLaboratoire(cytogenetique.getLaboratoire());
                }
                if (cytogenetique.getAgentPontant() != null) {
                    existingCytogenetique.setAgentPontant(cytogenetique.getAgentPontant());
                }
                if (cytogenetique.getInstabilite() != null) {
                    existingCytogenetique.setInstabilite(cytogenetique.getInstabilite());
                }
                if (cytogenetique.getInstabilitePourcentage() != null) {
                    existingCytogenetique.setInstabilitePourcentage(cytogenetique.getInstabilitePourcentage());
                }
                if (cytogenetique.getiR() != null) {
                    existingCytogenetique.setiR(cytogenetique.getiR());
                }
                if (cytogenetique.getiRPourcentage() != null) {
                    existingCytogenetique.setiRPourcentage(cytogenetique.getiRPourcentage());
                }
                if (cytogenetique.getMoelle() != null) {
                    existingCytogenetique.setMoelle(cytogenetique.getMoelle());
                }
                if (cytogenetique.getDateMoelle() != null) {
                    existingCytogenetique.setDateMoelle(cytogenetique.getDateMoelle());
                }
                if (cytogenetique.getResultatMoelle() != null) {
                    existingCytogenetique.setResultatMoelle(cytogenetique.getResultatMoelle());
                }

                return existingCytogenetique;
            })
            .map(cytogenetiqueRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cytogenetique.getId().toString())
        );
    }

    /**
     * {@code GET  /cytogenetiques} : get all the cytogenetiques.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cytogenetiques in body.
     */
    @GetMapping("")
    public List<Cytogenetique> getAllCytogenetiques() {
        LOG.debug("REST request to get all Cytogenetiques");
        return cytogenetiqueRepository.findAll();
    }

    /**
     * {@code GET  /cytogenetiques/:id} : get the "id" cytogenetique.
     *
     * @param id the id of the cytogenetique to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cytogenetique, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cytogenetique> getCytogenetique(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Cytogenetique : {}", id);
        Optional<Cytogenetique> cytogenetique = cytogenetiqueRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(cytogenetique);
    }

    /**
     * {@code DELETE  /cytogenetiques/:id} : delete the "id" cytogenetique.
     *
     * @param id the id of the cytogenetique to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCytogenetique(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Cytogenetique : {}", id);
        cytogenetiqueRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
