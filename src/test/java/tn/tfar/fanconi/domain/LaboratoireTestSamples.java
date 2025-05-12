package tn.tfar.fanconi.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class LaboratoireTestSamples {

    private static final Random random = new Random();
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Laboratoire getLaboratoireSample1() {
        return new Laboratoire().id(1).nomLaboratoire("nomLaboratoire1");
    }

    public static Laboratoire getLaboratoireSample2() {
        return new Laboratoire().id(2).nomLaboratoire("nomLaboratoire2");
    }

    public static Laboratoire getLaboratoireRandomSampleGenerator() {
        return new Laboratoire().id(intCount.incrementAndGet()).nomLaboratoire(UUID.randomUUID().toString());
    }
}
