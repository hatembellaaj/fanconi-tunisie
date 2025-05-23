package tn.tfar.fanconi.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class CytogeneticienAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCytogeneticienAllPropertiesEquals(Cytogeneticien expected, Cytogeneticien actual) {
        assertCytogeneticienAutoGeneratedPropertiesEquals(expected, actual);
        assertCytogeneticienAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCytogeneticienAllUpdatablePropertiesEquals(Cytogeneticien expected, Cytogeneticien actual) {
        assertCytogeneticienUpdatableFieldsEquals(expected, actual);
        assertCytogeneticienUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCytogeneticienAutoGeneratedPropertiesEquals(Cytogeneticien expected, Cytogeneticien actual) {
        assertThat(expected)
            .as("Verify Cytogeneticien auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCytogeneticienUpdatableFieldsEquals(Cytogeneticien expected, Cytogeneticien actual) {
        assertThat(expected)
            .as("Verify Cytogeneticien relevant properties")
            .satisfies(e -> assertThat(e.getCode()).as("check code").isEqualTo(actual.getCode()))
            .satisfies(e -> assertThat(e.getNom()).as("check nom").isEqualTo(actual.getNom()))
            .satisfies(e -> assertThat(e.getPrenom()).as("check prenom").isEqualTo(actual.getPrenom()))
            .satisfies(e -> assertThat(e.getService()).as("check service").isEqualTo(actual.getService()))
            .satisfies(e -> assertThat(e.getEtab()).as("check etab").isEqualTo(actual.getEtab()))
            .satisfies(e -> assertThat(e.getAdresse()).as("check adresse").isEqualTo(actual.getAdresse()))
            .satisfies(e -> assertThat(e.getTel()).as("check tel").isEqualTo(actual.getTel()))
            .satisfies(e -> assertThat(e.getPoste()).as("check poste").isEqualTo(actual.getPoste()))
            .satisfies(e -> assertThat(e.getFax()).as("check fax").isEqualTo(actual.getFax()))
            .satisfies(e -> assertThat(e.getEmail()).as("check email").isEqualTo(actual.getEmail()))
            .satisfies(e -> assertThat(e.getPhoto()).as("check photo").isEqualTo(actual.getPhoto()))
            .satisfies(e -> assertThat(e.getPhotoContentType()).as("check photo contenty type").isEqualTo(actual.getPhotoContentType()))
            .satisfies(e -> assertThat(e.getType()).as("check type").isEqualTo(actual.getType()))
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
    public static void assertCytogeneticienUpdatableRelationshipsEquals(Cytogeneticien expected, Cytogeneticien actual) {
        // empty method
    }
}
