package tn.tfar.fanconi.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class MembreTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Membre getMembreSample1() {
        return new Membre()
            .id(1L)
            .nDossierM("nDossierM1")
            .idMembre(1)
            .prenomM("prenomM1")
            .lienParente("lienParente1")
            .typeCancerM("typeCancerM1")
            .ageDecouverteM(1);
    }

    public static Membre getMembreSample2() {
        return new Membre()
            .id(2L)
            .nDossierM("nDossierM2")
            .idMembre(2)
            .prenomM("prenomM2")
            .lienParente("lienParente2")
            .typeCancerM("typeCancerM2")
            .ageDecouverteM(2);
    }

    public static Membre getMembreRandomSampleGenerator() {
        return new Membre()
            .id(longCount.incrementAndGet())
            .nDossierM(UUID.randomUUID().toString())
            .idMembre(intCount.incrementAndGet())
            .prenomM(UUID.randomUUID().toString())
            .lienParente(UUID.randomUUID().toString())
            .typeCancerM(UUID.randomUUID().toString())
            .ageDecouverteM(intCount.incrementAndGet());
    }
}
