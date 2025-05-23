package tn.tfar.fanconi.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class MedecinAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertMedecinAllPropertiesEquals(Medecin expected, Medecin actual) {
        assertMedecinAutoGeneratedPropertiesEquals(expected, actual);
        assertMedecinAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertMedecinAllUpdatablePropertiesEquals(Medecin expected, Medecin actual) {
        assertMedecinUpdatableFieldsEquals(expected, actual);
        assertMedecinUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertMedecinAutoGeneratedPropertiesEquals(Medecin expected, Medecin actual) {
        assertThat(expected)
            .as("Verify Medecin auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertMedecinUpdatableFieldsEquals(Medecin expected, Medecin actual) {
        assertThat(expected)
            .as("Verify Medecin relevant properties")
            .satisfies(e -> assertThat(e.getcIN()).as("check cIN").isEqualTo(actual.getcIN()))
            .satisfies(e -> assertThat(e.getNomMedecin()).as("check nomMedecin").isEqualTo(actual.getNomMedecin()))
            .satisfies(e -> assertThat(e.getPrenomMedecin()).as("check prenomMedecin").isEqualTo(actual.getPrenomMedecin()))
            .satisfies(e -> assertThat(e.getGrade()).as("check grade").isEqualTo(actual.getGrade()))
            .satisfies(e -> assertThat(e.getTypeMedecin()).as("check typeMedecin").isEqualTo(actual.getTypeMedecin()))
            .satisfies(e -> assertThat(e.getGouvernoratM()).as("check gouvernoratM").isEqualTo(actual.getGouvernoratM()))
            .satisfies(e -> assertThat(e.getAdresseM()).as("check adresseM").isEqualTo(actual.getAdresseM()))
            .satisfies(e -> assertThat(e.getTelM()).as("check telM").isEqualTo(actual.getTelM()))
            .satisfies(e -> assertThat(e.getPosteM()).as("check posteM").isEqualTo(actual.getPosteM()))
            .satisfies(e -> assertThat(e.getFaxM()).as("check faxM").isEqualTo(actual.getFaxM()))
            .satisfies(e -> assertThat(e.getEmailM()).as("check emailM").isEqualTo(actual.getEmailM()))
            .satisfies(e -> assertThat(e.getHopital()).as("check hopital").isEqualTo(actual.getHopital()))
            .satisfies(e -> assertThat(e.getService()).as("check service").isEqualTo(actual.getService()))
            .satisfies(e -> assertThat(e.getImage()).as("check image").isEqualTo(actual.getImage()))
            .satisfies(e -> assertThat(e.getImageContentType()).as("check image contenty type").isEqualTo(actual.getImageContentType()))
            .satisfies(e -> assertThat(e.getLogin()).as("check login").isEqualTo(actual.getLogin()))
            .satisfies(e -> assertThat(e.getPasswd()).as("check passwd").isEqualTo(actual.getPasswd()))
            .satisfies(e -> assertThat(e.getuRL()).as("check uRL").isEqualTo(actual.getuRL()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertMedecinUpdatableRelationshipsEquals(Medecin expected, Medecin actual) {
        // empty method
    }
}
