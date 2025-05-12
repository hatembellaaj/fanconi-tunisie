package tn.tfar.fanconi.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class HopitalTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Hopital getHopitalSample1() {
        return new Hopital().id(1L).codeHopital(1).nomHopital("nomHopital1");
    }

    public static Hopital getHopitalSample2() {
        return new Hopital().id(2L).codeHopital(2).nomHopital("nomHopital2");
    }

    public static Hopital getHopitalRandomSampleGenerator() {
        return new Hopital()
            .id(longCount.incrementAndGet())
            .codeHopital(intCount.incrementAndGet())
            .nomHopital(UUID.randomUUID().toString());
    }
}
