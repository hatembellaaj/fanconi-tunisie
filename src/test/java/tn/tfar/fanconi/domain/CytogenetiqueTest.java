package tn.tfar.fanconi.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tn.tfar.fanconi.domain.CytogenetiqueTestSamples.*;

import org.junit.jupiter.api.Test;
import tn.tfar.fanconi.web.rest.TestUtil;

class CytogenetiqueTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Cytogenetique.class);
        Cytogenetique cytogenetique1 = getCytogenetiqueSample1();
        Cytogenetique cytogenetique2 = new Cytogenetique();
        assertThat(cytogenetique1).isNotEqualTo(cytogenetique2);

        cytogenetique2.setId(cytogenetique1.getId());
        assertThat(cytogenetique1).isEqualTo(cytogenetique2);

        cytogenetique2 = getCytogenetiqueSample2();
        assertThat(cytogenetique1).isNotEqualTo(cytogenetique2);
    }
}
