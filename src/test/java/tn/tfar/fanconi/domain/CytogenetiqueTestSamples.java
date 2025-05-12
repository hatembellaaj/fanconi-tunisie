package tn.tfar.fanconi.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CytogenetiqueTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Cytogenetique getCytogenetiqueSample1() {
        return new Cytogenetique()
            .id(1L)
            .nDossierPatient("nDossierPatient1")
            .nEtudeCyto(1)
            .lymphocytes("lymphocytes1")
            .laboratoire("laboratoire1")
            .agentPontant("agentPontant1")
            .instabilite("instabilite1")
            .iR("iR1")
            .moelle("moelle1")
            .resultatMoelle("resultatMoelle1");
    }

    public static Cytogenetique getCytogenetiqueSample2() {
        return new Cytogenetique()
            .id(2L)
            .nDossierPatient("nDossierPatient2")
            .nEtudeCyto(2)
            .lymphocytes("lymphocytes2")
            .laboratoire("laboratoire2")
            .agentPontant("agentPontant2")
            .instabilite("instabilite2")
            .iR("iR2")
            .moelle("moelle2")
            .resultatMoelle("resultatMoelle2");
    }

    public static Cytogenetique getCytogenetiqueRandomSampleGenerator() {
        return new Cytogenetique()
            .id(longCount.incrementAndGet())
            .nDossierPatient(UUID.randomUUID().toString())
            .nEtudeCyto(intCount.incrementAndGet())
            .lymphocytes(UUID.randomUUID().toString())
            .laboratoire(UUID.randomUUID().toString())
            .agentPontant(UUID.randomUUID().toString())
            .instabilite(UUID.randomUUID().toString())
            .iR(UUID.randomUUID().toString())
            .moelle(UUID.randomUUID().toString())
            .resultatMoelle(UUID.randomUUID().toString());
    }
}
