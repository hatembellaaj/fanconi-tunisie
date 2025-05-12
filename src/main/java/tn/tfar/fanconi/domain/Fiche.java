package tn.tfar.fanconi.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Fiche.
 */
@Entity
@Table(name = "fiche")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Fiche implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "n_dossier")
    private String nDossier;

    @Column(name = "date_diagnostic")
    private LocalDate dateDiagnostic;

    @Column(name = "date_enregistrement")
    private LocalDate dateEnregistrement;

    @Column(name = "medecin")
    private String medecin;

    @Column(name = "hopital")
    private Integer hopital;

    @Column(name = "service")
    private Integer service;

    @Column(name = "deg_consang")
    private String degConsang;

    @Column(name = "place_enfant")
    private Integer placeEnfant;

    @Column(name = "nb_vivant")
    private Integer nbVivant;

    @Column(name = "nb_mort")
    private Integer nbMort;

    @Column(name = "mort_ne")
    private Integer mortNe;

    @Column(name = "avortement")
    private Integer avortement;

    @Column(name = "cousin")
    private Integer cousin;

    @Column(name = "membre")
    private Integer membre;

    @Lob
    @Column(name = "arbregenealogique")
    private byte[] arbregenealogique;

    @Column(name = "arbregenealogique_content_type")
    private String arbregenealogiqueContentType;

    @Lob
    @Column(name = "arbregenealogiquecancer")
    private byte[] arbregenealogiquecancer;

    @Column(name = "arbregenealogiquecancer_content_type")
    private String arbregenealogiquecancerContentType;

    @Column(name = "syndrome_anemique")
    private String syndromeAnemique;

    @Column(name = "syndrome_hem")
    private String syndromeHem;

    @Column(name = "syndrome_inf")
    private String syndromeInf;

    @Column(name = "decouverte_fort")
    private String decouverteFort;

    @Column(name = "enquete_fam")
    private String enqueteFam;

    @Column(name = "type_cancer")
    private String typeCancer;

    @Column(name = "cancer")
    private String cancer;

    @Column(name = "taille_naiss")
    private Float tailleNaiss;

    @Column(name = "poids_naiss")
    private Float poidsNaiss;

    @Column(name = "hypotrophie")
    private String hypotrophie;

    @Column(name = "trouble_croi")
    private String troubleCroi;

    @Column(name = "a_age_ch_diag")
    private Integer aAgeChDiag;

    @Column(name = "m_age_ch_diag")
    private Integer mAgeChDiag;

    @Column(name = "a_age_oss_diag")
    private Integer aAgeOssDiag;

    @Column(name = "m_age_oss_diag")
    private Integer mAgeOssDiag;

    @Column(name = "age_retard")
    private Integer ageRetard;

    @Column(name = "poids")
    private Float poids;

    @Column(name = "poids_ds")
    private String poidsDS;

    @Column(name = "taille")
    private Float taille;

    @Column(name = "taille_ds")
    private String tailleDS;

    @Column(name = "atteinte_cut")
    private String atteinteCut;

    @Column(name = "tache_caf")
    private String tacheCaf;

    @Column(name = "ventre")
    private String ventre;

    @Column(name = "membre_sup")
    private String membreSup;

    @Column(name = "membre_inf")
    private String membreInf;

    @Column(name = "visage")
    private String visage;

    @Column(name = "thorax")
    private String thorax;

    @Column(name = "d_os")
    private String dOS;

    @Column(name = "hyper_pig")
    private String hyperPig;

    @Column(name = "hypochromique")
    private String hypochromique;

    @Column(name = "couleur_peau")
    private String couleurPeau;

    @Column(name = "autre_at_cut")
    private String autreAtCut;

    @Column(name = "atteinte_tete")
    private String atteinteTete;

    @Column(name = "microcephalie")
    private String microcephalie;

    @Column(name = "microphtalmie")
    private String microphtalmie;

    @Column(name = "facie_trig")
    private String facieTrig;

    @Column(name = "traits_fin")
    private String traitsFin;

    @Column(name = "autre_at_tete")
    private String autreAtTete;

    @Column(name = "empreintedigitiforme")
    private String empreintedigitiforme;

    @Column(name = "mal_uro")
    private String malUro;

    @Column(name = "u_iv")
    private String uIV;

    @Column(name = "echo")
    private String echo;

    @Column(name = "rein_ectop")
    private String reinEctop;

    @Column(name = "siege_ectopie")
    private String siegeEctopie;

    @Column(name = "rein_fer_chev")
    private String reinFerChev;

    @Column(name = "petit_rein")
    private String petitRein;

    @Column(name = "rein_unique")
    private String reinUnique;

    @Column(name = "ectop_test")
    private String ectopTest;

    @Column(name = "verge_insuf")
    private String vergeInsuf;

    @Column(name = "autre_anom_verge")
    private String autreAnomVerge;

    @Column(name = "retard_pubertaire")
    private String retardPubertaire;

    @Column(name = "m_tanner")
    private String mTanner;

    @Column(name = "p_tanner")
    private String pTanner;

    @Column(name = "t_tanner")
    private String tTanner;

    @Column(name = "anom_urin")
    private String anomUrin;

    @Column(name = "type_anom_urin")
    private String typeAnomUrin;

    @Column(name = "atteinte_oss")
    private String atteinteOss;

    @Column(name = "radiosfaites")
    private String radiosfaites;

    @Column(name = "anom_pouce")
    private String anomPouce;

    @Column(name = "surnumerarie")
    private String surnumerarie;

    @Column(name = "agenesie_pouce")
    private String agenesiePouce;

    @Column(name = "bifide")
    private String bifide;

    @Column(name = "hypo_pouce")
    private String hypoPouce;

    @Column(name = "aspect_pouce")
    private String aspectPouce;

    @Column(name = "hypo_eminence")
    private String hypoEminence;

    @Column(name = "absence_radial")
    private String absenceRadial;

    @Column(name = "pouce_bas")
    private String pouceBas;

    @Column(name = "autre_anom_pouce")
    private String autreAnomPouce;

    @Column(name = "anom_aut_doigts")
    private String anomAutDoigts;

    @Column(name = "courtstrapus")
    private String courtstrapus;

    @Column(name = "syndactylie")
    private String syndactylie;

    @Column(name = "autre_anom_doigts")
    private String autreAnomDoigts;

    @Column(name = "anomalieos")
    private String anomalieos;

    @Column(name = "agenesie_radius")
    private String agenesieRadius;

    @Column(name = "autreanomalie_membresup")
    private String autreanomalieMembresup;

    @Column(name = "anom_orteil")
    private String anomOrteil;

    @Column(name = "precise_anom_ort")
    private String preciseAnomOrt;

    @Column(name = "l_ch")
    private String lCH;

    @Column(name = "autreanomalie_membreinf")
    private String autreanomalieMembreinf;

    @Column(name = "anom_rachis")
    private String anomRachis;

    @Column(name = "precise_anom_rac")
    private String preciseAnomRac;

    @Column(name = "autre_anom_oss")
    private String autreAnomOss;

    @Column(name = "anom_neuro")
    private String anomNeuro;

    @Column(name = "retard_ment")
    private String retardMent;

    @Column(name = "hypoacousie")
    private String hypoacousie;

    @Column(name = "strabisme")
    private String strabisme;

    @Column(name = "performance_scolaire")
    private String performanceScolaire;

    @Column(name = "autreanomalieneurologique")
    private String autreanomalieneurologique;

    @Column(name = "anom_card_vas")
    private String anomCardVas;

    @Column(name = "echo_coeur")
    private String echoCoeur;

    @Column(name = "precise_anom_cardio")
    private String preciseAnomCardio;

    @Column(name = "anom_dig")
    private String anomDig;

    @Column(name = "precise_anom_dig")
    private String preciseAnomDig;

    @Column(name = "endocrinopathie")
    private String endocrinopathie;

    @Column(name = "diabete")
    private String diabete;

    @Column(name = "insulino_dep")
    private String insulinoDep;

    @Column(name = "hypothyroidie")
    private String hypothyroidie;

    @Column(name = "age_decouverte")
    private Integer ageDecouverte;

    @Column(name = "autre_endocrinopathie")
    private String autreEndocrinopathie;

    @Column(name = "autre_anom_cong")
    private String autreAnomCong;

    @Column(name = "date_num_sanguine")
    private LocalDate dateNumSanguine;

    @Column(name = "age")
    private Integer age;

    @Column(name = "hb")
    private Float hb;

    @Column(name = "v_gm")
    private Float vGM;

    @Column(name = "retic")
    private Float retic;

    @Column(name = "leuco")
    private Float leuco;

    @Column(name = "p_nn")
    private Float pNN;

    @Column(name = "plq")
    private Float plq;

    @Column(name = "hb_inf")
    private String hbInf;

    @Column(name = "plq_inf")
    private String plqInf;

    @Column(name = "p_nn_inf")
    private String pNNInf;

    @Column(name = "electrophorese_hb")
    private String electrophoreseHb;

    @Column(name = "hbf")
    private Float hbf;

    @Column(name = "myelogramme")
    private String myelogramme;

    @Column(name = "cellularite")
    private String cellularite;

    @Column(name = "erythroblaste")
    private Float erythroblaste;

    @Column(name = "morphologie_eryth")
    private String morphologieEryth;

    @Column(name = "morphologie_gran")
    private String morphologieGran;

    @Column(name = "morphologie_mega")
    private String morphologieMega;

    @Column(name = "granuleux")
    private Float granuleux;

    @Column(name = "dysmyelopoiese")
    private String dysmyelopoiese;

    @Column(name = "megacaryocytes")
    private String megacaryocytes;

    @Column(name = "blaste")
    private Float blaste;

    @Column(name = "excesblastes")
    private String excesblastes;

    @Column(name = "b_om")
    private String bOM;

    @Column(name = "adipocytes")
    private Float adipocytes;

    @Column(name = "ubiquitination")
    private String ubiquitination;

    @Column(name = "res_ubiquitination")
    private String resUbiquitination;

    @Column(name = "group_comp")
    private String groupComp;

    @Column(name = "mutation_fanc")
    private String mutationFANC;

    @Column(name = "congelation_cellule")
    private String congelationCellule;

    @Column(name = "labo")
    private String labo;

    @Column(name = "type_prelevement")
    private String typePrelevement;

    @Column(name = "score_clinique")
    private Integer scoreClinique;

    @Column(name = "score_ebmt")
    private Integer scoreEBMT;

    @Column(name = "score")
    private String score;

    @Column(name = "transfusion")
    private String transfusion;

    @Column(name = "age_transf")
    private Integer ageTransf;

    @Column(name = "delai_diag")
    private Float delaiDiag;

    @Column(name = "nb_cg")
    private String nbCG;

    @Column(name = "nb_cp")
    private String nbCP;

    @Column(name = "chelation_fer")
    private String chelationFer;

    @Column(name = "chelateur")
    private String chelateur;

    @Column(name = "nilevar")
    private String nilevar;

    @Column(name = "danatrol")
    private String danatrol;

    @Column(name = "oxynethadone")
    private String oxynethadone;

    @Column(name = "androtordyl")
    private String androtordyl;

    @Column(name = "autre_androgene")
    private String autreAndrogene;

    @Column(name = "andro_debut")
    private LocalDate androDebut;

    @Column(name = "andro_fin")
    private LocalDate androFin;

    @Column(name = "observance")
    private String observance;

    @Column(name = "toxicite")
    private String toxicite;

    @Column(name = "autre_toxicite")
    private String autreToxicite;

    @Column(name = "enquete_hla")
    private String enqueteHLA;

    @Column(name = "pourquoi_enq_hla")
    private String pourquoiEnqHLA;

    @Column(name = "nb_frat_typ")
    private Integer nbFratTyp;

    @Column(name = "nb_frat_n_typ")
    private Integer nbFratNTyp;

    @Column(name = "donneur_comp")
    private Integer donneurComp;

    @Column(name = "precise_donneur")
    private String preciseDonneur;

    @Column(name = "donneur")
    private String donneur;

    @Column(name = "greffe_faite")
    private String greffeFaite;

    @Column(name = "delai_rapp_diag")
    private Float delaiRappDiag;

    @Column(name = "pourquoi_nfaite")
    private String pourquoiNfaite;

    @Column(name = "cyclophosphamide")
    private String cyclophosphamide;

    @Column(name = "fludarabine")
    private String fludarabine;

    @Column(name = "busulfan")
    private String busulfan;

    @Column(name = "dose_cyclo")
    private Float doseCyclo;

    @Column(name = "dose_flu")
    private Float doseFlu;

    @Column(name = "dose_bus")
    private Float doseBus;

    @Column(name = "autre_conditionnement")
    private String autreConditionnement;

    @Column(name = "irradiation")
    private String irradiation;

    @Column(name = "dose_totale_irr")
    private Float doseTotaleIrr;

    @Column(name = "serotherapie")
    private String serotherapie;

    @Column(name = "s_al")
    private String sAL;

    @Column(name = "dose_sal")
    private Float doseSAL;

    @Column(name = "source_cellule")
    private String sourceCellule;

    @Column(name = "sortie_aplasie")
    private Integer sortieAplasie;

    @Column(name = "gradea_gv_h")
    private String gradeaGvH;

    @Column(name = "c_gv_h")
    private String cGvH;

    @Column(name = "m_vo")
    private String mVO;

    @Column(name = "complication_pulmonaire")
    private String complicationPulmonaire;

    @Column(name = "precise_comp_pulm")
    private String preciseCompPulm;

    @Column(name = "survie_j_100")
    private String survieJ100;

    @Column(name = "s_md")
    private String sMD;

    @Column(name = "age_diag_smd")
    private Integer ageDiagSMD;

    @Column(name = "critere_diag_smd")
    private String critereDiagSMD;

    @Column(name = "traitement_smd")
    private String traitementSMD;

    @Column(name = "l_am")
    private String lAM;

    @Column(name = "critere_diag_lam")
    private String critereDiagLAM;

    @Column(name = "traitement_lam")
    private String traitementLAM;

    @Column(name = "autres_cancers")
    private String autresCancers;

    @Column(name = "d_dn")
    private LocalDate dDN;

    @Column(name = "transformation_aigue")
    private String transformationAigue;

    @Column(name = "age_diag_la")
    private Integer ageDiagLA;

    @Column(name = "traitement_la")
    private String traitementLA;

    @Column(name = "cancer_e")
    private String cancerE;

    @Column(name = "localisation")
    private String localisation;

    @Column(name = "type_histologique")
    private String typeHistologique;

    @Column(name = "traitement_cancer")
    private String traitementCancer;

    @Column(name = "precise_traitement")
    private String preciseTraitement;

    @Column(name = "evolution_cyto")
    private String evolutionCyto;

    @Column(name = "formule_chrom")
    private String formuleChrom;

    @Column(name = "age_e")
    private Integer ageE;

    @Column(name = "statut")
    private String statut;

    @Column(name = "cause_deces")
    private String causeDeces;

    @Column(name = "autre_cause_d")
    private String autreCauseD;

    @Column(name = "survie_globale")
    private Float survieGlobale;

    @Column(name = "code")
    private Integer code;

    @Column(name = "date_maj")
    private LocalDate dateMAJ;

    @Column(name = "nombre_tache_cafe")
    private Integer nombreTacheCafe;

    @Column(name = "nombre_tache_hypo")
    private Integer nombreTacheHypo;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Fiche id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getnDossier() {
        return this.nDossier;
    }

    public Fiche nDossier(String nDossier) {
        this.setnDossier(nDossier);
        return this;
    }

    public void setnDossier(String nDossier) {
        this.nDossier = nDossier;
    }

    public LocalDate getDateDiagnostic() {
        return this.dateDiagnostic;
    }

    public Fiche dateDiagnostic(LocalDate dateDiagnostic) {
        this.setDateDiagnostic(dateDiagnostic);
        return this;
    }

    public void setDateDiagnostic(LocalDate dateDiagnostic) {
        this.dateDiagnostic = dateDiagnostic;
    }

    public LocalDate getDateEnregistrement() {
        return this.dateEnregistrement;
    }

    public Fiche dateEnregistrement(LocalDate dateEnregistrement) {
        this.setDateEnregistrement(dateEnregistrement);
        return this;
    }

    public void setDateEnregistrement(LocalDate dateEnregistrement) {
        this.dateEnregistrement = dateEnregistrement;
    }

    public String getMedecin() {
        return this.medecin;
    }

    public Fiche medecin(String medecin) {
        this.setMedecin(medecin);
        return this;
    }

    public void setMedecin(String medecin) {
        this.medecin = medecin;
    }

    public Integer getHopital() {
        return this.hopital;
    }

    public Fiche hopital(Integer hopital) {
        this.setHopital(hopital);
        return this;
    }

    public void setHopital(Integer hopital) {
        this.hopital = hopital;
    }

    public Integer getService() {
        return this.service;
    }

    public Fiche service(Integer service) {
        this.setService(service);
        return this;
    }

    public void setService(Integer service) {
        this.service = service;
    }

    public String getDegConsang() {
        return this.degConsang;
    }

    public Fiche degConsang(String degConsang) {
        this.setDegConsang(degConsang);
        return this;
    }

    public void setDegConsang(String degConsang) {
        this.degConsang = degConsang;
    }

    public Integer getPlaceEnfant() {
        return this.placeEnfant;
    }

    public Fiche placeEnfant(Integer placeEnfant) {
        this.setPlaceEnfant(placeEnfant);
        return this;
    }

    public void setPlaceEnfant(Integer placeEnfant) {
        this.placeEnfant = placeEnfant;
    }

    public Integer getNbVivant() {
        return this.nbVivant;
    }

    public Fiche nbVivant(Integer nbVivant) {
        this.setNbVivant(nbVivant);
        return this;
    }

    public void setNbVivant(Integer nbVivant) {
        this.nbVivant = nbVivant;
    }

    public Integer getNbMort() {
        return this.nbMort;
    }

    public Fiche nbMort(Integer nbMort) {
        this.setNbMort(nbMort);
        return this;
    }

    public void setNbMort(Integer nbMort) {
        this.nbMort = nbMort;
    }

    public Integer getMortNe() {
        return this.mortNe;
    }

    public Fiche mortNe(Integer mortNe) {
        this.setMortNe(mortNe);
        return this;
    }

    public void setMortNe(Integer mortNe) {
        this.mortNe = mortNe;
    }

    public Integer getAvortement() {
        return this.avortement;
    }

    public Fiche avortement(Integer avortement) {
        this.setAvortement(avortement);
        return this;
    }

    public void setAvortement(Integer avortement) {
        this.avortement = avortement;
    }

    public Integer getCousin() {
        return this.cousin;
    }

    public Fiche cousin(Integer cousin) {
        this.setCousin(cousin);
        return this;
    }

    public void setCousin(Integer cousin) {
        this.cousin = cousin;
    }

    public Integer getMembre() {
        return this.membre;
    }

    public Fiche membre(Integer membre) {
        this.setMembre(membre);
        return this;
    }

    public void setMembre(Integer membre) {
        this.membre = membre;
    }

    public byte[] getArbregenealogique() {
        return this.arbregenealogique;
    }

    public Fiche arbregenealogique(byte[] arbregenealogique) {
        this.setArbregenealogique(arbregenealogique);
        return this;
    }

    public void setArbregenealogique(byte[] arbregenealogique) {
        this.arbregenealogique = arbregenealogique;
    }

    public String getArbregenealogiqueContentType() {
        return this.arbregenealogiqueContentType;
    }

    public Fiche arbregenealogiqueContentType(String arbregenealogiqueContentType) {
        this.arbregenealogiqueContentType = arbregenealogiqueContentType;
        return this;
    }

    public void setArbregenealogiqueContentType(String arbregenealogiqueContentType) {
        this.arbregenealogiqueContentType = arbregenealogiqueContentType;
    }

    public byte[] getArbregenealogiquecancer() {
        return this.arbregenealogiquecancer;
    }

    public Fiche arbregenealogiquecancer(byte[] arbregenealogiquecancer) {
        this.setArbregenealogiquecancer(arbregenealogiquecancer);
        return this;
    }

    public void setArbregenealogiquecancer(byte[] arbregenealogiquecancer) {
        this.arbregenealogiquecancer = arbregenealogiquecancer;
    }

    public String getArbregenealogiquecancerContentType() {
        return this.arbregenealogiquecancerContentType;
    }

    public Fiche arbregenealogiquecancerContentType(String arbregenealogiquecancerContentType) {
        this.arbregenealogiquecancerContentType = arbregenealogiquecancerContentType;
        return this;
    }

    public void setArbregenealogiquecancerContentType(String arbregenealogiquecancerContentType) {
        this.arbregenealogiquecancerContentType = arbregenealogiquecancerContentType;
    }

    public String getSyndromeAnemique() {
        return this.syndromeAnemique;
    }

    public Fiche syndromeAnemique(String syndromeAnemique) {
        this.setSyndromeAnemique(syndromeAnemique);
        return this;
    }

    public void setSyndromeAnemique(String syndromeAnemique) {
        this.syndromeAnemique = syndromeAnemique;
    }

    public String getSyndromeHem() {
        return this.syndromeHem;
    }

    public Fiche syndromeHem(String syndromeHem) {
        this.setSyndromeHem(syndromeHem);
        return this;
    }

    public void setSyndromeHem(String syndromeHem) {
        this.syndromeHem = syndromeHem;
    }

    public String getSyndromeInf() {
        return this.syndromeInf;
    }

    public Fiche syndromeInf(String syndromeInf) {
        this.setSyndromeInf(syndromeInf);
        return this;
    }

    public void setSyndromeInf(String syndromeInf) {
        this.syndromeInf = syndromeInf;
    }

    public String getDecouverteFort() {
        return this.decouverteFort;
    }

    public Fiche decouverteFort(String decouverteFort) {
        this.setDecouverteFort(decouverteFort);
        return this;
    }

    public void setDecouverteFort(String decouverteFort) {
        this.decouverteFort = decouverteFort;
    }

    public String getEnqueteFam() {
        return this.enqueteFam;
    }

    public Fiche enqueteFam(String enqueteFam) {
        this.setEnqueteFam(enqueteFam);
        return this;
    }

    public void setEnqueteFam(String enqueteFam) {
        this.enqueteFam = enqueteFam;
    }

    public String getTypeCancer() {
        return this.typeCancer;
    }

    public Fiche typeCancer(String typeCancer) {
        this.setTypeCancer(typeCancer);
        return this;
    }

    public void setTypeCancer(String typeCancer) {
        this.typeCancer = typeCancer;
    }

    public String getCancer() {
        return this.cancer;
    }

    public Fiche cancer(String cancer) {
        this.setCancer(cancer);
        return this;
    }

    public void setCancer(String cancer) {
        this.cancer = cancer;
    }

    public Float getTailleNaiss() {
        return this.tailleNaiss;
    }

    public Fiche tailleNaiss(Float tailleNaiss) {
        this.setTailleNaiss(tailleNaiss);
        return this;
    }

    public void setTailleNaiss(Float tailleNaiss) {
        this.tailleNaiss = tailleNaiss;
    }

    public Float getPoidsNaiss() {
        return this.poidsNaiss;
    }

    public Fiche poidsNaiss(Float poidsNaiss) {
        this.setPoidsNaiss(poidsNaiss);
        return this;
    }

    public void setPoidsNaiss(Float poidsNaiss) {
        this.poidsNaiss = poidsNaiss;
    }

    public String getHypotrophie() {
        return this.hypotrophie;
    }

    public Fiche hypotrophie(String hypotrophie) {
        this.setHypotrophie(hypotrophie);
        return this;
    }

    public void setHypotrophie(String hypotrophie) {
        this.hypotrophie = hypotrophie;
    }

    public String getTroubleCroi() {
        return this.troubleCroi;
    }

    public Fiche troubleCroi(String troubleCroi) {
        this.setTroubleCroi(troubleCroi);
        return this;
    }

    public void setTroubleCroi(String troubleCroi) {
        this.troubleCroi = troubleCroi;
    }

    public Integer getaAgeChDiag() {
        return this.aAgeChDiag;
    }

    public Fiche aAgeChDiag(Integer aAgeChDiag) {
        this.setaAgeChDiag(aAgeChDiag);
        return this;
    }

    public void setaAgeChDiag(Integer aAgeChDiag) {
        this.aAgeChDiag = aAgeChDiag;
    }

    public Integer getmAgeChDiag() {
        return this.mAgeChDiag;
    }

    public Fiche mAgeChDiag(Integer mAgeChDiag) {
        this.setmAgeChDiag(mAgeChDiag);
        return this;
    }

    public void setmAgeChDiag(Integer mAgeChDiag) {
        this.mAgeChDiag = mAgeChDiag;
    }

    public Integer getaAgeOssDiag() {
        return this.aAgeOssDiag;
    }

    public Fiche aAgeOssDiag(Integer aAgeOssDiag) {
        this.setaAgeOssDiag(aAgeOssDiag);
        return this;
    }

    public void setaAgeOssDiag(Integer aAgeOssDiag) {
        this.aAgeOssDiag = aAgeOssDiag;
    }

    public Integer getmAgeOssDiag() {
        return this.mAgeOssDiag;
    }

    public Fiche mAgeOssDiag(Integer mAgeOssDiag) {
        this.setmAgeOssDiag(mAgeOssDiag);
        return this;
    }

    public void setmAgeOssDiag(Integer mAgeOssDiag) {
        this.mAgeOssDiag = mAgeOssDiag;
    }

    public Integer getAgeRetard() {
        return this.ageRetard;
    }

    public Fiche ageRetard(Integer ageRetard) {
        this.setAgeRetard(ageRetard);
        return this;
    }

    public void setAgeRetard(Integer ageRetard) {
        this.ageRetard = ageRetard;
    }

    public Float getPoids() {
        return this.poids;
    }

    public Fiche poids(Float poids) {
        this.setPoids(poids);
        return this;
    }

    public void setPoids(Float poids) {
        this.poids = poids;
    }

    public String getPoidsDS() {
        return this.poidsDS;
    }

    public Fiche poidsDS(String poidsDS) {
        this.setPoidsDS(poidsDS);
        return this;
    }

    public void setPoidsDS(String poidsDS) {
        this.poidsDS = poidsDS;
    }

    public Float getTaille() {
        return this.taille;
    }

    public Fiche taille(Float taille) {
        this.setTaille(taille);
        return this;
    }

    public void setTaille(Float taille) {
        this.taille = taille;
    }

    public String getTailleDS() {
        return this.tailleDS;
    }

    public Fiche tailleDS(String tailleDS) {
        this.setTailleDS(tailleDS);
        return this;
    }

    public void setTailleDS(String tailleDS) {
        this.tailleDS = tailleDS;
    }

    public String getAtteinteCut() {
        return this.atteinteCut;
    }

    public Fiche atteinteCut(String atteinteCut) {
        this.setAtteinteCut(atteinteCut);
        return this;
    }

    public void setAtteinteCut(String atteinteCut) {
        this.atteinteCut = atteinteCut;
    }

    public String getTacheCaf() {
        return this.tacheCaf;
    }

    public Fiche tacheCaf(String tacheCaf) {
        this.setTacheCaf(tacheCaf);
        return this;
    }

    public void setTacheCaf(String tacheCaf) {
        this.tacheCaf = tacheCaf;
    }

    public String getVentre() {
        return this.ventre;
    }

    public Fiche ventre(String ventre) {
        this.setVentre(ventre);
        return this;
    }

    public void setVentre(String ventre) {
        this.ventre = ventre;
    }

    public String getMembreSup() {
        return this.membreSup;
    }

    public Fiche membreSup(String membreSup) {
        this.setMembreSup(membreSup);
        return this;
    }

    public void setMembreSup(String membreSup) {
        this.membreSup = membreSup;
    }

    public String getMembreInf() {
        return this.membreInf;
    }

    public Fiche membreInf(String membreInf) {
        this.setMembreInf(membreInf);
        return this;
    }

    public void setMembreInf(String membreInf) {
        this.membreInf = membreInf;
    }

    public String getVisage() {
        return this.visage;
    }

    public Fiche visage(String visage) {
        this.setVisage(visage);
        return this;
    }

    public void setVisage(String visage) {
        this.visage = visage;
    }

    public String getThorax() {
        return this.thorax;
    }

    public Fiche thorax(String thorax) {
        this.setThorax(thorax);
        return this;
    }

    public void setThorax(String thorax) {
        this.thorax = thorax;
    }

    public String getdOS() {
        return this.dOS;
    }

    public Fiche dOS(String dOS) {
        this.setdOS(dOS);
        return this;
    }

    public void setdOS(String dOS) {
        this.dOS = dOS;
    }

    public String getHyperPig() {
        return this.hyperPig;
    }

    public Fiche hyperPig(String hyperPig) {
        this.setHyperPig(hyperPig);
        return this;
    }

    public void setHyperPig(String hyperPig) {
        this.hyperPig = hyperPig;
    }

    public String getHypochromique() {
        return this.hypochromique;
    }

    public Fiche hypochromique(String hypochromique) {
        this.setHypochromique(hypochromique);
        return this;
    }

    public void setHypochromique(String hypochromique) {
        this.hypochromique = hypochromique;
    }

    public String getCouleurPeau() {
        return this.couleurPeau;
    }

    public Fiche couleurPeau(String couleurPeau) {
        this.setCouleurPeau(couleurPeau);
        return this;
    }

    public void setCouleurPeau(String couleurPeau) {
        this.couleurPeau = couleurPeau;
    }

    public String getAutreAtCut() {
        return this.autreAtCut;
    }

    public Fiche autreAtCut(String autreAtCut) {
        this.setAutreAtCut(autreAtCut);
        return this;
    }

    public void setAutreAtCut(String autreAtCut) {
        this.autreAtCut = autreAtCut;
    }

    public String getAtteinteTete() {
        return this.atteinteTete;
    }

    public Fiche atteinteTete(String atteinteTete) {
        this.setAtteinteTete(atteinteTete);
        return this;
    }

    public void setAtteinteTete(String atteinteTete) {
        this.atteinteTete = atteinteTete;
    }

    public String getMicrocephalie() {
        return this.microcephalie;
    }

    public Fiche microcephalie(String microcephalie) {
        this.setMicrocephalie(microcephalie);
        return this;
    }

    public void setMicrocephalie(String microcephalie) {
        this.microcephalie = microcephalie;
    }

    public String getMicrophtalmie() {
        return this.microphtalmie;
    }

    public Fiche microphtalmie(String microphtalmie) {
        this.setMicrophtalmie(microphtalmie);
        return this;
    }

    public void setMicrophtalmie(String microphtalmie) {
        this.microphtalmie = microphtalmie;
    }

    public String getFacieTrig() {
        return this.facieTrig;
    }

    public Fiche facieTrig(String facieTrig) {
        this.setFacieTrig(facieTrig);
        return this;
    }

    public void setFacieTrig(String facieTrig) {
        this.facieTrig = facieTrig;
    }

    public String getTraitsFin() {
        return this.traitsFin;
    }

    public Fiche traitsFin(String traitsFin) {
        this.setTraitsFin(traitsFin);
        return this;
    }

    public void setTraitsFin(String traitsFin) {
        this.traitsFin = traitsFin;
    }

    public String getAutreAtTete() {
        return this.autreAtTete;
    }

    public Fiche autreAtTete(String autreAtTete) {
        this.setAutreAtTete(autreAtTete);
        return this;
    }

    public void setAutreAtTete(String autreAtTete) {
        this.autreAtTete = autreAtTete;
    }

    public String getEmpreintedigitiforme() {
        return this.empreintedigitiforme;
    }

    public Fiche empreintedigitiforme(String empreintedigitiforme) {
        this.setEmpreintedigitiforme(empreintedigitiforme);
        return this;
    }

    public void setEmpreintedigitiforme(String empreintedigitiforme) {
        this.empreintedigitiforme = empreintedigitiforme;
    }

    public String getMalUro() {
        return this.malUro;
    }

    public Fiche malUro(String malUro) {
        this.setMalUro(malUro);
        return this;
    }

    public void setMalUro(String malUro) {
        this.malUro = malUro;
    }

    public String getuIV() {
        return this.uIV;
    }

    public Fiche uIV(String uIV) {
        this.setuIV(uIV);
        return this;
    }

    public void setuIV(String uIV) {
        this.uIV = uIV;
    }

    public String getEcho() {
        return this.echo;
    }

    public Fiche echo(String echo) {
        this.setEcho(echo);
        return this;
    }

    public void setEcho(String echo) {
        this.echo = echo;
    }

    public String getReinEctop() {
        return this.reinEctop;
    }

    public Fiche reinEctop(String reinEctop) {
        this.setReinEctop(reinEctop);
        return this;
    }

    public void setReinEctop(String reinEctop) {
        this.reinEctop = reinEctop;
    }

    public String getSiegeEctopie() {
        return this.siegeEctopie;
    }

    public Fiche siegeEctopie(String siegeEctopie) {
        this.setSiegeEctopie(siegeEctopie);
        return this;
    }

    public void setSiegeEctopie(String siegeEctopie) {
        this.siegeEctopie = siegeEctopie;
    }

    public String getReinFerChev() {
        return this.reinFerChev;
    }

    public Fiche reinFerChev(String reinFerChev) {
        this.setReinFerChev(reinFerChev);
        return this;
    }

    public void setReinFerChev(String reinFerChev) {
        this.reinFerChev = reinFerChev;
    }

    public String getPetitRein() {
        return this.petitRein;
    }

    public Fiche petitRein(String petitRein) {
        this.setPetitRein(petitRein);
        return this;
    }

    public void setPetitRein(String petitRein) {
        this.petitRein = petitRein;
    }

    public String getReinUnique() {
        return this.reinUnique;
    }

    public Fiche reinUnique(String reinUnique) {
        this.setReinUnique(reinUnique);
        return this;
    }

    public void setReinUnique(String reinUnique) {
        this.reinUnique = reinUnique;
    }

    public String getEctopTest() {
        return this.ectopTest;
    }

    public Fiche ectopTest(String ectopTest) {
        this.setEctopTest(ectopTest);
        return this;
    }

    public void setEctopTest(String ectopTest) {
        this.ectopTest = ectopTest;
    }

    public String getVergeInsuf() {
        return this.vergeInsuf;
    }

    public Fiche vergeInsuf(String vergeInsuf) {
        this.setVergeInsuf(vergeInsuf);
        return this;
    }

    public void setVergeInsuf(String vergeInsuf) {
        this.vergeInsuf = vergeInsuf;
    }

    public String getAutreAnomVerge() {
        return this.autreAnomVerge;
    }

    public Fiche autreAnomVerge(String autreAnomVerge) {
        this.setAutreAnomVerge(autreAnomVerge);
        return this;
    }

    public void setAutreAnomVerge(String autreAnomVerge) {
        this.autreAnomVerge = autreAnomVerge;
    }

    public String getRetardPubertaire() {
        return this.retardPubertaire;
    }

    public Fiche retardPubertaire(String retardPubertaire) {
        this.setRetardPubertaire(retardPubertaire);
        return this;
    }

    public void setRetardPubertaire(String retardPubertaire) {
        this.retardPubertaire = retardPubertaire;
    }

    public String getmTanner() {
        return this.mTanner;
    }

    public Fiche mTanner(String mTanner) {
        this.setmTanner(mTanner);
        return this;
    }

    public void setmTanner(String mTanner) {
        this.mTanner = mTanner;
    }

    public String getpTanner() {
        return this.pTanner;
    }

    public Fiche pTanner(String pTanner) {
        this.setpTanner(pTanner);
        return this;
    }

    public void setpTanner(String pTanner) {
        this.pTanner = pTanner;
    }

    public String gettTanner() {
        return this.tTanner;
    }

    public Fiche tTanner(String tTanner) {
        this.settTanner(tTanner);
        return this;
    }

    public void settTanner(String tTanner) {
        this.tTanner = tTanner;
    }

    public String getAnomUrin() {
        return this.anomUrin;
    }

    public Fiche anomUrin(String anomUrin) {
        this.setAnomUrin(anomUrin);
        return this;
    }

    public void setAnomUrin(String anomUrin) {
        this.anomUrin = anomUrin;
    }

    public String getTypeAnomUrin() {
        return this.typeAnomUrin;
    }

    public Fiche typeAnomUrin(String typeAnomUrin) {
        this.setTypeAnomUrin(typeAnomUrin);
        return this;
    }

    public void setTypeAnomUrin(String typeAnomUrin) {
        this.typeAnomUrin = typeAnomUrin;
    }

    public String getAtteinteOss() {
        return this.atteinteOss;
    }

    public Fiche atteinteOss(String atteinteOss) {
        this.setAtteinteOss(atteinteOss);
        return this;
    }

    public void setAtteinteOss(String atteinteOss) {
        this.atteinteOss = atteinteOss;
    }

    public String getRadiosfaites() {
        return this.radiosfaites;
    }

    public Fiche radiosfaites(String radiosfaites) {
        this.setRadiosfaites(radiosfaites);
        return this;
    }

    public void setRadiosfaites(String radiosfaites) {
        this.radiosfaites = radiosfaites;
    }

    public String getAnomPouce() {
        return this.anomPouce;
    }

    public Fiche anomPouce(String anomPouce) {
        this.setAnomPouce(anomPouce);
        return this;
    }

    public void setAnomPouce(String anomPouce) {
        this.anomPouce = anomPouce;
    }

    public String getSurnumerarie() {
        return this.surnumerarie;
    }

    public Fiche surnumerarie(String surnumerarie) {
        this.setSurnumerarie(surnumerarie);
        return this;
    }

    public void setSurnumerarie(String surnumerarie) {
        this.surnumerarie = surnumerarie;
    }

    public String getAgenesiePouce() {
        return this.agenesiePouce;
    }

    public Fiche agenesiePouce(String agenesiePouce) {
        this.setAgenesiePouce(agenesiePouce);
        return this;
    }

    public void setAgenesiePouce(String agenesiePouce) {
        this.agenesiePouce = agenesiePouce;
    }

    public String getBifide() {
        return this.bifide;
    }

    public Fiche bifide(String bifide) {
        this.setBifide(bifide);
        return this;
    }

    public void setBifide(String bifide) {
        this.bifide = bifide;
    }

    public String getHypoPouce() {
        return this.hypoPouce;
    }

    public Fiche hypoPouce(String hypoPouce) {
        this.setHypoPouce(hypoPouce);
        return this;
    }

    public void setHypoPouce(String hypoPouce) {
        this.hypoPouce = hypoPouce;
    }

    public String getAspectPouce() {
        return this.aspectPouce;
    }

    public Fiche aspectPouce(String aspectPouce) {
        this.setAspectPouce(aspectPouce);
        return this;
    }

    public void setAspectPouce(String aspectPouce) {
        this.aspectPouce = aspectPouce;
    }

    public String getHypoEminence() {
        return this.hypoEminence;
    }

    public Fiche hypoEminence(String hypoEminence) {
        this.setHypoEminence(hypoEminence);
        return this;
    }

    public void setHypoEminence(String hypoEminence) {
        this.hypoEminence = hypoEminence;
    }

    public String getAbsenceRadial() {
        return this.absenceRadial;
    }

    public Fiche absenceRadial(String absenceRadial) {
        this.setAbsenceRadial(absenceRadial);
        return this;
    }

    public void setAbsenceRadial(String absenceRadial) {
        this.absenceRadial = absenceRadial;
    }

    public String getPouceBas() {
        return this.pouceBas;
    }

    public Fiche pouceBas(String pouceBas) {
        this.setPouceBas(pouceBas);
        return this;
    }

    public void setPouceBas(String pouceBas) {
        this.pouceBas = pouceBas;
    }

    public String getAutreAnomPouce() {
        return this.autreAnomPouce;
    }

    public Fiche autreAnomPouce(String autreAnomPouce) {
        this.setAutreAnomPouce(autreAnomPouce);
        return this;
    }

    public void setAutreAnomPouce(String autreAnomPouce) {
        this.autreAnomPouce = autreAnomPouce;
    }

    public String getAnomAutDoigts() {
        return this.anomAutDoigts;
    }

    public Fiche anomAutDoigts(String anomAutDoigts) {
        this.setAnomAutDoigts(anomAutDoigts);
        return this;
    }

    public void setAnomAutDoigts(String anomAutDoigts) {
        this.anomAutDoigts = anomAutDoigts;
    }

    public String getCourtstrapus() {
        return this.courtstrapus;
    }

    public Fiche courtstrapus(String courtstrapus) {
        this.setCourtstrapus(courtstrapus);
        return this;
    }

    public void setCourtstrapus(String courtstrapus) {
        this.courtstrapus = courtstrapus;
    }

    public String getSyndactylie() {
        return this.syndactylie;
    }

    public Fiche syndactylie(String syndactylie) {
        this.setSyndactylie(syndactylie);
        return this;
    }

    public void setSyndactylie(String syndactylie) {
        this.syndactylie = syndactylie;
    }

    public String getAutreAnomDoigts() {
        return this.autreAnomDoigts;
    }

    public Fiche autreAnomDoigts(String autreAnomDoigts) {
        this.setAutreAnomDoigts(autreAnomDoigts);
        return this;
    }

    public void setAutreAnomDoigts(String autreAnomDoigts) {
        this.autreAnomDoigts = autreAnomDoigts;
    }

    public String getAnomalieos() {
        return this.anomalieos;
    }

    public Fiche anomalieos(String anomalieos) {
        this.setAnomalieos(anomalieos);
        return this;
    }

    public void setAnomalieos(String anomalieos) {
        this.anomalieos = anomalieos;
    }

    public String getAgenesieRadius() {
        return this.agenesieRadius;
    }

    public Fiche agenesieRadius(String agenesieRadius) {
        this.setAgenesieRadius(agenesieRadius);
        return this;
    }

    public void setAgenesieRadius(String agenesieRadius) {
        this.agenesieRadius = agenesieRadius;
    }

    public String getAutreanomalieMembresup() {
        return this.autreanomalieMembresup;
    }

    public Fiche autreanomalieMembresup(String autreanomalieMembresup) {
        this.setAutreanomalieMembresup(autreanomalieMembresup);
        return this;
    }

    public void setAutreanomalieMembresup(String autreanomalieMembresup) {
        this.autreanomalieMembresup = autreanomalieMembresup;
    }

    public String getAnomOrteil() {
        return this.anomOrteil;
    }

    public Fiche anomOrteil(String anomOrteil) {
        this.setAnomOrteil(anomOrteil);
        return this;
    }

    public void setAnomOrteil(String anomOrteil) {
        this.anomOrteil = anomOrteil;
    }

    public String getPreciseAnomOrt() {
        return this.preciseAnomOrt;
    }

    public Fiche preciseAnomOrt(String preciseAnomOrt) {
        this.setPreciseAnomOrt(preciseAnomOrt);
        return this;
    }

    public void setPreciseAnomOrt(String preciseAnomOrt) {
        this.preciseAnomOrt = preciseAnomOrt;
    }

    public String getlCH() {
        return this.lCH;
    }

    public Fiche lCH(String lCH) {
        this.setlCH(lCH);
        return this;
    }

    public void setlCH(String lCH) {
        this.lCH = lCH;
    }

    public String getAutreanomalieMembreinf() {
        return this.autreanomalieMembreinf;
    }

    public Fiche autreanomalieMembreinf(String autreanomalieMembreinf) {
        this.setAutreanomalieMembreinf(autreanomalieMembreinf);
        return this;
    }

    public void setAutreanomalieMembreinf(String autreanomalieMembreinf) {
        this.autreanomalieMembreinf = autreanomalieMembreinf;
    }

    public String getAnomRachis() {
        return this.anomRachis;
    }

    public Fiche anomRachis(String anomRachis) {
        this.setAnomRachis(anomRachis);
        return this;
    }

    public void setAnomRachis(String anomRachis) {
        this.anomRachis = anomRachis;
    }

    public String getPreciseAnomRac() {
        return this.preciseAnomRac;
    }

    public Fiche preciseAnomRac(String preciseAnomRac) {
        this.setPreciseAnomRac(preciseAnomRac);
        return this;
    }

    public void setPreciseAnomRac(String preciseAnomRac) {
        this.preciseAnomRac = preciseAnomRac;
    }

    public String getAutreAnomOss() {
        return this.autreAnomOss;
    }

    public Fiche autreAnomOss(String autreAnomOss) {
        this.setAutreAnomOss(autreAnomOss);
        return this;
    }

    public void setAutreAnomOss(String autreAnomOss) {
        this.autreAnomOss = autreAnomOss;
    }

    public String getAnomNeuro() {
        return this.anomNeuro;
    }

    public Fiche anomNeuro(String anomNeuro) {
        this.setAnomNeuro(anomNeuro);
        return this;
    }

    public void setAnomNeuro(String anomNeuro) {
        this.anomNeuro = anomNeuro;
    }

    public String getRetardMent() {
        return this.retardMent;
    }

    public Fiche retardMent(String retardMent) {
        this.setRetardMent(retardMent);
        return this;
    }

    public void setRetardMent(String retardMent) {
        this.retardMent = retardMent;
    }

    public String getHypoacousie() {
        return this.hypoacousie;
    }

    public Fiche hypoacousie(String hypoacousie) {
        this.setHypoacousie(hypoacousie);
        return this;
    }

    public void setHypoacousie(String hypoacousie) {
        this.hypoacousie = hypoacousie;
    }

    public String getStrabisme() {
        return this.strabisme;
    }

    public Fiche strabisme(String strabisme) {
        this.setStrabisme(strabisme);
        return this;
    }

    public void setStrabisme(String strabisme) {
        this.strabisme = strabisme;
    }

    public String getPerformanceScolaire() {
        return this.performanceScolaire;
    }

    public Fiche performanceScolaire(String performanceScolaire) {
        this.setPerformanceScolaire(performanceScolaire);
        return this;
    }

    public void setPerformanceScolaire(String performanceScolaire) {
        this.performanceScolaire = performanceScolaire;
    }

    public String getAutreanomalieneurologique() {
        return this.autreanomalieneurologique;
    }

    public Fiche autreanomalieneurologique(String autreanomalieneurologique) {
        this.setAutreanomalieneurologique(autreanomalieneurologique);
        return this;
    }

    public void setAutreanomalieneurologique(String autreanomalieneurologique) {
        this.autreanomalieneurologique = autreanomalieneurologique;
    }

    public String getAnomCardVas() {
        return this.anomCardVas;
    }

    public Fiche anomCardVas(String anomCardVas) {
        this.setAnomCardVas(anomCardVas);
        return this;
    }

    public void setAnomCardVas(String anomCardVas) {
        this.anomCardVas = anomCardVas;
    }

    public String getEchoCoeur() {
        return this.echoCoeur;
    }

    public Fiche echoCoeur(String echoCoeur) {
        this.setEchoCoeur(echoCoeur);
        return this;
    }

    public void setEchoCoeur(String echoCoeur) {
        this.echoCoeur = echoCoeur;
    }

    public String getPreciseAnomCardio() {
        return this.preciseAnomCardio;
    }

    public Fiche preciseAnomCardio(String preciseAnomCardio) {
        this.setPreciseAnomCardio(preciseAnomCardio);
        return this;
    }

    public void setPreciseAnomCardio(String preciseAnomCardio) {
        this.preciseAnomCardio = preciseAnomCardio;
    }

    public String getAnomDig() {
        return this.anomDig;
    }

    public Fiche anomDig(String anomDig) {
        this.setAnomDig(anomDig);
        return this;
    }

    public void setAnomDig(String anomDig) {
        this.anomDig = anomDig;
    }

    public String getPreciseAnomDig() {
        return this.preciseAnomDig;
    }

    public Fiche preciseAnomDig(String preciseAnomDig) {
        this.setPreciseAnomDig(preciseAnomDig);
        return this;
    }

    public void setPreciseAnomDig(String preciseAnomDig) {
        this.preciseAnomDig = preciseAnomDig;
    }

    public String getEndocrinopathie() {
        return this.endocrinopathie;
    }

    public Fiche endocrinopathie(String endocrinopathie) {
        this.setEndocrinopathie(endocrinopathie);
        return this;
    }

    public void setEndocrinopathie(String endocrinopathie) {
        this.endocrinopathie = endocrinopathie;
    }

    public String getDiabete() {
        return this.diabete;
    }

    public Fiche diabete(String diabete) {
        this.setDiabete(diabete);
        return this;
    }

    public void setDiabete(String diabete) {
        this.diabete = diabete;
    }

    public String getInsulinoDep() {
        return this.insulinoDep;
    }

    public Fiche insulinoDep(String insulinoDep) {
        this.setInsulinoDep(insulinoDep);
        return this;
    }

    public void setInsulinoDep(String insulinoDep) {
        this.insulinoDep = insulinoDep;
    }

    public String getHypothyroidie() {
        return this.hypothyroidie;
    }

    public Fiche hypothyroidie(String hypothyroidie) {
        this.setHypothyroidie(hypothyroidie);
        return this;
    }

    public void setHypothyroidie(String hypothyroidie) {
        this.hypothyroidie = hypothyroidie;
    }

    public Integer getAgeDecouverte() {
        return this.ageDecouverte;
    }

    public Fiche ageDecouverte(Integer ageDecouverte) {
        this.setAgeDecouverte(ageDecouverte);
        return this;
    }

    public void setAgeDecouverte(Integer ageDecouverte) {
        this.ageDecouverte = ageDecouverte;
    }

    public String getAutreEndocrinopathie() {
        return this.autreEndocrinopathie;
    }

    public Fiche autreEndocrinopathie(String autreEndocrinopathie) {
        this.setAutreEndocrinopathie(autreEndocrinopathie);
        return this;
    }

    public void setAutreEndocrinopathie(String autreEndocrinopathie) {
        this.autreEndocrinopathie = autreEndocrinopathie;
    }

    public String getAutreAnomCong() {
        return this.autreAnomCong;
    }

    public Fiche autreAnomCong(String autreAnomCong) {
        this.setAutreAnomCong(autreAnomCong);
        return this;
    }

    public void setAutreAnomCong(String autreAnomCong) {
        this.autreAnomCong = autreAnomCong;
    }

    public LocalDate getDateNumSanguine() {
        return this.dateNumSanguine;
    }

    public Fiche dateNumSanguine(LocalDate dateNumSanguine) {
        this.setDateNumSanguine(dateNumSanguine);
        return this;
    }

    public void setDateNumSanguine(LocalDate dateNumSanguine) {
        this.dateNumSanguine = dateNumSanguine;
    }

    public Integer getAge() {
        return this.age;
    }

    public Fiche age(Integer age) {
        this.setAge(age);
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Float getHb() {
        return this.hb;
    }

    public Fiche hb(Float hb) {
        this.setHb(hb);
        return this;
    }

    public void setHb(Float hb) {
        this.hb = hb;
    }

    public Float getvGM() {
        return this.vGM;
    }

    public Fiche vGM(Float vGM) {
        this.setvGM(vGM);
        return this;
    }

    public void setvGM(Float vGM) {
        this.vGM = vGM;
    }

    public Float getRetic() {
        return this.retic;
    }

    public Fiche retic(Float retic) {
        this.setRetic(retic);
        return this;
    }

    public void setRetic(Float retic) {
        this.retic = retic;
    }

    public Float getLeuco() {
        return this.leuco;
    }

    public Fiche leuco(Float leuco) {
        this.setLeuco(leuco);
        return this;
    }

    public void setLeuco(Float leuco) {
        this.leuco = leuco;
    }

    public Float getpNN() {
        return this.pNN;
    }

    public Fiche pNN(Float pNN) {
        this.setpNN(pNN);
        return this;
    }

    public void setpNN(Float pNN) {
        this.pNN = pNN;
    }

    public Float getPlq() {
        return this.plq;
    }

    public Fiche plq(Float plq) {
        this.setPlq(plq);
        return this;
    }

    public void setPlq(Float plq) {
        this.plq = plq;
    }

    public String getHbInf() {
        return this.hbInf;
    }

    public Fiche hbInf(String hbInf) {
        this.setHbInf(hbInf);
        return this;
    }

    public void setHbInf(String hbInf) {
        this.hbInf = hbInf;
    }

    public String getPlqInf() {
        return this.plqInf;
    }

    public Fiche plqInf(String plqInf) {
        this.setPlqInf(plqInf);
        return this;
    }

    public void setPlqInf(String plqInf) {
        this.plqInf = plqInf;
    }

    public String getpNNInf() {
        return this.pNNInf;
    }

    public Fiche pNNInf(String pNNInf) {
        this.setpNNInf(pNNInf);
        return this;
    }

    public void setpNNInf(String pNNInf) {
        this.pNNInf = pNNInf;
    }

    public String getElectrophoreseHb() {
        return this.electrophoreseHb;
    }

    public Fiche electrophoreseHb(String electrophoreseHb) {
        this.setElectrophoreseHb(electrophoreseHb);
        return this;
    }

    public void setElectrophoreseHb(String electrophoreseHb) {
        this.electrophoreseHb = electrophoreseHb;
    }

    public Float getHbf() {
        return this.hbf;
    }

    public Fiche hbf(Float hbf) {
        this.setHbf(hbf);
        return this;
    }

    public void setHbf(Float hbf) {
        this.hbf = hbf;
    }

    public String getMyelogramme() {
        return this.myelogramme;
    }

    public Fiche myelogramme(String myelogramme) {
        this.setMyelogramme(myelogramme);
        return this;
    }

    public void setMyelogramme(String myelogramme) {
        this.myelogramme = myelogramme;
    }

    public String getCellularite() {
        return this.cellularite;
    }

    public Fiche cellularite(String cellularite) {
        this.setCellularite(cellularite);
        return this;
    }

    public void setCellularite(String cellularite) {
        this.cellularite = cellularite;
    }

    public Float getErythroblaste() {
        return this.erythroblaste;
    }

    public Fiche erythroblaste(Float erythroblaste) {
        this.setErythroblaste(erythroblaste);
        return this;
    }

    public void setErythroblaste(Float erythroblaste) {
        this.erythroblaste = erythroblaste;
    }

    public String getMorphologieEryth() {
        return this.morphologieEryth;
    }

    public Fiche morphologieEryth(String morphologieEryth) {
        this.setMorphologieEryth(morphologieEryth);
        return this;
    }

    public void setMorphologieEryth(String morphologieEryth) {
        this.morphologieEryth = morphologieEryth;
    }

    public String getMorphologieGran() {
        return this.morphologieGran;
    }

    public Fiche morphologieGran(String morphologieGran) {
        this.setMorphologieGran(morphologieGran);
        return this;
    }

    public void setMorphologieGran(String morphologieGran) {
        this.morphologieGran = morphologieGran;
    }

    public String getMorphologieMega() {
        return this.morphologieMega;
    }

    public Fiche morphologieMega(String morphologieMega) {
        this.setMorphologieMega(morphologieMega);
        return this;
    }

    public void setMorphologieMega(String morphologieMega) {
        this.morphologieMega = morphologieMega;
    }

    public Float getGranuleux() {
        return this.granuleux;
    }

    public Fiche granuleux(Float granuleux) {
        this.setGranuleux(granuleux);
        return this;
    }

    public void setGranuleux(Float granuleux) {
        this.granuleux = granuleux;
    }

    public String getDysmyelopoiese() {
        return this.dysmyelopoiese;
    }

    public Fiche dysmyelopoiese(String dysmyelopoiese) {
        this.setDysmyelopoiese(dysmyelopoiese);
        return this;
    }

    public void setDysmyelopoiese(String dysmyelopoiese) {
        this.dysmyelopoiese = dysmyelopoiese;
    }

    public String getMegacaryocytes() {
        return this.megacaryocytes;
    }

    public Fiche megacaryocytes(String megacaryocytes) {
        this.setMegacaryocytes(megacaryocytes);
        return this;
    }

    public void setMegacaryocytes(String megacaryocytes) {
        this.megacaryocytes = megacaryocytes;
    }

    public Float getBlaste() {
        return this.blaste;
    }

    public Fiche blaste(Float blaste) {
        this.setBlaste(blaste);
        return this;
    }

    public void setBlaste(Float blaste) {
        this.blaste = blaste;
    }

    public String getExcesblastes() {
        return this.excesblastes;
    }

    public Fiche excesblastes(String excesblastes) {
        this.setExcesblastes(excesblastes);
        return this;
    }

    public void setExcesblastes(String excesblastes) {
        this.excesblastes = excesblastes;
    }

    public String getbOM() {
        return this.bOM;
    }

    public Fiche bOM(String bOM) {
        this.setbOM(bOM);
        return this;
    }

    public void setbOM(String bOM) {
        this.bOM = bOM;
    }

    public Float getAdipocytes() {
        return this.adipocytes;
    }

    public Fiche adipocytes(Float adipocytes) {
        this.setAdipocytes(adipocytes);
        return this;
    }

    public void setAdipocytes(Float adipocytes) {
        this.adipocytes = adipocytes;
    }

    public String getUbiquitination() {
        return this.ubiquitination;
    }

    public Fiche ubiquitination(String ubiquitination) {
        this.setUbiquitination(ubiquitination);
        return this;
    }

    public void setUbiquitination(String ubiquitination) {
        this.ubiquitination = ubiquitination;
    }

    public String getResUbiquitination() {
        return this.resUbiquitination;
    }

    public Fiche resUbiquitination(String resUbiquitination) {
        this.setResUbiquitination(resUbiquitination);
        return this;
    }

    public void setResUbiquitination(String resUbiquitination) {
        this.resUbiquitination = resUbiquitination;
    }

    public String getGroupComp() {
        return this.groupComp;
    }

    public Fiche groupComp(String groupComp) {
        this.setGroupComp(groupComp);
        return this;
    }

    public void setGroupComp(String groupComp) {
        this.groupComp = groupComp;
    }

    public String getMutationFANC() {
        return this.mutationFANC;
    }

    public Fiche mutationFANC(String mutationFANC) {
        this.setMutationFANC(mutationFANC);
        return this;
    }

    public void setMutationFANC(String mutationFANC) {
        this.mutationFANC = mutationFANC;
    }

    public String getCongelationCellule() {
        return this.congelationCellule;
    }

    public Fiche congelationCellule(String congelationCellule) {
        this.setCongelationCellule(congelationCellule);
        return this;
    }

    public void setCongelationCellule(String congelationCellule) {
        this.congelationCellule = congelationCellule;
    }

    public String getLabo() {
        return this.labo;
    }

    public Fiche labo(String labo) {
        this.setLabo(labo);
        return this;
    }

    public void setLabo(String labo) {
        this.labo = labo;
    }

    public String getTypePrelevement() {
        return this.typePrelevement;
    }

    public Fiche typePrelevement(String typePrelevement) {
        this.setTypePrelevement(typePrelevement);
        return this;
    }

    public void setTypePrelevement(String typePrelevement) {
        this.typePrelevement = typePrelevement;
    }

    public Integer getScoreClinique() {
        return this.scoreClinique;
    }

    public Fiche scoreClinique(Integer scoreClinique) {
        this.setScoreClinique(scoreClinique);
        return this;
    }

    public void setScoreClinique(Integer scoreClinique) {
        this.scoreClinique = scoreClinique;
    }

    public Integer getScoreEBMT() {
        return this.scoreEBMT;
    }

    public Fiche scoreEBMT(Integer scoreEBMT) {
        this.setScoreEBMT(scoreEBMT);
        return this;
    }

    public void setScoreEBMT(Integer scoreEBMT) {
        this.scoreEBMT = scoreEBMT;
    }

    public String getScore() {
        return this.score;
    }

    public Fiche score(String score) {
        this.setScore(score);
        return this;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTransfusion() {
        return this.transfusion;
    }

    public Fiche transfusion(String transfusion) {
        this.setTransfusion(transfusion);
        return this;
    }

    public void setTransfusion(String transfusion) {
        this.transfusion = transfusion;
    }

    public Integer getAgeTransf() {
        return this.ageTransf;
    }

    public Fiche ageTransf(Integer ageTransf) {
        this.setAgeTransf(ageTransf);
        return this;
    }

    public void setAgeTransf(Integer ageTransf) {
        this.ageTransf = ageTransf;
    }

    public Float getDelaiDiag() {
        return this.delaiDiag;
    }

    public Fiche delaiDiag(Float delaiDiag) {
        this.setDelaiDiag(delaiDiag);
        return this;
    }

    public void setDelaiDiag(Float delaiDiag) {
        this.delaiDiag = delaiDiag;
    }

    public String getNbCG() {
        return this.nbCG;
    }

    public Fiche nbCG(String nbCG) {
        this.setNbCG(nbCG);
        return this;
    }

    public void setNbCG(String nbCG) {
        this.nbCG = nbCG;
    }

    public String getNbCP() {
        return this.nbCP;
    }

    public Fiche nbCP(String nbCP) {
        this.setNbCP(nbCP);
        return this;
    }

    public void setNbCP(String nbCP) {
        this.nbCP = nbCP;
    }

    public String getChelationFer() {
        return this.chelationFer;
    }

    public Fiche chelationFer(String chelationFer) {
        this.setChelationFer(chelationFer);
        return this;
    }

    public void setChelationFer(String chelationFer) {
        this.chelationFer = chelationFer;
    }

    public String getChelateur() {
        return this.chelateur;
    }

    public Fiche chelateur(String chelateur) {
        this.setChelateur(chelateur);
        return this;
    }

    public void setChelateur(String chelateur) {
        this.chelateur = chelateur;
    }

    public String getNilevar() {
        return this.nilevar;
    }

    public Fiche nilevar(String nilevar) {
        this.setNilevar(nilevar);
        return this;
    }

    public void setNilevar(String nilevar) {
        this.nilevar = nilevar;
    }

    public String getDanatrol() {
        return this.danatrol;
    }

    public Fiche danatrol(String danatrol) {
        this.setDanatrol(danatrol);
        return this;
    }

    public void setDanatrol(String danatrol) {
        this.danatrol = danatrol;
    }

    public String getOxynethadone() {
        return this.oxynethadone;
    }

    public Fiche oxynethadone(String oxynethadone) {
        this.setOxynethadone(oxynethadone);
        return this;
    }

    public void setOxynethadone(String oxynethadone) {
        this.oxynethadone = oxynethadone;
    }

    public String getAndrotordyl() {
        return this.androtordyl;
    }

    public Fiche androtordyl(String androtordyl) {
        this.setAndrotordyl(androtordyl);
        return this;
    }

    public void setAndrotordyl(String androtordyl) {
        this.androtordyl = androtordyl;
    }

    public String getAutreAndrogene() {
        return this.autreAndrogene;
    }

    public Fiche autreAndrogene(String autreAndrogene) {
        this.setAutreAndrogene(autreAndrogene);
        return this;
    }

    public void setAutreAndrogene(String autreAndrogene) {
        this.autreAndrogene = autreAndrogene;
    }

    public LocalDate getAndroDebut() {
        return this.androDebut;
    }

    public Fiche androDebut(LocalDate androDebut) {
        this.setAndroDebut(androDebut);
        return this;
    }

    public void setAndroDebut(LocalDate androDebut) {
        this.androDebut = androDebut;
    }

    public LocalDate getAndroFin() {
        return this.androFin;
    }

    public Fiche androFin(LocalDate androFin) {
        this.setAndroFin(androFin);
        return this;
    }

    public void setAndroFin(LocalDate androFin) {
        this.androFin = androFin;
    }

    public String getObservance() {
        return this.observance;
    }

    public Fiche observance(String observance) {
        this.setObservance(observance);
        return this;
    }

    public void setObservance(String observance) {
        this.observance = observance;
    }

    public String getToxicite() {
        return this.toxicite;
    }

    public Fiche toxicite(String toxicite) {
        this.setToxicite(toxicite);
        return this;
    }

    public void setToxicite(String toxicite) {
        this.toxicite = toxicite;
    }

    public String getAutreToxicite() {
        return this.autreToxicite;
    }

    public Fiche autreToxicite(String autreToxicite) {
        this.setAutreToxicite(autreToxicite);
        return this;
    }

    public void setAutreToxicite(String autreToxicite) {
        this.autreToxicite = autreToxicite;
    }

    public String getEnqueteHLA() {
        return this.enqueteHLA;
    }

    public Fiche enqueteHLA(String enqueteHLA) {
        this.setEnqueteHLA(enqueteHLA);
        return this;
    }

    public void setEnqueteHLA(String enqueteHLA) {
        this.enqueteHLA = enqueteHLA;
    }

    public String getPourquoiEnqHLA() {
        return this.pourquoiEnqHLA;
    }

    public Fiche pourquoiEnqHLA(String pourquoiEnqHLA) {
        this.setPourquoiEnqHLA(pourquoiEnqHLA);
        return this;
    }

    public void setPourquoiEnqHLA(String pourquoiEnqHLA) {
        this.pourquoiEnqHLA = pourquoiEnqHLA;
    }

    public Integer getNbFratTyp() {
        return this.nbFratTyp;
    }

    public Fiche nbFratTyp(Integer nbFratTyp) {
        this.setNbFratTyp(nbFratTyp);
        return this;
    }

    public void setNbFratTyp(Integer nbFratTyp) {
        this.nbFratTyp = nbFratTyp;
    }

    public Integer getNbFratNTyp() {
        return this.nbFratNTyp;
    }

    public Fiche nbFratNTyp(Integer nbFratNTyp) {
        this.setNbFratNTyp(nbFratNTyp);
        return this;
    }

    public void setNbFratNTyp(Integer nbFratNTyp) {
        this.nbFratNTyp = nbFratNTyp;
    }

    public Integer getDonneurComp() {
        return this.donneurComp;
    }

    public Fiche donneurComp(Integer donneurComp) {
        this.setDonneurComp(donneurComp);
        return this;
    }

    public void setDonneurComp(Integer donneurComp) {
        this.donneurComp = donneurComp;
    }

    public String getPreciseDonneur() {
        return this.preciseDonneur;
    }

    public Fiche preciseDonneur(String preciseDonneur) {
        this.setPreciseDonneur(preciseDonneur);
        return this;
    }

    public void setPreciseDonneur(String preciseDonneur) {
        this.preciseDonneur = preciseDonneur;
    }

    public String getDonneur() {
        return this.donneur;
    }

    public Fiche donneur(String donneur) {
        this.setDonneur(donneur);
        return this;
    }

    public void setDonneur(String donneur) {
        this.donneur = donneur;
    }

    public String getGreffeFaite() {
        return this.greffeFaite;
    }

    public Fiche greffeFaite(String greffeFaite) {
        this.setGreffeFaite(greffeFaite);
        return this;
    }

    public void setGreffeFaite(String greffeFaite) {
        this.greffeFaite = greffeFaite;
    }

    public Float getDelaiRappDiag() {
        return this.delaiRappDiag;
    }

    public Fiche delaiRappDiag(Float delaiRappDiag) {
        this.setDelaiRappDiag(delaiRappDiag);
        return this;
    }

    public void setDelaiRappDiag(Float delaiRappDiag) {
        this.delaiRappDiag = delaiRappDiag;
    }

    public String getPourquoiNfaite() {
        return this.pourquoiNfaite;
    }

    public Fiche pourquoiNfaite(String pourquoiNfaite) {
        this.setPourquoiNfaite(pourquoiNfaite);
        return this;
    }

    public void setPourquoiNfaite(String pourquoiNfaite) {
        this.pourquoiNfaite = pourquoiNfaite;
    }

    public String getCyclophosphamide() {
        return this.cyclophosphamide;
    }

    public Fiche cyclophosphamide(String cyclophosphamide) {
        this.setCyclophosphamide(cyclophosphamide);
        return this;
    }

    public void setCyclophosphamide(String cyclophosphamide) {
        this.cyclophosphamide = cyclophosphamide;
    }

    public String getFludarabine() {
        return this.fludarabine;
    }

    public Fiche fludarabine(String fludarabine) {
        this.setFludarabine(fludarabine);
        return this;
    }

    public void setFludarabine(String fludarabine) {
        this.fludarabine = fludarabine;
    }

    public String getBusulfan() {
        return this.busulfan;
    }

    public Fiche busulfan(String busulfan) {
        this.setBusulfan(busulfan);
        return this;
    }

    public void setBusulfan(String busulfan) {
        this.busulfan = busulfan;
    }

    public Float getDoseCyclo() {
        return this.doseCyclo;
    }

    public Fiche doseCyclo(Float doseCyclo) {
        this.setDoseCyclo(doseCyclo);
        return this;
    }

    public void setDoseCyclo(Float doseCyclo) {
        this.doseCyclo = doseCyclo;
    }

    public Float getDoseFlu() {
        return this.doseFlu;
    }

    public Fiche doseFlu(Float doseFlu) {
        this.setDoseFlu(doseFlu);
        return this;
    }

    public void setDoseFlu(Float doseFlu) {
        this.doseFlu = doseFlu;
    }

    public Float getDoseBus() {
        return this.doseBus;
    }

    public Fiche doseBus(Float doseBus) {
        this.setDoseBus(doseBus);
        return this;
    }

    public void setDoseBus(Float doseBus) {
        this.doseBus = doseBus;
    }

    public String getAutreConditionnement() {
        return this.autreConditionnement;
    }

    public Fiche autreConditionnement(String autreConditionnement) {
        this.setAutreConditionnement(autreConditionnement);
        return this;
    }

    public void setAutreConditionnement(String autreConditionnement) {
        this.autreConditionnement = autreConditionnement;
    }

    public String getIrradiation() {
        return this.irradiation;
    }

    public Fiche irradiation(String irradiation) {
        this.setIrradiation(irradiation);
        return this;
    }

    public void setIrradiation(String irradiation) {
        this.irradiation = irradiation;
    }

    public Float getDoseTotaleIrr() {
        return this.doseTotaleIrr;
    }

    public Fiche doseTotaleIrr(Float doseTotaleIrr) {
        this.setDoseTotaleIrr(doseTotaleIrr);
        return this;
    }

    public void setDoseTotaleIrr(Float doseTotaleIrr) {
        this.doseTotaleIrr = doseTotaleIrr;
    }

    public String getSerotherapie() {
        return this.serotherapie;
    }

    public Fiche serotherapie(String serotherapie) {
        this.setSerotherapie(serotherapie);
        return this;
    }

    public void setSerotherapie(String serotherapie) {
        this.serotherapie = serotherapie;
    }

    public String getsAL() {
        return this.sAL;
    }

    public Fiche sAL(String sAL) {
        this.setsAL(sAL);
        return this;
    }

    public void setsAL(String sAL) {
        this.sAL = sAL;
    }

    public Float getDoseSAL() {
        return this.doseSAL;
    }

    public Fiche doseSAL(Float doseSAL) {
        this.setDoseSAL(doseSAL);
        return this;
    }

    public void setDoseSAL(Float doseSAL) {
        this.doseSAL = doseSAL;
    }

    public String getSourceCellule() {
        return this.sourceCellule;
    }

    public Fiche sourceCellule(String sourceCellule) {
        this.setSourceCellule(sourceCellule);
        return this;
    }

    public void setSourceCellule(String sourceCellule) {
        this.sourceCellule = sourceCellule;
    }

    public Integer getSortieAplasie() {
        return this.sortieAplasie;
    }

    public Fiche sortieAplasie(Integer sortieAplasie) {
        this.setSortieAplasie(sortieAplasie);
        return this;
    }

    public void setSortieAplasie(Integer sortieAplasie) {
        this.sortieAplasie = sortieAplasie;
    }

    public String getGradeaGvH() {
        return this.gradeaGvH;
    }

    public Fiche gradeaGvH(String gradeaGvH) {
        this.setGradeaGvH(gradeaGvH);
        return this;
    }

    public void setGradeaGvH(String gradeaGvH) {
        this.gradeaGvH = gradeaGvH;
    }

    public String getcGvH() {
        return this.cGvH;
    }

    public Fiche cGvH(String cGvH) {
        this.setcGvH(cGvH);
        return this;
    }

    public void setcGvH(String cGvH) {
        this.cGvH = cGvH;
    }

    public String getmVO() {
        return this.mVO;
    }

    public Fiche mVO(String mVO) {
        this.setmVO(mVO);
        return this;
    }

    public void setmVO(String mVO) {
        this.mVO = mVO;
    }

    public String getComplicationPulmonaire() {
        return this.complicationPulmonaire;
    }

    public Fiche complicationPulmonaire(String complicationPulmonaire) {
        this.setComplicationPulmonaire(complicationPulmonaire);
        return this;
    }

    public void setComplicationPulmonaire(String complicationPulmonaire) {
        this.complicationPulmonaire = complicationPulmonaire;
    }

    public String getPreciseCompPulm() {
        return this.preciseCompPulm;
    }

    public Fiche preciseCompPulm(String preciseCompPulm) {
        this.setPreciseCompPulm(preciseCompPulm);
        return this;
    }

    public void setPreciseCompPulm(String preciseCompPulm) {
        this.preciseCompPulm = preciseCompPulm;
    }

    public String getSurvieJ100() {
        return this.survieJ100;
    }

    public Fiche survieJ100(String survieJ100) {
        this.setSurvieJ100(survieJ100);
        return this;
    }

    public void setSurvieJ100(String survieJ100) {
        this.survieJ100 = survieJ100;
    }

    public String getsMD() {
        return this.sMD;
    }

    public Fiche sMD(String sMD) {
        this.setsMD(sMD);
        return this;
    }

    public void setsMD(String sMD) {
        this.sMD = sMD;
    }

    public Integer getAgeDiagSMD() {
        return this.ageDiagSMD;
    }

    public Fiche ageDiagSMD(Integer ageDiagSMD) {
        this.setAgeDiagSMD(ageDiagSMD);
        return this;
    }

    public void setAgeDiagSMD(Integer ageDiagSMD) {
        this.ageDiagSMD = ageDiagSMD;
    }

    public String getCritereDiagSMD() {
        return this.critereDiagSMD;
    }

    public Fiche critereDiagSMD(String critereDiagSMD) {
        this.setCritereDiagSMD(critereDiagSMD);
        return this;
    }

    public void setCritereDiagSMD(String critereDiagSMD) {
        this.critereDiagSMD = critereDiagSMD;
    }

    public String getTraitementSMD() {
        return this.traitementSMD;
    }

    public Fiche traitementSMD(String traitementSMD) {
        this.setTraitementSMD(traitementSMD);
        return this;
    }

    public void setTraitementSMD(String traitementSMD) {
        this.traitementSMD = traitementSMD;
    }

    public String getlAM() {
        return this.lAM;
    }

    public Fiche lAM(String lAM) {
        this.setlAM(lAM);
        return this;
    }

    public void setlAM(String lAM) {
        this.lAM = lAM;
    }

    public String getCritereDiagLAM() {
        return this.critereDiagLAM;
    }

    public Fiche critereDiagLAM(String critereDiagLAM) {
        this.setCritereDiagLAM(critereDiagLAM);
        return this;
    }

    public void setCritereDiagLAM(String critereDiagLAM) {
        this.critereDiagLAM = critereDiagLAM;
    }

    public String getTraitementLAM() {
        return this.traitementLAM;
    }

    public Fiche traitementLAM(String traitementLAM) {
        this.setTraitementLAM(traitementLAM);
        return this;
    }

    public void setTraitementLAM(String traitementLAM) {
        this.traitementLAM = traitementLAM;
    }

    public String getAutresCancers() {
        return this.autresCancers;
    }

    public Fiche autresCancers(String autresCancers) {
        this.setAutresCancers(autresCancers);
        return this;
    }

    public void setAutresCancers(String autresCancers) {
        this.autresCancers = autresCancers;
    }

    public LocalDate getdDN() {
        return this.dDN;
    }

    public Fiche dDN(LocalDate dDN) {
        this.setdDN(dDN);
        return this;
    }

    public void setdDN(LocalDate dDN) {
        this.dDN = dDN;
    }

    public String getTransformationAigue() {
        return this.transformationAigue;
    }

    public Fiche transformationAigue(String transformationAigue) {
        this.setTransformationAigue(transformationAigue);
        return this;
    }

    public void setTransformationAigue(String transformationAigue) {
        this.transformationAigue = transformationAigue;
    }

    public Integer getAgeDiagLA() {
        return this.ageDiagLA;
    }

    public Fiche ageDiagLA(Integer ageDiagLA) {
        this.setAgeDiagLA(ageDiagLA);
        return this;
    }

    public void setAgeDiagLA(Integer ageDiagLA) {
        this.ageDiagLA = ageDiagLA;
    }

    public String getTraitementLA() {
        return this.traitementLA;
    }

    public Fiche traitementLA(String traitementLA) {
        this.setTraitementLA(traitementLA);
        return this;
    }

    public void setTraitementLA(String traitementLA) {
        this.traitementLA = traitementLA;
    }

    public String getCancerE() {
        return this.cancerE;
    }

    public Fiche cancerE(String cancerE) {
        this.setCancerE(cancerE);
        return this;
    }

    public void setCancerE(String cancerE) {
        this.cancerE = cancerE;
    }

    public String getLocalisation() {
        return this.localisation;
    }

    public Fiche localisation(String localisation) {
        this.setLocalisation(localisation);
        return this;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getTypeHistologique() {
        return this.typeHistologique;
    }

    public Fiche typeHistologique(String typeHistologique) {
        this.setTypeHistologique(typeHistologique);
        return this;
    }

    public void setTypeHistologique(String typeHistologique) {
        this.typeHistologique = typeHistologique;
    }

    public String getTraitementCancer() {
        return this.traitementCancer;
    }

    public Fiche traitementCancer(String traitementCancer) {
        this.setTraitementCancer(traitementCancer);
        return this;
    }

    public void setTraitementCancer(String traitementCancer) {
        this.traitementCancer = traitementCancer;
    }

    public String getPreciseTraitement() {
        return this.preciseTraitement;
    }

    public Fiche preciseTraitement(String preciseTraitement) {
        this.setPreciseTraitement(preciseTraitement);
        return this;
    }

    public void setPreciseTraitement(String preciseTraitement) {
        this.preciseTraitement = preciseTraitement;
    }

    public String getEvolutionCyto() {
        return this.evolutionCyto;
    }

    public Fiche evolutionCyto(String evolutionCyto) {
        this.setEvolutionCyto(evolutionCyto);
        return this;
    }

    public void setEvolutionCyto(String evolutionCyto) {
        this.evolutionCyto = evolutionCyto;
    }

    public String getFormuleChrom() {
        return this.formuleChrom;
    }

    public Fiche formuleChrom(String formuleChrom) {
        this.setFormuleChrom(formuleChrom);
        return this;
    }

    public void setFormuleChrom(String formuleChrom) {
        this.formuleChrom = formuleChrom;
    }

    public Integer getAgeE() {
        return this.ageE;
    }

    public Fiche ageE(Integer ageE) {
        this.setAgeE(ageE);
        return this;
    }

    public void setAgeE(Integer ageE) {
        this.ageE = ageE;
    }

    public String getStatut() {
        return this.statut;
    }

    public Fiche statut(String statut) {
        this.setStatut(statut);
        return this;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public String getCauseDeces() {
        return this.causeDeces;
    }

    public Fiche causeDeces(String causeDeces) {
        this.setCauseDeces(causeDeces);
        return this;
    }

    public void setCauseDeces(String causeDeces) {
        this.causeDeces = causeDeces;
    }

    public String getAutreCauseD() {
        return this.autreCauseD;
    }

    public Fiche autreCauseD(String autreCauseD) {
        this.setAutreCauseD(autreCauseD);
        return this;
    }

    public void setAutreCauseD(String autreCauseD) {
        this.autreCauseD = autreCauseD;
    }

    public Float getSurvieGlobale() {
        return this.survieGlobale;
    }

    public Fiche survieGlobale(Float survieGlobale) {
        this.setSurvieGlobale(survieGlobale);
        return this;
    }

    public void setSurvieGlobale(Float survieGlobale) {
        this.survieGlobale = survieGlobale;
    }

    public Integer getCode() {
        return this.code;
    }

    public Fiche code(Integer code) {
        this.setCode(code);
        return this;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public LocalDate getDateMAJ() {
        return this.dateMAJ;
    }

    public Fiche dateMAJ(LocalDate dateMAJ) {
        this.setDateMAJ(dateMAJ);
        return this;
    }

    public void setDateMAJ(LocalDate dateMAJ) {
        this.dateMAJ = dateMAJ;
    }

    public Integer getNombreTacheCafe() {
        return this.nombreTacheCafe;
    }

    public Fiche nombreTacheCafe(Integer nombreTacheCafe) {
        this.setNombreTacheCafe(nombreTacheCafe);
        return this;
    }

    public void setNombreTacheCafe(Integer nombreTacheCafe) {
        this.nombreTacheCafe = nombreTacheCafe;
    }

    public Integer getNombreTacheHypo() {
        return this.nombreTacheHypo;
    }

    public Fiche nombreTacheHypo(Integer nombreTacheHypo) {
        this.setNombreTacheHypo(nombreTacheHypo);
        return this;
    }

    public void setNombreTacheHypo(Integer nombreTacheHypo) {
        this.nombreTacheHypo = nombreTacheHypo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Fiche)) {
            return false;
        }
        return getId() != null && getId().equals(((Fiche) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Fiche{" +
            "id=" + getId() +
            ", nDossier='" + getnDossier() + "'" +
            ", dateDiagnostic='" + getDateDiagnostic() + "'" +
            ", dateEnregistrement='" + getDateEnregistrement() + "'" +
            ", medecin='" + getMedecin() + "'" +
            ", hopital=" + getHopital() +
            ", service=" + getService() +
            ", degConsang='" + getDegConsang() + "'" +
            ", placeEnfant=" + getPlaceEnfant() +
            ", nbVivant=" + getNbVivant() +
            ", nbMort=" + getNbMort() +
            ", mortNe=" + getMortNe() +
            ", avortement=" + getAvortement() +
            ", cousin=" + getCousin() +
            ", membre=" + getMembre() +
            ", arbregenealogique='" + getArbregenealogique() + "'" +
            ", arbregenealogiqueContentType='" + getArbregenealogiqueContentType() + "'" +
            ", arbregenealogiquecancer='" + getArbregenealogiquecancer() + "'" +
            ", arbregenealogiquecancerContentType='" + getArbregenealogiquecancerContentType() + "'" +
            ", syndromeAnemique='" + getSyndromeAnemique() + "'" +
            ", syndromeHem='" + getSyndromeHem() + "'" +
            ", syndromeInf='" + getSyndromeInf() + "'" +
            ", decouverteFort='" + getDecouverteFort() + "'" +
            ", enqueteFam='" + getEnqueteFam() + "'" +
            ", typeCancer='" + getTypeCancer() + "'" +
            ", cancer='" + getCancer() + "'" +
            ", tailleNaiss=" + getTailleNaiss() +
            ", poidsNaiss=" + getPoidsNaiss() +
            ", hypotrophie='" + getHypotrophie() + "'" +
            ", troubleCroi='" + getTroubleCroi() + "'" +
            ", aAgeChDiag=" + getaAgeChDiag() +
            ", mAgeChDiag=" + getmAgeChDiag() +
            ", aAgeOssDiag=" + getaAgeOssDiag() +
            ", mAgeOssDiag=" + getmAgeOssDiag() +
            ", ageRetard=" + getAgeRetard() +
            ", poids=" + getPoids() +
            ", poidsDS='" + getPoidsDS() + "'" +
            ", taille=" + getTaille() +
            ", tailleDS='" + getTailleDS() + "'" +
            ", atteinteCut='" + getAtteinteCut() + "'" +
            ", tacheCaf='" + getTacheCaf() + "'" +
            ", ventre='" + getVentre() + "'" +
            ", membreSup='" + getMembreSup() + "'" +
            ", membreInf='" + getMembreInf() + "'" +
            ", visage='" + getVisage() + "'" +
            ", thorax='" + getThorax() + "'" +
            ", dOS='" + getdOS() + "'" +
            ", hyperPig='" + getHyperPig() + "'" +
            ", hypochromique='" + getHypochromique() + "'" +
            ", couleurPeau='" + getCouleurPeau() + "'" +
            ", autreAtCut='" + getAutreAtCut() + "'" +
            ", atteinteTete='" + getAtteinteTete() + "'" +
            ", microcephalie='" + getMicrocephalie() + "'" +
            ", microphtalmie='" + getMicrophtalmie() + "'" +
            ", facieTrig='" + getFacieTrig() + "'" +
            ", traitsFin='" + getTraitsFin() + "'" +
            ", autreAtTete='" + getAutreAtTete() + "'" +
            ", empreintedigitiforme='" + getEmpreintedigitiforme() + "'" +
            ", malUro='" + getMalUro() + "'" +
            ", uIV='" + getuIV() + "'" +
            ", echo='" + getEcho() + "'" +
            ", reinEctop='" + getReinEctop() + "'" +
            ", siegeEctopie='" + getSiegeEctopie() + "'" +
            ", reinFerChev='" + getReinFerChev() + "'" +
            ", petitRein='" + getPetitRein() + "'" +
            ", reinUnique='" + getReinUnique() + "'" +
            ", ectopTest='" + getEctopTest() + "'" +
            ", vergeInsuf='" + getVergeInsuf() + "'" +
            ", autreAnomVerge='" + getAutreAnomVerge() + "'" +
            ", retardPubertaire='" + getRetardPubertaire() + "'" +
            ", mTanner='" + getmTanner() + "'" +
            ", pTanner='" + getpTanner() + "'" +
            ", tTanner='" + gettTanner() + "'" +
            ", anomUrin='" + getAnomUrin() + "'" +
            ", typeAnomUrin='" + getTypeAnomUrin() + "'" +
            ", atteinteOss='" + getAtteinteOss() + "'" +
            ", radiosfaites='" + getRadiosfaites() + "'" +
            ", anomPouce='" + getAnomPouce() + "'" +
            ", surnumerarie='" + getSurnumerarie() + "'" +
            ", agenesiePouce='" + getAgenesiePouce() + "'" +
            ", bifide='" + getBifide() + "'" +
            ", hypoPouce='" + getHypoPouce() + "'" +
            ", aspectPouce='" + getAspectPouce() + "'" +
            ", hypoEminence='" + getHypoEminence() + "'" +
            ", absenceRadial='" + getAbsenceRadial() + "'" +
            ", pouceBas='" + getPouceBas() + "'" +
            ", autreAnomPouce='" + getAutreAnomPouce() + "'" +
            ", anomAutDoigts='" + getAnomAutDoigts() + "'" +
            ", courtstrapus='" + getCourtstrapus() + "'" +
            ", syndactylie='" + getSyndactylie() + "'" +
            ", autreAnomDoigts='" + getAutreAnomDoigts() + "'" +
            ", anomalieos='" + getAnomalieos() + "'" +
            ", agenesieRadius='" + getAgenesieRadius() + "'" +
            ", autreanomalieMembresup='" + getAutreanomalieMembresup() + "'" +
            ", anomOrteil='" + getAnomOrteil() + "'" +
            ", preciseAnomOrt='" + getPreciseAnomOrt() + "'" +
            ", lCH='" + getlCH() + "'" +
            ", autreanomalieMembreinf='" + getAutreanomalieMembreinf() + "'" +
            ", anomRachis='" + getAnomRachis() + "'" +
            ", preciseAnomRac='" + getPreciseAnomRac() + "'" +
            ", autreAnomOss='" + getAutreAnomOss() + "'" +
            ", anomNeuro='" + getAnomNeuro() + "'" +
            ", retardMent='" + getRetardMent() + "'" +
            ", hypoacousie='" + getHypoacousie() + "'" +
            ", strabisme='" + getStrabisme() + "'" +
            ", performanceScolaire='" + getPerformanceScolaire() + "'" +
            ", autreanomalieneurologique='" + getAutreanomalieneurologique() + "'" +
            ", anomCardVas='" + getAnomCardVas() + "'" +
            ", echoCoeur='" + getEchoCoeur() + "'" +
            ", preciseAnomCardio='" + getPreciseAnomCardio() + "'" +
            ", anomDig='" + getAnomDig() + "'" +
            ", preciseAnomDig='" + getPreciseAnomDig() + "'" +
            ", endocrinopathie='" + getEndocrinopathie() + "'" +
            ", diabete='" + getDiabete() + "'" +
            ", insulinoDep='" + getInsulinoDep() + "'" +
            ", hypothyroidie='" + getHypothyroidie() + "'" +
            ", ageDecouverte=" + getAgeDecouverte() +
            ", autreEndocrinopathie='" + getAutreEndocrinopathie() + "'" +
            ", autreAnomCong='" + getAutreAnomCong() + "'" +
            ", dateNumSanguine='" + getDateNumSanguine() + "'" +
            ", age=" + getAge() +
            ", hb=" + getHb() +
            ", vGM=" + getvGM() +
            ", retic=" + getRetic() +
            ", leuco=" + getLeuco() +
            ", pNN=" + getpNN() +
            ", plq=" + getPlq() +
            ", hbInf='" + getHbInf() + "'" +
            ", plqInf='" + getPlqInf() + "'" +
            ", pNNInf='" + getpNNInf() + "'" +
            ", electrophoreseHb='" + getElectrophoreseHb() + "'" +
            ", hbf=" + getHbf() +
            ", myelogramme='" + getMyelogramme() + "'" +
            ", cellularite='" + getCellularite() + "'" +
            ", erythroblaste=" + getErythroblaste() +
            ", morphologieEryth='" + getMorphologieEryth() + "'" +
            ", morphologieGran='" + getMorphologieGran() + "'" +
            ", morphologieMega='" + getMorphologieMega() + "'" +
            ", granuleux=" + getGranuleux() +
            ", dysmyelopoiese='" + getDysmyelopoiese() + "'" +
            ", megacaryocytes='" + getMegacaryocytes() + "'" +
            ", blaste=" + getBlaste() +
            ", excesblastes='" + getExcesblastes() + "'" +
            ", bOM='" + getbOM() + "'" +
            ", adipocytes=" + getAdipocytes() +
            ", ubiquitination='" + getUbiquitination() + "'" +
            ", resUbiquitination='" + getResUbiquitination() + "'" +
            ", groupComp='" + getGroupComp() + "'" +
            ", mutationFANC='" + getMutationFANC() + "'" +
            ", congelationCellule='" + getCongelationCellule() + "'" +
            ", labo='" + getLabo() + "'" +
            ", typePrelevement='" + getTypePrelevement() + "'" +
            ", scoreClinique=" + getScoreClinique() +
            ", scoreEBMT=" + getScoreEBMT() +
            ", score='" + getScore() + "'" +
            ", transfusion='" + getTransfusion() + "'" +
            ", ageTransf=" + getAgeTransf() +
            ", delaiDiag=" + getDelaiDiag() +
            ", nbCG='" + getNbCG() + "'" +
            ", nbCP='" + getNbCP() + "'" +
            ", chelationFer='" + getChelationFer() + "'" +
            ", chelateur='" + getChelateur() + "'" +
            ", nilevar='" + getNilevar() + "'" +
            ", danatrol='" + getDanatrol() + "'" +
            ", oxynethadone='" + getOxynethadone() + "'" +
            ", androtordyl='" + getAndrotordyl() + "'" +
            ", autreAndrogene='" + getAutreAndrogene() + "'" +
            ", androDebut='" + getAndroDebut() + "'" +
            ", androFin='" + getAndroFin() + "'" +
            ", observance='" + getObservance() + "'" +
            ", toxicite='" + getToxicite() + "'" +
            ", autreToxicite='" + getAutreToxicite() + "'" +
            ", enqueteHLA='" + getEnqueteHLA() + "'" +
            ", pourquoiEnqHLA='" + getPourquoiEnqHLA() + "'" +
            ", nbFratTyp=" + getNbFratTyp() +
            ", nbFratNTyp=" + getNbFratNTyp() +
            ", donneurComp=" + getDonneurComp() +
            ", preciseDonneur='" + getPreciseDonneur() + "'" +
            ", donneur='" + getDonneur() + "'" +
            ", greffeFaite='" + getGreffeFaite() + "'" +
            ", delaiRappDiag=" + getDelaiRappDiag() +
            ", pourquoiNfaite='" + getPourquoiNfaite() + "'" +
            ", cyclophosphamide='" + getCyclophosphamide() + "'" +
            ", fludarabine='" + getFludarabine() + "'" +
            ", busulfan='" + getBusulfan() + "'" +
            ", doseCyclo=" + getDoseCyclo() +
            ", doseFlu=" + getDoseFlu() +
            ", doseBus=" + getDoseBus() +
            ", autreConditionnement='" + getAutreConditionnement() + "'" +
            ", irradiation='" + getIrradiation() + "'" +
            ", doseTotaleIrr=" + getDoseTotaleIrr() +
            ", serotherapie='" + getSerotherapie() + "'" +
            ", sAL='" + getsAL() + "'" +
            ", doseSAL=" + getDoseSAL() +
            ", sourceCellule='" + getSourceCellule() + "'" +
            ", sortieAplasie=" + getSortieAplasie() +
            ", gradeaGvH='" + getGradeaGvH() + "'" +
            ", cGvH='" + getcGvH() + "'" +
            ", mVO='" + getmVO() + "'" +
            ", complicationPulmonaire='" + getComplicationPulmonaire() + "'" +
            ", preciseCompPulm='" + getPreciseCompPulm() + "'" +
            ", survieJ100='" + getSurvieJ100() + "'" +
            ", sMD='" + getsMD() + "'" +
            ", ageDiagSMD=" + getAgeDiagSMD() +
            ", critereDiagSMD='" + getCritereDiagSMD() + "'" +
            ", traitementSMD='" + getTraitementSMD() + "'" +
            ", lAM='" + getlAM() + "'" +
            ", critereDiagLAM='" + getCritereDiagLAM() + "'" +
            ", traitementLAM='" + getTraitementLAM() + "'" +
            ", autresCancers='" + getAutresCancers() + "'" +
            ", dDN='" + getdDN() + "'" +
            ", transformationAigue='" + getTransformationAigue() + "'" +
            ", ageDiagLA=" + getAgeDiagLA() +
            ", traitementLA='" + getTraitementLA() + "'" +
            ", cancerE='" + getCancerE() + "'" +
            ", localisation='" + getLocalisation() + "'" +
            ", typeHistologique='" + getTypeHistologique() + "'" +
            ", traitementCancer='" + getTraitementCancer() + "'" +
            ", preciseTraitement='" + getPreciseTraitement() + "'" +
            ", evolutionCyto='" + getEvolutionCyto() + "'" +
            ", formuleChrom='" + getFormuleChrom() + "'" +
            ", ageE=" + getAgeE() +
            ", statut='" + getStatut() + "'" +
            ", causeDeces='" + getCauseDeces() + "'" +
            ", autreCauseD='" + getAutreCauseD() + "'" +
            ", survieGlobale=" + getSurvieGlobale() +
            ", code=" + getCode() +
            ", dateMAJ='" + getDateMAJ() + "'" +
            ", nombreTacheCafe=" + getNombreTacheCafe() +
            ", nombreTacheHypo=" + getNombreTacheHypo() +
            "}";
    }
}
