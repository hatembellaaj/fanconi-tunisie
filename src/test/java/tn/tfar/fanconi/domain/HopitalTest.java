package tn.tfar.fanconi.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tn.tfar.fanconi.domain.HopitalTestSamples.*;

import org.junit.jupiter.api.Test;
import tn.tfar.fanconi.web.rest.TestUtil;

class HopitalTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Hopital.class);
        Hopital hopital1 = getHopitalSample1();
        Hopital hopital2 = new Hopital();
        assertThat(hopital1).isNotEqualTo(hopital2);

        hopital2.setId(hopital1.getId());
        assertThat(hopital1).isEqualTo(hopital2);

        hopital2 = getHopitalSample2();
        assertThat(hopital1).isNotEqualTo(hopital2);
    }
}
