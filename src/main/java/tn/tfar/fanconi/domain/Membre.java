package tn.tfar.fanconi.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Membre.
 */
@Entity
@Table(name = "membre")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Membre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "n_dossier_m")
    private String nDossierM;

    @Column(name = "id_membre")
    private Integer idMembre;

    @Column(name = "prenom_m")
    private String prenomM;

    @Column(name = "lien_parente")
    private String lienParente;

    @Column(name = "type_cancer_m")
    private String typeCancerM;

    @Column(name = "age_decouverte_m")
    private Integer ageDecouverteM;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Membre id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getnDossierM() {
        return this.nDossierM;
    }

    public Membre nDossierM(String nDossierM) {
        this.setnDossierM(nDossierM);
        return this;
    }

    public void setnDossierM(String nDossierM) {
        this.nDossierM = nDossierM;
    }

    public Integer getIdMembre() {
        return this.idMembre;
    }

    public Membre idMembre(Integer idMembre) {
        this.setIdMembre(idMembre);
        return this;
    }

    public void setIdMembre(Integer idMembre) {
        this.idMembre = idMembre;
    }

    public String getPrenomM() {
        return this.prenomM;
    }

    public Membre prenomM(String prenomM) {
        this.setPrenomM(prenomM);
        return this;
    }

    public void setPrenomM(String prenomM) {
        this.prenomM = prenomM;
    }

    public String getLienParente() {
        return this.lienParente;
    }

    public Membre lienParente(String lienParente) {
        this.setLienParente(lienParente);
        return this;
    }

    public void setLienParente(String lienParente) {
        this.lienParente = lienParente;
    }

    public String getTypeCancerM() {
        return this.typeCancerM;
    }

    public Membre typeCancerM(String typeCancerM) {
        this.setTypeCancerM(typeCancerM);
        return this;
    }

    public void setTypeCancerM(String typeCancerM) {
        this.typeCancerM = typeCancerM;
    }

    public Integer getAgeDecouverteM() {
        return this.ageDecouverteM;
    }

    public Membre ageDecouverteM(Integer ageDecouverteM) {
        this.setAgeDecouverteM(ageDecouverteM);
        return this;
    }

    public void setAgeDecouverteM(Integer ageDecouverteM) {
        this.ageDecouverteM = ageDecouverteM;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Membre)) {
            return false;
        }
        return getId() != null && getId().equals(((Membre) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Membre{" +
            "id=" + getId() +
            ", nDossierM='" + getnDossierM() + "'" +
            ", idMembre=" + getIdMembre() +
            ", prenomM='" + getPrenomM() + "'" +
            ", lienParente='" + getLienParente() + "'" +
            ", typeCancerM='" + getTypeCancerM() + "'" +
            ", ageDecouverteM=" + getAgeDecouverteM() +
            "}";
    }
}
