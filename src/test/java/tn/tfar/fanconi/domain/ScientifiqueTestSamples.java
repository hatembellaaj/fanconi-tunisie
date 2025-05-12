package tn.tfar.fanconi.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class ScientifiqueTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Scientifique getScientifiqueSample1() {
        return new Scientifique()
            .id(1L)
            .codeSC(1)
            .nomSC("nomSC1")
            .prenomSC("prenomSC1")
            .serviceSC("serviceSC1")
            .centreSC("centreSC1")
            .adresseSC("adresseSC1")
            .telSC("telSC1")
            .emailSC("emailSC1")
            .typeSC("typeSC1")
            .loginSC("loginSC1")
            .passwdSC("passwdSC1")
            .uRL("uRL1");
    }

    public static Scientifique getScientifiqueSample2() {
        return new Scientifique()
            .id(2L)
            .codeSC(2)
            .nomSC("nomSC2")
            .prenomSC("prenomSC2")
            .serviceSC("serviceSC2")
            .centreSC("centreSC2")
            .adresseSC("adresseSC2")
            .telSC("telSC2")
            .emailSC("emailSC2")
            .typeSC("typeSC2")
            .loginSC("loginSC2")
            .passwdSC("passwdSC2")
            .uRL("uRL2");
    }

    public static Scientifique getScientifiqueRandomSampleGenerator() {
        return new Scientifique()
            .id(longCount.incrementAndGet())
            .codeSC(intCount.incrementAndGet())
            .nomSC(UUID.randomUUID().toString())
            .prenomSC(UUID.randomUUID().toString())
            .serviceSC(UUID.randomUUID().toString())
            .centreSC(UUID.randomUUID().toString())
            .adresseSC(UUID.randomUUID().toString())
            .telSC(UUID.randomUUID().toString())
            .emailSC(UUID.randomUUID().toString())
            .typeSC(UUID.randomUUID().toString())
            .loginSC(UUID.randomUUID().toString())
            .passwdSC(UUID.randomUUID().toString())
            .uRL(UUID.randomUUID().toString());
    }
}
