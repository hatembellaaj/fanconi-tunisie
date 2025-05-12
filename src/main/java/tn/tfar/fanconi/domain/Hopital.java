package tn.tfar.fanconi.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Hopital.
 */
@Entity
@Table(name = "hopital")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Hopital implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "code_hopital")
    private Integer codeHopital;

    @Column(name = "nom_hopital")
    private String nomHopital;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Hopital id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodeHopital() {
        return this.codeHopital;
    }

    public Hopital codeHopital(Integer codeHopital) {
        this.setCodeHopital(codeHopital);
        return this;
    }

    public void setCodeHopital(Integer codeHopital) {
        this.codeHopital = codeHopital;
    }

    public String getNomHopital() {
        return this.nomHopital;
    }

    public Hopital nomHopital(String nomHopital) {
        this.setNomHopital(nomHopital);
        return this;
    }

    public void setNomHopital(String nomHopital) {
        this.nomHopital = nomHopital;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Hopital)) {
            return false;
        }
        return getId() != null && getId().equals(((Hopital) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Hopital{" +
            "id=" + getId() +
            ", codeHopital=" + getCodeHopital() +
            ", nomHopital='" + getNomHopital() + "'" +
            "}";
    }
}
