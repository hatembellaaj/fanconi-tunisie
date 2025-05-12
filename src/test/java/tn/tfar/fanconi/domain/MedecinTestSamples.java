package tn.tfar.fanconi.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class MedecinTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Medecin getMedecinSample1() {
        return new Medecin()
            .id(1L)
            .cIN(1)
            .nomMedecin("nomMedecin1")
            .prenomMedecin("prenomMedecin1")
            .grade("grade1")
            .typeMedecin("typeMedecin1")
            .gouvernoratM("gouvernoratM1")
            .adresseM("adresseM1")
            .telM("telM1")
            .posteM("posteM1")
            .faxM("faxM1")
            .emailM("emailM1")
            .hopital("hopital1")
            .service("service1")
            .login("login1")
            .passwd("passwd1")
            .uRL("uRL1");
    }

    public static Medecin getMedecinSample2() {
        return new Medecin()
            .id(2L)
            .cIN(2)
            .nomMedecin("nomMedecin2")
            .prenomMedecin("prenomMedecin2")
            .grade("grade2")
            .typeMedecin("typeMedecin2")
            .gouvernoratM("gouvernoratM2")
            .adresseM("adresseM2")
            .telM("telM2")
            .posteM("posteM2")
            .faxM("faxM2")
            .emailM("emailM2")
            .hopital("hopital2")
            .service("service2")
            .login("login2")
            .passwd("passwd2")
            .uRL("uRL2");
    }

    public static Medecin getMedecinRandomSampleGenerator() {
        return new Medecin()
            .id(longCount.incrementAndGet())
            .cIN(intCount.incrementAndGet())
            .nomMedecin(UUID.randomUUID().toString())
            .prenomMedecin(UUID.randomUUID().toString())
            .grade(UUID.randomUUID().toString())
            .typeMedecin(UUID.randomUUID().toString())
            .gouvernoratM(UUID.randomUUID().toString())
            .adresseM(UUID.randomUUID().toString())
            .telM(UUID.randomUUID().toString())
            .posteM(UUID.randomUUID().toString())
            .faxM(UUID.randomUUID().toString())
            .emailM(UUID.randomUUID().toString())
            .hopital(UUID.randomUUID().toString())
            .service(UUID.randomUUID().toString())
            .login(UUID.randomUUID().toString())
            .passwd(UUID.randomUUID().toString())
            .uRL(UUID.randomUUID().toString());
    }
}
