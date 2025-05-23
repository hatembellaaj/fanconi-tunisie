package tn.tfar.fanconi.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class HopitalAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertHopitalAllPropertiesEquals(Hopital expected, Hopital actual) {
        assertHopitalAutoGeneratedPropertiesEquals(expected, actual);
        assertHopitalAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertHopitalAllUpdatablePropertiesEquals(Hopital expected, Hopital actual) {
        assertHopitalUpdatableFieldsEquals(expected, actual);
        assertHopitalUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertHopitalAutoGeneratedPropertiesEquals(Hopital expected, Hopital actual) {
        assertThat(expected)
            .as("Verify Hopital auto generated properties")
            .satisfies(e -> assertThat(e.getId()).as("check id").isEqualTo(actual.getId()));
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertHopitalUpdatableFieldsEquals(Hopital expected, Hopital actual) {
        assertThat(expected)
            .as("Verify Hopital relevant properties")
            .satisfies(e -> assertThat(e.getCodeHopital()).as("check codeHopital").isEqualTo(actual.getCodeHopital()))
            .satisfies(e -> assertThat(e.getNomHopital()).as("check nomHopital").isEqualTo(actual.getNomHopital()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual the actual entity
     */
    public static void assertHopitalUpdatableRelationshipsEquals(Hopital expected, Hopital actual) {
        // empty method
    }
}
