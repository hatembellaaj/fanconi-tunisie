package tn.tfar.fanconi.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tn.tfar.fanconi.domain.FicheTestSamples.*;

import org.junit.jupiter.api.Test;
import tn.tfar.fanconi.web.rest.TestUtil;

class FicheTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Fiche.class);
        Fiche fiche1 = getFicheSample1();
        Fiche fiche2 = new Fiche();
        assertThat(fiche1).isNotEqualTo(fiche2);

        fiche2.setId(fiche1.getId());
        assertThat(fiche1).isEqualTo(fiche2);

        fiche2 = getFicheSample2();
        assertThat(fiche1).isNotEqualTo(fiche2);
    }
}
