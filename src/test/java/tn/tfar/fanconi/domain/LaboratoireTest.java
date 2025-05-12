package tn.tfar.fanconi.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tn.tfar.fanconi.domain.LaboratoireTestSamples.*;

import org.junit.jupiter.api.Test;
import tn.tfar.fanconi.web.rest.TestUtil;

class LaboratoireTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Laboratoire.class);
        Laboratoire laboratoire1 = getLaboratoireSample1();
        Laboratoire laboratoire2 = new Laboratoire();
        assertThat(laboratoire1).isNotEqualTo(laboratoire2);

        laboratoire2.setId(laboratoire1.getId());
        assertThat(laboratoire1).isEqualTo(laboratoire2);

        laboratoire2 = getLaboratoireSample2();
        assertThat(laboratoire1).isNotEqualTo(laboratoire2);
    }
}
