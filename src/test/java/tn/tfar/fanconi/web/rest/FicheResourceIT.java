package tn.tfar.fanconi.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static tn.tfar.fanconi.domain.FicheAsserts.*;
import static tn.tfar.fanconi.web.rest.TestUtil.createUpdateProxyForBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tn.tfar.fanconi.IntegrationTest;
import tn.tfar.fanconi.domain.Fiche;
import tn.tfar.fanconi.repository.FicheRepository;

/**
 * Integration tests for the {@link FicheResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class FicheResourceIT {

    private static final String DEFAULT_N_DOSSIER = "AAAAAAAAAA";
    private static final String UPDATED_N_DOSSIER = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_DIAGNOSTIC = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DIAGNOSTIC = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_ENREGISTREMENT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ENREGISTREMENT = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_MEDECIN = "AAAAAAAAAA";
    private static final String UPDATED_MEDECIN = "BBBBBBBBBB";

    private static final Integer DEFAULT_HOPITAL = 1;
    private static final Integer UPDATED_HOPITAL = 2;

    private static final Integer DEFAULT_SERVICE = 1;
    private static final Integer UPDATED_SERVICE = 2;

    private static final String DEFAULT_DEG_CONSANG = "AAAAAAAAAA";
    private static final String UPDATED_DEG_CONSANG = "BBBBBBBBBB";

    private static final Integer DEFAULT_PLACE_ENFANT = 1;
    private static final Integer UPDATED_PLACE_ENFANT = 2;

    private static final Integer DEFAULT_NB_VIVANT = 1;
    private static final Integer UPDATED_NB_VIVANT = 2;

    private static final Integer DEFAULT_NB_MORT = 1;
    private static final Integer UPDATED_NB_MORT = 2;

    private static final Integer DEFAULT_MORT_NE = 1;
    private static final Integer UPDATED_MORT_NE = 2;

    private static final Integer DEFAULT_AVORTEMENT = 1;
    private static final Integer UPDATED_AVORTEMENT = 2;

    private static final Integer DEFAULT_COUSIN = 1;
    private static final Integer UPDATED_COUSIN = 2;

    private static final Integer DEFAULT_MEMBRE = 1;
    private static final Integer UPDATED_MEMBRE = 2;

    private static final byte[] DEFAULT_ARBREGENEALOGIQUE = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_ARBREGENEALOGIQUE = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_ARBREGENEALOGIQUE_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_ARBREGENEALOGIQUE_CONTENT_TYPE = "image/png";

    private static final byte[] DEFAULT_ARBREGENEALOGIQUECANCER = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_ARBREGENEALOGIQUECANCER = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_ARBREGENEALOGIQUECANCER_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_ARBREGENEALOGIQUECANCER_CONTENT_TYPE = "image/png";

    private static final String DEFAULT_SYNDROME_ANEMIQUE = "AAAAAAAAAA";
    private static final String UPDATED_SYNDROME_ANEMIQUE = "BBBBBBBBBB";

    private static final String DEFAULT_SYNDROME_HEM = "AAAAAAAAAA";
    private static final String UPDATED_SYNDROME_HEM = "BBBBBBBBBB";

    private static final String DEFAULT_SYNDROME_INF = "AAAAAAAAAA";
    private static final String UPDATED_SYNDROME_INF = "BBBBBBBBBB";

    private static final String DEFAULT_DECOUVERTE_FORT = "AAAAAAAAAA";
    private static final String UPDATED_DECOUVERTE_FORT = "BBBBBBBBBB";

    private static final String DEFAULT_ENQUETE_FAM = "AAAAAAAAAA";
    private static final String UPDATED_ENQUETE_FAM = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_CANCER = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_CANCER = "BBBBBBBBBB";

    private static final String DEFAULT_CANCER = "AAAAAAAAAA";
    private static final String UPDATED_CANCER = "BBBBBBBBBB";

    private static final Float DEFAULT_TAILLE_NAISS = 1F;
    private static final Float UPDATED_TAILLE_NAISS = 2F;

    private static final Float DEFAULT_POIDS_NAISS = 1F;
    private static final Float UPDATED_POIDS_NAISS = 2F;

    private static final String DEFAULT_HYPOTROPHIE = "AAAAAAAAAA";
    private static final String UPDATED_HYPOTROPHIE = "BBBBBBBBBB";

    private static final String DEFAULT_TROUBLE_CROI = "AAAAAAAAAA";
    private static final String UPDATED_TROUBLE_CROI = "BBBBBBBBBB";

    private static final Integer DEFAULT_A_AGE_CH_DIAG = 1;
    private static final Integer UPDATED_A_AGE_CH_DIAG = 2;

    private static final Integer DEFAULT_M_AGE_CH_DIAG = 1;
    private static final Integer UPDATED_M_AGE_CH_DIAG = 2;

    private static final Integer DEFAULT_A_AGE_OSS_DIAG = 1;
    private static final Integer UPDATED_A_AGE_OSS_DIAG = 2;

    private static final Integer DEFAULT_M_AGE_OSS_DIAG = 1;
    private static final Integer UPDATED_M_AGE_OSS_DIAG = 2;

    private static final Integer DEFAULT_AGE_RETARD = 1;
    private static final Integer UPDATED_AGE_RETARD = 2;

    private static final Float DEFAULT_POIDS = 1F;
    private static final Float UPDATED_POIDS = 2F;

    private static final String DEFAULT_POIDS_DS = "AAAAAAAAAA";
    private static final String UPDATED_POIDS_DS = "BBBBBBBBBB";

    private static final Float DEFAULT_TAILLE = 1F;
    private static final Float UPDATED_TAILLE = 2F;

    private static final String DEFAULT_TAILLE_DS = "AAAAAAAAAA";
    private static final String UPDATED_TAILLE_DS = "BBBBBBBBBB";

    private static final String DEFAULT_ATTEINTE_CUT = "AAAAAAAAAA";
    private static final String UPDATED_ATTEINTE_CUT = "BBBBBBBBBB";

    private static final String DEFAULT_TACHE_CAF = "AAAAAAAAAA";
    private static final String UPDATED_TACHE_CAF = "BBBBBBBBBB";

    private static final String DEFAULT_VENTRE = "AAAAAAAAAA";
    private static final String UPDATED_VENTRE = "BBBBBBBBBB";

    private static final String DEFAULT_MEMBRE_SUP = "AAAAAAAAAA";
    private static final String UPDATED_MEMBRE_SUP = "BBBBBBBBBB";

    private static final String DEFAULT_MEMBRE_INF = "AAAAAAAAAA";
    private static final String UPDATED_MEMBRE_INF = "BBBBBBBBBB";

    private static final String DEFAULT_VISAGE = "AAAAAAAAAA";
    private static final String UPDATED_VISAGE = "BBBBBBBBBB";

    private static final String DEFAULT_THORAX = "AAAAAAAAAA";
    private static final String UPDATED_THORAX = "BBBBBBBBBB";

    private static final String DEFAULT_D_OS = "AAAAAAAAAA";
    private static final String UPDATED_D_OS = "BBBBBBBBBB";

    private static final String DEFAULT_HYPER_PIG = "AAAAAAAAAA";
    private static final String UPDATED_HYPER_PIG = "BBBBBBBBBB";

    private static final String DEFAULT_HYPOCHROMIQUE = "AAAAAAAAAA";
    private static final String UPDATED_HYPOCHROMIQUE = "BBBBBBBBBB";

    private static final String DEFAULT_COULEUR_PEAU = "AAAAAAAAAA";
    private static final String UPDATED_COULEUR_PEAU = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_AT_CUT = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_AT_CUT = "BBBBBBBBBB";

    private static final String DEFAULT_ATTEINTE_TETE = "AAAAAAAAAA";
    private static final String UPDATED_ATTEINTE_TETE = "BBBBBBBBBB";

    private static final String DEFAULT_MICROCEPHALIE = "AAAAAAAAAA";
    private static final String UPDATED_MICROCEPHALIE = "BBBBBBBBBB";

    private static final String DEFAULT_MICROPHTALMIE = "AAAAAAAAAA";
    private static final String UPDATED_MICROPHTALMIE = "BBBBBBBBBB";

    private static final String DEFAULT_FACIE_TRIG = "AAAAAAAAAA";
    private static final String UPDATED_FACIE_TRIG = "BBBBBBBBBB";

    private static final String DEFAULT_TRAITS_FIN = "AAAAAAAAAA";
    private static final String UPDATED_TRAITS_FIN = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_AT_TETE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_AT_TETE = "BBBBBBBBBB";

    private static final String DEFAULT_EMPREINTEDIGITIFORME = "AAAAAAAAAA";
    private static final String UPDATED_EMPREINTEDIGITIFORME = "BBBBBBBBBB";

    private static final String DEFAULT_MAL_URO = "AAAAAAAAAA";
    private static final String UPDATED_MAL_URO = "BBBBBBBBBB";

    private static final String DEFAULT_U_IV = "AAAAAAAAAA";
    private static final String UPDATED_U_IV = "BBBBBBBBBB";

    private static final String DEFAULT_ECHO = "AAAAAAAAAA";
    private static final String UPDATED_ECHO = "BBBBBBBBBB";

    private static final String DEFAULT_REIN_ECTOP = "AAAAAAAAAA";
    private static final String UPDATED_REIN_ECTOP = "BBBBBBBBBB";

    private static final String DEFAULT_SIEGE_ECTOPIE = "AAAAAAAAAA";
    private static final String UPDATED_SIEGE_ECTOPIE = "BBBBBBBBBB";

    private static final String DEFAULT_REIN_FER_CHEV = "AAAAAAAAAA";
    private static final String UPDATED_REIN_FER_CHEV = "BBBBBBBBBB";

    private static final String DEFAULT_PETIT_REIN = "AAAAAAAAAA";
    private static final String UPDATED_PETIT_REIN = "BBBBBBBBBB";

    private static final String DEFAULT_REIN_UNIQUE = "AAAAAAAAAA";
    private static final String UPDATED_REIN_UNIQUE = "BBBBBBBBBB";

    private static final String DEFAULT_ECTOP_TEST = "AAAAAAAAAA";
    private static final String UPDATED_ECTOP_TEST = "BBBBBBBBBB";

    private static final String DEFAULT_VERGE_INSUF = "AAAAAAAAAA";
    private static final String UPDATED_VERGE_INSUF = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_ANOM_VERGE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_ANOM_VERGE = "BBBBBBBBBB";

    private static final String DEFAULT_RETARD_PUBERTAIRE = "AAAAAAAAAA";
    private static final String UPDATED_RETARD_PUBERTAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_M_TANNER = "AAAAAAAAAA";
    private static final String UPDATED_M_TANNER = "BBBBBBBBBB";

    private static final String DEFAULT_P_TANNER = "AAAAAAAAAA";
    private static final String UPDATED_P_TANNER = "BBBBBBBBBB";

    private static final String DEFAULT_T_TANNER = "AAAAAAAAAA";
    private static final String UPDATED_T_TANNER = "BBBBBBBBBB";

    private static final String DEFAULT_ANOM_URIN = "AAAAAAAAAA";
    private static final String UPDATED_ANOM_URIN = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_ANOM_URIN = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_ANOM_URIN = "BBBBBBBBBB";

    private static final String DEFAULT_ATTEINTE_OSS = "AAAAAAAAAA";
    private static final String UPDATED_ATTEINTE_OSS = "BBBBBBBBBB";

    private static final String DEFAULT_RADIOSFAITES = "AAAAAAAAAA";
    private static final String UPDATED_RADIOSFAITES = "BBBBBBBBBB";

    private static final String DEFAULT_ANOM_POUCE = "AAAAAAAAAA";
    private static final String UPDATED_ANOM_POUCE = "BBBBBBBBBB";

    private static final String DEFAULT_SURNUMERARIE = "AAAAAAAAAA";
    private static final String UPDATED_SURNUMERARIE = "BBBBBBBBBB";

    private static final String DEFAULT_AGENESIE_POUCE = "AAAAAAAAAA";
    private static final String UPDATED_AGENESIE_POUCE = "BBBBBBBBBB";

    private static final String DEFAULT_BIFIDE = "AAAAAAAAAA";
    private static final String UPDATED_BIFIDE = "BBBBBBBBBB";

    private static final String DEFAULT_HYPO_POUCE = "AAAAAAAAAA";
    private static final String UPDATED_HYPO_POUCE = "BBBBBBBBBB";

    private static final String DEFAULT_ASPECT_POUCE = "AAAAAAAAAA";
    private static final String UPDATED_ASPECT_POUCE = "BBBBBBBBBB";

    private static final String DEFAULT_HYPO_EMINENCE = "AAAAAAAAAA";
    private static final String UPDATED_HYPO_EMINENCE = "BBBBBBBBBB";

    private static final String DEFAULT_ABSENCE_RADIAL = "AAAAAAAAAA";
    private static final String UPDATED_ABSENCE_RADIAL = "BBBBBBBBBB";

    private static final String DEFAULT_POUCE_BAS = "AAAAAAAAAA";
    private static final String UPDATED_POUCE_BAS = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_ANOM_POUCE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_ANOM_POUCE = "BBBBBBBBBB";

    private static final String DEFAULT_ANOM_AUT_DOIGTS = "AAAAAAAAAA";
    private static final String UPDATED_ANOM_AUT_DOIGTS = "BBBBBBBBBB";

    private static final String DEFAULT_COURTSTRAPUS = "AAAAAAAAAA";
    private static final String UPDATED_COURTSTRAPUS = "BBBBBBBBBB";

    private static final String DEFAULT_SYNDACTYLIE = "AAAAAAAAAA";
    private static final String UPDATED_SYNDACTYLIE = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_ANOM_DOIGTS = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_ANOM_DOIGTS = "BBBBBBBBBB";

    private static final String DEFAULT_ANOMALIEOS = "AAAAAAAAAA";
    private static final String UPDATED_ANOMALIEOS = "BBBBBBBBBB";

    private static final String DEFAULT_AGENESIE_RADIUS = "AAAAAAAAAA";
    private static final String UPDATED_AGENESIE_RADIUS = "BBBBBBBBBB";

    private static final String DEFAULT_AUTREANOMALIE_MEMBRESUP = "AAAAAAAAAA";
    private static final String UPDATED_AUTREANOMALIE_MEMBRESUP = "BBBBBBBBBB";

    private static final String DEFAULT_ANOM_ORTEIL = "AAAAAAAAAA";
    private static final String UPDATED_ANOM_ORTEIL = "BBBBBBBBBB";

    private static final String DEFAULT_PRECISE_ANOM_ORT = "AAAAAAAAAA";
    private static final String UPDATED_PRECISE_ANOM_ORT = "BBBBBBBBBB";

    private static final String DEFAULT_L_CH = "AAAAAAAAAA";
    private static final String UPDATED_L_CH = "BBBBBBBBBB";

    private static final String DEFAULT_AUTREANOMALIE_MEMBREINF = "AAAAAAAAAA";
    private static final String UPDATED_AUTREANOMALIE_MEMBREINF = "BBBBBBBBBB";

    private static final String DEFAULT_ANOM_RACHIS = "AAAAAAAAAA";
    private static final String UPDATED_ANOM_RACHIS = "BBBBBBBBBB";

    private static final String DEFAULT_PRECISE_ANOM_RAC = "AAAAAAAAAA";
    private static final String UPDATED_PRECISE_ANOM_RAC = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_ANOM_OSS = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_ANOM_OSS = "BBBBBBBBBB";

    private static final String DEFAULT_ANOM_NEURO = "AAAAAAAAAA";
    private static final String UPDATED_ANOM_NEURO = "BBBBBBBBBB";

    private static final String DEFAULT_RETARD_MENT = "AAAAAAAAAA";
    private static final String UPDATED_RETARD_MENT = "BBBBBBBBBB";

    private static final String DEFAULT_HYPOACOUSIE = "AAAAAAAAAA";
    private static final String UPDATED_HYPOACOUSIE = "BBBBBBBBBB";

    private static final String DEFAULT_STRABISME = "AAAAAAAAAA";
    private static final String UPDATED_STRABISME = "BBBBBBBBBB";

    private static final String DEFAULT_PERFORMANCE_SCOLAIRE = "AAAAAAAAAA";
    private static final String UPDATED_PERFORMANCE_SCOLAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_AUTREANOMALIENEUROLOGIQUE = "AAAAAAAAAA";
    private static final String UPDATED_AUTREANOMALIENEUROLOGIQUE = "BBBBBBBBBB";

    private static final String DEFAULT_ANOM_CARD_VAS = "AAAAAAAAAA";
    private static final String UPDATED_ANOM_CARD_VAS = "BBBBBBBBBB";

    private static final String DEFAULT_ECHO_COEUR = "AAAAAAAAAA";
    private static final String UPDATED_ECHO_COEUR = "BBBBBBBBBB";

    private static final String DEFAULT_PRECISE_ANOM_CARDIO = "AAAAAAAAAA";
    private static final String UPDATED_PRECISE_ANOM_CARDIO = "BBBBBBBBBB";

    private static final String DEFAULT_ANOM_DIG = "AAAAAAAAAA";
    private static final String UPDATED_ANOM_DIG = "BBBBBBBBBB";

    private static final String DEFAULT_PRECISE_ANOM_DIG = "AAAAAAAAAA";
    private static final String UPDATED_PRECISE_ANOM_DIG = "BBBBBBBBBB";

    private static final String DEFAULT_ENDOCRINOPATHIE = "AAAAAAAAAA";
    private static final String UPDATED_ENDOCRINOPATHIE = "BBBBBBBBBB";

    private static final String DEFAULT_DIABETE = "AAAAAAAAAA";
    private static final String UPDATED_DIABETE = "BBBBBBBBBB";

    private static final String DEFAULT_INSULINO_DEP = "AAAAAAAAAA";
    private static final String UPDATED_INSULINO_DEP = "BBBBBBBBBB";

    private static final String DEFAULT_HYPOTHYROIDIE = "AAAAAAAAAA";
    private static final String UPDATED_HYPOTHYROIDIE = "BBBBBBBBBB";

    private static final Integer DEFAULT_AGE_DECOUVERTE = 1;
    private static final Integer UPDATED_AGE_DECOUVERTE = 2;

    private static final String DEFAULT_AUTRE_ENDOCRINOPATHIE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_ENDOCRINOPATHIE = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_ANOM_CONG = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_ANOM_CONG = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_NUM_SANGUINE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_NUM_SANGUINE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_AGE = 1;
    private static final Integer UPDATED_AGE = 2;

    private static final Float DEFAULT_HB = 1F;
    private static final Float UPDATED_HB = 2F;

    private static final Float DEFAULT_V_GM = 1F;
    private static final Float UPDATED_V_GM = 2F;

    private static final Float DEFAULT_RETIC = 1F;
    private static final Float UPDATED_RETIC = 2F;

    private static final Float DEFAULT_LEUCO = 1F;
    private static final Float UPDATED_LEUCO = 2F;

    private static final Float DEFAULT_P_NN = 1F;
    private static final Float UPDATED_P_NN = 2F;

    private static final Float DEFAULT_PLQ = 1F;
    private static final Float UPDATED_PLQ = 2F;

    private static final String DEFAULT_HB_INF = "AAAAAAAAAA";
    private static final String UPDATED_HB_INF = "BBBBBBBBBB";

    private static final String DEFAULT_PLQ_INF = "AAAAAAAAAA";
    private static final String UPDATED_PLQ_INF = "BBBBBBBBBB";

    private static final String DEFAULT_P_NN_INF = "AAAAAAAAAA";
    private static final String UPDATED_P_NN_INF = "BBBBBBBBBB";

    private static final String DEFAULT_ELECTROPHORESE_HB = "AAAAAAAAAA";
    private static final String UPDATED_ELECTROPHORESE_HB = "BBBBBBBBBB";

    private static final Float DEFAULT_HBF = 1F;
    private static final Float UPDATED_HBF = 2F;

    private static final String DEFAULT_MYELOGRAMME = "AAAAAAAAAA";
    private static final String UPDATED_MYELOGRAMME = "BBBBBBBBBB";

    private static final String DEFAULT_CELLULARITE = "AAAAAAAAAA";
    private static final String UPDATED_CELLULARITE = "BBBBBBBBBB";

    private static final Float DEFAULT_ERYTHROBLASTE = 1F;
    private static final Float UPDATED_ERYTHROBLASTE = 2F;

    private static final String DEFAULT_MORPHOLOGIE_ERYTH = "AAAAAAAAAA";
    private static final String UPDATED_MORPHOLOGIE_ERYTH = "BBBBBBBBBB";

    private static final String DEFAULT_MORPHOLOGIE_GRAN = "AAAAAAAAAA";
    private static final String UPDATED_MORPHOLOGIE_GRAN = "BBBBBBBBBB";

    private static final String DEFAULT_MORPHOLOGIE_MEGA = "AAAAAAAAAA";
    private static final String UPDATED_MORPHOLOGIE_MEGA = "BBBBBBBBBB";

    private static final Float DEFAULT_GRANULEUX = 1F;
    private static final Float UPDATED_GRANULEUX = 2F;

    private static final String DEFAULT_DYSMYELOPOIESE = "AAAAAAAAAA";
    private static final String UPDATED_DYSMYELOPOIESE = "BBBBBBBBBB";

    private static final String DEFAULT_MEGACARYOCYTES = "AAAAAAAAAA";
    private static final String UPDATED_MEGACARYOCYTES = "BBBBBBBBBB";

    private static final Float DEFAULT_BLASTE = 1F;
    private static final Float UPDATED_BLASTE = 2F;

    private static final String DEFAULT_EXCESBLASTES = "AAAAAAAAAA";
    private static final String UPDATED_EXCESBLASTES = "BBBBBBBBBB";

    private static final String DEFAULT_B_OM = "AAAAAAAAAA";
    private static final String UPDATED_B_OM = "BBBBBBBBBB";

    private static final Float DEFAULT_ADIPOCYTES = 1F;
    private static final Float UPDATED_ADIPOCYTES = 2F;

    private static final String DEFAULT_UBIQUITINATION = "AAAAAAAAAA";
    private static final String UPDATED_UBIQUITINATION = "BBBBBBBBBB";

    private static final String DEFAULT_RES_UBIQUITINATION = "AAAAAAAAAA";
    private static final String UPDATED_RES_UBIQUITINATION = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP_COMP = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_COMP = "BBBBBBBBBB";

    private static final String DEFAULT_MUTATION_FANC = "AAAAAAAAAA";
    private static final String UPDATED_MUTATION_FANC = "BBBBBBBBBB";

    private static final String DEFAULT_CONGELATION_CELLULE = "AAAAAAAAAA";
    private static final String UPDATED_CONGELATION_CELLULE = "BBBBBBBBBB";

    private static final String DEFAULT_LABO = "AAAAAAAAAA";
    private static final String UPDATED_LABO = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_PRELEVEMENT = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_PRELEVEMENT = "BBBBBBBBBB";

    private static final Integer DEFAULT_SCORE_CLINIQUE = 1;
    private static final Integer UPDATED_SCORE_CLINIQUE = 2;

    private static final Integer DEFAULT_SCORE_EBMT = 1;
    private static final Integer UPDATED_SCORE_EBMT = 2;

    private static final String DEFAULT_SCORE = "AAAAAAAAAA";
    private static final String UPDATED_SCORE = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSFUSION = "AAAAAAAAAA";
    private static final String UPDATED_TRANSFUSION = "BBBBBBBBBB";

    private static final Integer DEFAULT_AGE_TRANSF = 1;
    private static final Integer UPDATED_AGE_TRANSF = 2;

    private static final Float DEFAULT_DELAI_DIAG = 1F;
    private static final Float UPDATED_DELAI_DIAG = 2F;

    private static final String DEFAULT_NB_CG = "AAAAAAAAAA";
    private static final String UPDATED_NB_CG = "BBBBBBBBBB";

    private static final String DEFAULT_NB_CP = "AAAAAAAAAA";
    private static final String UPDATED_NB_CP = "BBBBBBBBBB";

    private static final String DEFAULT_CHELATION_FER = "AAAAAAAAAA";
    private static final String UPDATED_CHELATION_FER = "BBBBBBBBBB";

    private static final String DEFAULT_CHELATEUR = "AAAAAAAAAA";
    private static final String UPDATED_CHELATEUR = "BBBBBBBBBB";

    private static final String DEFAULT_NILEVAR = "AAAAAAAAAA";
    private static final String UPDATED_NILEVAR = "BBBBBBBBBB";

    private static final String DEFAULT_DANATROL = "AAAAAAAAAA";
    private static final String UPDATED_DANATROL = "BBBBBBBBBB";

    private static final String DEFAULT_OXYNETHADONE = "AAAAAAAAAA";
    private static final String UPDATED_OXYNETHADONE = "BBBBBBBBBB";

    private static final String DEFAULT_ANDROTORDYL = "AAAAAAAAAA";
    private static final String UPDATED_ANDROTORDYL = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_ANDROGENE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_ANDROGENE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_ANDRO_DEBUT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ANDRO_DEBUT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ANDRO_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ANDRO_FIN = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_OBSERVANCE = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVANCE = "BBBBBBBBBB";

    private static final String DEFAULT_TOXICITE = "AAAAAAAAAA";
    private static final String UPDATED_TOXICITE = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_TOXICITE = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_TOXICITE = "BBBBBBBBBB";

    private static final String DEFAULT_ENQUETE_HLA = "AAAAAAAAAA";
    private static final String UPDATED_ENQUETE_HLA = "BBBBBBBBBB";

    private static final String DEFAULT_POURQUOI_ENQ_HLA = "AAAAAAAAAA";
    private static final String UPDATED_POURQUOI_ENQ_HLA = "BBBBBBBBBB";

    private static final Integer DEFAULT_NB_FRAT_TYP = 1;
    private static final Integer UPDATED_NB_FRAT_TYP = 2;

    private static final Integer DEFAULT_NB_FRAT_N_TYP = 1;
    private static final Integer UPDATED_NB_FRAT_N_TYP = 2;

    private static final Integer DEFAULT_DONNEUR_COMP = 1;
    private static final Integer UPDATED_DONNEUR_COMP = 2;

    private static final String DEFAULT_PRECISE_DONNEUR = "AAAAAAAAAA";
    private static final String UPDATED_PRECISE_DONNEUR = "BBBBBBBBBB";

    private static final String DEFAULT_DONNEUR = "AAAAAAAAAA";
    private static final String UPDATED_DONNEUR = "BBBBBBBBBB";

    private static final String DEFAULT_GREFFE_FAITE = "AAAAAAAAAA";
    private static final String UPDATED_GREFFE_FAITE = "BBBBBBBBBB";

    private static final Float DEFAULT_DELAI_RAPP_DIAG = 1F;
    private static final Float UPDATED_DELAI_RAPP_DIAG = 2F;

    private static final String DEFAULT_POURQUOI_NFAITE = "AAAAAAAAAA";
    private static final String UPDATED_POURQUOI_NFAITE = "BBBBBBBBBB";

    private static final String DEFAULT_CYCLOPHOSPHAMIDE = "AAAAAAAAAA";
    private static final String UPDATED_CYCLOPHOSPHAMIDE = "BBBBBBBBBB";

    private static final String DEFAULT_FLUDARABINE = "AAAAAAAAAA";
    private static final String UPDATED_FLUDARABINE = "BBBBBBBBBB";

    private static final String DEFAULT_BUSULFAN = "AAAAAAAAAA";
    private static final String UPDATED_BUSULFAN = "BBBBBBBBBB";

    private static final Float DEFAULT_DOSE_CYCLO = 1F;
    private static final Float UPDATED_DOSE_CYCLO = 2F;

    private static final Float DEFAULT_DOSE_FLU = 1F;
    private static final Float UPDATED_DOSE_FLU = 2F;

    private static final Float DEFAULT_DOSE_BUS = 1F;
    private static final Float UPDATED_DOSE_BUS = 2F;

    private static final String DEFAULT_AUTRE_CONDITIONNEMENT = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_CONDITIONNEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_IRRADIATION = "AAAAAAAAAA";
    private static final String UPDATED_IRRADIATION = "BBBBBBBBBB";

    private static final Float DEFAULT_DOSE_TOTALE_IRR = 1F;
    private static final Float UPDATED_DOSE_TOTALE_IRR = 2F;

    private static final String DEFAULT_SEROTHERAPIE = "AAAAAAAAAA";
    private static final String UPDATED_SEROTHERAPIE = "BBBBBBBBBB";

    private static final String DEFAULT_S_AL = "AAAAAAAAAA";
    private static final String UPDATED_S_AL = "BBBBBBBBBB";

    private static final Float DEFAULT_DOSE_SAL = 1F;
    private static final Float UPDATED_DOSE_SAL = 2F;

    private static final String DEFAULT_SOURCE_CELLULE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE_CELLULE = "BBBBBBBBBB";

    private static final Integer DEFAULT_SORTIE_APLASIE = 1;
    private static final Integer UPDATED_SORTIE_APLASIE = 2;

    private static final String DEFAULT_GRADEA_GV_H = "AAAAAAAAAA";
    private static final String UPDATED_GRADEA_GV_H = "BBBBBBBBBB";

    private static final String DEFAULT_C_GV_H = "AAAAAAAAAA";
    private static final String UPDATED_C_GV_H = "BBBBBBBBBB";

    private static final String DEFAULT_M_VO = "AAAAAAAAAA";
    private static final String UPDATED_M_VO = "BBBBBBBBBB";

    private static final String DEFAULT_COMPLICATION_PULMONAIRE = "AAAAAAAAAA";
    private static final String UPDATED_COMPLICATION_PULMONAIRE = "BBBBBBBBBB";

    private static final String DEFAULT_PRECISE_COMP_PULM = "AAAAAAAAAA";
    private static final String UPDATED_PRECISE_COMP_PULM = "BBBBBBBBBB";

    private static final String DEFAULT_SURVIE_J_100 = "AAAAAAAAAA";
    private static final String UPDATED_SURVIE_J_100 = "BBBBBBBBBB";

    private static final String DEFAULT_S_MD = "AAAAAAAAAA";
    private static final String UPDATED_S_MD = "BBBBBBBBBB";

    private static final Integer DEFAULT_AGE_DIAG_SMD = 1;
    private static final Integer UPDATED_AGE_DIAG_SMD = 2;

    private static final String DEFAULT_CRITERE_DIAG_SMD = "AAAAAAAAAA";
    private static final String UPDATED_CRITERE_DIAG_SMD = "BBBBBBBBBB";

    private static final String DEFAULT_TRAITEMENT_SMD = "AAAAAAAAAA";
    private static final String UPDATED_TRAITEMENT_SMD = "BBBBBBBBBB";

    private static final String DEFAULT_L_AM = "AAAAAAAAAA";
    private static final String UPDATED_L_AM = "BBBBBBBBBB";

    private static final String DEFAULT_CRITERE_DIAG_LAM = "AAAAAAAAAA";
    private static final String UPDATED_CRITERE_DIAG_LAM = "BBBBBBBBBB";

    private static final String DEFAULT_TRAITEMENT_LAM = "AAAAAAAAAA";
    private static final String UPDATED_TRAITEMENT_LAM = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRES_CANCERS = "AAAAAAAAAA";
    private static final String UPDATED_AUTRES_CANCERS = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_D_DN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_D_DN = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_TRANSFORMATION_AIGUE = "AAAAAAAAAA";
    private static final String UPDATED_TRANSFORMATION_AIGUE = "BBBBBBBBBB";

    private static final Integer DEFAULT_AGE_DIAG_LA = 1;
    private static final Integer UPDATED_AGE_DIAG_LA = 2;

    private static final String DEFAULT_TRAITEMENT_LA = "AAAAAAAAAA";
    private static final String UPDATED_TRAITEMENT_LA = "BBBBBBBBBB";

    private static final String DEFAULT_CANCER_E = "AAAAAAAAAA";
    private static final String UPDATED_CANCER_E = "BBBBBBBBBB";

    private static final String DEFAULT_LOCALISATION = "AAAAAAAAAA";
    private static final String UPDATED_LOCALISATION = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_HISTOLOGIQUE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_HISTOLOGIQUE = "BBBBBBBBBB";

    private static final String DEFAULT_TRAITEMENT_CANCER = "AAAAAAAAAA";
    private static final String UPDATED_TRAITEMENT_CANCER = "BBBBBBBBBB";

    private static final String DEFAULT_PRECISE_TRAITEMENT = "AAAAAAAAAA";
    private static final String UPDATED_PRECISE_TRAITEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_EVOLUTION_CYTO = "AAAAAAAAAA";
    private static final String UPDATED_EVOLUTION_CYTO = "BBBBBBBBBB";

    private static final String DEFAULT_FORMULE_CHROM = "AAAAAAAAAA";
    private static final String UPDATED_FORMULE_CHROM = "BBBBBBBBBB";

    private static final Integer DEFAULT_AGE_E = 1;
    private static final Integer UPDATED_AGE_E = 2;

    private static final String DEFAULT_STATUT = "AAAAAAAAAA";
    private static final String UPDATED_STATUT = "BBBBBBBBBB";

    private static final String DEFAULT_CAUSE_DECES = "AAAAAAAAAA";
    private static final String UPDATED_CAUSE_DECES = "BBBBBBBBBB";

    private static final String DEFAULT_AUTRE_CAUSE_D = "AAAAAAAAAA";
    private static final String UPDATED_AUTRE_CAUSE_D = "BBBBBBBBBB";

    private static final Float DEFAULT_SURVIE_GLOBALE = 1F;
    private static final Float UPDATED_SURVIE_GLOBALE = 2F;

    private static final Integer DEFAULT_CODE = 1;
    private static final Integer UPDATED_CODE = 2;

    private static final LocalDate DEFAULT_DATE_MAJ = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_MAJ = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_NOMBRE_TACHE_CAFE = 1;
    private static final Integer UPDATED_NOMBRE_TACHE_CAFE = 2;

    private static final Integer DEFAULT_NOMBRE_TACHE_HYPO = 1;
    private static final Integer UPDATED_NOMBRE_TACHE_HYPO = 2;

    private static final String ENTITY_API_URL = "/api/fiches";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ObjectMapper om;

    @Autowired
    private FicheRepository ficheRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFicheMockMvc;

    private Fiche fiche;

    private Fiche insertedFiche;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fiche createEntity() {
        return new Fiche()
            .nDossier(DEFAULT_N_DOSSIER)
            .dateDiagnostic(DEFAULT_DATE_DIAGNOSTIC)
            .dateEnregistrement(DEFAULT_DATE_ENREGISTREMENT)
            .medecin(DEFAULT_MEDECIN)
            .hopital(DEFAULT_HOPITAL)
            .service(DEFAULT_SERVICE)
            .degConsang(DEFAULT_DEG_CONSANG)
            .placeEnfant(DEFAULT_PLACE_ENFANT)
            .nbVivant(DEFAULT_NB_VIVANT)
            .nbMort(DEFAULT_NB_MORT)
            .mortNe(DEFAULT_MORT_NE)
            .avortement(DEFAULT_AVORTEMENT)
            .cousin(DEFAULT_COUSIN)
            .membre(DEFAULT_MEMBRE)
            .arbregenealogique(DEFAULT_ARBREGENEALOGIQUE)
            .arbregenealogiqueContentType(DEFAULT_ARBREGENEALOGIQUE_CONTENT_TYPE)
            .arbregenealogiquecancer(DEFAULT_ARBREGENEALOGIQUECANCER)
            .arbregenealogiquecancerContentType(DEFAULT_ARBREGENEALOGIQUECANCER_CONTENT_TYPE)
            .syndromeAnemique(DEFAULT_SYNDROME_ANEMIQUE)
            .syndromeHem(DEFAULT_SYNDROME_HEM)
            .syndromeInf(DEFAULT_SYNDROME_INF)
            .decouverteFort(DEFAULT_DECOUVERTE_FORT)
            .enqueteFam(DEFAULT_ENQUETE_FAM)
            .typeCancer(DEFAULT_TYPE_CANCER)
            .cancer(DEFAULT_CANCER)
            .tailleNaiss(DEFAULT_TAILLE_NAISS)
            .poidsNaiss(DEFAULT_POIDS_NAISS)
            .hypotrophie(DEFAULT_HYPOTROPHIE)
            .troubleCroi(DEFAULT_TROUBLE_CROI)
            .aAgeChDiag(DEFAULT_A_AGE_CH_DIAG)
            .mAgeChDiag(DEFAULT_M_AGE_CH_DIAG)
            .aAgeOssDiag(DEFAULT_A_AGE_OSS_DIAG)
            .mAgeOssDiag(DEFAULT_M_AGE_OSS_DIAG)
            .ageRetard(DEFAULT_AGE_RETARD)
            .poids(DEFAULT_POIDS)
            .poidsDS(DEFAULT_POIDS_DS)
            .taille(DEFAULT_TAILLE)
            .tailleDS(DEFAULT_TAILLE_DS)
            .atteinteCut(DEFAULT_ATTEINTE_CUT)
            .tacheCaf(DEFAULT_TACHE_CAF)
            .ventre(DEFAULT_VENTRE)
            .membreSup(DEFAULT_MEMBRE_SUP)
            .membreInf(DEFAULT_MEMBRE_INF)
            .visage(DEFAULT_VISAGE)
            .thorax(DEFAULT_THORAX)
            .dOS(DEFAULT_D_OS)
            .hyperPig(DEFAULT_HYPER_PIG)
            .hypochromique(DEFAULT_HYPOCHROMIQUE)
            .couleurPeau(DEFAULT_COULEUR_PEAU)
            .autreAtCut(DEFAULT_AUTRE_AT_CUT)
            .atteinteTete(DEFAULT_ATTEINTE_TETE)
            .microcephalie(DEFAULT_MICROCEPHALIE)
            .microphtalmie(DEFAULT_MICROPHTALMIE)
            .facieTrig(DEFAULT_FACIE_TRIG)
            .traitsFin(DEFAULT_TRAITS_FIN)
            .autreAtTete(DEFAULT_AUTRE_AT_TETE)
            .empreintedigitiforme(DEFAULT_EMPREINTEDIGITIFORME)
            .malUro(DEFAULT_MAL_URO)
            .uIV(DEFAULT_U_IV)
            .echo(DEFAULT_ECHO)
            .reinEctop(DEFAULT_REIN_ECTOP)
            .siegeEctopie(DEFAULT_SIEGE_ECTOPIE)
            .reinFerChev(DEFAULT_REIN_FER_CHEV)
            .petitRein(DEFAULT_PETIT_REIN)
            .reinUnique(DEFAULT_REIN_UNIQUE)
            .ectopTest(DEFAULT_ECTOP_TEST)
            .vergeInsuf(DEFAULT_VERGE_INSUF)
            .autreAnomVerge(DEFAULT_AUTRE_ANOM_VERGE)
            .retardPubertaire(DEFAULT_RETARD_PUBERTAIRE)
            .mTanner(DEFAULT_M_TANNER)
            .pTanner(DEFAULT_P_TANNER)
            .tTanner(DEFAULT_T_TANNER)
            .anomUrin(DEFAULT_ANOM_URIN)
            .typeAnomUrin(DEFAULT_TYPE_ANOM_URIN)
            .atteinteOss(DEFAULT_ATTEINTE_OSS)
            .radiosfaites(DEFAULT_RADIOSFAITES)
            .anomPouce(DEFAULT_ANOM_POUCE)
            .surnumerarie(DEFAULT_SURNUMERARIE)
            .agenesiePouce(DEFAULT_AGENESIE_POUCE)
            .bifide(DEFAULT_BIFIDE)
            .hypoPouce(DEFAULT_HYPO_POUCE)
            .aspectPouce(DEFAULT_ASPECT_POUCE)
            .hypoEminence(DEFAULT_HYPO_EMINENCE)
            .absenceRadial(DEFAULT_ABSENCE_RADIAL)
            .pouceBas(DEFAULT_POUCE_BAS)
            .autreAnomPouce(DEFAULT_AUTRE_ANOM_POUCE)
            .anomAutDoigts(DEFAULT_ANOM_AUT_DOIGTS)
            .courtstrapus(DEFAULT_COURTSTRAPUS)
            .syndactylie(DEFAULT_SYNDACTYLIE)
            .autreAnomDoigts(DEFAULT_AUTRE_ANOM_DOIGTS)
            .anomalieos(DEFAULT_ANOMALIEOS)
            .agenesieRadius(DEFAULT_AGENESIE_RADIUS)
            .autreanomalieMembresup(DEFAULT_AUTREANOMALIE_MEMBRESUP)
            .anomOrteil(DEFAULT_ANOM_ORTEIL)
            .preciseAnomOrt(DEFAULT_PRECISE_ANOM_ORT)
            .lCH(DEFAULT_L_CH)
            .autreanomalieMembreinf(DEFAULT_AUTREANOMALIE_MEMBREINF)
            .anomRachis(DEFAULT_ANOM_RACHIS)
            .preciseAnomRac(DEFAULT_PRECISE_ANOM_RAC)
            .autreAnomOss(DEFAULT_AUTRE_ANOM_OSS)
            .anomNeuro(DEFAULT_ANOM_NEURO)
            .retardMent(DEFAULT_RETARD_MENT)
            .hypoacousie(DEFAULT_HYPOACOUSIE)
            .strabisme(DEFAULT_STRABISME)
            .performanceScolaire(DEFAULT_PERFORMANCE_SCOLAIRE)
            .autreanomalieneurologique(DEFAULT_AUTREANOMALIENEUROLOGIQUE)
            .anomCardVas(DEFAULT_ANOM_CARD_VAS)
            .echoCoeur(DEFAULT_ECHO_COEUR)
            .preciseAnomCardio(DEFAULT_PRECISE_ANOM_CARDIO)
            .anomDig(DEFAULT_ANOM_DIG)
            .preciseAnomDig(DEFAULT_PRECISE_ANOM_DIG)
            .endocrinopathie(DEFAULT_ENDOCRINOPATHIE)
            .diabete(DEFAULT_DIABETE)
            .insulinoDep(DEFAULT_INSULINO_DEP)
            .hypothyroidie(DEFAULT_HYPOTHYROIDIE)
            .ageDecouverte(DEFAULT_AGE_DECOUVERTE)
            .autreEndocrinopathie(DEFAULT_AUTRE_ENDOCRINOPATHIE)
            .autreAnomCong(DEFAULT_AUTRE_ANOM_CONG)
            .dateNumSanguine(DEFAULT_DATE_NUM_SANGUINE)
            .age(DEFAULT_AGE)
            .hb(DEFAULT_HB)
            .vGM(DEFAULT_V_GM)
            .retic(DEFAULT_RETIC)
            .leuco(DEFAULT_LEUCO)
            .pNN(DEFAULT_P_NN)
            .plq(DEFAULT_PLQ)
            .hbInf(DEFAULT_HB_INF)
            .plqInf(DEFAULT_PLQ_INF)
            .pNNInf(DEFAULT_P_NN_INF)
            .electrophoreseHb(DEFAULT_ELECTROPHORESE_HB)
            .hbf(DEFAULT_HBF)
            .myelogramme(DEFAULT_MYELOGRAMME)
            .cellularite(DEFAULT_CELLULARITE)
            .erythroblaste(DEFAULT_ERYTHROBLASTE)
            .morphologieEryth(DEFAULT_MORPHOLOGIE_ERYTH)
            .morphologieGran(DEFAULT_MORPHOLOGIE_GRAN)
            .morphologieMega(DEFAULT_MORPHOLOGIE_MEGA)
            .granuleux(DEFAULT_GRANULEUX)
            .dysmyelopoiese(DEFAULT_DYSMYELOPOIESE)
            .megacaryocytes(DEFAULT_MEGACARYOCYTES)
            .blaste(DEFAULT_BLASTE)
            .excesblastes(DEFAULT_EXCESBLASTES)
            .bOM(DEFAULT_B_OM)
            .adipocytes(DEFAULT_ADIPOCYTES)
            .ubiquitination(DEFAULT_UBIQUITINATION)
            .resUbiquitination(DEFAULT_RES_UBIQUITINATION)
            .groupComp(DEFAULT_GROUP_COMP)
            .mutationFANC(DEFAULT_MUTATION_FANC)
            .congelationCellule(DEFAULT_CONGELATION_CELLULE)
            .labo(DEFAULT_LABO)
            .typePrelevement(DEFAULT_TYPE_PRELEVEMENT)
            .scoreClinique(DEFAULT_SCORE_CLINIQUE)
            .scoreEBMT(DEFAULT_SCORE_EBMT)
            .score(DEFAULT_SCORE)
            .transfusion(DEFAULT_TRANSFUSION)
            .ageTransf(DEFAULT_AGE_TRANSF)
            .delaiDiag(DEFAULT_DELAI_DIAG)
            .nbCG(DEFAULT_NB_CG)
            .nbCP(DEFAULT_NB_CP)
            .chelationFer(DEFAULT_CHELATION_FER)
            .chelateur(DEFAULT_CHELATEUR)
            .nilevar(DEFAULT_NILEVAR)
            .danatrol(DEFAULT_DANATROL)
            .oxynethadone(DEFAULT_OXYNETHADONE)
            .androtordyl(DEFAULT_ANDROTORDYL)
            .autreAndrogene(DEFAULT_AUTRE_ANDROGENE)
            .androDebut(DEFAULT_ANDRO_DEBUT)
            .androFin(DEFAULT_ANDRO_FIN)
            .observance(DEFAULT_OBSERVANCE)
            .toxicite(DEFAULT_TOXICITE)
            .autreToxicite(DEFAULT_AUTRE_TOXICITE)
            .enqueteHLA(DEFAULT_ENQUETE_HLA)
            .pourquoiEnqHLA(DEFAULT_POURQUOI_ENQ_HLA)
            .nbFratTyp(DEFAULT_NB_FRAT_TYP)
            .nbFratNTyp(DEFAULT_NB_FRAT_N_TYP)
            .donneurComp(DEFAULT_DONNEUR_COMP)
            .preciseDonneur(DEFAULT_PRECISE_DONNEUR)
            .donneur(DEFAULT_DONNEUR)
            .greffeFaite(DEFAULT_GREFFE_FAITE)
            .delaiRappDiag(DEFAULT_DELAI_RAPP_DIAG)
            .pourquoiNfaite(DEFAULT_POURQUOI_NFAITE)
            .cyclophosphamide(DEFAULT_CYCLOPHOSPHAMIDE)
            .fludarabine(DEFAULT_FLUDARABINE)
            .busulfan(DEFAULT_BUSULFAN)
            .doseCyclo(DEFAULT_DOSE_CYCLO)
            .doseFlu(DEFAULT_DOSE_FLU)
            .doseBus(DEFAULT_DOSE_BUS)
            .autreConditionnement(DEFAULT_AUTRE_CONDITIONNEMENT)
            .irradiation(DEFAULT_IRRADIATION)
            .doseTotaleIrr(DEFAULT_DOSE_TOTALE_IRR)
            .serotherapie(DEFAULT_SEROTHERAPIE)
            .sAL(DEFAULT_S_AL)
            .doseSAL(DEFAULT_DOSE_SAL)
            .sourceCellule(DEFAULT_SOURCE_CELLULE)
            .sortieAplasie(DEFAULT_SORTIE_APLASIE)
            .gradeaGvH(DEFAULT_GRADEA_GV_H)
            .cGvH(DEFAULT_C_GV_H)
            .mVO(DEFAULT_M_VO)
            .complicationPulmonaire(DEFAULT_COMPLICATION_PULMONAIRE)
            .preciseCompPulm(DEFAULT_PRECISE_COMP_PULM)
            .survieJ100(DEFAULT_SURVIE_J_100)
            .sMD(DEFAULT_S_MD)
            .ageDiagSMD(DEFAULT_AGE_DIAG_SMD)
            .critereDiagSMD(DEFAULT_CRITERE_DIAG_SMD)
            .traitementSMD(DEFAULT_TRAITEMENT_SMD)
            .lAM(DEFAULT_L_AM)
            .critereDiagLAM(DEFAULT_CRITERE_DIAG_LAM)
            .traitementLAM(DEFAULT_TRAITEMENT_LAM)
            .autresCancers(DEFAULT_AUTRES_CANCERS)
            .dDN(DEFAULT_D_DN)
            .transformationAigue(DEFAULT_TRANSFORMATION_AIGUE)
            .ageDiagLA(DEFAULT_AGE_DIAG_LA)
            .traitementLA(DEFAULT_TRAITEMENT_LA)
            .cancerE(DEFAULT_CANCER_E)
            .localisation(DEFAULT_LOCALISATION)
            .typeHistologique(DEFAULT_TYPE_HISTOLOGIQUE)
            .traitementCancer(DEFAULT_TRAITEMENT_CANCER)
            .preciseTraitement(DEFAULT_PRECISE_TRAITEMENT)
            .evolutionCyto(DEFAULT_EVOLUTION_CYTO)
            .formuleChrom(DEFAULT_FORMULE_CHROM)
            .ageE(DEFAULT_AGE_E)
            .statut(DEFAULT_STATUT)
            .causeDeces(DEFAULT_CAUSE_DECES)
            .autreCauseD(DEFAULT_AUTRE_CAUSE_D)
            .survieGlobale(DEFAULT_SURVIE_GLOBALE)
            .code(DEFAULT_CODE)
            .dateMAJ(DEFAULT_DATE_MAJ)
            .nombreTacheCafe(DEFAULT_NOMBRE_TACHE_CAFE)
            .nombreTacheHypo(DEFAULT_NOMBRE_TACHE_HYPO);
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fiche createUpdatedEntity() {
        return new Fiche()
            .nDossier(UPDATED_N_DOSSIER)
            .dateDiagnostic(UPDATED_DATE_DIAGNOSTIC)
            .dateEnregistrement(UPDATED_DATE_ENREGISTREMENT)
            .medecin(UPDATED_MEDECIN)
            .hopital(UPDATED_HOPITAL)
            .service(UPDATED_SERVICE)
            .degConsang(UPDATED_DEG_CONSANG)
            .placeEnfant(UPDATED_PLACE_ENFANT)
            .nbVivant(UPDATED_NB_VIVANT)
            .nbMort(UPDATED_NB_MORT)
            .mortNe(UPDATED_MORT_NE)
            .avortement(UPDATED_AVORTEMENT)
            .cousin(UPDATED_COUSIN)
            .membre(UPDATED_MEMBRE)
            .arbregenealogique(UPDATED_ARBREGENEALOGIQUE)
            .arbregenealogiqueContentType(UPDATED_ARBREGENEALOGIQUE_CONTENT_TYPE)
            .arbregenealogiquecancer(UPDATED_ARBREGENEALOGIQUECANCER)
            .arbregenealogiquecancerContentType(UPDATED_ARBREGENEALOGIQUECANCER_CONTENT_TYPE)
            .syndromeAnemique(UPDATED_SYNDROME_ANEMIQUE)
            .syndromeHem(UPDATED_SYNDROME_HEM)
            .syndromeInf(UPDATED_SYNDROME_INF)
            .decouverteFort(UPDATED_DECOUVERTE_FORT)
            .enqueteFam(UPDATED_ENQUETE_FAM)
            .typeCancer(UPDATED_TYPE_CANCER)
            .cancer(UPDATED_CANCER)
            .tailleNaiss(UPDATED_TAILLE_NAISS)
            .poidsNaiss(UPDATED_POIDS_NAISS)
            .hypotrophie(UPDATED_HYPOTROPHIE)
            .troubleCroi(UPDATED_TROUBLE_CROI)
            .aAgeChDiag(UPDATED_A_AGE_CH_DIAG)
            .mAgeChDiag(UPDATED_M_AGE_CH_DIAG)
            .aAgeOssDiag(UPDATED_A_AGE_OSS_DIAG)
            .mAgeOssDiag(UPDATED_M_AGE_OSS_DIAG)
            .ageRetard(UPDATED_AGE_RETARD)
            .poids(UPDATED_POIDS)
            .poidsDS(UPDATED_POIDS_DS)
            .taille(UPDATED_TAILLE)
            .tailleDS(UPDATED_TAILLE_DS)
            .atteinteCut(UPDATED_ATTEINTE_CUT)
            .tacheCaf(UPDATED_TACHE_CAF)
            .ventre(UPDATED_VENTRE)
            .membreSup(UPDATED_MEMBRE_SUP)
            .membreInf(UPDATED_MEMBRE_INF)
            .visage(UPDATED_VISAGE)
            .thorax(UPDATED_THORAX)
            .dOS(UPDATED_D_OS)
            .hyperPig(UPDATED_HYPER_PIG)
            .hypochromique(UPDATED_HYPOCHROMIQUE)
            .couleurPeau(UPDATED_COULEUR_PEAU)
            .autreAtCut(UPDATED_AUTRE_AT_CUT)
            .atteinteTete(UPDATED_ATTEINTE_TETE)
            .microcephalie(UPDATED_MICROCEPHALIE)
            .microphtalmie(UPDATED_MICROPHTALMIE)
            .facieTrig(UPDATED_FACIE_TRIG)
            .traitsFin(UPDATED_TRAITS_FIN)
            .autreAtTete(UPDATED_AUTRE_AT_TETE)
            .empreintedigitiforme(UPDATED_EMPREINTEDIGITIFORME)
            .malUro(UPDATED_MAL_URO)
            .uIV(UPDATED_U_IV)
            .echo(UPDATED_ECHO)
            .reinEctop(UPDATED_REIN_ECTOP)
            .siegeEctopie(UPDATED_SIEGE_ECTOPIE)
            .reinFerChev(UPDATED_REIN_FER_CHEV)
            .petitRein(UPDATED_PETIT_REIN)
            .reinUnique(UPDATED_REIN_UNIQUE)
            .ectopTest(UPDATED_ECTOP_TEST)
            .vergeInsuf(UPDATED_VERGE_INSUF)
            .autreAnomVerge(UPDATED_AUTRE_ANOM_VERGE)
            .retardPubertaire(UPDATED_RETARD_PUBERTAIRE)
            .mTanner(UPDATED_M_TANNER)
            .pTanner(UPDATED_P_TANNER)
            .tTanner(UPDATED_T_TANNER)
            .anomUrin(UPDATED_ANOM_URIN)
            .typeAnomUrin(UPDATED_TYPE_ANOM_URIN)
            .atteinteOss(UPDATED_ATTEINTE_OSS)
            .radiosfaites(UPDATED_RADIOSFAITES)
            .anomPouce(UPDATED_ANOM_POUCE)
            .surnumerarie(UPDATED_SURNUMERARIE)
            .agenesiePouce(UPDATED_AGENESIE_POUCE)
            .bifide(UPDATED_BIFIDE)
            .hypoPouce(UPDATED_HYPO_POUCE)
            .aspectPouce(UPDATED_ASPECT_POUCE)
            .hypoEminence(UPDATED_HYPO_EMINENCE)
            .absenceRadial(UPDATED_ABSENCE_RADIAL)
            .pouceBas(UPDATED_POUCE_BAS)
            .autreAnomPouce(UPDATED_AUTRE_ANOM_POUCE)
            .anomAutDoigts(UPDATED_ANOM_AUT_DOIGTS)
            .courtstrapus(UPDATED_COURTSTRAPUS)
            .syndactylie(UPDATED_SYNDACTYLIE)
            .autreAnomDoigts(UPDATED_AUTRE_ANOM_DOIGTS)
            .anomalieos(UPDATED_ANOMALIEOS)
            .agenesieRadius(UPDATED_AGENESIE_RADIUS)
            .autreanomalieMembresup(UPDATED_AUTREANOMALIE_MEMBRESUP)
            .anomOrteil(UPDATED_ANOM_ORTEIL)
            .preciseAnomOrt(UPDATED_PRECISE_ANOM_ORT)
            .lCH(UPDATED_L_CH)
            .autreanomalieMembreinf(UPDATED_AUTREANOMALIE_MEMBREINF)
            .anomRachis(UPDATED_ANOM_RACHIS)
            .preciseAnomRac(UPDATED_PRECISE_ANOM_RAC)
            .autreAnomOss(UPDATED_AUTRE_ANOM_OSS)
            .anomNeuro(UPDATED_ANOM_NEURO)
            .retardMent(UPDATED_RETARD_MENT)
            .hypoacousie(UPDATED_HYPOACOUSIE)
            .strabisme(UPDATED_STRABISME)
            .performanceScolaire(UPDATED_PERFORMANCE_SCOLAIRE)
            .autreanomalieneurologique(UPDATED_AUTREANOMALIENEUROLOGIQUE)
            .anomCardVas(UPDATED_ANOM_CARD_VAS)
            .echoCoeur(UPDATED_ECHO_COEUR)
            .preciseAnomCardio(UPDATED_PRECISE_ANOM_CARDIO)
            .anomDig(UPDATED_ANOM_DIG)
            .preciseAnomDig(UPDATED_PRECISE_ANOM_DIG)
            .endocrinopathie(UPDATED_ENDOCRINOPATHIE)
            .diabete(UPDATED_DIABETE)
            .insulinoDep(UPDATED_INSULINO_DEP)
            .hypothyroidie(UPDATED_HYPOTHYROIDIE)
            .ageDecouverte(UPDATED_AGE_DECOUVERTE)
            .autreEndocrinopathie(UPDATED_AUTRE_ENDOCRINOPATHIE)
            .autreAnomCong(UPDATED_AUTRE_ANOM_CONG)
            .dateNumSanguine(UPDATED_DATE_NUM_SANGUINE)
            .age(UPDATED_AGE)
            .hb(UPDATED_HB)
            .vGM(UPDATED_V_GM)
            .retic(UPDATED_RETIC)
            .leuco(UPDATED_LEUCO)
            .pNN(UPDATED_P_NN)
            .plq(UPDATED_PLQ)
            .hbInf(UPDATED_HB_INF)
            .plqInf(UPDATED_PLQ_INF)
            .pNNInf(UPDATED_P_NN_INF)
            .electrophoreseHb(UPDATED_ELECTROPHORESE_HB)
            .hbf(UPDATED_HBF)
            .myelogramme(UPDATED_MYELOGRAMME)
            .cellularite(UPDATED_CELLULARITE)
            .erythroblaste(UPDATED_ERYTHROBLASTE)
            .morphologieEryth(UPDATED_MORPHOLOGIE_ERYTH)
            .morphologieGran(UPDATED_MORPHOLOGIE_GRAN)
            .morphologieMega(UPDATED_MORPHOLOGIE_MEGA)
            .granuleux(UPDATED_GRANULEUX)
            .dysmyelopoiese(UPDATED_DYSMYELOPOIESE)
            .megacaryocytes(UPDATED_MEGACARYOCYTES)
            .blaste(UPDATED_BLASTE)
            .excesblastes(UPDATED_EXCESBLASTES)
            .bOM(UPDATED_B_OM)
            .adipocytes(UPDATED_ADIPOCYTES)
            .ubiquitination(UPDATED_UBIQUITINATION)
            .resUbiquitination(UPDATED_RES_UBIQUITINATION)
            .groupComp(UPDATED_GROUP_COMP)
            .mutationFANC(UPDATED_MUTATION_FANC)
            .congelationCellule(UPDATED_CONGELATION_CELLULE)
            .labo(UPDATED_LABO)
            .typePrelevement(UPDATED_TYPE_PRELEVEMENT)
            .scoreClinique(UPDATED_SCORE_CLINIQUE)
            .scoreEBMT(UPDATED_SCORE_EBMT)
            .score(UPDATED_SCORE)
            .transfusion(UPDATED_TRANSFUSION)
            .ageTransf(UPDATED_AGE_TRANSF)
            .delaiDiag(UPDATED_DELAI_DIAG)
            .nbCG(UPDATED_NB_CG)
            .nbCP(UPDATED_NB_CP)
            .chelationFer(UPDATED_CHELATION_FER)
            .chelateur(UPDATED_CHELATEUR)
            .nilevar(UPDATED_NILEVAR)
            .danatrol(UPDATED_DANATROL)
            .oxynethadone(UPDATED_OXYNETHADONE)
            .androtordyl(UPDATED_ANDROTORDYL)
            .autreAndrogene(UPDATED_AUTRE_ANDROGENE)
            .androDebut(UPDATED_ANDRO_DEBUT)
            .androFin(UPDATED_ANDRO_FIN)
            .observance(UPDATED_OBSERVANCE)
            .toxicite(UPDATED_TOXICITE)
            .autreToxicite(UPDATED_AUTRE_TOXICITE)
            .enqueteHLA(UPDATED_ENQUETE_HLA)
            .pourquoiEnqHLA(UPDATED_POURQUOI_ENQ_HLA)
            .nbFratTyp(UPDATED_NB_FRAT_TYP)
            .nbFratNTyp(UPDATED_NB_FRAT_N_TYP)
            .donneurComp(UPDATED_DONNEUR_COMP)
            .preciseDonneur(UPDATED_PRECISE_DONNEUR)
            .donneur(UPDATED_DONNEUR)
            .greffeFaite(UPDATED_GREFFE_FAITE)
            .delaiRappDiag(UPDATED_DELAI_RAPP_DIAG)
            .pourquoiNfaite(UPDATED_POURQUOI_NFAITE)
            .cyclophosphamide(UPDATED_CYCLOPHOSPHAMIDE)
            .fludarabine(UPDATED_FLUDARABINE)
            .busulfan(UPDATED_BUSULFAN)
            .doseCyclo(UPDATED_DOSE_CYCLO)
            .doseFlu(UPDATED_DOSE_FLU)
            .doseBus(UPDATED_DOSE_BUS)
            .autreConditionnement(UPDATED_AUTRE_CONDITIONNEMENT)
            .irradiation(UPDATED_IRRADIATION)
            .doseTotaleIrr(UPDATED_DOSE_TOTALE_IRR)
            .serotherapie(UPDATED_SEROTHERAPIE)
            .sAL(UPDATED_S_AL)
            .doseSAL(UPDATED_DOSE_SAL)
            .sourceCellule(UPDATED_SOURCE_CELLULE)
            .sortieAplasie(UPDATED_SORTIE_APLASIE)
            .gradeaGvH(UPDATED_GRADEA_GV_H)
            .cGvH(UPDATED_C_GV_H)
            .mVO(UPDATED_M_VO)
            .complicationPulmonaire(UPDATED_COMPLICATION_PULMONAIRE)
            .preciseCompPulm(UPDATED_PRECISE_COMP_PULM)
            .survieJ100(UPDATED_SURVIE_J_100)
            .sMD(UPDATED_S_MD)
            .ageDiagSMD(UPDATED_AGE_DIAG_SMD)
            .critereDiagSMD(UPDATED_CRITERE_DIAG_SMD)
            .traitementSMD(UPDATED_TRAITEMENT_SMD)
            .lAM(UPDATED_L_AM)
            .critereDiagLAM(UPDATED_CRITERE_DIAG_LAM)
            .traitementLAM(UPDATED_TRAITEMENT_LAM)
            .autresCancers(UPDATED_AUTRES_CANCERS)
            .dDN(UPDATED_D_DN)
            .transformationAigue(UPDATED_TRANSFORMATION_AIGUE)
            .ageDiagLA(UPDATED_AGE_DIAG_LA)
            .traitementLA(UPDATED_TRAITEMENT_LA)
            .cancerE(UPDATED_CANCER_E)
            .localisation(UPDATED_LOCALISATION)
            .typeHistologique(UPDATED_TYPE_HISTOLOGIQUE)
            .traitementCancer(UPDATED_TRAITEMENT_CANCER)
            .preciseTraitement(UPDATED_PRECISE_TRAITEMENT)
            .evolutionCyto(UPDATED_EVOLUTION_CYTO)
            .formuleChrom(UPDATED_FORMULE_CHROM)
            .ageE(UPDATED_AGE_E)
            .statut(UPDATED_STATUT)
            .causeDeces(UPDATED_CAUSE_DECES)
            .autreCauseD(UPDATED_AUTRE_CAUSE_D)
            .survieGlobale(UPDATED_SURVIE_GLOBALE)
            .code(UPDATED_CODE)
            .dateMAJ(UPDATED_DATE_MAJ)
            .nombreTacheCafe(UPDATED_NOMBRE_TACHE_CAFE)
            .nombreTacheHypo(UPDATED_NOMBRE_TACHE_HYPO);
    }

    @BeforeEach
    public void initTest() {
        fiche = createEntity();
    }

    @AfterEach
    public void cleanup() {
        if (insertedFiche != null) {
            ficheRepository.delete(insertedFiche);
            insertedFiche = null;
        }
    }

    @Test
    @Transactional
    void createFiche() throws Exception {
        long databaseSizeBeforeCreate = getRepositoryCount();
        // Create the Fiche
        var returnedFiche = om.readValue(
            restFicheMockMvc
                .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fiche)))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString(),
            Fiche.class
        );

        // Validate the Fiche in the database
        assertIncrementedRepositoryCount(databaseSizeBeforeCreate);
        assertFicheUpdatableFieldsEquals(returnedFiche, getPersistedFiche(returnedFiche));

        insertedFiche = returnedFiche;
    }

    @Test
    @Transactional
    void createFicheWithExistingId() throws Exception {
        // Create the Fiche with an existing ID
        fiche.setId(1L);

        long databaseSizeBeforeCreate = getRepositoryCount();

        // An entity with an existing ID cannot be created, so this API call must fail
        restFicheMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fiche)))
            .andExpect(status().isBadRequest());

        // Validate the Fiche in the database
        assertSameRepositoryCount(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllFiches() throws Exception {
        // Initialize the database
        insertedFiche = ficheRepository.saveAndFlush(fiche);

        // Get all the ficheList
        restFicheMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fiche.getId().intValue())))
            .andExpect(jsonPath("$.[*].nDossier").value(hasItem(DEFAULT_N_DOSSIER)))
            .andExpect(jsonPath("$.[*].dateDiagnostic").value(hasItem(DEFAULT_DATE_DIAGNOSTIC.toString())))
            .andExpect(jsonPath("$.[*].dateEnregistrement").value(hasItem(DEFAULT_DATE_ENREGISTREMENT.toString())))
            .andExpect(jsonPath("$.[*].medecin").value(hasItem(DEFAULT_MEDECIN)))
            .andExpect(jsonPath("$.[*].hopital").value(hasItem(DEFAULT_HOPITAL)))
            .andExpect(jsonPath("$.[*].service").value(hasItem(DEFAULT_SERVICE)))
            .andExpect(jsonPath("$.[*].degConsang").value(hasItem(DEFAULT_DEG_CONSANG)))
            .andExpect(jsonPath("$.[*].placeEnfant").value(hasItem(DEFAULT_PLACE_ENFANT)))
            .andExpect(jsonPath("$.[*].nbVivant").value(hasItem(DEFAULT_NB_VIVANT)))
            .andExpect(jsonPath("$.[*].nbMort").value(hasItem(DEFAULT_NB_MORT)))
            .andExpect(jsonPath("$.[*].mortNe").value(hasItem(DEFAULT_MORT_NE)))
            .andExpect(jsonPath("$.[*].avortement").value(hasItem(DEFAULT_AVORTEMENT)))
            .andExpect(jsonPath("$.[*].cousin").value(hasItem(DEFAULT_COUSIN)))
            .andExpect(jsonPath("$.[*].membre").value(hasItem(DEFAULT_MEMBRE)))
            .andExpect(jsonPath("$.[*].arbregenealogiqueContentType").value(hasItem(DEFAULT_ARBREGENEALOGIQUE_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].arbregenealogique").value(hasItem(Base64.getEncoder().encodeToString(DEFAULT_ARBREGENEALOGIQUE))))
            .andExpect(jsonPath("$.[*].arbregenealogiquecancerContentType").value(hasItem(DEFAULT_ARBREGENEALOGIQUECANCER_CONTENT_TYPE)))
            .andExpect(
                jsonPath("$.[*].arbregenealogiquecancer").value(
                    hasItem(Base64.getEncoder().encodeToString(DEFAULT_ARBREGENEALOGIQUECANCER))
                )
            )
            .andExpect(jsonPath("$.[*].syndromeAnemique").value(hasItem(DEFAULT_SYNDROME_ANEMIQUE)))
            .andExpect(jsonPath("$.[*].syndromeHem").value(hasItem(DEFAULT_SYNDROME_HEM)))
            .andExpect(jsonPath("$.[*].syndromeInf").value(hasItem(DEFAULT_SYNDROME_INF)))
            .andExpect(jsonPath("$.[*].decouverteFort").value(hasItem(DEFAULT_DECOUVERTE_FORT)))
            .andExpect(jsonPath("$.[*].enqueteFam").value(hasItem(DEFAULT_ENQUETE_FAM)))
            .andExpect(jsonPath("$.[*].typeCancer").value(hasItem(DEFAULT_TYPE_CANCER)))
            .andExpect(jsonPath("$.[*].cancer").value(hasItem(DEFAULT_CANCER)))
            .andExpect(jsonPath("$.[*].tailleNaiss").value(hasItem(DEFAULT_TAILLE_NAISS.doubleValue())))
            .andExpect(jsonPath("$.[*].poidsNaiss").value(hasItem(DEFAULT_POIDS_NAISS.doubleValue())))
            .andExpect(jsonPath("$.[*].hypotrophie").value(hasItem(DEFAULT_HYPOTROPHIE)))
            .andExpect(jsonPath("$.[*].troubleCroi").value(hasItem(DEFAULT_TROUBLE_CROI)))
            .andExpect(jsonPath("$.[*].aAgeChDiag").value(hasItem(DEFAULT_A_AGE_CH_DIAG)))
            .andExpect(jsonPath("$.[*].mAgeChDiag").value(hasItem(DEFAULT_M_AGE_CH_DIAG)))
            .andExpect(jsonPath("$.[*].aAgeOssDiag").value(hasItem(DEFAULT_A_AGE_OSS_DIAG)))
            .andExpect(jsonPath("$.[*].mAgeOssDiag").value(hasItem(DEFAULT_M_AGE_OSS_DIAG)))
            .andExpect(jsonPath("$.[*].ageRetard").value(hasItem(DEFAULT_AGE_RETARD)))
            .andExpect(jsonPath("$.[*].poids").value(hasItem(DEFAULT_POIDS.doubleValue())))
            .andExpect(jsonPath("$.[*].poidsDS").value(hasItem(DEFAULT_POIDS_DS)))
            .andExpect(jsonPath("$.[*].taille").value(hasItem(DEFAULT_TAILLE.doubleValue())))
            .andExpect(jsonPath("$.[*].tailleDS").value(hasItem(DEFAULT_TAILLE_DS)))
            .andExpect(jsonPath("$.[*].atteinteCut").value(hasItem(DEFAULT_ATTEINTE_CUT)))
            .andExpect(jsonPath("$.[*].tacheCaf").value(hasItem(DEFAULT_TACHE_CAF)))
            .andExpect(jsonPath("$.[*].ventre").value(hasItem(DEFAULT_VENTRE)))
            .andExpect(jsonPath("$.[*].membreSup").value(hasItem(DEFAULT_MEMBRE_SUP)))
            .andExpect(jsonPath("$.[*].membreInf").value(hasItem(DEFAULT_MEMBRE_INF)))
            .andExpect(jsonPath("$.[*].visage").value(hasItem(DEFAULT_VISAGE)))
            .andExpect(jsonPath("$.[*].thorax").value(hasItem(DEFAULT_THORAX)))
            .andExpect(jsonPath("$.[*].dOS").value(hasItem(DEFAULT_D_OS)))
            .andExpect(jsonPath("$.[*].hyperPig").value(hasItem(DEFAULT_HYPER_PIG)))
            .andExpect(jsonPath("$.[*].hypochromique").value(hasItem(DEFAULT_HYPOCHROMIQUE)))
            .andExpect(jsonPath("$.[*].couleurPeau").value(hasItem(DEFAULT_COULEUR_PEAU)))
            .andExpect(jsonPath("$.[*].autreAtCut").value(hasItem(DEFAULT_AUTRE_AT_CUT)))
            .andExpect(jsonPath("$.[*].atteinteTete").value(hasItem(DEFAULT_ATTEINTE_TETE)))
            .andExpect(jsonPath("$.[*].microcephalie").value(hasItem(DEFAULT_MICROCEPHALIE)))
            .andExpect(jsonPath("$.[*].microphtalmie").value(hasItem(DEFAULT_MICROPHTALMIE)))
            .andExpect(jsonPath("$.[*].facieTrig").value(hasItem(DEFAULT_FACIE_TRIG)))
            .andExpect(jsonPath("$.[*].traitsFin").value(hasItem(DEFAULT_TRAITS_FIN)))
            .andExpect(jsonPath("$.[*].autreAtTete").value(hasItem(DEFAULT_AUTRE_AT_TETE)))
            .andExpect(jsonPath("$.[*].empreintedigitiforme").value(hasItem(DEFAULT_EMPREINTEDIGITIFORME)))
            .andExpect(jsonPath("$.[*].malUro").value(hasItem(DEFAULT_MAL_URO)))
            .andExpect(jsonPath("$.[*].uIV").value(hasItem(DEFAULT_U_IV)))
            .andExpect(jsonPath("$.[*].echo").value(hasItem(DEFAULT_ECHO)))
            .andExpect(jsonPath("$.[*].reinEctop").value(hasItem(DEFAULT_REIN_ECTOP)))
            .andExpect(jsonPath("$.[*].siegeEctopie").value(hasItem(DEFAULT_SIEGE_ECTOPIE)))
            .andExpect(jsonPath("$.[*].reinFerChev").value(hasItem(DEFAULT_REIN_FER_CHEV)))
            .andExpect(jsonPath("$.[*].petitRein").value(hasItem(DEFAULT_PETIT_REIN)))
            .andExpect(jsonPath("$.[*].reinUnique").value(hasItem(DEFAULT_REIN_UNIQUE)))
            .andExpect(jsonPath("$.[*].ectopTest").value(hasItem(DEFAULT_ECTOP_TEST)))
            .andExpect(jsonPath("$.[*].vergeInsuf").value(hasItem(DEFAULT_VERGE_INSUF)))
            .andExpect(jsonPath("$.[*].autreAnomVerge").value(hasItem(DEFAULT_AUTRE_ANOM_VERGE)))
            .andExpect(jsonPath("$.[*].retardPubertaire").value(hasItem(DEFAULT_RETARD_PUBERTAIRE)))
            .andExpect(jsonPath("$.[*].mTanner").value(hasItem(DEFAULT_M_TANNER)))
            .andExpect(jsonPath("$.[*].pTanner").value(hasItem(DEFAULT_P_TANNER)))
            .andExpect(jsonPath("$.[*].tTanner").value(hasItem(DEFAULT_T_TANNER)))
            .andExpect(jsonPath("$.[*].anomUrin").value(hasItem(DEFAULT_ANOM_URIN)))
            .andExpect(jsonPath("$.[*].typeAnomUrin").value(hasItem(DEFAULT_TYPE_ANOM_URIN)))
            .andExpect(jsonPath("$.[*].atteinteOss").value(hasItem(DEFAULT_ATTEINTE_OSS)))
            .andExpect(jsonPath("$.[*].radiosfaites").value(hasItem(DEFAULT_RADIOSFAITES)))
            .andExpect(jsonPath("$.[*].anomPouce").value(hasItem(DEFAULT_ANOM_POUCE)))
            .andExpect(jsonPath("$.[*].surnumerarie").value(hasItem(DEFAULT_SURNUMERARIE)))
            .andExpect(jsonPath("$.[*].agenesiePouce").value(hasItem(DEFAULT_AGENESIE_POUCE)))
            .andExpect(jsonPath("$.[*].bifide").value(hasItem(DEFAULT_BIFIDE)))
            .andExpect(jsonPath("$.[*].hypoPouce").value(hasItem(DEFAULT_HYPO_POUCE)))
            .andExpect(jsonPath("$.[*].aspectPouce").value(hasItem(DEFAULT_ASPECT_POUCE)))
            .andExpect(jsonPath("$.[*].hypoEminence").value(hasItem(DEFAULT_HYPO_EMINENCE)))
            .andExpect(jsonPath("$.[*].absenceRadial").value(hasItem(DEFAULT_ABSENCE_RADIAL)))
            .andExpect(jsonPath("$.[*].pouceBas").value(hasItem(DEFAULT_POUCE_BAS)))
            .andExpect(jsonPath("$.[*].autreAnomPouce").value(hasItem(DEFAULT_AUTRE_ANOM_POUCE)))
            .andExpect(jsonPath("$.[*].anomAutDoigts").value(hasItem(DEFAULT_ANOM_AUT_DOIGTS)))
            .andExpect(jsonPath("$.[*].courtstrapus").value(hasItem(DEFAULT_COURTSTRAPUS)))
            .andExpect(jsonPath("$.[*].syndactylie").value(hasItem(DEFAULT_SYNDACTYLIE)))
            .andExpect(jsonPath("$.[*].autreAnomDoigts").value(hasItem(DEFAULT_AUTRE_ANOM_DOIGTS)))
            .andExpect(jsonPath("$.[*].anomalieos").value(hasItem(DEFAULT_ANOMALIEOS)))
            .andExpect(jsonPath("$.[*].agenesieRadius").value(hasItem(DEFAULT_AGENESIE_RADIUS)))
            .andExpect(jsonPath("$.[*].autreanomalieMembresup").value(hasItem(DEFAULT_AUTREANOMALIE_MEMBRESUP)))
            .andExpect(jsonPath("$.[*].anomOrteil").value(hasItem(DEFAULT_ANOM_ORTEIL)))
            .andExpect(jsonPath("$.[*].preciseAnomOrt").value(hasItem(DEFAULT_PRECISE_ANOM_ORT)))
            .andExpect(jsonPath("$.[*].lCH").value(hasItem(DEFAULT_L_CH)))
            .andExpect(jsonPath("$.[*].autreanomalieMembreinf").value(hasItem(DEFAULT_AUTREANOMALIE_MEMBREINF)))
            .andExpect(jsonPath("$.[*].anomRachis").value(hasItem(DEFAULT_ANOM_RACHIS)))
            .andExpect(jsonPath("$.[*].preciseAnomRac").value(hasItem(DEFAULT_PRECISE_ANOM_RAC)))
            .andExpect(jsonPath("$.[*].autreAnomOss").value(hasItem(DEFAULT_AUTRE_ANOM_OSS)))
            .andExpect(jsonPath("$.[*].anomNeuro").value(hasItem(DEFAULT_ANOM_NEURO)))
            .andExpect(jsonPath("$.[*].retardMent").value(hasItem(DEFAULT_RETARD_MENT)))
            .andExpect(jsonPath("$.[*].hypoacousie").value(hasItem(DEFAULT_HYPOACOUSIE)))
            .andExpect(jsonPath("$.[*].strabisme").value(hasItem(DEFAULT_STRABISME)))
            .andExpect(jsonPath("$.[*].performanceScolaire").value(hasItem(DEFAULT_PERFORMANCE_SCOLAIRE)))
            .andExpect(jsonPath("$.[*].autreanomalieneurologique").value(hasItem(DEFAULT_AUTREANOMALIENEUROLOGIQUE)))
            .andExpect(jsonPath("$.[*].anomCardVas").value(hasItem(DEFAULT_ANOM_CARD_VAS)))
            .andExpect(jsonPath("$.[*].echoCoeur").value(hasItem(DEFAULT_ECHO_COEUR)))
            .andExpect(jsonPath("$.[*].preciseAnomCardio").value(hasItem(DEFAULT_PRECISE_ANOM_CARDIO)))
            .andExpect(jsonPath("$.[*].anomDig").value(hasItem(DEFAULT_ANOM_DIG)))
            .andExpect(jsonPath("$.[*].preciseAnomDig").value(hasItem(DEFAULT_PRECISE_ANOM_DIG)))
            .andExpect(jsonPath("$.[*].endocrinopathie").value(hasItem(DEFAULT_ENDOCRINOPATHIE)))
            .andExpect(jsonPath("$.[*].diabete").value(hasItem(DEFAULT_DIABETE)))
            .andExpect(jsonPath("$.[*].insulinoDep").value(hasItem(DEFAULT_INSULINO_DEP)))
            .andExpect(jsonPath("$.[*].hypothyroidie").value(hasItem(DEFAULT_HYPOTHYROIDIE)))
            .andExpect(jsonPath("$.[*].ageDecouverte").value(hasItem(DEFAULT_AGE_DECOUVERTE)))
            .andExpect(jsonPath("$.[*].autreEndocrinopathie").value(hasItem(DEFAULT_AUTRE_ENDOCRINOPATHIE)))
            .andExpect(jsonPath("$.[*].autreAnomCong").value(hasItem(DEFAULT_AUTRE_ANOM_CONG)))
            .andExpect(jsonPath("$.[*].dateNumSanguine").value(hasItem(DEFAULT_DATE_NUM_SANGUINE.toString())))
            .andExpect(jsonPath("$.[*].age").value(hasItem(DEFAULT_AGE)))
            .andExpect(jsonPath("$.[*].hb").value(hasItem(DEFAULT_HB.doubleValue())))
            .andExpect(jsonPath("$.[*].vGM").value(hasItem(DEFAULT_V_GM.doubleValue())))
            .andExpect(jsonPath("$.[*].retic").value(hasItem(DEFAULT_RETIC.doubleValue())))
            .andExpect(jsonPath("$.[*].leuco").value(hasItem(DEFAULT_LEUCO.doubleValue())))
            .andExpect(jsonPath("$.[*].pNN").value(hasItem(DEFAULT_P_NN.doubleValue())))
            .andExpect(jsonPath("$.[*].plq").value(hasItem(DEFAULT_PLQ.doubleValue())))
            .andExpect(jsonPath("$.[*].hbInf").value(hasItem(DEFAULT_HB_INF)))
            .andExpect(jsonPath("$.[*].plqInf").value(hasItem(DEFAULT_PLQ_INF)))
            .andExpect(jsonPath("$.[*].pNNInf").value(hasItem(DEFAULT_P_NN_INF)))
            .andExpect(jsonPath("$.[*].electrophoreseHb").value(hasItem(DEFAULT_ELECTROPHORESE_HB)))
            .andExpect(jsonPath("$.[*].hbf").value(hasItem(DEFAULT_HBF.doubleValue())))
            .andExpect(jsonPath("$.[*].myelogramme").value(hasItem(DEFAULT_MYELOGRAMME)))
            .andExpect(jsonPath("$.[*].cellularite").value(hasItem(DEFAULT_CELLULARITE)))
            .andExpect(jsonPath("$.[*].erythroblaste").value(hasItem(DEFAULT_ERYTHROBLASTE.doubleValue())))
            .andExpect(jsonPath("$.[*].morphologieEryth").value(hasItem(DEFAULT_MORPHOLOGIE_ERYTH)))
            .andExpect(jsonPath("$.[*].morphologieGran").value(hasItem(DEFAULT_MORPHOLOGIE_GRAN)))
            .andExpect(jsonPath("$.[*].morphologieMega").value(hasItem(DEFAULT_MORPHOLOGIE_MEGA)))
            .andExpect(jsonPath("$.[*].granuleux").value(hasItem(DEFAULT_GRANULEUX.doubleValue())))
            .andExpect(jsonPath("$.[*].dysmyelopoiese").value(hasItem(DEFAULT_DYSMYELOPOIESE)))
            .andExpect(jsonPath("$.[*].megacaryocytes").value(hasItem(DEFAULT_MEGACARYOCYTES)))
            .andExpect(jsonPath("$.[*].blaste").value(hasItem(DEFAULT_BLASTE.doubleValue())))
            .andExpect(jsonPath("$.[*].excesblastes").value(hasItem(DEFAULT_EXCESBLASTES)))
            .andExpect(jsonPath("$.[*].bOM").value(hasItem(DEFAULT_B_OM)))
            .andExpect(jsonPath("$.[*].adipocytes").value(hasItem(DEFAULT_ADIPOCYTES.doubleValue())))
            .andExpect(jsonPath("$.[*].ubiquitination").value(hasItem(DEFAULT_UBIQUITINATION)))
            .andExpect(jsonPath("$.[*].resUbiquitination").value(hasItem(DEFAULT_RES_UBIQUITINATION)))
            .andExpect(jsonPath("$.[*].groupComp").value(hasItem(DEFAULT_GROUP_COMP)))
            .andExpect(jsonPath("$.[*].mutationFANC").value(hasItem(DEFAULT_MUTATION_FANC)))
            .andExpect(jsonPath("$.[*].congelationCellule").value(hasItem(DEFAULT_CONGELATION_CELLULE)))
            .andExpect(jsonPath("$.[*].labo").value(hasItem(DEFAULT_LABO)))
            .andExpect(jsonPath("$.[*].typePrelevement").value(hasItem(DEFAULT_TYPE_PRELEVEMENT)))
            .andExpect(jsonPath("$.[*].scoreClinique").value(hasItem(DEFAULT_SCORE_CLINIQUE)))
            .andExpect(jsonPath("$.[*].scoreEBMT").value(hasItem(DEFAULT_SCORE_EBMT)))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE)))
            .andExpect(jsonPath("$.[*].transfusion").value(hasItem(DEFAULT_TRANSFUSION)))
            .andExpect(jsonPath("$.[*].ageTransf").value(hasItem(DEFAULT_AGE_TRANSF)))
            .andExpect(jsonPath("$.[*].delaiDiag").value(hasItem(DEFAULT_DELAI_DIAG.doubleValue())))
            .andExpect(jsonPath("$.[*].nbCG").value(hasItem(DEFAULT_NB_CG)))
            .andExpect(jsonPath("$.[*].nbCP").value(hasItem(DEFAULT_NB_CP)))
            .andExpect(jsonPath("$.[*].chelationFer").value(hasItem(DEFAULT_CHELATION_FER)))
            .andExpect(jsonPath("$.[*].chelateur").value(hasItem(DEFAULT_CHELATEUR)))
            .andExpect(jsonPath("$.[*].nilevar").value(hasItem(DEFAULT_NILEVAR)))
            .andExpect(jsonPath("$.[*].danatrol").value(hasItem(DEFAULT_DANATROL)))
            .andExpect(jsonPath("$.[*].oxynethadone").value(hasItem(DEFAULT_OXYNETHADONE)))
            .andExpect(jsonPath("$.[*].androtordyl").value(hasItem(DEFAULT_ANDROTORDYL)))
            .andExpect(jsonPath("$.[*].autreAndrogene").value(hasItem(DEFAULT_AUTRE_ANDROGENE)))
            .andExpect(jsonPath("$.[*].androDebut").value(hasItem(DEFAULT_ANDRO_DEBUT.toString())))
            .andExpect(jsonPath("$.[*].androFin").value(hasItem(DEFAULT_ANDRO_FIN.toString())))
            .andExpect(jsonPath("$.[*].observance").value(hasItem(DEFAULT_OBSERVANCE)))
            .andExpect(jsonPath("$.[*].toxicite").value(hasItem(DEFAULT_TOXICITE)))
            .andExpect(jsonPath("$.[*].autreToxicite").value(hasItem(DEFAULT_AUTRE_TOXICITE)))
            .andExpect(jsonPath("$.[*].enqueteHLA").value(hasItem(DEFAULT_ENQUETE_HLA)))
            .andExpect(jsonPath("$.[*].pourquoiEnqHLA").value(hasItem(DEFAULT_POURQUOI_ENQ_HLA)))
            .andExpect(jsonPath("$.[*].nbFratTyp").value(hasItem(DEFAULT_NB_FRAT_TYP)))
            .andExpect(jsonPath("$.[*].nbFratNTyp").value(hasItem(DEFAULT_NB_FRAT_N_TYP)))
            .andExpect(jsonPath("$.[*].donneurComp").value(hasItem(DEFAULT_DONNEUR_COMP)))
            .andExpect(jsonPath("$.[*].preciseDonneur").value(hasItem(DEFAULT_PRECISE_DONNEUR)))
            .andExpect(jsonPath("$.[*].donneur").value(hasItem(DEFAULT_DONNEUR)))
            .andExpect(jsonPath("$.[*].greffeFaite").value(hasItem(DEFAULT_GREFFE_FAITE)))
            .andExpect(jsonPath("$.[*].delaiRappDiag").value(hasItem(DEFAULT_DELAI_RAPP_DIAG.doubleValue())))
            .andExpect(jsonPath("$.[*].pourquoiNfaite").value(hasItem(DEFAULT_POURQUOI_NFAITE)))
            .andExpect(jsonPath("$.[*].cyclophosphamide").value(hasItem(DEFAULT_CYCLOPHOSPHAMIDE)))
            .andExpect(jsonPath("$.[*].fludarabine").value(hasItem(DEFAULT_FLUDARABINE)))
            .andExpect(jsonPath("$.[*].busulfan").value(hasItem(DEFAULT_BUSULFAN)))
            .andExpect(jsonPath("$.[*].doseCyclo").value(hasItem(DEFAULT_DOSE_CYCLO.doubleValue())))
            .andExpect(jsonPath("$.[*].doseFlu").value(hasItem(DEFAULT_DOSE_FLU.doubleValue())))
            .andExpect(jsonPath("$.[*].doseBus").value(hasItem(DEFAULT_DOSE_BUS.doubleValue())))
            .andExpect(jsonPath("$.[*].autreConditionnement").value(hasItem(DEFAULT_AUTRE_CONDITIONNEMENT)))
            .andExpect(jsonPath("$.[*].irradiation").value(hasItem(DEFAULT_IRRADIATION)))
            .andExpect(jsonPath("$.[*].doseTotaleIrr").value(hasItem(DEFAULT_DOSE_TOTALE_IRR.doubleValue())))
            .andExpect(jsonPath("$.[*].serotherapie").value(hasItem(DEFAULT_SEROTHERAPIE)))
            .andExpect(jsonPath("$.[*].sAL").value(hasItem(DEFAULT_S_AL)))
            .andExpect(jsonPath("$.[*].doseSAL").value(hasItem(DEFAULT_DOSE_SAL.doubleValue())))
            .andExpect(jsonPath("$.[*].sourceCellule").value(hasItem(DEFAULT_SOURCE_CELLULE)))
            .andExpect(jsonPath("$.[*].sortieAplasie").value(hasItem(DEFAULT_SORTIE_APLASIE)))
            .andExpect(jsonPath("$.[*].gradeaGvH").value(hasItem(DEFAULT_GRADEA_GV_H)))
            .andExpect(jsonPath("$.[*].cGvH").value(hasItem(DEFAULT_C_GV_H)))
            .andExpect(jsonPath("$.[*].mVO").value(hasItem(DEFAULT_M_VO)))
            .andExpect(jsonPath("$.[*].complicationPulmonaire").value(hasItem(DEFAULT_COMPLICATION_PULMONAIRE)))
            .andExpect(jsonPath("$.[*].preciseCompPulm").value(hasItem(DEFAULT_PRECISE_COMP_PULM)))
            .andExpect(jsonPath("$.[*].survieJ100").value(hasItem(DEFAULT_SURVIE_J_100)))
            .andExpect(jsonPath("$.[*].sMD").value(hasItem(DEFAULT_S_MD)))
            .andExpect(jsonPath("$.[*].ageDiagSMD").value(hasItem(DEFAULT_AGE_DIAG_SMD)))
            .andExpect(jsonPath("$.[*].critereDiagSMD").value(hasItem(DEFAULT_CRITERE_DIAG_SMD)))
            .andExpect(jsonPath("$.[*].traitementSMD").value(hasItem(DEFAULT_TRAITEMENT_SMD)))
            .andExpect(jsonPath("$.[*].lAM").value(hasItem(DEFAULT_L_AM)))
            .andExpect(jsonPath("$.[*].critereDiagLAM").value(hasItem(DEFAULT_CRITERE_DIAG_LAM)))
            .andExpect(jsonPath("$.[*].traitementLAM").value(hasItem(DEFAULT_TRAITEMENT_LAM)))
            .andExpect(jsonPath("$.[*].autresCancers").value(hasItem(DEFAULT_AUTRES_CANCERS)))
            .andExpect(jsonPath("$.[*].dDN").value(hasItem(DEFAULT_D_DN.toString())))
            .andExpect(jsonPath("$.[*].transformationAigue").value(hasItem(DEFAULT_TRANSFORMATION_AIGUE)))
            .andExpect(jsonPath("$.[*].ageDiagLA").value(hasItem(DEFAULT_AGE_DIAG_LA)))
            .andExpect(jsonPath("$.[*].traitementLA").value(hasItem(DEFAULT_TRAITEMENT_LA)))
            .andExpect(jsonPath("$.[*].cancerE").value(hasItem(DEFAULT_CANCER_E)))
            .andExpect(jsonPath("$.[*].localisation").value(hasItem(DEFAULT_LOCALISATION)))
            .andExpect(jsonPath("$.[*].typeHistologique").value(hasItem(DEFAULT_TYPE_HISTOLOGIQUE)))
            .andExpect(jsonPath("$.[*].traitementCancer").value(hasItem(DEFAULT_TRAITEMENT_CANCER)))
            .andExpect(jsonPath("$.[*].preciseTraitement").value(hasItem(DEFAULT_PRECISE_TRAITEMENT)))
            .andExpect(jsonPath("$.[*].evolutionCyto").value(hasItem(DEFAULT_EVOLUTION_CYTO)))
            .andExpect(jsonPath("$.[*].formuleChrom").value(hasItem(DEFAULT_FORMULE_CHROM)))
            .andExpect(jsonPath("$.[*].ageE").value(hasItem(DEFAULT_AGE_E)))
            .andExpect(jsonPath("$.[*].statut").value(hasItem(DEFAULT_STATUT)))
            .andExpect(jsonPath("$.[*].causeDeces").value(hasItem(DEFAULT_CAUSE_DECES)))
            .andExpect(jsonPath("$.[*].autreCauseD").value(hasItem(DEFAULT_AUTRE_CAUSE_D)))
            .andExpect(jsonPath("$.[*].survieGlobale").value(hasItem(DEFAULT_SURVIE_GLOBALE.doubleValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].dateMAJ").value(hasItem(DEFAULT_DATE_MAJ.toString())))
            .andExpect(jsonPath("$.[*].nombreTacheCafe").value(hasItem(DEFAULT_NOMBRE_TACHE_CAFE)))
            .andExpect(jsonPath("$.[*].nombreTacheHypo").value(hasItem(DEFAULT_NOMBRE_TACHE_HYPO)));
    }

    @Test
    @Transactional
    void getFiche() throws Exception {
        // Initialize the database
        insertedFiche = ficheRepository.saveAndFlush(fiche);

        // Get the fiche
        restFicheMockMvc
            .perform(get(ENTITY_API_URL_ID, fiche.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fiche.getId().intValue()))
            .andExpect(jsonPath("$.nDossier").value(DEFAULT_N_DOSSIER))
            .andExpect(jsonPath("$.dateDiagnostic").value(DEFAULT_DATE_DIAGNOSTIC.toString()))
            .andExpect(jsonPath("$.dateEnregistrement").value(DEFAULT_DATE_ENREGISTREMENT.toString()))
            .andExpect(jsonPath("$.medecin").value(DEFAULT_MEDECIN))
            .andExpect(jsonPath("$.hopital").value(DEFAULT_HOPITAL))
            .andExpect(jsonPath("$.service").value(DEFAULT_SERVICE))
            .andExpect(jsonPath("$.degConsang").value(DEFAULT_DEG_CONSANG))
            .andExpect(jsonPath("$.placeEnfant").value(DEFAULT_PLACE_ENFANT))
            .andExpect(jsonPath("$.nbVivant").value(DEFAULT_NB_VIVANT))
            .andExpect(jsonPath("$.nbMort").value(DEFAULT_NB_MORT))
            .andExpect(jsonPath("$.mortNe").value(DEFAULT_MORT_NE))
            .andExpect(jsonPath("$.avortement").value(DEFAULT_AVORTEMENT))
            .andExpect(jsonPath("$.cousin").value(DEFAULT_COUSIN))
            .andExpect(jsonPath("$.membre").value(DEFAULT_MEMBRE))
            .andExpect(jsonPath("$.arbregenealogiqueContentType").value(DEFAULT_ARBREGENEALOGIQUE_CONTENT_TYPE))
            .andExpect(jsonPath("$.arbregenealogique").value(Base64.getEncoder().encodeToString(DEFAULT_ARBREGENEALOGIQUE)))
            .andExpect(jsonPath("$.arbregenealogiquecancerContentType").value(DEFAULT_ARBREGENEALOGIQUECANCER_CONTENT_TYPE))
            .andExpect(jsonPath("$.arbregenealogiquecancer").value(Base64.getEncoder().encodeToString(DEFAULT_ARBREGENEALOGIQUECANCER)))
            .andExpect(jsonPath("$.syndromeAnemique").value(DEFAULT_SYNDROME_ANEMIQUE))
            .andExpect(jsonPath("$.syndromeHem").value(DEFAULT_SYNDROME_HEM))
            .andExpect(jsonPath("$.syndromeInf").value(DEFAULT_SYNDROME_INF))
            .andExpect(jsonPath("$.decouverteFort").value(DEFAULT_DECOUVERTE_FORT))
            .andExpect(jsonPath("$.enqueteFam").value(DEFAULT_ENQUETE_FAM))
            .andExpect(jsonPath("$.typeCancer").value(DEFAULT_TYPE_CANCER))
            .andExpect(jsonPath("$.cancer").value(DEFAULT_CANCER))
            .andExpect(jsonPath("$.tailleNaiss").value(DEFAULT_TAILLE_NAISS.doubleValue()))
            .andExpect(jsonPath("$.poidsNaiss").value(DEFAULT_POIDS_NAISS.doubleValue()))
            .andExpect(jsonPath("$.hypotrophie").value(DEFAULT_HYPOTROPHIE))
            .andExpect(jsonPath("$.troubleCroi").value(DEFAULT_TROUBLE_CROI))
            .andExpect(jsonPath("$.aAgeChDiag").value(DEFAULT_A_AGE_CH_DIAG))
            .andExpect(jsonPath("$.mAgeChDiag").value(DEFAULT_M_AGE_CH_DIAG))
            .andExpect(jsonPath("$.aAgeOssDiag").value(DEFAULT_A_AGE_OSS_DIAG))
            .andExpect(jsonPath("$.mAgeOssDiag").value(DEFAULT_M_AGE_OSS_DIAG))
            .andExpect(jsonPath("$.ageRetard").value(DEFAULT_AGE_RETARD))
            .andExpect(jsonPath("$.poids").value(DEFAULT_POIDS.doubleValue()))
            .andExpect(jsonPath("$.poidsDS").value(DEFAULT_POIDS_DS))
            .andExpect(jsonPath("$.taille").value(DEFAULT_TAILLE.doubleValue()))
            .andExpect(jsonPath("$.tailleDS").value(DEFAULT_TAILLE_DS))
            .andExpect(jsonPath("$.atteinteCut").value(DEFAULT_ATTEINTE_CUT))
            .andExpect(jsonPath("$.tacheCaf").value(DEFAULT_TACHE_CAF))
            .andExpect(jsonPath("$.ventre").value(DEFAULT_VENTRE))
            .andExpect(jsonPath("$.membreSup").value(DEFAULT_MEMBRE_SUP))
            .andExpect(jsonPath("$.membreInf").value(DEFAULT_MEMBRE_INF))
            .andExpect(jsonPath("$.visage").value(DEFAULT_VISAGE))
            .andExpect(jsonPath("$.thorax").value(DEFAULT_THORAX))
            .andExpect(jsonPath("$.dOS").value(DEFAULT_D_OS))
            .andExpect(jsonPath("$.hyperPig").value(DEFAULT_HYPER_PIG))
            .andExpect(jsonPath("$.hypochromique").value(DEFAULT_HYPOCHROMIQUE))
            .andExpect(jsonPath("$.couleurPeau").value(DEFAULT_COULEUR_PEAU))
            .andExpect(jsonPath("$.autreAtCut").value(DEFAULT_AUTRE_AT_CUT))
            .andExpect(jsonPath("$.atteinteTete").value(DEFAULT_ATTEINTE_TETE))
            .andExpect(jsonPath("$.microcephalie").value(DEFAULT_MICROCEPHALIE))
            .andExpect(jsonPath("$.microphtalmie").value(DEFAULT_MICROPHTALMIE))
            .andExpect(jsonPath("$.facieTrig").value(DEFAULT_FACIE_TRIG))
            .andExpect(jsonPath("$.traitsFin").value(DEFAULT_TRAITS_FIN))
            .andExpect(jsonPath("$.autreAtTete").value(DEFAULT_AUTRE_AT_TETE))
            .andExpect(jsonPath("$.empreintedigitiforme").value(DEFAULT_EMPREINTEDIGITIFORME))
            .andExpect(jsonPath("$.malUro").value(DEFAULT_MAL_URO))
            .andExpect(jsonPath("$.uIV").value(DEFAULT_U_IV))
            .andExpect(jsonPath("$.echo").value(DEFAULT_ECHO))
            .andExpect(jsonPath("$.reinEctop").value(DEFAULT_REIN_ECTOP))
            .andExpect(jsonPath("$.siegeEctopie").value(DEFAULT_SIEGE_ECTOPIE))
            .andExpect(jsonPath("$.reinFerChev").value(DEFAULT_REIN_FER_CHEV))
            .andExpect(jsonPath("$.petitRein").value(DEFAULT_PETIT_REIN))
            .andExpect(jsonPath("$.reinUnique").value(DEFAULT_REIN_UNIQUE))
            .andExpect(jsonPath("$.ectopTest").value(DEFAULT_ECTOP_TEST))
            .andExpect(jsonPath("$.vergeInsuf").value(DEFAULT_VERGE_INSUF))
            .andExpect(jsonPath("$.autreAnomVerge").value(DEFAULT_AUTRE_ANOM_VERGE))
            .andExpect(jsonPath("$.retardPubertaire").value(DEFAULT_RETARD_PUBERTAIRE))
            .andExpect(jsonPath("$.mTanner").value(DEFAULT_M_TANNER))
            .andExpect(jsonPath("$.pTanner").value(DEFAULT_P_TANNER))
            .andExpect(jsonPath("$.tTanner").value(DEFAULT_T_TANNER))
            .andExpect(jsonPath("$.anomUrin").value(DEFAULT_ANOM_URIN))
            .andExpect(jsonPath("$.typeAnomUrin").value(DEFAULT_TYPE_ANOM_URIN))
            .andExpect(jsonPath("$.atteinteOss").value(DEFAULT_ATTEINTE_OSS))
            .andExpect(jsonPath("$.radiosfaites").value(DEFAULT_RADIOSFAITES))
            .andExpect(jsonPath("$.anomPouce").value(DEFAULT_ANOM_POUCE))
            .andExpect(jsonPath("$.surnumerarie").value(DEFAULT_SURNUMERARIE))
            .andExpect(jsonPath("$.agenesiePouce").value(DEFAULT_AGENESIE_POUCE))
            .andExpect(jsonPath("$.bifide").value(DEFAULT_BIFIDE))
            .andExpect(jsonPath("$.hypoPouce").value(DEFAULT_HYPO_POUCE))
            .andExpect(jsonPath("$.aspectPouce").value(DEFAULT_ASPECT_POUCE))
            .andExpect(jsonPath("$.hypoEminence").value(DEFAULT_HYPO_EMINENCE))
            .andExpect(jsonPath("$.absenceRadial").value(DEFAULT_ABSENCE_RADIAL))
            .andExpect(jsonPath("$.pouceBas").value(DEFAULT_POUCE_BAS))
            .andExpect(jsonPath("$.autreAnomPouce").value(DEFAULT_AUTRE_ANOM_POUCE))
            .andExpect(jsonPath("$.anomAutDoigts").value(DEFAULT_ANOM_AUT_DOIGTS))
            .andExpect(jsonPath("$.courtstrapus").value(DEFAULT_COURTSTRAPUS))
            .andExpect(jsonPath("$.syndactylie").value(DEFAULT_SYNDACTYLIE))
            .andExpect(jsonPath("$.autreAnomDoigts").value(DEFAULT_AUTRE_ANOM_DOIGTS))
            .andExpect(jsonPath("$.anomalieos").value(DEFAULT_ANOMALIEOS))
            .andExpect(jsonPath("$.agenesieRadius").value(DEFAULT_AGENESIE_RADIUS))
            .andExpect(jsonPath("$.autreanomalieMembresup").value(DEFAULT_AUTREANOMALIE_MEMBRESUP))
            .andExpect(jsonPath("$.anomOrteil").value(DEFAULT_ANOM_ORTEIL))
            .andExpect(jsonPath("$.preciseAnomOrt").value(DEFAULT_PRECISE_ANOM_ORT))
            .andExpect(jsonPath("$.lCH").value(DEFAULT_L_CH))
            .andExpect(jsonPath("$.autreanomalieMembreinf").value(DEFAULT_AUTREANOMALIE_MEMBREINF))
            .andExpect(jsonPath("$.anomRachis").value(DEFAULT_ANOM_RACHIS))
            .andExpect(jsonPath("$.preciseAnomRac").value(DEFAULT_PRECISE_ANOM_RAC))
            .andExpect(jsonPath("$.autreAnomOss").value(DEFAULT_AUTRE_ANOM_OSS))
            .andExpect(jsonPath("$.anomNeuro").value(DEFAULT_ANOM_NEURO))
            .andExpect(jsonPath("$.retardMent").value(DEFAULT_RETARD_MENT))
            .andExpect(jsonPath("$.hypoacousie").value(DEFAULT_HYPOACOUSIE))
            .andExpect(jsonPath("$.strabisme").value(DEFAULT_STRABISME))
            .andExpect(jsonPath("$.performanceScolaire").value(DEFAULT_PERFORMANCE_SCOLAIRE))
            .andExpect(jsonPath("$.autreanomalieneurologique").value(DEFAULT_AUTREANOMALIENEUROLOGIQUE))
            .andExpect(jsonPath("$.anomCardVas").value(DEFAULT_ANOM_CARD_VAS))
            .andExpect(jsonPath("$.echoCoeur").value(DEFAULT_ECHO_COEUR))
            .andExpect(jsonPath("$.preciseAnomCardio").value(DEFAULT_PRECISE_ANOM_CARDIO))
            .andExpect(jsonPath("$.anomDig").value(DEFAULT_ANOM_DIG))
            .andExpect(jsonPath("$.preciseAnomDig").value(DEFAULT_PRECISE_ANOM_DIG))
            .andExpect(jsonPath("$.endocrinopathie").value(DEFAULT_ENDOCRINOPATHIE))
            .andExpect(jsonPath("$.diabete").value(DEFAULT_DIABETE))
            .andExpect(jsonPath("$.insulinoDep").value(DEFAULT_INSULINO_DEP))
            .andExpect(jsonPath("$.hypothyroidie").value(DEFAULT_HYPOTHYROIDIE))
            .andExpect(jsonPath("$.ageDecouverte").value(DEFAULT_AGE_DECOUVERTE))
            .andExpect(jsonPath("$.autreEndocrinopathie").value(DEFAULT_AUTRE_ENDOCRINOPATHIE))
            .andExpect(jsonPath("$.autreAnomCong").value(DEFAULT_AUTRE_ANOM_CONG))
            .andExpect(jsonPath("$.dateNumSanguine").value(DEFAULT_DATE_NUM_SANGUINE.toString()))
            .andExpect(jsonPath("$.age").value(DEFAULT_AGE))
            .andExpect(jsonPath("$.hb").value(DEFAULT_HB.doubleValue()))
            .andExpect(jsonPath("$.vGM").value(DEFAULT_V_GM.doubleValue()))
            .andExpect(jsonPath("$.retic").value(DEFAULT_RETIC.doubleValue()))
            .andExpect(jsonPath("$.leuco").value(DEFAULT_LEUCO.doubleValue()))
            .andExpect(jsonPath("$.pNN").value(DEFAULT_P_NN.doubleValue()))
            .andExpect(jsonPath("$.plq").value(DEFAULT_PLQ.doubleValue()))
            .andExpect(jsonPath("$.hbInf").value(DEFAULT_HB_INF))
            .andExpect(jsonPath("$.plqInf").value(DEFAULT_PLQ_INF))
            .andExpect(jsonPath("$.pNNInf").value(DEFAULT_P_NN_INF))
            .andExpect(jsonPath("$.electrophoreseHb").value(DEFAULT_ELECTROPHORESE_HB))
            .andExpect(jsonPath("$.hbf").value(DEFAULT_HBF.doubleValue()))
            .andExpect(jsonPath("$.myelogramme").value(DEFAULT_MYELOGRAMME))
            .andExpect(jsonPath("$.cellularite").value(DEFAULT_CELLULARITE))
            .andExpect(jsonPath("$.erythroblaste").value(DEFAULT_ERYTHROBLASTE.doubleValue()))
            .andExpect(jsonPath("$.morphologieEryth").value(DEFAULT_MORPHOLOGIE_ERYTH))
            .andExpect(jsonPath("$.morphologieGran").value(DEFAULT_MORPHOLOGIE_GRAN))
            .andExpect(jsonPath("$.morphologieMega").value(DEFAULT_MORPHOLOGIE_MEGA))
            .andExpect(jsonPath("$.granuleux").value(DEFAULT_GRANULEUX.doubleValue()))
            .andExpect(jsonPath("$.dysmyelopoiese").value(DEFAULT_DYSMYELOPOIESE))
            .andExpect(jsonPath("$.megacaryocytes").value(DEFAULT_MEGACARYOCYTES))
            .andExpect(jsonPath("$.blaste").value(DEFAULT_BLASTE.doubleValue()))
            .andExpect(jsonPath("$.excesblastes").value(DEFAULT_EXCESBLASTES))
            .andExpect(jsonPath("$.bOM").value(DEFAULT_B_OM))
            .andExpect(jsonPath("$.adipocytes").value(DEFAULT_ADIPOCYTES.doubleValue()))
            .andExpect(jsonPath("$.ubiquitination").value(DEFAULT_UBIQUITINATION))
            .andExpect(jsonPath("$.resUbiquitination").value(DEFAULT_RES_UBIQUITINATION))
            .andExpect(jsonPath("$.groupComp").value(DEFAULT_GROUP_COMP))
            .andExpect(jsonPath("$.mutationFANC").value(DEFAULT_MUTATION_FANC))
            .andExpect(jsonPath("$.congelationCellule").value(DEFAULT_CONGELATION_CELLULE))
            .andExpect(jsonPath("$.labo").value(DEFAULT_LABO))
            .andExpect(jsonPath("$.typePrelevement").value(DEFAULT_TYPE_PRELEVEMENT))
            .andExpect(jsonPath("$.scoreClinique").value(DEFAULT_SCORE_CLINIQUE))
            .andExpect(jsonPath("$.scoreEBMT").value(DEFAULT_SCORE_EBMT))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE))
            .andExpect(jsonPath("$.transfusion").value(DEFAULT_TRANSFUSION))
            .andExpect(jsonPath("$.ageTransf").value(DEFAULT_AGE_TRANSF))
            .andExpect(jsonPath("$.delaiDiag").value(DEFAULT_DELAI_DIAG.doubleValue()))
            .andExpect(jsonPath("$.nbCG").value(DEFAULT_NB_CG))
            .andExpect(jsonPath("$.nbCP").value(DEFAULT_NB_CP))
            .andExpect(jsonPath("$.chelationFer").value(DEFAULT_CHELATION_FER))
            .andExpect(jsonPath("$.chelateur").value(DEFAULT_CHELATEUR))
            .andExpect(jsonPath("$.nilevar").value(DEFAULT_NILEVAR))
            .andExpect(jsonPath("$.danatrol").value(DEFAULT_DANATROL))
            .andExpect(jsonPath("$.oxynethadone").value(DEFAULT_OXYNETHADONE))
            .andExpect(jsonPath("$.androtordyl").value(DEFAULT_ANDROTORDYL))
            .andExpect(jsonPath("$.autreAndrogene").value(DEFAULT_AUTRE_ANDROGENE))
            .andExpect(jsonPath("$.androDebut").value(DEFAULT_ANDRO_DEBUT.toString()))
            .andExpect(jsonPath("$.androFin").value(DEFAULT_ANDRO_FIN.toString()))
            .andExpect(jsonPath("$.observance").value(DEFAULT_OBSERVANCE))
            .andExpect(jsonPath("$.toxicite").value(DEFAULT_TOXICITE))
            .andExpect(jsonPath("$.autreToxicite").value(DEFAULT_AUTRE_TOXICITE))
            .andExpect(jsonPath("$.enqueteHLA").value(DEFAULT_ENQUETE_HLA))
            .andExpect(jsonPath("$.pourquoiEnqHLA").value(DEFAULT_POURQUOI_ENQ_HLA))
            .andExpect(jsonPath("$.nbFratTyp").value(DEFAULT_NB_FRAT_TYP))
            .andExpect(jsonPath("$.nbFratNTyp").value(DEFAULT_NB_FRAT_N_TYP))
            .andExpect(jsonPath("$.donneurComp").value(DEFAULT_DONNEUR_COMP))
            .andExpect(jsonPath("$.preciseDonneur").value(DEFAULT_PRECISE_DONNEUR))
            .andExpect(jsonPath("$.donneur").value(DEFAULT_DONNEUR))
            .andExpect(jsonPath("$.greffeFaite").value(DEFAULT_GREFFE_FAITE))
            .andExpect(jsonPath("$.delaiRappDiag").value(DEFAULT_DELAI_RAPP_DIAG.doubleValue()))
            .andExpect(jsonPath("$.pourquoiNfaite").value(DEFAULT_POURQUOI_NFAITE))
            .andExpect(jsonPath("$.cyclophosphamide").value(DEFAULT_CYCLOPHOSPHAMIDE))
            .andExpect(jsonPath("$.fludarabine").value(DEFAULT_FLUDARABINE))
            .andExpect(jsonPath("$.busulfan").value(DEFAULT_BUSULFAN))
            .andExpect(jsonPath("$.doseCyclo").value(DEFAULT_DOSE_CYCLO.doubleValue()))
            .andExpect(jsonPath("$.doseFlu").value(DEFAULT_DOSE_FLU.doubleValue()))
            .andExpect(jsonPath("$.doseBus").value(DEFAULT_DOSE_BUS.doubleValue()))
            .andExpect(jsonPath("$.autreConditionnement").value(DEFAULT_AUTRE_CONDITIONNEMENT))
            .andExpect(jsonPath("$.irradiation").value(DEFAULT_IRRADIATION))
            .andExpect(jsonPath("$.doseTotaleIrr").value(DEFAULT_DOSE_TOTALE_IRR.doubleValue()))
            .andExpect(jsonPath("$.serotherapie").value(DEFAULT_SEROTHERAPIE))
            .andExpect(jsonPath("$.sAL").value(DEFAULT_S_AL))
            .andExpect(jsonPath("$.doseSAL").value(DEFAULT_DOSE_SAL.doubleValue()))
            .andExpect(jsonPath("$.sourceCellule").value(DEFAULT_SOURCE_CELLULE))
            .andExpect(jsonPath("$.sortieAplasie").value(DEFAULT_SORTIE_APLASIE))
            .andExpect(jsonPath("$.gradeaGvH").value(DEFAULT_GRADEA_GV_H))
            .andExpect(jsonPath("$.cGvH").value(DEFAULT_C_GV_H))
            .andExpect(jsonPath("$.mVO").value(DEFAULT_M_VO))
            .andExpect(jsonPath("$.complicationPulmonaire").value(DEFAULT_COMPLICATION_PULMONAIRE))
            .andExpect(jsonPath("$.preciseCompPulm").value(DEFAULT_PRECISE_COMP_PULM))
            .andExpect(jsonPath("$.survieJ100").value(DEFAULT_SURVIE_J_100))
            .andExpect(jsonPath("$.sMD").value(DEFAULT_S_MD))
            .andExpect(jsonPath("$.ageDiagSMD").value(DEFAULT_AGE_DIAG_SMD))
            .andExpect(jsonPath("$.critereDiagSMD").value(DEFAULT_CRITERE_DIAG_SMD))
            .andExpect(jsonPath("$.traitementSMD").value(DEFAULT_TRAITEMENT_SMD))
            .andExpect(jsonPath("$.lAM").value(DEFAULT_L_AM))
            .andExpect(jsonPath("$.critereDiagLAM").value(DEFAULT_CRITERE_DIAG_LAM))
            .andExpect(jsonPath("$.traitementLAM").value(DEFAULT_TRAITEMENT_LAM))
            .andExpect(jsonPath("$.autresCancers").value(DEFAULT_AUTRES_CANCERS))
            .andExpect(jsonPath("$.dDN").value(DEFAULT_D_DN.toString()))
            .andExpect(jsonPath("$.transformationAigue").value(DEFAULT_TRANSFORMATION_AIGUE))
            .andExpect(jsonPath("$.ageDiagLA").value(DEFAULT_AGE_DIAG_LA))
            .andExpect(jsonPath("$.traitementLA").value(DEFAULT_TRAITEMENT_LA))
            .andExpect(jsonPath("$.cancerE").value(DEFAULT_CANCER_E))
            .andExpect(jsonPath("$.localisation").value(DEFAULT_LOCALISATION))
            .andExpect(jsonPath("$.typeHistologique").value(DEFAULT_TYPE_HISTOLOGIQUE))
            .andExpect(jsonPath("$.traitementCancer").value(DEFAULT_TRAITEMENT_CANCER))
            .andExpect(jsonPath("$.preciseTraitement").value(DEFAULT_PRECISE_TRAITEMENT))
            .andExpect(jsonPath("$.evolutionCyto").value(DEFAULT_EVOLUTION_CYTO))
            .andExpect(jsonPath("$.formuleChrom").value(DEFAULT_FORMULE_CHROM))
            .andExpect(jsonPath("$.ageE").value(DEFAULT_AGE_E))
            .andExpect(jsonPath("$.statut").value(DEFAULT_STATUT))
            .andExpect(jsonPath("$.causeDeces").value(DEFAULT_CAUSE_DECES))
            .andExpect(jsonPath("$.autreCauseD").value(DEFAULT_AUTRE_CAUSE_D))
            .andExpect(jsonPath("$.survieGlobale").value(DEFAULT_SURVIE_GLOBALE.doubleValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.dateMAJ").value(DEFAULT_DATE_MAJ.toString()))
            .andExpect(jsonPath("$.nombreTacheCafe").value(DEFAULT_NOMBRE_TACHE_CAFE))
            .andExpect(jsonPath("$.nombreTacheHypo").value(DEFAULT_NOMBRE_TACHE_HYPO));
    }

    @Test
    @Transactional
    void getNonExistingFiche() throws Exception {
        // Get the fiche
        restFicheMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingFiche() throws Exception {
        // Initialize the database
        insertedFiche = ficheRepository.saveAndFlush(fiche);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fiche
        Fiche updatedFiche = ficheRepository.findById(fiche.getId()).orElseThrow();
        // Disconnect from session so that the updates on updatedFiche are not directly saved in db
        em.detach(updatedFiche);
        updatedFiche
            .nDossier(UPDATED_N_DOSSIER)
            .dateDiagnostic(UPDATED_DATE_DIAGNOSTIC)
            .dateEnregistrement(UPDATED_DATE_ENREGISTREMENT)
            .medecin(UPDATED_MEDECIN)
            .hopital(UPDATED_HOPITAL)
            .service(UPDATED_SERVICE)
            .degConsang(UPDATED_DEG_CONSANG)
            .placeEnfant(UPDATED_PLACE_ENFANT)
            .nbVivant(UPDATED_NB_VIVANT)
            .nbMort(UPDATED_NB_MORT)
            .mortNe(UPDATED_MORT_NE)
            .avortement(UPDATED_AVORTEMENT)
            .cousin(UPDATED_COUSIN)
            .membre(UPDATED_MEMBRE)
            .arbregenealogique(UPDATED_ARBREGENEALOGIQUE)
            .arbregenealogiqueContentType(UPDATED_ARBREGENEALOGIQUE_CONTENT_TYPE)
            .arbregenealogiquecancer(UPDATED_ARBREGENEALOGIQUECANCER)
            .arbregenealogiquecancerContentType(UPDATED_ARBREGENEALOGIQUECANCER_CONTENT_TYPE)
            .syndromeAnemique(UPDATED_SYNDROME_ANEMIQUE)
            .syndromeHem(UPDATED_SYNDROME_HEM)
            .syndromeInf(UPDATED_SYNDROME_INF)
            .decouverteFort(UPDATED_DECOUVERTE_FORT)
            .enqueteFam(UPDATED_ENQUETE_FAM)
            .typeCancer(UPDATED_TYPE_CANCER)
            .cancer(UPDATED_CANCER)
            .tailleNaiss(UPDATED_TAILLE_NAISS)
            .poidsNaiss(UPDATED_POIDS_NAISS)
            .hypotrophie(UPDATED_HYPOTROPHIE)
            .troubleCroi(UPDATED_TROUBLE_CROI)
            .aAgeChDiag(UPDATED_A_AGE_CH_DIAG)
            .mAgeChDiag(UPDATED_M_AGE_CH_DIAG)
            .aAgeOssDiag(UPDATED_A_AGE_OSS_DIAG)
            .mAgeOssDiag(UPDATED_M_AGE_OSS_DIAG)
            .ageRetard(UPDATED_AGE_RETARD)
            .poids(UPDATED_POIDS)
            .poidsDS(UPDATED_POIDS_DS)
            .taille(UPDATED_TAILLE)
            .tailleDS(UPDATED_TAILLE_DS)
            .atteinteCut(UPDATED_ATTEINTE_CUT)
            .tacheCaf(UPDATED_TACHE_CAF)
            .ventre(UPDATED_VENTRE)
            .membreSup(UPDATED_MEMBRE_SUP)
            .membreInf(UPDATED_MEMBRE_INF)
            .visage(UPDATED_VISAGE)
            .thorax(UPDATED_THORAX)
            .dOS(UPDATED_D_OS)
            .hyperPig(UPDATED_HYPER_PIG)
            .hypochromique(UPDATED_HYPOCHROMIQUE)
            .couleurPeau(UPDATED_COULEUR_PEAU)
            .autreAtCut(UPDATED_AUTRE_AT_CUT)
            .atteinteTete(UPDATED_ATTEINTE_TETE)
            .microcephalie(UPDATED_MICROCEPHALIE)
            .microphtalmie(UPDATED_MICROPHTALMIE)
            .facieTrig(UPDATED_FACIE_TRIG)
            .traitsFin(UPDATED_TRAITS_FIN)
            .autreAtTete(UPDATED_AUTRE_AT_TETE)
            .empreintedigitiforme(UPDATED_EMPREINTEDIGITIFORME)
            .malUro(UPDATED_MAL_URO)
            .uIV(UPDATED_U_IV)
            .echo(UPDATED_ECHO)
            .reinEctop(UPDATED_REIN_ECTOP)
            .siegeEctopie(UPDATED_SIEGE_ECTOPIE)
            .reinFerChev(UPDATED_REIN_FER_CHEV)
            .petitRein(UPDATED_PETIT_REIN)
            .reinUnique(UPDATED_REIN_UNIQUE)
            .ectopTest(UPDATED_ECTOP_TEST)
            .vergeInsuf(UPDATED_VERGE_INSUF)
            .autreAnomVerge(UPDATED_AUTRE_ANOM_VERGE)
            .retardPubertaire(UPDATED_RETARD_PUBERTAIRE)
            .mTanner(UPDATED_M_TANNER)
            .pTanner(UPDATED_P_TANNER)
            .tTanner(UPDATED_T_TANNER)
            .anomUrin(UPDATED_ANOM_URIN)
            .typeAnomUrin(UPDATED_TYPE_ANOM_URIN)
            .atteinteOss(UPDATED_ATTEINTE_OSS)
            .radiosfaites(UPDATED_RADIOSFAITES)
            .anomPouce(UPDATED_ANOM_POUCE)
            .surnumerarie(UPDATED_SURNUMERARIE)
            .agenesiePouce(UPDATED_AGENESIE_POUCE)
            .bifide(UPDATED_BIFIDE)
            .hypoPouce(UPDATED_HYPO_POUCE)
            .aspectPouce(UPDATED_ASPECT_POUCE)
            .hypoEminence(UPDATED_HYPO_EMINENCE)
            .absenceRadial(UPDATED_ABSENCE_RADIAL)
            .pouceBas(UPDATED_POUCE_BAS)
            .autreAnomPouce(UPDATED_AUTRE_ANOM_POUCE)
            .anomAutDoigts(UPDATED_ANOM_AUT_DOIGTS)
            .courtstrapus(UPDATED_COURTSTRAPUS)
            .syndactylie(UPDATED_SYNDACTYLIE)
            .autreAnomDoigts(UPDATED_AUTRE_ANOM_DOIGTS)
            .anomalieos(UPDATED_ANOMALIEOS)
            .agenesieRadius(UPDATED_AGENESIE_RADIUS)
            .autreanomalieMembresup(UPDATED_AUTREANOMALIE_MEMBRESUP)
            .anomOrteil(UPDATED_ANOM_ORTEIL)
            .preciseAnomOrt(UPDATED_PRECISE_ANOM_ORT)
            .lCH(UPDATED_L_CH)
            .autreanomalieMembreinf(UPDATED_AUTREANOMALIE_MEMBREINF)
            .anomRachis(UPDATED_ANOM_RACHIS)
            .preciseAnomRac(UPDATED_PRECISE_ANOM_RAC)
            .autreAnomOss(UPDATED_AUTRE_ANOM_OSS)
            .anomNeuro(UPDATED_ANOM_NEURO)
            .retardMent(UPDATED_RETARD_MENT)
            .hypoacousie(UPDATED_HYPOACOUSIE)
            .strabisme(UPDATED_STRABISME)
            .performanceScolaire(UPDATED_PERFORMANCE_SCOLAIRE)
            .autreanomalieneurologique(UPDATED_AUTREANOMALIENEUROLOGIQUE)
            .anomCardVas(UPDATED_ANOM_CARD_VAS)
            .echoCoeur(UPDATED_ECHO_COEUR)
            .preciseAnomCardio(UPDATED_PRECISE_ANOM_CARDIO)
            .anomDig(UPDATED_ANOM_DIG)
            .preciseAnomDig(UPDATED_PRECISE_ANOM_DIG)
            .endocrinopathie(UPDATED_ENDOCRINOPATHIE)
            .diabete(UPDATED_DIABETE)
            .insulinoDep(UPDATED_INSULINO_DEP)
            .hypothyroidie(UPDATED_HYPOTHYROIDIE)
            .ageDecouverte(UPDATED_AGE_DECOUVERTE)
            .autreEndocrinopathie(UPDATED_AUTRE_ENDOCRINOPATHIE)
            .autreAnomCong(UPDATED_AUTRE_ANOM_CONG)
            .dateNumSanguine(UPDATED_DATE_NUM_SANGUINE)
            .age(UPDATED_AGE)
            .hb(UPDATED_HB)
            .vGM(UPDATED_V_GM)
            .retic(UPDATED_RETIC)
            .leuco(UPDATED_LEUCO)
            .pNN(UPDATED_P_NN)
            .plq(UPDATED_PLQ)
            .hbInf(UPDATED_HB_INF)
            .plqInf(UPDATED_PLQ_INF)
            .pNNInf(UPDATED_P_NN_INF)
            .electrophoreseHb(UPDATED_ELECTROPHORESE_HB)
            .hbf(UPDATED_HBF)
            .myelogramme(UPDATED_MYELOGRAMME)
            .cellularite(UPDATED_CELLULARITE)
            .erythroblaste(UPDATED_ERYTHROBLASTE)
            .morphologieEryth(UPDATED_MORPHOLOGIE_ERYTH)
            .morphologieGran(UPDATED_MORPHOLOGIE_GRAN)
            .morphologieMega(UPDATED_MORPHOLOGIE_MEGA)
            .granuleux(UPDATED_GRANULEUX)
            .dysmyelopoiese(UPDATED_DYSMYELOPOIESE)
            .megacaryocytes(UPDATED_MEGACARYOCYTES)
            .blaste(UPDATED_BLASTE)
            .excesblastes(UPDATED_EXCESBLASTES)
            .bOM(UPDATED_B_OM)
            .adipocytes(UPDATED_ADIPOCYTES)
            .ubiquitination(UPDATED_UBIQUITINATION)
            .resUbiquitination(UPDATED_RES_UBIQUITINATION)
            .groupComp(UPDATED_GROUP_COMP)
            .mutationFANC(UPDATED_MUTATION_FANC)
            .congelationCellule(UPDATED_CONGELATION_CELLULE)
            .labo(UPDATED_LABO)
            .typePrelevement(UPDATED_TYPE_PRELEVEMENT)
            .scoreClinique(UPDATED_SCORE_CLINIQUE)
            .scoreEBMT(UPDATED_SCORE_EBMT)
            .score(UPDATED_SCORE)
            .transfusion(UPDATED_TRANSFUSION)
            .ageTransf(UPDATED_AGE_TRANSF)
            .delaiDiag(UPDATED_DELAI_DIAG)
            .nbCG(UPDATED_NB_CG)
            .nbCP(UPDATED_NB_CP)
            .chelationFer(UPDATED_CHELATION_FER)
            .chelateur(UPDATED_CHELATEUR)
            .nilevar(UPDATED_NILEVAR)
            .danatrol(UPDATED_DANATROL)
            .oxynethadone(UPDATED_OXYNETHADONE)
            .androtordyl(UPDATED_ANDROTORDYL)
            .autreAndrogene(UPDATED_AUTRE_ANDROGENE)
            .androDebut(UPDATED_ANDRO_DEBUT)
            .androFin(UPDATED_ANDRO_FIN)
            .observance(UPDATED_OBSERVANCE)
            .toxicite(UPDATED_TOXICITE)
            .autreToxicite(UPDATED_AUTRE_TOXICITE)
            .enqueteHLA(UPDATED_ENQUETE_HLA)
            .pourquoiEnqHLA(UPDATED_POURQUOI_ENQ_HLA)
            .nbFratTyp(UPDATED_NB_FRAT_TYP)
            .nbFratNTyp(UPDATED_NB_FRAT_N_TYP)
            .donneurComp(UPDATED_DONNEUR_COMP)
            .preciseDonneur(UPDATED_PRECISE_DONNEUR)
            .donneur(UPDATED_DONNEUR)
            .greffeFaite(UPDATED_GREFFE_FAITE)
            .delaiRappDiag(UPDATED_DELAI_RAPP_DIAG)
            .pourquoiNfaite(UPDATED_POURQUOI_NFAITE)
            .cyclophosphamide(UPDATED_CYCLOPHOSPHAMIDE)
            .fludarabine(UPDATED_FLUDARABINE)
            .busulfan(UPDATED_BUSULFAN)
            .doseCyclo(UPDATED_DOSE_CYCLO)
            .doseFlu(UPDATED_DOSE_FLU)
            .doseBus(UPDATED_DOSE_BUS)
            .autreConditionnement(UPDATED_AUTRE_CONDITIONNEMENT)
            .irradiation(UPDATED_IRRADIATION)
            .doseTotaleIrr(UPDATED_DOSE_TOTALE_IRR)
            .serotherapie(UPDATED_SEROTHERAPIE)
            .sAL(UPDATED_S_AL)
            .doseSAL(UPDATED_DOSE_SAL)
            .sourceCellule(UPDATED_SOURCE_CELLULE)
            .sortieAplasie(UPDATED_SORTIE_APLASIE)
            .gradeaGvH(UPDATED_GRADEA_GV_H)
            .cGvH(UPDATED_C_GV_H)
            .mVO(UPDATED_M_VO)
            .complicationPulmonaire(UPDATED_COMPLICATION_PULMONAIRE)
            .preciseCompPulm(UPDATED_PRECISE_COMP_PULM)
            .survieJ100(UPDATED_SURVIE_J_100)
            .sMD(UPDATED_S_MD)
            .ageDiagSMD(UPDATED_AGE_DIAG_SMD)
            .critereDiagSMD(UPDATED_CRITERE_DIAG_SMD)
            .traitementSMD(UPDATED_TRAITEMENT_SMD)
            .lAM(UPDATED_L_AM)
            .critereDiagLAM(UPDATED_CRITERE_DIAG_LAM)
            .traitementLAM(UPDATED_TRAITEMENT_LAM)
            .autresCancers(UPDATED_AUTRES_CANCERS)
            .dDN(UPDATED_D_DN)
            .transformationAigue(UPDATED_TRANSFORMATION_AIGUE)
            .ageDiagLA(UPDATED_AGE_DIAG_LA)
            .traitementLA(UPDATED_TRAITEMENT_LA)
            .cancerE(UPDATED_CANCER_E)
            .localisation(UPDATED_LOCALISATION)
            .typeHistologique(UPDATED_TYPE_HISTOLOGIQUE)
            .traitementCancer(UPDATED_TRAITEMENT_CANCER)
            .preciseTraitement(UPDATED_PRECISE_TRAITEMENT)
            .evolutionCyto(UPDATED_EVOLUTION_CYTO)
            .formuleChrom(UPDATED_FORMULE_CHROM)
            .ageE(UPDATED_AGE_E)
            .statut(UPDATED_STATUT)
            .causeDeces(UPDATED_CAUSE_DECES)
            .autreCauseD(UPDATED_AUTRE_CAUSE_D)
            .survieGlobale(UPDATED_SURVIE_GLOBALE)
            .code(UPDATED_CODE)
            .dateMAJ(UPDATED_DATE_MAJ)
            .nombreTacheCafe(UPDATED_NOMBRE_TACHE_CAFE)
            .nombreTacheHypo(UPDATED_NOMBRE_TACHE_HYPO);

        restFicheMockMvc
            .perform(
                put(ENTITY_API_URL_ID, updatedFiche.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(updatedFiche))
            )
            .andExpect(status().isOk());

        // Validate the Fiche in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertPersistedFicheToMatchAllProperties(updatedFiche);
    }

    @Test
    @Transactional
    void putNonExistingFiche() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fiche.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFicheMockMvc
            .perform(put(ENTITY_API_URL_ID, fiche.getId()).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fiche)))
            .andExpect(status().isBadRequest());

        // Validate the Fiche in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchFiche() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fiche.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheMockMvc
            .perform(
                put(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(om.writeValueAsBytes(fiche))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fiche in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamFiche() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fiche.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(om.writeValueAsBytes(fiche)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Fiche in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateFicheWithPatch() throws Exception {
        // Initialize the database
        insertedFiche = ficheRepository.saveAndFlush(fiche);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fiche using partial update
        Fiche partialUpdatedFiche = new Fiche();
        partialUpdatedFiche.setId(fiche.getId());

        partialUpdatedFiche
            .dateEnregistrement(UPDATED_DATE_ENREGISTREMENT)
            .medecin(UPDATED_MEDECIN)
            .service(UPDATED_SERVICE)
            .placeEnfant(UPDATED_PLACE_ENFANT)
            .avortement(UPDATED_AVORTEMENT)
            .membre(UPDATED_MEMBRE)
            .arbregenealogique(UPDATED_ARBREGENEALOGIQUE)
            .arbregenealogiqueContentType(UPDATED_ARBREGENEALOGIQUE_CONTENT_TYPE)
            .syndromeAnemique(UPDATED_SYNDROME_ANEMIQUE)
            .decouverteFort(UPDATED_DECOUVERTE_FORT)
            .enqueteFam(UPDATED_ENQUETE_FAM)
            .typeCancer(UPDATED_TYPE_CANCER)
            .cancer(UPDATED_CANCER)
            .poidsNaiss(UPDATED_POIDS_NAISS)
            .troubleCroi(UPDATED_TROUBLE_CROI)
            .aAgeChDiag(UPDATED_A_AGE_CH_DIAG)
            .aAgeOssDiag(UPDATED_A_AGE_OSS_DIAG)
            .poids(UPDATED_POIDS)
            .taille(UPDATED_TAILLE)
            .tailleDS(UPDATED_TAILLE_DS)
            .atteinteCut(UPDATED_ATTEINTE_CUT)
            .membreSup(UPDATED_MEMBRE_SUP)
            .membreInf(UPDATED_MEMBRE_INF)
            .dOS(UPDATED_D_OS)
            .hyperPig(UPDATED_HYPER_PIG)
            .hypochromique(UPDATED_HYPOCHROMIQUE)
            .couleurPeau(UPDATED_COULEUR_PEAU)
            .autreAtCut(UPDATED_AUTRE_AT_CUT)
            .microphtalmie(UPDATED_MICROPHTALMIE)
            .facieTrig(UPDATED_FACIE_TRIG)
            .traitsFin(UPDATED_TRAITS_FIN)
            .autreAtTete(UPDATED_AUTRE_AT_TETE)
            .malUro(UPDATED_MAL_URO)
            .uIV(UPDATED_U_IV)
            .echo(UPDATED_ECHO)
            .reinEctop(UPDATED_REIN_ECTOP)
            .reinFerChev(UPDATED_REIN_FER_CHEV)
            .ectopTest(UPDATED_ECTOP_TEST)
            .vergeInsuf(UPDATED_VERGE_INSUF)
            .retardPubertaire(UPDATED_RETARD_PUBERTAIRE)
            .pTanner(UPDATED_P_TANNER)
            .typeAnomUrin(UPDATED_TYPE_ANOM_URIN)
            .anomPouce(UPDATED_ANOM_POUCE)
            .bifide(UPDATED_BIFIDE)
            .hypoPouce(UPDATED_HYPO_POUCE)
            .absenceRadial(UPDATED_ABSENCE_RADIAL)
            .pouceBas(UPDATED_POUCE_BAS)
            .syndactylie(UPDATED_SYNDACTYLIE)
            .anomalieos(UPDATED_ANOMALIEOS)
            .agenesieRadius(UPDATED_AGENESIE_RADIUS)
            .anomRachis(UPDATED_ANOM_RACHIS)
            .preciseAnomRac(UPDATED_PRECISE_ANOM_RAC)
            .autreAnomOss(UPDATED_AUTRE_ANOM_OSS)
            .performanceScolaire(UPDATED_PERFORMANCE_SCOLAIRE)
            .anomDig(UPDATED_ANOM_DIG)
            .diabete(UPDATED_DIABETE)
            .insulinoDep(UPDATED_INSULINO_DEP)
            .hypothyroidie(UPDATED_HYPOTHYROIDIE)
            .ageDecouverte(UPDATED_AGE_DECOUVERTE)
            .autreAnomCong(UPDATED_AUTRE_ANOM_CONG)
            .dateNumSanguine(UPDATED_DATE_NUM_SANGUINE)
            .age(UPDATED_AGE)
            .hb(UPDATED_HB)
            .vGM(UPDATED_V_GM)
            .plq(UPDATED_PLQ)
            .plqInf(UPDATED_PLQ_INF)
            .electrophoreseHb(UPDATED_ELECTROPHORESE_HB)
            .myelogramme(UPDATED_MYELOGRAMME)
            .erythroblaste(UPDATED_ERYTHROBLASTE)
            .morphologieGran(UPDATED_MORPHOLOGIE_GRAN)
            .granuleux(UPDATED_GRANULEUX)
            .dysmyelopoiese(UPDATED_DYSMYELOPOIESE)
            .excesblastes(UPDATED_EXCESBLASTES)
            .ubiquitination(UPDATED_UBIQUITINATION)
            .resUbiquitination(UPDATED_RES_UBIQUITINATION)
            .labo(UPDATED_LABO)
            .typePrelevement(UPDATED_TYPE_PRELEVEMENT)
            .scoreClinique(UPDATED_SCORE_CLINIQUE)
            .transfusion(UPDATED_TRANSFUSION)
            .ageTransf(UPDATED_AGE_TRANSF)
            .chelateur(UPDATED_CHELATEUR)
            .danatrol(UPDATED_DANATROL)
            .oxynethadone(UPDATED_OXYNETHADONE)
            .androtordyl(UPDATED_ANDROTORDYL)
            .autreAndrogene(UPDATED_AUTRE_ANDROGENE)
            .androDebut(UPDATED_ANDRO_DEBUT)
            .enqueteHLA(UPDATED_ENQUETE_HLA)
            .preciseDonneur(UPDATED_PRECISE_DONNEUR)
            .donneur(UPDATED_DONNEUR)
            .pourquoiNfaite(UPDATED_POURQUOI_NFAITE)
            .fludarabine(UPDATED_FLUDARABINE)
            .busulfan(UPDATED_BUSULFAN)
            .doseCyclo(UPDATED_DOSE_CYCLO)
            .irradiation(UPDATED_IRRADIATION)
            .doseTotaleIrr(UPDATED_DOSE_TOTALE_IRR)
            .serotherapie(UPDATED_SEROTHERAPIE)
            .gradeaGvH(UPDATED_GRADEA_GV_H)
            .cGvH(UPDATED_C_GV_H)
            .mVO(UPDATED_M_VO)
            .complicationPulmonaire(UPDATED_COMPLICATION_PULMONAIRE)
            .preciseCompPulm(UPDATED_PRECISE_COMP_PULM)
            .survieJ100(UPDATED_SURVIE_J_100)
            .sMD(UPDATED_S_MD)
            .lAM(UPDATED_L_AM)
            .critereDiagLAM(UPDATED_CRITERE_DIAG_LAM)
            .transformationAigue(UPDATED_TRANSFORMATION_AIGUE)
            .traitementLA(UPDATED_TRAITEMENT_LA)
            .typeHistologique(UPDATED_TYPE_HISTOLOGIQUE)
            .traitementCancer(UPDATED_TRAITEMENT_CANCER)
            .preciseTraitement(UPDATED_PRECISE_TRAITEMENT)
            .evolutionCyto(UPDATED_EVOLUTION_CYTO)
            .ageE(UPDATED_AGE_E)
            .causeDeces(UPDATED_CAUSE_DECES)
            .autreCauseD(UPDATED_AUTRE_CAUSE_D)
            .nombreTacheCafe(UPDATED_NOMBRE_TACHE_CAFE)
            .nombreTacheHypo(UPDATED_NOMBRE_TACHE_HYPO);

        restFicheMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFiche.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFiche))
            )
            .andExpect(status().isOk());

        // Validate the Fiche in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFicheUpdatableFieldsEquals(createUpdateProxyForBean(partialUpdatedFiche, fiche), getPersistedFiche(fiche));
    }

    @Test
    @Transactional
    void fullUpdateFicheWithPatch() throws Exception {
        // Initialize the database
        insertedFiche = ficheRepository.saveAndFlush(fiche);

        long databaseSizeBeforeUpdate = getRepositoryCount();

        // Update the fiche using partial update
        Fiche partialUpdatedFiche = new Fiche();
        partialUpdatedFiche.setId(fiche.getId());

        partialUpdatedFiche
            .nDossier(UPDATED_N_DOSSIER)
            .dateDiagnostic(UPDATED_DATE_DIAGNOSTIC)
            .dateEnregistrement(UPDATED_DATE_ENREGISTREMENT)
            .medecin(UPDATED_MEDECIN)
            .hopital(UPDATED_HOPITAL)
            .service(UPDATED_SERVICE)
            .degConsang(UPDATED_DEG_CONSANG)
            .placeEnfant(UPDATED_PLACE_ENFANT)
            .nbVivant(UPDATED_NB_VIVANT)
            .nbMort(UPDATED_NB_MORT)
            .mortNe(UPDATED_MORT_NE)
            .avortement(UPDATED_AVORTEMENT)
            .cousin(UPDATED_COUSIN)
            .membre(UPDATED_MEMBRE)
            .arbregenealogique(UPDATED_ARBREGENEALOGIQUE)
            .arbregenealogiqueContentType(UPDATED_ARBREGENEALOGIQUE_CONTENT_TYPE)
            .arbregenealogiquecancer(UPDATED_ARBREGENEALOGIQUECANCER)
            .arbregenealogiquecancerContentType(UPDATED_ARBREGENEALOGIQUECANCER_CONTENT_TYPE)
            .syndromeAnemique(UPDATED_SYNDROME_ANEMIQUE)
            .syndromeHem(UPDATED_SYNDROME_HEM)
            .syndromeInf(UPDATED_SYNDROME_INF)
            .decouverteFort(UPDATED_DECOUVERTE_FORT)
            .enqueteFam(UPDATED_ENQUETE_FAM)
            .typeCancer(UPDATED_TYPE_CANCER)
            .cancer(UPDATED_CANCER)
            .tailleNaiss(UPDATED_TAILLE_NAISS)
            .poidsNaiss(UPDATED_POIDS_NAISS)
            .hypotrophie(UPDATED_HYPOTROPHIE)
            .troubleCroi(UPDATED_TROUBLE_CROI)
            .aAgeChDiag(UPDATED_A_AGE_CH_DIAG)
            .mAgeChDiag(UPDATED_M_AGE_CH_DIAG)
            .aAgeOssDiag(UPDATED_A_AGE_OSS_DIAG)
            .mAgeOssDiag(UPDATED_M_AGE_OSS_DIAG)
            .ageRetard(UPDATED_AGE_RETARD)
            .poids(UPDATED_POIDS)
            .poidsDS(UPDATED_POIDS_DS)
            .taille(UPDATED_TAILLE)
            .tailleDS(UPDATED_TAILLE_DS)
            .atteinteCut(UPDATED_ATTEINTE_CUT)
            .tacheCaf(UPDATED_TACHE_CAF)
            .ventre(UPDATED_VENTRE)
            .membreSup(UPDATED_MEMBRE_SUP)
            .membreInf(UPDATED_MEMBRE_INF)
            .visage(UPDATED_VISAGE)
            .thorax(UPDATED_THORAX)
            .dOS(UPDATED_D_OS)
            .hyperPig(UPDATED_HYPER_PIG)
            .hypochromique(UPDATED_HYPOCHROMIQUE)
            .couleurPeau(UPDATED_COULEUR_PEAU)
            .autreAtCut(UPDATED_AUTRE_AT_CUT)
            .atteinteTete(UPDATED_ATTEINTE_TETE)
            .microcephalie(UPDATED_MICROCEPHALIE)
            .microphtalmie(UPDATED_MICROPHTALMIE)
            .facieTrig(UPDATED_FACIE_TRIG)
            .traitsFin(UPDATED_TRAITS_FIN)
            .autreAtTete(UPDATED_AUTRE_AT_TETE)
            .empreintedigitiforme(UPDATED_EMPREINTEDIGITIFORME)
            .malUro(UPDATED_MAL_URO)
            .uIV(UPDATED_U_IV)
            .echo(UPDATED_ECHO)
            .reinEctop(UPDATED_REIN_ECTOP)
            .siegeEctopie(UPDATED_SIEGE_ECTOPIE)
            .reinFerChev(UPDATED_REIN_FER_CHEV)
            .petitRein(UPDATED_PETIT_REIN)
            .reinUnique(UPDATED_REIN_UNIQUE)
            .ectopTest(UPDATED_ECTOP_TEST)
            .vergeInsuf(UPDATED_VERGE_INSUF)
            .autreAnomVerge(UPDATED_AUTRE_ANOM_VERGE)
            .retardPubertaire(UPDATED_RETARD_PUBERTAIRE)
            .mTanner(UPDATED_M_TANNER)
            .pTanner(UPDATED_P_TANNER)
            .tTanner(UPDATED_T_TANNER)
            .anomUrin(UPDATED_ANOM_URIN)
            .typeAnomUrin(UPDATED_TYPE_ANOM_URIN)
            .atteinteOss(UPDATED_ATTEINTE_OSS)
            .radiosfaites(UPDATED_RADIOSFAITES)
            .anomPouce(UPDATED_ANOM_POUCE)
            .surnumerarie(UPDATED_SURNUMERARIE)
            .agenesiePouce(UPDATED_AGENESIE_POUCE)
            .bifide(UPDATED_BIFIDE)
            .hypoPouce(UPDATED_HYPO_POUCE)
            .aspectPouce(UPDATED_ASPECT_POUCE)
            .hypoEminence(UPDATED_HYPO_EMINENCE)
            .absenceRadial(UPDATED_ABSENCE_RADIAL)
            .pouceBas(UPDATED_POUCE_BAS)
            .autreAnomPouce(UPDATED_AUTRE_ANOM_POUCE)
            .anomAutDoigts(UPDATED_ANOM_AUT_DOIGTS)
            .courtstrapus(UPDATED_COURTSTRAPUS)
            .syndactylie(UPDATED_SYNDACTYLIE)
            .autreAnomDoigts(UPDATED_AUTRE_ANOM_DOIGTS)
            .anomalieos(UPDATED_ANOMALIEOS)
            .agenesieRadius(UPDATED_AGENESIE_RADIUS)
            .autreanomalieMembresup(UPDATED_AUTREANOMALIE_MEMBRESUP)
            .anomOrteil(UPDATED_ANOM_ORTEIL)
            .preciseAnomOrt(UPDATED_PRECISE_ANOM_ORT)
            .lCH(UPDATED_L_CH)
            .autreanomalieMembreinf(UPDATED_AUTREANOMALIE_MEMBREINF)
            .anomRachis(UPDATED_ANOM_RACHIS)
            .preciseAnomRac(UPDATED_PRECISE_ANOM_RAC)
            .autreAnomOss(UPDATED_AUTRE_ANOM_OSS)
            .anomNeuro(UPDATED_ANOM_NEURO)
            .retardMent(UPDATED_RETARD_MENT)
            .hypoacousie(UPDATED_HYPOACOUSIE)
            .strabisme(UPDATED_STRABISME)
            .performanceScolaire(UPDATED_PERFORMANCE_SCOLAIRE)
            .autreanomalieneurologique(UPDATED_AUTREANOMALIENEUROLOGIQUE)
            .anomCardVas(UPDATED_ANOM_CARD_VAS)
            .echoCoeur(UPDATED_ECHO_COEUR)
            .preciseAnomCardio(UPDATED_PRECISE_ANOM_CARDIO)
            .anomDig(UPDATED_ANOM_DIG)
            .preciseAnomDig(UPDATED_PRECISE_ANOM_DIG)
            .endocrinopathie(UPDATED_ENDOCRINOPATHIE)
            .diabete(UPDATED_DIABETE)
            .insulinoDep(UPDATED_INSULINO_DEP)
            .hypothyroidie(UPDATED_HYPOTHYROIDIE)
            .ageDecouverte(UPDATED_AGE_DECOUVERTE)
            .autreEndocrinopathie(UPDATED_AUTRE_ENDOCRINOPATHIE)
            .autreAnomCong(UPDATED_AUTRE_ANOM_CONG)
            .dateNumSanguine(UPDATED_DATE_NUM_SANGUINE)
            .age(UPDATED_AGE)
            .hb(UPDATED_HB)
            .vGM(UPDATED_V_GM)
            .retic(UPDATED_RETIC)
            .leuco(UPDATED_LEUCO)
            .pNN(UPDATED_P_NN)
            .plq(UPDATED_PLQ)
            .hbInf(UPDATED_HB_INF)
            .plqInf(UPDATED_PLQ_INF)
            .pNNInf(UPDATED_P_NN_INF)
            .electrophoreseHb(UPDATED_ELECTROPHORESE_HB)
            .hbf(UPDATED_HBF)
            .myelogramme(UPDATED_MYELOGRAMME)
            .cellularite(UPDATED_CELLULARITE)
            .erythroblaste(UPDATED_ERYTHROBLASTE)
            .morphologieEryth(UPDATED_MORPHOLOGIE_ERYTH)
            .morphologieGran(UPDATED_MORPHOLOGIE_GRAN)
            .morphologieMega(UPDATED_MORPHOLOGIE_MEGA)
            .granuleux(UPDATED_GRANULEUX)
            .dysmyelopoiese(UPDATED_DYSMYELOPOIESE)
            .megacaryocytes(UPDATED_MEGACARYOCYTES)
            .blaste(UPDATED_BLASTE)
            .excesblastes(UPDATED_EXCESBLASTES)
            .bOM(UPDATED_B_OM)
            .adipocytes(UPDATED_ADIPOCYTES)
            .ubiquitination(UPDATED_UBIQUITINATION)
            .resUbiquitination(UPDATED_RES_UBIQUITINATION)
            .groupComp(UPDATED_GROUP_COMP)
            .mutationFANC(UPDATED_MUTATION_FANC)
            .congelationCellule(UPDATED_CONGELATION_CELLULE)
            .labo(UPDATED_LABO)
            .typePrelevement(UPDATED_TYPE_PRELEVEMENT)
            .scoreClinique(UPDATED_SCORE_CLINIQUE)
            .scoreEBMT(UPDATED_SCORE_EBMT)
            .score(UPDATED_SCORE)
            .transfusion(UPDATED_TRANSFUSION)
            .ageTransf(UPDATED_AGE_TRANSF)
            .delaiDiag(UPDATED_DELAI_DIAG)
            .nbCG(UPDATED_NB_CG)
            .nbCP(UPDATED_NB_CP)
            .chelationFer(UPDATED_CHELATION_FER)
            .chelateur(UPDATED_CHELATEUR)
            .nilevar(UPDATED_NILEVAR)
            .danatrol(UPDATED_DANATROL)
            .oxynethadone(UPDATED_OXYNETHADONE)
            .androtordyl(UPDATED_ANDROTORDYL)
            .autreAndrogene(UPDATED_AUTRE_ANDROGENE)
            .androDebut(UPDATED_ANDRO_DEBUT)
            .androFin(UPDATED_ANDRO_FIN)
            .observance(UPDATED_OBSERVANCE)
            .toxicite(UPDATED_TOXICITE)
            .autreToxicite(UPDATED_AUTRE_TOXICITE)
            .enqueteHLA(UPDATED_ENQUETE_HLA)
            .pourquoiEnqHLA(UPDATED_POURQUOI_ENQ_HLA)
            .nbFratTyp(UPDATED_NB_FRAT_TYP)
            .nbFratNTyp(UPDATED_NB_FRAT_N_TYP)
            .donneurComp(UPDATED_DONNEUR_COMP)
            .preciseDonneur(UPDATED_PRECISE_DONNEUR)
            .donneur(UPDATED_DONNEUR)
            .greffeFaite(UPDATED_GREFFE_FAITE)
            .delaiRappDiag(UPDATED_DELAI_RAPP_DIAG)
            .pourquoiNfaite(UPDATED_POURQUOI_NFAITE)
            .cyclophosphamide(UPDATED_CYCLOPHOSPHAMIDE)
            .fludarabine(UPDATED_FLUDARABINE)
            .busulfan(UPDATED_BUSULFAN)
            .doseCyclo(UPDATED_DOSE_CYCLO)
            .doseFlu(UPDATED_DOSE_FLU)
            .doseBus(UPDATED_DOSE_BUS)
            .autreConditionnement(UPDATED_AUTRE_CONDITIONNEMENT)
            .irradiation(UPDATED_IRRADIATION)
            .doseTotaleIrr(UPDATED_DOSE_TOTALE_IRR)
            .serotherapie(UPDATED_SEROTHERAPIE)
            .sAL(UPDATED_S_AL)
            .doseSAL(UPDATED_DOSE_SAL)
            .sourceCellule(UPDATED_SOURCE_CELLULE)
            .sortieAplasie(UPDATED_SORTIE_APLASIE)
            .gradeaGvH(UPDATED_GRADEA_GV_H)
            .cGvH(UPDATED_C_GV_H)
            .mVO(UPDATED_M_VO)
            .complicationPulmonaire(UPDATED_COMPLICATION_PULMONAIRE)
            .preciseCompPulm(UPDATED_PRECISE_COMP_PULM)
            .survieJ100(UPDATED_SURVIE_J_100)
            .sMD(UPDATED_S_MD)
            .ageDiagSMD(UPDATED_AGE_DIAG_SMD)
            .critereDiagSMD(UPDATED_CRITERE_DIAG_SMD)
            .traitementSMD(UPDATED_TRAITEMENT_SMD)
            .lAM(UPDATED_L_AM)
            .critereDiagLAM(UPDATED_CRITERE_DIAG_LAM)
            .traitementLAM(UPDATED_TRAITEMENT_LAM)
            .autresCancers(UPDATED_AUTRES_CANCERS)
            .dDN(UPDATED_D_DN)
            .transformationAigue(UPDATED_TRANSFORMATION_AIGUE)
            .ageDiagLA(UPDATED_AGE_DIAG_LA)
            .traitementLA(UPDATED_TRAITEMENT_LA)
            .cancerE(UPDATED_CANCER_E)
            .localisation(UPDATED_LOCALISATION)
            .typeHistologique(UPDATED_TYPE_HISTOLOGIQUE)
            .traitementCancer(UPDATED_TRAITEMENT_CANCER)
            .preciseTraitement(UPDATED_PRECISE_TRAITEMENT)
            .evolutionCyto(UPDATED_EVOLUTION_CYTO)
            .formuleChrom(UPDATED_FORMULE_CHROM)
            .ageE(UPDATED_AGE_E)
            .statut(UPDATED_STATUT)
            .causeDeces(UPDATED_CAUSE_DECES)
            .autreCauseD(UPDATED_AUTRE_CAUSE_D)
            .survieGlobale(UPDATED_SURVIE_GLOBALE)
            .code(UPDATED_CODE)
            .dateMAJ(UPDATED_DATE_MAJ)
            .nombreTacheCafe(UPDATED_NOMBRE_TACHE_CAFE)
            .nombreTacheHypo(UPDATED_NOMBRE_TACHE_HYPO);

        restFicheMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedFiche.getId())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(partialUpdatedFiche))
            )
            .andExpect(status().isOk());

        // Validate the Fiche in the database

        assertSameRepositoryCount(databaseSizeBeforeUpdate);
        assertFicheUpdatableFieldsEquals(partialUpdatedFiche, getPersistedFiche(partialUpdatedFiche));
    }

    @Test
    @Transactional
    void patchNonExistingFiche() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fiche.setId(longCount.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFicheMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, fiche.getId()).contentType("application/merge-patch+json").content(om.writeValueAsBytes(fiche))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fiche in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchFiche() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fiche.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, longCount.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(om.writeValueAsBytes(fiche))
            )
            .andExpect(status().isBadRequest());

        // Validate the Fiche in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamFiche() throws Exception {
        long databaseSizeBeforeUpdate = getRepositoryCount();
        fiche.setId(longCount.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restFicheMockMvc
            .perform(patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(om.writeValueAsBytes(fiche)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Fiche in the database
        assertSameRepositoryCount(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteFiche() throws Exception {
        // Initialize the database
        insertedFiche = ficheRepository.saveAndFlush(fiche);

        long databaseSizeBeforeDelete = getRepositoryCount();

        // Delete the fiche
        restFicheMockMvc
            .perform(delete(ENTITY_API_URL_ID, fiche.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        assertDecrementedRepositoryCount(databaseSizeBeforeDelete);
    }

    protected long getRepositoryCount() {
        return ficheRepository.count();
    }

    protected void assertIncrementedRepositoryCount(long countBefore) {
        assertThat(countBefore + 1).isEqualTo(getRepositoryCount());
    }

    protected void assertDecrementedRepositoryCount(long countBefore) {
        assertThat(countBefore - 1).isEqualTo(getRepositoryCount());
    }

    protected void assertSameRepositoryCount(long countBefore) {
        assertThat(countBefore).isEqualTo(getRepositoryCount());
    }

    protected Fiche getPersistedFiche(Fiche fiche) {
        return ficheRepository.findById(fiche.getId()).orElseThrow();
    }

    protected void assertPersistedFicheToMatchAllProperties(Fiche expectedFiche) {
        assertFicheAllPropertiesEquals(expectedFiche, getPersistedFiche(expectedFiche));
    }

    protected void assertPersistedFicheToMatchUpdatableProperties(Fiche expectedFiche) {
        assertFicheAllUpdatablePropertiesEquals(expectedFiche, getPersistedFiche(expectedFiche));
    }
}
