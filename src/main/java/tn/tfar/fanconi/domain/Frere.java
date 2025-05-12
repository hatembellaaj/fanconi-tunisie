package tn.tfar.fanconi.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Frere.
 */
@Entity
@Table(name = "frere")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Frere implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "n_dossier_f")
    private String nDossierF;

    @Column(name = "id_frere")
    private Integer idFrere;

    @Column(name = "prenom_frere")
    private String prenomFrere;

    @Column(name = "atteint")
    private String atteint;

    @Column(name = "placefratrie")
    private Integer placefratrie;

    @Column(name = "sexe_f")
    private String sexeF;

    @Column(name = "decedes")
    private String decedes;

    @Column(name = "age")
    private Integer age;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Frere id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getnDossierF() {
        return this.nDossierF;
    }

    public Frere nDossierF(String nDossierF) {
        this.setnDossierF(nDossierF);
        return this;
    }

    public void setnDossierF(String nDossierF) {
        this.nDossierF = nDossierF;
    }

    public Integer getIdFrere() {
        return this.idFrere;
    }

    public Frere idFrere(Integer idFrere) {
        this.setIdFrere(idFrere);
        return this;
    }

    public void setIdFrere(Integer idFrere) {
        this.idFrere = idFrere;
    }

    public String getPrenomFrere() {
        return this.prenomFrere;
    }

    public Frere prenomFrere(String prenomFrere) {
        this.setPrenomFrere(prenomFrere);
        return this;
    }

    public void setPrenomFrere(String prenomFrere) {
        this.prenomFrere = prenomFrere;
    }

    public String getAtteint() {
        return this.atteint;
    }

    public Frere atteint(String atteint) {
        this.setAtteint(atteint);
        return this;
    }

    public void setAtteint(String atteint) {
        this.atteint = atteint;
    }

    public Integer getPlacefratrie() {
        return this.placefratrie;
    }

    public Frere placefratrie(Integer placefratrie) {
        this.setPlacefratrie(placefratrie);
        return this;
    }

    public void setPlacefratrie(Integer placefratrie) {
        this.placefratrie = placefratrie;
    }

    public String getSexeF() {
        return this.sexeF;
    }

    public Frere sexeF(String sexeF) {
        this.setSexeF(sexeF);
        return this;
    }

    public void setSexeF(String sexeF) {
        this.sexeF = sexeF;
    }

    public String getDecedes() {
        return this.decedes;
    }

    public Frere decedes(String decedes) {
        this.setDecedes(decedes);
        return this;
    }

    public void setDecedes(String decedes) {
        this.decedes = decedes;
    }

    public Integer getAge() {
        return this.age;
    }

    public Frere age(Integer age) {
        this.setAge(age);
        return this;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Frere)) {
            return false;
        }
        return getId() != null && getId().equals(((Frere) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Frere{" +
            "id=" + getId() +
            ", nDossierF='" + getnDossierF() + "'" +
            ", idFrere=" + getIdFrere() +
            ", prenomFrere='" + getPrenomFrere() + "'" +
            ", atteint='" + getAtteint() + "'" +
            ", placefratrie=" + getPlacefratrie() +
            ", sexeF='" + getSexeF() + "'" +
            ", decedes='" + getDecedes() + "'" +
            ", age=" + getAge() +
            "}";
    }
}
