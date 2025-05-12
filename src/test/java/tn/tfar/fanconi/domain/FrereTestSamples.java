package tn.tfar.fanconi.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class FrereTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Frere getFrereSample1() {
        return new Frere()
            .id(1L)
            .nDossierF("nDossierF1")
            .idFrere(1)
            .prenomFrere("prenomFrere1")
            .atteint("atteint1")
            .placefratrie(1)
            .sexeF("sexeF1")
            .decedes("decedes1")
            .age(1);
    }

    public static Frere getFrereSample2() {
        return new Frere()
            .id(2L)
            .nDossierF("nDossierF2")
            .idFrere(2)
            .prenomFrere("prenomFrere2")
            .atteint("atteint2")
            .placefratrie(2)
            .sexeF("sexeF2")
            .decedes("decedes2")
            .age(2);
    }

    public static Frere getFrereRandomSampleGenerator() {
        return new Frere()
            .id(longCount.incrementAndGet())
            .nDossierF(UUID.randomUUID().toString())
            .idFrere(intCount.incrementAndGet())
            .prenomFrere(UUID.randomUUID().toString())
            .atteint(UUID.randomUUID().toString())
            .placefratrie(intCount.incrementAndGet())
            .sexeF(UUID.randomUUID().toString())
            .decedes(UUID.randomUUID().toString())
            .age(intCount.incrementAndGet());
    }
}
