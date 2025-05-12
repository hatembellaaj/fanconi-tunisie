package tn.tfar.fanconi.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class AndrogeneTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Androgene getAndrogeneSample1() {
        return new Androgene().id(1L).nDossierPa("nDossierPa1").mois(1).reponse("reponse1");
    }

    public static Androgene getAndrogeneSample2() {
        return new Androgene().id(2L).nDossierPa("nDossierPa2").mois(2).reponse("reponse2");
    }

    public static Androgene getAndrogeneRandomSampleGenerator() {
        return new Androgene()
            .id(longCount.incrementAndGet())
            .nDossierPa(UUID.randomUUID().toString())
            .mois(intCount.incrementAndGet())
            .reponse(UUID.randomUUID().toString());
    }
}
