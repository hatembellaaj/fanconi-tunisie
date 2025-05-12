package tn.tfar.fanconi.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tn.tfar.fanconi.domain.CytogeneticienTestSamples.*;

import org.junit.jupiter.api.Test;
import tn.tfar.fanconi.web.rest.TestUtil;

class CytogeneticienTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cytogeneticien.class);
        Cytogeneticien cytogeneticien1 = getCytogeneticienSample1();
        Cytogeneticien cytogeneticien2 = new Cytogeneticien();
        assertThat(cytogeneticien1).isNotEqualTo(cytogeneticien2);

        cytogeneticien2.setId(cytogeneticien1.getId());
        assertThat(cytogeneticien1).isEqualTo(cytogeneticien2);

        cytogeneticien2 = getCytogeneticienSample2();
        assertThat(cytogeneticien1).isNotEqualTo(cytogeneticien2);
    }
}
