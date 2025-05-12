package tn.tfar.fanconi.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tn.tfar.fanconi.domain.CousinTestSamples.*;

import org.junit.jupiter.api.Test;
import tn.tfar.fanconi.web.rest.TestUtil;

class CousinTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cousin.class);
        Cousin cousin1 = getCousinSample1();
        Cousin cousin2 = new Cousin();
        assertThat(cousin1).isNotEqualTo(cousin2);

        cousin2.setId(cousin1.getId());
        assertThat(cousin1).isEqualTo(cousin2);

        cousin2 = getCousinSample2();
        assertThat(cousin1).isNotEqualTo(cousin2);
    }
}
