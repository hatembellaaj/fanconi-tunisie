package tn.tfar.fanconi.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Androgene.
 */
@Entity
@Table(name = "androgene")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Androgene implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "n_dossier_pa")
    private String nDossierPa;

    @Column(name = "mois")
    private Integer mois;

    @Column(name = "reponse")
    private String reponse;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Androgene id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getnDossierPa() {
        return this.nDossierPa;
    }

    public Androgene nDossierPa(String nDossierPa) {
        this.setnDossierPa(nDossierPa);
        return this;
    }

    public void setnDossierPa(String nDossierPa) {
        this.nDossierPa = nDossierPa;
    }

    public Integer getMois() {
        return this.mois;
    }

    public Androgene mois(Integer mois) {
        this.setMois(mois);
        return this;
    }

    public void setMois(Integer mois) {
        this.mois = mois;
    }

    public String getReponse() {
        return this.reponse;
    }

    public Androgene reponse(String reponse) {
        this.setReponse(reponse);
        return this;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Androgene)) {
            return false;
        }
        return getId() != null && getId().equals(((Androgene) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Androgene{" +
            "id=" + getId() +
            ", nDossierPa='" + getnDossierPa() + "'" +
            ", mois=" + getMois() +
            ", reponse='" + getReponse() + "'" +
            "}";
    }
}
