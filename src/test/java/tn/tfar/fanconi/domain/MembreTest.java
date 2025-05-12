package tn.tfar.fanconi.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static tn.tfar.fanconi.domain.MembreTestSamples.*;

import org.junit.jupiter.api.Test;
import tn.tfar.fanconi.web.rest.TestUtil;

class MembreTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Membre.class);
        Membre membre1 = getMembreSample1();
        Membre membre2 = new Membre();
        assertThat(membre1).isNotEqualTo(membre2);

        membre2.setId(membre1.getId());
        assertThat(membre1).isEqualTo(membre2);

        membre2 = getMembreSample2();
        assertThat(membre1).isNotEqualTo(membre2);
    }
}
