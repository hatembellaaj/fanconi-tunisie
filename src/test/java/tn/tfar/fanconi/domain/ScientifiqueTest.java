package tn.tfar.fanconi.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tn.tfar.fanconi.domain.ScientifiqueTestSamples.*;

import org.junit.jupiter.api.Test;
import tn.tfar.fanconi.web.rest.TestUtil;

class ScientifiqueTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Scientifique.class);
        Scientifique scientifique1 = getScientifiqueSample1();
        Scientifique scientifique2 = new Scientifique();
        assertThat(scientifique1).isNotEqualTo(scientifique2);

        scientifique2.setId(scientifique1.getId());
        assertThat(scientifique1).isEqualTo(scientifique2);

        scientifique2 = getScientifiqueSample2();
        assertThat(scientifique1).isNotEqualTo(scientifique2);
    }
}
