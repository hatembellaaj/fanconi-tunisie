package tn.tfar.fanconi.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class CytogeneticienTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Cytogeneticien getCytogeneticienSample1() {
        return new Cytogeneticien()
            .id(1L)
            .code(1)
            .nom("nom1")
            .prenom("prenom1")
            .service("service1")
            .etab("etab1")
            .adresse("adresse1")
            .tel("tel1")
            .poste("poste1")
            .fax("fax1")
            .email("email1")
            .type("type1")
            .login("login1")
            .passwd("passwd1")
            .uRL("uRL1");
    }

    public static Cytogeneticien getCytogeneticienSample2() {
        return new Cytogeneticien()
            .id(2L)
            .code(2)
            .nom("nom2")
            .prenom("prenom2")
            .service("service2")
            .etab("etab2")
            .adresse("adresse2")
            .tel("tel2")
            .poste("poste2")
            .fax("fax2")
            .email("email2")
            .type("type2")
            .login("login2")
            .passwd("passwd2")
            .uRL("uRL2");
    }

    public static Cytogeneticien getCytogeneticienRandomSampleGenerator() {
        return new Cytogeneticien()
            .id(longCount.incrementAndGet())
            .code(intCount.incrementAndGet())
            .nom(UUID.randomUUID().toString())
            .prenom(UUID.randomUUID().toString())
            .service(UUID.randomUUID().toString())
            .etab(UUID.randomUUID().toString())
            .adresse(UUID.randomUUID().toString())
            .tel(UUID.randomUUID().toString())
            .poste(UUID.randomUUID().toString())
            .fax(UUID.randomUUID().toString())
            .email(UUID.randomUUID().toString())
            .type(UUID.randomUUID().toString())
            .login(UUID.randomUUID().toString())
            .passwd(UUID.randomUUID().toString())
            .uRL(UUID.randomUUID().toString());
    }
}
