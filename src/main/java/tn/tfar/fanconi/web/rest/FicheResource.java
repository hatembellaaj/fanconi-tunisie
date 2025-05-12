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
import tn.tfar.fanconi.domain.Fiche;
import tn.tfar.fanconi.repository.FicheRepository;
import tn.tfar.fanconi.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link tn.tfar.fanconi.domain.Fiche}.
 */
@RestController
@RequestMapping("/api/fiches")
@Transactional
public class FicheResource {

    private static final Logger LOG = LoggerFactory.getLogger(FicheResource.class);

    private static final String ENTITY_NAME = "fiche";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FicheRepository ficheRepository;

    public FicheResource(FicheRepository ficheRepository) {
        this.ficheRepository = ficheRepository;
    }

    /**
     * {@code POST  /fiches} : Create a new fiche.
     *
     * @param fiche the fiche to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fiche, or with status {@code 400 (Bad Request)} if the fiche has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Fiche> createFiche(@RequestBody Fiche fiche) throws URISyntaxException {
        LOG.debug("REST request to save Fiche : {}", fiche);
        if (fiche.getId() != null) {
            throw new BadRequestAlertException("A new fiche cannot already have an ID", ENTITY_NAME, "idexists");
        }
        fiche = ficheRepository.save(fiche);
        return ResponseEntity.created(new URI("/api/fiches/" + fiche.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, fiche.getId().toString()))
            .body(fiche);
    }

    /**
     * {@code PUT  /fiches/:id} : Updates an existing fiche.
     *
     * @param id the id of the fiche to save.
     * @param fiche the fiche to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fiche,
     * or with status {@code 400 (Bad Request)} if the fiche is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fiche couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Fiche> updateFiche(@PathVariable(value = "id", required = false) final Long id, @RequestBody Fiche fiche)
        throws URISyntaxException {
        LOG.debug("REST request to update Fiche : {}, {}", id, fiche);
        if (fiche.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fiche.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ficheRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        fiche = ficheRepository.save(fiche);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fiche.getId().toString()))
            .body(fiche);
    }

    /**
     * {@code PATCH  /fiches/:id} : Partial updates given fields of an existing fiche, field will ignore if it is null
     *
     * @param id the id of the fiche to save.
     * @param fiche the fiche to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fiche,
     * or with status {@code 400 (Bad Request)} if the fiche is not valid,
     * or with status {@code 404 (Not Found)} if the fiche is not found,
     * or with status {@code 500 (Internal Server Error)} if the fiche couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Fiche> partialUpdateFiche(@PathVariable(value = "id", required = false) final Long id, @RequestBody Fiche fiche)
        throws URISyntaxException {
        LOG.debug("REST request to partial update Fiche partially : {}, {}", id, fiche);
        if (fiche.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, fiche.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ficheRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Fiche> result = ficheRepository
            .findById(fiche.getId())
            .map(existingFiche -> {
                if (fiche.getnDossier() != null) {
                    existingFiche.setnDossier(fiche.getnDossier());
                }
                if (fiche.getDateDiagnostic() != null) {
                    existingFiche.setDateDiagnostic(fiche.getDateDiagnostic());
                }
                if (fiche.getDateEnregistrement() != null) {
                    existingFiche.setDateEnregistrement(fiche.getDateEnregistrement());
                }
                if (fiche.getMedecin() != null) {
                    existingFiche.setMedecin(fiche.getMedecin());
                }
                if (fiche.getHopital() != null) {
                    existingFiche.setHopital(fiche.getHopital());
                }
                if (fiche.getService() != null) {
                    existingFiche.setService(fiche.getService());
                }
                if (fiche.getDegConsang() != null) {
                    existingFiche.setDegConsang(fiche.getDegConsang());
                }
                if (fiche.getPlaceEnfant() != null) {
                    existingFiche.setPlaceEnfant(fiche.getPlaceEnfant());
                }
                if (fiche.getNbVivant() != null) {
                    existingFiche.setNbVivant(fiche.getNbVivant());
                }
                if (fiche.getNbMort() != null) {
                    existingFiche.setNbMort(fiche.getNbMort());
                }
                if (fiche.getMortNe() != null) {
                    existingFiche.setMortNe(fiche.getMortNe());
                }
                if (fiche.getAvortement() != null) {
                    existingFiche.setAvortement(fiche.getAvortement());
                }
                if (fiche.getCousin() != null) {
                    existingFiche.setCousin(fiche.getCousin());
                }
                if (fiche.getMembre() != null) {
                    existingFiche.setMembre(fiche.getMembre());
                }
                if (fiche.getArbregenealogique() != null) {
                    existingFiche.setArbregenealogique(fiche.getArbregenealogique());
                }
                if (fiche.getArbregenealogiqueContentType() != null) {
                    existingFiche.setArbregenealogiqueContentType(fiche.getArbregenealogiqueContentType());
                }
                if (fiche.getArbregenealogiquecancer() != null) {
                    existingFiche.setArbregenealogiquecancer(fiche.getArbregenealogiquecancer());
                }
                if (fiche.getArbregenealogiquecancerContentType() != null) {
                    existingFiche.setArbregenealogiquecancerContentType(fiche.getArbregenealogiquecancerContentType());
                }
                if (fiche.getSyndromeAnemique() != null) {
                    existingFiche.setSyndromeAnemique(fiche.getSyndromeAnemique());
                }
                if (fiche.getSyndromeHem() != null) {
                    existingFiche.setSyndromeHem(fiche.getSyndromeHem());
                }
                if (fiche.getSyndromeInf() != null) {
                    existingFiche.setSyndromeInf(fiche.getSyndromeInf());
                }
                if (fiche.getDecouverteFort() != null) {
                    existingFiche.setDecouverteFort(fiche.getDecouverteFort());
                }
                if (fiche.getEnqueteFam() != null) {
                    existingFiche.setEnqueteFam(fiche.getEnqueteFam());
                }
                if (fiche.getTypeCancer() != null) {
                    existingFiche.setTypeCancer(fiche.getTypeCancer());
                }
                if (fiche.getCancer() != null) {
                    existingFiche.setCancer(fiche.getCancer());
                }
                if (fiche.getTailleNaiss() != null) {
                    existingFiche.setTailleNaiss(fiche.getTailleNaiss());
                }
                if (fiche.getPoidsNaiss() != null) {
                    existingFiche.setPoidsNaiss(fiche.getPoidsNaiss());
                }
                if (fiche.getHypotrophie() != null) {
                    existingFiche.setHypotrophie(fiche.getHypotrophie());
                }
                if (fiche.getTroubleCroi() != null) {
                    existingFiche.setTroubleCroi(fiche.getTroubleCroi());
                }
                if (fiche.getaAgeChDiag() != null) {
                    existingFiche.setaAgeChDiag(fiche.getaAgeChDiag());
                }
                if (fiche.getmAgeChDiag() != null) {
                    existingFiche.setmAgeChDiag(fiche.getmAgeChDiag());
                }
                if (fiche.getaAgeOssDiag() != null) {
                    existingFiche.setaAgeOssDiag(fiche.getaAgeOssDiag());
                }
                if (fiche.getmAgeOssDiag() != null) {
                    existingFiche.setmAgeOssDiag(fiche.getmAgeOssDiag());
                }
                if (fiche.getAgeRetard() != null) {
                    existingFiche.setAgeRetard(fiche.getAgeRetard());
                }
                if (fiche.getPoids() != null) {
                    existingFiche.setPoids(fiche.getPoids());
                }
                if (fiche.getPoidsDS() != null) {
                    existingFiche.setPoidsDS(fiche.getPoidsDS());
                }
                if (fiche.getTaille() != null) {
                    existingFiche.setTaille(fiche.getTaille());
                }
                if (fiche.getTailleDS() != null) {
                    existingFiche.setTailleDS(fiche.getTailleDS());
                }
                if (fiche.getAtteinteCut() != null) {
                    existingFiche.setAtteinteCut(fiche.getAtteinteCut());
                }
                if (fiche.getTacheCaf() != null) {
                    existingFiche.setTacheCaf(fiche.getTacheCaf());
                }
                if (fiche.getVentre() != null) {
                    existingFiche.setVentre(fiche.getVentre());
                }
                if (fiche.getMembreSup() != null) {
                    existingFiche.setMembreSup(fiche.getMembreSup());
                }
                if (fiche.getMembreInf() != null) {
                    existingFiche.setMembreInf(fiche.getMembreInf());
                }
                if (fiche.getVisage() != null) {
                    existingFiche.setVisage(fiche.getVisage());
                }
                if (fiche.getThorax() != null) {
                    existingFiche.setThorax(fiche.getThorax());
                }
                if (fiche.getdOS() != null) {
                    existingFiche.setdOS(fiche.getdOS());
                }
                if (fiche.getHyperPig() != null) {
                    existingFiche.setHyperPig(fiche.getHyperPig());
                }
                if (fiche.getHypochromique() != null) {
                    existingFiche.setHypochromique(fiche.getHypochromique());
                }
                if (fiche.getCouleurPeau() != null) {
                    existingFiche.setCouleurPeau(fiche.getCouleurPeau());
                }
                if (fiche.getAutreAtCut() != null) {
                    existingFiche.setAutreAtCut(fiche.getAutreAtCut());
                }
                if (fiche.getAtteinteTete() != null) {
                    existingFiche.setAtteinteTete(fiche.getAtteinteTete());
                }
                if (fiche.getMicrocephalie() != null) {
                    existingFiche.setMicrocephalie(fiche.getMicrocephalie());
                }
                if (fiche.getMicrophtalmie() != null) {
                    existingFiche.setMicrophtalmie(fiche.getMicrophtalmie());
                }
                if (fiche.getFacieTrig() != null) {
                    existingFiche.setFacieTrig(fiche.getFacieTrig());
                }
                if (fiche.getTraitsFin() != null) {
                    existingFiche.setTraitsFin(fiche.getTraitsFin());
                }
                if (fiche.getAutreAtTete() != null) {
                    existingFiche.setAutreAtTete(fiche.getAutreAtTete());
                }
                if (fiche.getEmpreintedigitiforme() != null) {
                    existingFiche.setEmpreintedigitiforme(fiche.getEmpreintedigitiforme());
                }
                if (fiche.getMalUro() != null) {
                    existingFiche.setMalUro(fiche.getMalUro());
                }
                if (fiche.getuIV() != null) {
                    existingFiche.setuIV(fiche.getuIV());
                }
                if (fiche.getEcho() != null) {
                    existingFiche.setEcho(fiche.getEcho());
                }
                if (fiche.getReinEctop() != null) {
                    existingFiche.setReinEctop(fiche.getReinEctop());
                }
                if (fiche.getSiegeEctopie() != null) {
                    existingFiche.setSiegeEctopie(fiche.getSiegeEctopie());
                }
                if (fiche.getReinFerChev() != null) {
                    existingFiche.setReinFerChev(fiche.getReinFerChev());
                }
                if (fiche.getPetitRein() != null) {
                    existingFiche.setPetitRein(fiche.getPetitRein());
                }
                if (fiche.getReinUnique() != null) {
                    existingFiche.setReinUnique(fiche.getReinUnique());
                }
                if (fiche.getEctopTest() != null) {
                    existingFiche.setEctopTest(fiche.getEctopTest());
                }
                if (fiche.getVergeInsuf() != null) {
                    existingFiche.setVergeInsuf(fiche.getVergeInsuf());
                }
                if (fiche.getAutreAnomVerge() != null) {
                    existingFiche.setAutreAnomVerge(fiche.getAutreAnomVerge());
                }
                if (fiche.getRetardPubertaire() != null) {
                    existingFiche.setRetardPubertaire(fiche.getRetardPubertaire());
                }
                if (fiche.getmTanner() != null) {
                    existingFiche.setmTanner(fiche.getmTanner());
                }
                if (fiche.getpTanner() != null) {
                    existingFiche.setpTanner(fiche.getpTanner());
                }
                if (fiche.gettTanner() != null) {
                    existingFiche.settTanner(fiche.gettTanner());
                }
                if (fiche.getAnomUrin() != null) {
                    existingFiche.setAnomUrin(fiche.getAnomUrin());
                }
                if (fiche.getTypeAnomUrin() != null) {
                    existingFiche.setTypeAnomUrin(fiche.getTypeAnomUrin());
                }
                if (fiche.getAtteinteOss() != null) {
                    existingFiche.setAtteinteOss(fiche.getAtteinteOss());
                }
                if (fiche.getRadiosfaites() != null) {
                    existingFiche.setRadiosfaites(fiche.getRadiosfaites());
                }
                if (fiche.getAnomPouce() != null) {
                    existingFiche.setAnomPouce(fiche.getAnomPouce());
                }
                if (fiche.getSurnumerarie() != null) {
                    existingFiche.setSurnumerarie(fiche.getSurnumerarie());
                }
                if (fiche.getAgenesiePouce() != null) {
                    existingFiche.setAgenesiePouce(fiche.getAgenesiePouce());
                }
                if (fiche.getBifide() != null) {
                    existingFiche.setBifide(fiche.getBifide());
                }
                if (fiche.getHypoPouce() != null) {
                    existingFiche.setHypoPouce(fiche.getHypoPouce());
                }
                if (fiche.getAspectPouce() != null) {
                    existingFiche.setAspectPouce(fiche.getAspectPouce());
                }
                if (fiche.getHypoEminence() != null) {
                    existingFiche.setHypoEminence(fiche.getHypoEminence());
                }
                if (fiche.getAbsenceRadial() != null) {
                    existingFiche.setAbsenceRadial(fiche.getAbsenceRadial());
                }
                if (fiche.getPouceBas() != null) {
                    existingFiche.setPouceBas(fiche.getPouceBas());
                }
                if (fiche.getAutreAnomPouce() != null) {
                    existingFiche.setAutreAnomPouce(fiche.getAutreAnomPouce());
                }
                if (fiche.getAnomAutDoigts() != null) {
                    existingFiche.setAnomAutDoigts(fiche.getAnomAutDoigts());
                }
                if (fiche.getCourtstrapus() != null) {
                    existingFiche.setCourtstrapus(fiche.getCourtstrapus());
                }
                if (fiche.getSyndactylie() != null) {
                    existingFiche.setSyndactylie(fiche.getSyndactylie());
                }
                if (fiche.getAutreAnomDoigts() != null) {
                    existingFiche.setAutreAnomDoigts(fiche.getAutreAnomDoigts());
                }
                if (fiche.getAnomalieos() != null) {
                    existingFiche.setAnomalieos(fiche.getAnomalieos());
                }
                if (fiche.getAgenesieRadius() != null) {
                    existingFiche.setAgenesieRadius(fiche.getAgenesieRadius());
                }
                if (fiche.getAutreanomalieMembresup() != null) {
                    existingFiche.setAutreanomalieMembresup(fiche.getAutreanomalieMembresup());
                }
                if (fiche.getAnomOrteil() != null) {
                    existingFiche.setAnomOrteil(fiche.getAnomOrteil());
                }
                if (fiche.getPreciseAnomOrt() != null) {
                    existingFiche.setPreciseAnomOrt(fiche.getPreciseAnomOrt());
                }
                if (fiche.getlCH() != null) {
                    existingFiche.setlCH(fiche.getlCH());
                }
                if (fiche.getAutreanomalieMembreinf() != null) {
                    existingFiche.setAutreanomalieMembreinf(fiche.getAutreanomalieMembreinf());
                }
                if (fiche.getAnomRachis() != null) {
                    existingFiche.setAnomRachis(fiche.getAnomRachis());
                }
                if (fiche.getPreciseAnomRac() != null) {
                    existingFiche.setPreciseAnomRac(fiche.getPreciseAnomRac());
                }
                if (fiche.getAutreAnomOss() != null) {
                    existingFiche.setAutreAnomOss(fiche.getAutreAnomOss());
                }
                if (fiche.getAnomNeuro() != null) {
                    existingFiche.setAnomNeuro(fiche.getAnomNeuro());
                }
                if (fiche.getRetardMent() != null) {
                    existingFiche.setRetardMent(fiche.getRetardMent());
                }
                if (fiche.getHypoacousie() != null) {
                    existingFiche.setHypoacousie(fiche.getHypoacousie());
                }
                if (fiche.getStrabisme() != null) {
                    existingFiche.setStrabisme(fiche.getStrabisme());
                }
                if (fiche.getPerformanceScolaire() != null) {
                    existingFiche.setPerformanceScolaire(fiche.getPerformanceScolaire());
                }
                if (fiche.getAutreanomalieneurologique() != null) {
                    existingFiche.setAutreanomalieneurologique(fiche.getAutreanomalieneurologique());
                }
                if (fiche.getAnomCardVas() != null) {
                    existingFiche.setAnomCardVas(fiche.getAnomCardVas());
                }
                if (fiche.getEchoCoeur() != null) {
                    existingFiche.setEchoCoeur(fiche.getEchoCoeur());
                }
                if (fiche.getPreciseAnomCardio() != null) {
                    existingFiche.setPreciseAnomCardio(fiche.getPreciseAnomCardio());
                }
                if (fiche.getAnomDig() != null) {
                    existingFiche.setAnomDig(fiche.getAnomDig());
                }
                if (fiche.getPreciseAnomDig() != null) {
                    existingFiche.setPreciseAnomDig(fiche.getPreciseAnomDig());
                }
                if (fiche.getEndocrinopathie() != null) {
                    existingFiche.setEndocrinopathie(fiche.getEndocrinopathie());
                }
                if (fiche.getDiabete() != null) {
                    existingFiche.setDiabete(fiche.getDiabete());
                }
                if (fiche.getInsulinoDep() != null) {
                    existingFiche.setInsulinoDep(fiche.getInsulinoDep());
                }
                if (fiche.getHypothyroidie() != null) {
                    existingFiche.setHypothyroidie(fiche.getHypothyroidie());
                }
                if (fiche.getAgeDecouverte() != null) {
                    existingFiche.setAgeDecouverte(fiche.getAgeDecouverte());
                }
                if (fiche.getAutreEndocrinopathie() != null) {
                    existingFiche.setAutreEndocrinopathie(fiche.getAutreEndocrinopathie());
                }
                if (fiche.getAutreAnomCong() != null) {
                    existingFiche.setAutreAnomCong(fiche.getAutreAnomCong());
                }
                if (fiche.getDateNumSanguine() != null) {
                    existingFiche.setDateNumSanguine(fiche.getDateNumSanguine());
                }
                if (fiche.getAge() != null) {
                    existingFiche.setAge(fiche.getAge());
                }
                if (fiche.getHb() != null) {
                    existingFiche.setHb(fiche.getHb());
                }
                if (fiche.getvGM() != null) {
                    existingFiche.setvGM(fiche.getvGM());
                }
                if (fiche.getRetic() != null) {
                    existingFiche.setRetic(fiche.getRetic());
                }
                if (fiche.getLeuco() != null) {
                    existingFiche.setLeuco(fiche.getLeuco());
                }
                if (fiche.getpNN() != null) {
                    existingFiche.setpNN(fiche.getpNN());
                }
                if (fiche.getPlq() != null) {
                    existingFiche.setPlq(fiche.getPlq());
                }
                if (fiche.getHbInf() != null) {
                    existingFiche.setHbInf(fiche.getHbInf());
                }
                if (fiche.getPlqInf() != null) {
                    existingFiche.setPlqInf(fiche.getPlqInf());
                }
                if (fiche.getpNNInf() != null) {
                    existingFiche.setpNNInf(fiche.getpNNInf());
                }
                if (fiche.getElectrophoreseHb() != null) {
                    existingFiche.setElectrophoreseHb(fiche.getElectrophoreseHb());
                }
                if (fiche.getHbf() != null) {
                    existingFiche.setHbf(fiche.getHbf());
                }
                if (fiche.getMyelogramme() != null) {
                    existingFiche.setMyelogramme(fiche.getMyelogramme());
                }
                if (fiche.getCellularite() != null) {
                    existingFiche.setCellularite(fiche.getCellularite());
                }
                if (fiche.getErythroblaste() != null) {
                    existingFiche.setErythroblaste(fiche.getErythroblaste());
                }
                if (fiche.getMorphologieEryth() != null) {
                    existingFiche.setMorphologieEryth(fiche.getMorphologieEryth());
                }
                if (fiche.getMorphologieGran() != null) {
                    existingFiche.setMorphologieGran(fiche.getMorphologieGran());
                }
                if (fiche.getMorphologieMega() != null) {
                    existingFiche.setMorphologieMega(fiche.getMorphologieMega());
                }
                if (fiche.getGranuleux() != null) {
                    existingFiche.setGranuleux(fiche.getGranuleux());
                }
                if (fiche.getDysmyelopoiese() != null) {
                    existingFiche.setDysmyelopoiese(fiche.getDysmyelopoiese());
                }
                if (fiche.getMegacaryocytes() != null) {
                    existingFiche.setMegacaryocytes(fiche.getMegacaryocytes());
                }
                if (fiche.getBlaste() != null) {
                    existingFiche.setBlaste(fiche.getBlaste());
                }
                if (fiche.getExcesblastes() != null) {
                    existingFiche.setExcesblastes(fiche.getExcesblastes());
                }
                if (fiche.getbOM() != null) {
                    existingFiche.setbOM(fiche.getbOM());
                }
                if (fiche.getAdipocytes() != null) {
                    existingFiche.setAdipocytes(fiche.getAdipocytes());
                }
                if (fiche.getUbiquitination() != null) {
                    existingFiche.setUbiquitination(fiche.getUbiquitination());
                }
                if (fiche.getResUbiquitination() != null) {
                    existingFiche.setResUbiquitination(fiche.getResUbiquitination());
                }
                if (fiche.getGroupComp() != null) {
                    existingFiche.setGroupComp(fiche.getGroupComp());
                }
                if (fiche.getMutationFANC() != null) {
                    existingFiche.setMutationFANC(fiche.getMutationFANC());
                }
                if (fiche.getCongelationCellule() != null) {
                    existingFiche.setCongelationCellule(fiche.getCongelationCellule());
                }
                if (fiche.getLabo() != null) {
                    existingFiche.setLabo(fiche.getLabo());
                }
                if (fiche.getTypePrelevement() != null) {
                    existingFiche.setTypePrelevement(fiche.getTypePrelevement());
                }
                if (fiche.getScoreClinique() != null) {
                    existingFiche.setScoreClinique(fiche.getScoreClinique());
                }
                if (fiche.getScoreEBMT() != null) {
                    existingFiche.setScoreEBMT(fiche.getScoreEBMT());
                }
                if (fiche.getScore() != null) {
                    existingFiche.setScore(fiche.getScore());
                }
                if (fiche.getTransfusion() != null) {
                    existingFiche.setTransfusion(fiche.getTransfusion());
                }
                if (fiche.getAgeTransf() != null) {
                    existingFiche.setAgeTransf(fiche.getAgeTransf());
                }
                if (fiche.getDelaiDiag() != null) {
                    existingFiche.setDelaiDiag(fiche.getDelaiDiag());
                }
                if (fiche.getNbCG() != null) {
                    existingFiche.setNbCG(fiche.getNbCG());
                }
                if (fiche.getNbCP() != null) {
                    existingFiche.setNbCP(fiche.getNbCP());
                }
                if (fiche.getChelationFer() != null) {
                    existingFiche.setChelationFer(fiche.getChelationFer());
                }
                if (fiche.getChelateur() != null) {
                    existingFiche.setChelateur(fiche.getChelateur());
                }
                if (fiche.getNilevar() != null) {
                    existingFiche.setNilevar(fiche.getNilevar());
                }
                if (fiche.getDanatrol() != null) {
                    existingFiche.setDanatrol(fiche.getDanatrol());
                }
                if (fiche.getOxynethadone() != null) {
                    existingFiche.setOxynethadone(fiche.getOxynethadone());
                }
                if (fiche.getAndrotordyl() != null) {
                    existingFiche.setAndrotordyl(fiche.getAndrotordyl());
                }
                if (fiche.getAutreAndrogene() != null) {
                    existingFiche.setAutreAndrogene(fiche.getAutreAndrogene());
                }
                if (fiche.getAndroDebut() != null) {
                    existingFiche.setAndroDebut(fiche.getAndroDebut());
                }
                if (fiche.getAndroFin() != null) {
                    existingFiche.setAndroFin(fiche.getAndroFin());
                }
                if (fiche.getObservance() != null) {
                    existingFiche.setObservance(fiche.getObservance());
                }
                if (fiche.getToxicite() != null) {
                    existingFiche.setToxicite(fiche.getToxicite());
                }
                if (fiche.getAutreToxicite() != null) {
                    existingFiche.setAutreToxicite(fiche.getAutreToxicite());
                }
                if (fiche.getEnqueteHLA() != null) {
                    existingFiche.setEnqueteHLA(fiche.getEnqueteHLA());
                }
                if (fiche.getPourquoiEnqHLA() != null) {
                    existingFiche.setPourquoiEnqHLA(fiche.getPourquoiEnqHLA());
                }
                if (fiche.getNbFratTyp() != null) {
                    existingFiche.setNbFratTyp(fiche.getNbFratTyp());
                }
                if (fiche.getNbFratNTyp() != null) {
                    existingFiche.setNbFratNTyp(fiche.getNbFratNTyp());
                }
                if (fiche.getDonneurComp() != null) {
                    existingFiche.setDonneurComp(fiche.getDonneurComp());
                }
                if (fiche.getPreciseDonneur() != null) {
                    existingFiche.setPreciseDonneur(fiche.getPreciseDonneur());
                }
                if (fiche.getDonneur() != null) {
                    existingFiche.setDonneur(fiche.getDonneur());
                }
                if (fiche.getGreffeFaite() != null) {
                    existingFiche.setGreffeFaite(fiche.getGreffeFaite());
                }
                if (fiche.getDelaiRappDiag() != null) {
                    existingFiche.setDelaiRappDiag(fiche.getDelaiRappDiag());
                }
                if (fiche.getPourquoiNfaite() != null) {
                    existingFiche.setPourquoiNfaite(fiche.getPourquoiNfaite());
                }
                if (fiche.getCyclophosphamide() != null) {
                    existingFiche.setCyclophosphamide(fiche.getCyclophosphamide());
                }
                if (fiche.getFludarabine() != null) {
                    existingFiche.setFludarabine(fiche.getFludarabine());
                }
                if (fiche.getBusulfan() != null) {
                    existingFiche.setBusulfan(fiche.getBusulfan());
                }
                if (fiche.getDoseCyclo() != null) {
                    existingFiche.setDoseCyclo(fiche.getDoseCyclo());
                }
                if (fiche.getDoseFlu() != null) {
                    existingFiche.setDoseFlu(fiche.getDoseFlu());
                }
                if (fiche.getDoseBus() != null) {
                    existingFiche.setDoseBus(fiche.getDoseBus());
                }
                if (fiche.getAutreConditionnement() != null) {
                    existingFiche.setAutreConditionnement(fiche.getAutreConditionnement());
                }
                if (fiche.getIrradiation() != null) {
                    existingFiche.setIrradiation(fiche.getIrradiation());
                }
                if (fiche.getDoseTotaleIrr() != null) {
                    existingFiche.setDoseTotaleIrr(fiche.getDoseTotaleIrr());
                }
                if (fiche.getSerotherapie() != null) {
                    existingFiche.setSerotherapie(fiche.getSerotherapie());
                }
                if (fiche.getsAL() != null) {
                    existingFiche.setsAL(fiche.getsAL());
                }
                if (fiche.getDoseSAL() != null) {
                    existingFiche.setDoseSAL(fiche.getDoseSAL());
                }
                if (fiche.getSourceCellule() != null) {
                    existingFiche.setSourceCellule(fiche.getSourceCellule());
                }
                if (fiche.getSortieAplasie() != null) {
                    existingFiche.setSortieAplasie(fiche.getSortieAplasie());
                }
                if (fiche.getGradeaGvH() != null) {
                    existingFiche.setGradeaGvH(fiche.getGradeaGvH());
                }
                if (fiche.getcGvH() != null) {
                    existingFiche.setcGvH(fiche.getcGvH());
                }
                if (fiche.getmVO() != null) {
                    existingFiche.setmVO(fiche.getmVO());
                }
                if (fiche.getComplicationPulmonaire() != null) {
                    existingFiche.setComplicationPulmonaire(fiche.getComplicationPulmonaire());
                }
                if (fiche.getPreciseCompPulm() != null) {
                    existingFiche.setPreciseCompPulm(fiche.getPreciseCompPulm());
                }
                if (fiche.getSurvieJ100() != null) {
                    existingFiche.setSurvieJ100(fiche.getSurvieJ100());
                }
                if (fiche.getsMD() != null) {
                    existingFiche.setsMD(fiche.getsMD());
                }
                if (fiche.getAgeDiagSMD() != null) {
                    existingFiche.setAgeDiagSMD(fiche.getAgeDiagSMD());
                }
                if (fiche.getCritereDiagSMD() != null) {
                    existingFiche.setCritereDiagSMD(fiche.getCritereDiagSMD());
                }
                if (fiche.getTraitementSMD() != null) {
                    existingFiche.setTraitementSMD(fiche.getTraitementSMD());
                }
                if (fiche.getlAM() != null) {
                    existingFiche.setlAM(fiche.getlAM());
                }
                if (fiche.getCritereDiagLAM() != null) {
                    existingFiche.setCritereDiagLAM(fiche.getCritereDiagLAM());
                }
                if (fiche.getTraitementLAM() != null) {
                    existingFiche.setTraitementLAM(fiche.getTraitementLAM());
                }
                if (fiche.getAutresCancers() != null) {
                    existingFiche.setAutresCancers(fiche.getAutresCancers());
                }
                if (fiche.getdDN() != null) {
                    existingFiche.setdDN(fiche.getdDN());
                }
                if (fiche.getTransformationAigue() != null) {
                    existingFiche.setTransformationAigue(fiche.getTransformationAigue());
                }
                if (fiche.getAgeDiagLA() != null) {
                    existingFiche.setAgeDiagLA(fiche.getAgeDiagLA());
                }
                if (fiche.getTraitementLA() != null) {
                    existingFiche.setTraitementLA(fiche.getTraitementLA());
                }
                if (fiche.getCancerE() != null) {
                    existingFiche.setCancerE(fiche.getCancerE());
                }
                if (fiche.getLocalisation() != null) {
                    existingFiche.setLocalisation(fiche.getLocalisation());
                }
                if (fiche.getTypeHistologique() != null) {
                    existingFiche.setTypeHistologique(fiche.getTypeHistologique());
                }
                if (fiche.getTraitementCancer() != null) {
                    existingFiche.setTraitementCancer(fiche.getTraitementCancer());
                }
                if (fiche.getPreciseTraitement() != null) {
                    existingFiche.setPreciseTraitement(fiche.getPreciseTraitement());
                }
                if (fiche.getEvolutionCyto() != null) {
                    existingFiche.setEvolutionCyto(fiche.getEvolutionCyto());
                }
                if (fiche.getFormuleChrom() != null) {
                    existingFiche.setFormuleChrom(fiche.getFormuleChrom());
                }
                if (fiche.getAgeE() != null) {
                    existingFiche.setAgeE(fiche.getAgeE());
                }
                if (fiche.getStatut() != null) {
                    existingFiche.setStatut(fiche.getStatut());
                }
                if (fiche.getCauseDeces() != null) {
                    existingFiche.setCauseDeces(fiche.getCauseDeces());
                }
                if (fiche.getAutreCauseD() != null) {
                    existingFiche.setAutreCauseD(fiche.getAutreCauseD());
                }
                if (fiche.getSurvieGlobale() != null) {
                    existingFiche.setSurvieGlobale(fiche.getSurvieGlobale());
                }
                if (fiche.getCode() != null) {
                    existingFiche.setCode(fiche.getCode());
                }
                if (fiche.getDateMAJ() != null) {
                    existingFiche.setDateMAJ(fiche.getDateMAJ());
                }
                if (fiche.getNombreTacheCafe() != null) {
                    existingFiche.setNombreTacheCafe(fiche.getNombreTacheCafe());
                }
                if (fiche.getNombreTacheHypo() != null) {
                    existingFiche.setNombreTacheHypo(fiche.getNombreTacheHypo());
                }

                return existingFiche;
            })
            .map(ficheRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fiche.getId().toString())
        );
    }

    /**
     * {@code GET  /fiches} : get all the fiches.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fiches in body.
     */
    @GetMapping("")
    public List<Fiche> getAllFiches() {
        LOG.debug("REST request to get all Fiches");
        return ficheRepository.findAll();
    }

    /**
     * {@code GET  /fiches/:id} : get the "id" fiche.
     *
     * @param id the id of the fiche to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fiche, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Fiche> getFiche(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Fiche : {}", id);
        Optional<Fiche> fiche = ficheRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fiche);
    }

    /**
     * {@code DELETE  /fiches/:id} : delete the "id" fiche.
     *
     * @param id the id of the fiche to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFiche(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Fiche : {}", id);
        ficheRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
