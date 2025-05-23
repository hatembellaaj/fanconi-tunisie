package tn.tfar.fanconi.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class CousinAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCousinAllPropertiesEquals(Cousin expected, Cousin actual) {
        assertCousinAutoGeneratedPropertiesEquals(expected, actual);
        assertCousinAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCousinAllUpdatablePropertiesEquals(Cousin expected, Cousin actual) {
        assertCousinUpdatableFieldsEquals(expected, actual);
        assertCousinUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCousinAutoGeneratedPropertiesEquals(Cousin expected, Cousin actual) {
        assertThat(expected)
            .as("Verify Cousin auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCousinUpdatableFieldsEquals(Cousin expected, Cousin actual) {
        assertThat(expected)
            .as("Verify Cousin relevant properties")
            .satisfies(e -> assertThat(e.getNdossierC()).as("check ndossierC").isEqualTo(actual.getNdossierC()))
            .satisfies(e -> assertThat(e.getIdCousin()).as("check idCousin").isEqualTo(actual.getIdCousin()))
            .satisfies(e -> assertThat(e.getPrenomCousin()).as("check prenomCousin").isEqualTo(actual.getPrenomCousin()))
            .satisfies(e -> assertThat(e.getPlaceCousin()).as("check placeCousin").isEqualTo(actual.getPlaceCousin()))
            .satisfies(e -> assertThat(e.getSexeC()).as("check sexeC").isEqualTo(actual.getSexeC()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertCousinUpdatableRelationshipsEquals(Cousin expected, Cousin actual) {
        // empty method
    }
}
