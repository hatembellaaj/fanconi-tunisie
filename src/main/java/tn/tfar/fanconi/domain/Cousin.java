package tn.tfar.fanconi.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Cousin.
 */
@Entity
@Table(name = "cousin")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Cousin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "ndossier_c")
    private String ndossierC;

    @Column(name = "id_cousin")
    private Integer idCousin;

    @Column(name = "prenom_cousin")
    private String prenomCousin;

    @Column(name = "place_cousin")
    private String placeCousin;

    @Column(name = "sexe_c")
    private String sexeC;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Cousin id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNdossierC() {
        return this.ndossierC;
    }

    public Cousin ndossierC(String ndossierC) {
        this.setNdossierC(ndossierC);
        return this;
    }

    public void setNdossierC(String ndossierC) {
        this.ndossierC = ndossierC;
    }

    public Integer getIdCousin() {
        return this.idCousin;
    }

    public Cousin idCousin(Integer idCousin) {
        this.setIdCousin(idCousin);
        return this;
    }

    public void setIdCousin(Integer idCousin) {
        this.idCousin = idCousin;
    }

    public String getPrenomCousin() {
        return this.prenomCousin;
    }

    public Cousin prenomCousin(String prenomCousin) {
        this.setPrenomCousin(prenomCousin);
        return this;
    }

    public void setPrenomCousin(String prenomCousin) {
        this.prenomCousin = prenomCousin;
    }

    public String getPlaceCousin() {
        return this.placeCousin;
    }

    public Cousin placeCousin(String placeCousin) {
        this.setPlaceCousin(placeCousin);
        return this;
    }

    public void setPlaceCousin(String placeCousin) {
        this.placeCousin = placeCousin;
    }

    public String getSexeC() {
        return this.sexeC;
    }

    public Cousin sexeC(String sexeC) {
        this.setSexeC(sexeC);
        return this;
    }

    public void setSexeC(String sexeC) {
        this.sexeC = sexeC;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cousin)) {
            return false;
        }
        return getId() != null && getId().equals(((Cousin) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Cousin{" +
            "id=" + getId() +
            ", ndossierC='" + getNdossierC() + "'" +
            ", idCousin=" + getIdCousin() +
            ", prenomCousin='" + getPrenomCousin() + "'" +
            ", placeCousin='" + getPlaceCousin() + "'" +
            ", sexeC='" + getSexeC() + "'" +
            "}";
    }
}
