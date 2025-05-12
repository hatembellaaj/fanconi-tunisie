package tn.tfar.fanconi.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tn.tfar.fanconi.domain.FrereTestSamples.*;

import org.junit.jupiter.api.Test;
import tn.tfar.fanconi.web.rest.TestUtil;

class FrereTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Frere.class);
        Frere frere1 = getFrereSample1();
        Frere frere2 = new Frere();
        assertThat(frere1).isNotEqualTo(frere2);

        frere2.setId(frere1.getId());
        assertThat(frere1).isEqualTo(frere2);

        frere2 = getFrereSample2();
        assertThat(frere1).isNotEqualTo(frere2);
    }
}
