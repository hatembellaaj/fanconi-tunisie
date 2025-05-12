package tn.tfar.fanconi.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CousinTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Cousin getCousinSample1() {
        return new Cousin()
            .id(1L)
            .ndossierC("ndossierC1")
            .idCousin(1)
            .prenomCousin("prenomCousin1")
            .placeCousin("placeCousin1")
            .sexeC("sexeC1");
    }

    public static Cousin getCousinSample2() {
        return new Cousin()
            .id(2L)
            .ndossierC("ndossierC2")
            .idCousin(2)
            .prenomCousin("prenomCousin2")
            .placeCousin("placeCousin2")
            .sexeC("sexeC2");
    }

    public static Cousin getCousinRandomSampleGenerator() {
        return new Cousin()
            .id(longCount.incrementAndGet())
            .ndossierC(UUID.randomUUID().toString())
            .idCousin(intCount.incrementAndGet())
            .prenomCousin(UUID.randomUUID().toString())
            .placeCousin(UUID.randomUUID().toString())
            .sexeC(UUID.randomUUID().toString());
    }
}
