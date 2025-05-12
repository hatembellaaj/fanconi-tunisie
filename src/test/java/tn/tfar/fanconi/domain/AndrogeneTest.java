package tn.tfar.fanconi.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tn.tfar.fanconi.domain.AndrogeneTestSamples.*;

import org.junit.jupiter.api.Test;
import tn.tfar.fanconi.web.rest.TestUtil;

class AndrogeneTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Androgene.class);
        Androgene androgene1 = getAndrogeneSample1();
        Androgene androgene2 = new Androgene();
        assertThat(androgene1).isNotEqualTo(androgene2);

        androgene2.setId(androgene1.getId());
        assertThat(androgene1).isEqualTo(androgene2);

        androgene2 = getAndrogeneSample2();
        assertThat(androgene1).isNotEqualTo(androgene2);
    }
}
