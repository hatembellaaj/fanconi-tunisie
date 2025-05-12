package tn.tfar.fanconi.domain;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class PatientTestSamples {

    private static final Random random = new Random();
    private static final AtomicLong longCount = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));
    private static final AtomicInteger intCount = new AtomicInteger(random.nextInt() + (2 * Short.MAX_VALUE));

    public static Patient getPatientSample1() {
        return new Patient()
            .id(1L)
            .ndossierP("ndossierP1")
            .nom("nom1")
            .prenom("prenom1")
            .dateNaissance("dateNaissance1")
            .lieuNaissance("lieuNaissance1")
            .sexe("sexe1")
            .gouvernorat("gouvernorat1")
            .adresse("adresse1")
            .reperes("reperes1")
            .tel("tel1")
            .prenomPere("prenomPere1")
            .nomMere("nomMere1")
            .prenomMere("prenomMere1")
            .nomGMP("nomGMP1")
            .nomGMM("nomGMM1")
            .age(1);
    }

    public static Patient getPatientSample2() {
        return new Patient()
            .id(2L)
            .ndossierP("ndossierP2")
            .nom("nom2")
            .prenom("prenom2")
            .dateNaissance("dateNaissance2")
            .lieuNaissance("lieuNaissance2")
            .sexe("sexe2")
            .gouvernorat("gouvernorat2")
            .adresse("adresse2")
            .reperes("reperes2")
            .tel("tel2")
            .prenomPere("prenomPere2")
            .nomMere("nomMere2")
            .prenomMere("prenomMere2")
            .nomGMP("nomGMP2")
            .nomGMM("nomGMM2")
            .age(2);
    }

    public static Patient getPatientRandomSampleGenerator() {
        return new Patient()
            .id(longCount.incrementAndGet())
            .ndossierP(UUID.randomUUID().toString())
            .nom(UUID.randomUUID().toString())
            .prenom(UUID.randomUUID().toString())
            .dateNaissance(UUID.randomUUID().toString())
            .lieuNaissance(UUID.randomUUID().toString())
            .sexe(UUID.randomUUID().toString())
            .gouvernorat(UUID.randomUUID().toString())
            .adresse(UUID.randomUUID().toString())
            .reperes(UUID.randomUUID().toString())
            .tel(UUID.randomUUID().toString())
            .prenomPere(UUID.randomUUID().toString())
            .nomMere(UUID.randomUUID().toString())
            .prenomMere(UUID.randomUUID().toString())
            .nomGMP(UUID.randomUUID().toString())
            .nomGMM(UUID.randomUUID().toString())
            .age(intCount.incrementAndGet());
    }
}
