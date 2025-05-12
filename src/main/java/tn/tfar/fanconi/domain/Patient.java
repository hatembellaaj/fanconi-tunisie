package tn.tfar.fanconi.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Patient.
 */
@Entity
@Table(name = "patient")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "ndossier_p")
    private String ndossierP;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "date_naissance")
    private String dateNaissance;

    @Column(name = "lieu_naissance")
    private String lieuNaissance;

    @Column(name = "sexe")
    private String sexe;

    @Column(name = "gouvernorat")
    private String gouvernorat;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "reperes")
    private String reperes;

    @Column(name = "tel")
    private String tel;

    @Column(name = "prenom_pere")
    private String prenomPere;

    @Column(name = "nom_mere")
    private String nomMere;

    @Column(name = "prenom_mere")
    private String prenomMere;

    @Column(name = "nom_gmp")
    private String nomGMP;

    @Column(name = "nom_gmm")
    private String nomGMM;

    @Column(name = "age")
    private Integer age;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Patient id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNdossierP() {
        return this.ndossierP;
    }

    public Patient ndossierP(String ndossierP) {
        this.setNdossierP(ndossierP);
        return this;
    }

    public void setNdossierP(String ndossierP) {
        this.ndossierP = ndossierP;
    }

    public String getNom() {
        return this.nom;
    }

    public Patient nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public Patient prenom(String prenom) {
        this.setPrenom(prenom);
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return this.dateNaissance;
    }

    public Patient dateNaissance(String dateNaissance) {
        this.setDateNaissance(dateNaissance);
        return this;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return this.lieuNaissance;
    }

    public Patient lieuNaissance(String lieuNaissance) {
        this.setLieuNaissance(lieuNaissance);
        return this;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getSexe() {
        return this.sexe;
    }

    public Patient sexe(String sexe) {
        this.setSexe(sexe);
        return this;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getGouvernorat() {
        return this.gouvernorat;
    }

    public Patient gouvernorat(String gouvernorat) {
        this.setGouvernorat(gouvernorat);
        return this;
    }

    public void setGouvernorat(String gouvernorat) {
        this.gouvernorat = gouvernorat;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public Patient adresse(String adresse) {
        this.setAdresse(adresse);
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getReperes() {
        return this.reperes;
    }

    public Patient reperes(String reperes) {
        this.setReperes(reperes);
        return this;
    }

    public void setReperes(String reperes) {
        this.reperes = reperes;
    }

    public String getTel() {
        return this.tel;
    }

    public Patient tel(String tel) {
        this.setTel(tel);
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPrenomPere() {
        return this.prenomPere;
    }

    public Patient prenomPere(String prenomPere) {
        this.setPrenomPere(prenomPere);
        return this;
    }

    public void setPrenomPere(String prenomPere) {
        this.prenomPere = prenomPere;
    }

    public String getNomMere() {
        return this.nomMere;
    }

    public Patient nomMere(String nomMere) {
        this.setNomMere(nomMere);
        return this;
    }

    public void setNomMere(String nomMere) {
        this.nomMere = nomMere;
    }

    public String getPrenomMere() {
        return this.prenomMere;
    }

    public Patient prenomMere(String prenomMere) {
        this.setPrenomMere(prenomMere);
        return this;
    }

    public void setPrenomMere(String prenomMere) {
        this.prenomMere = prenomMere;
    }

    public String getNomGMP() {
        return this.nomGMP;
    }

    public Patient nomGMP(String nomGMP) {
        this.setNomGMP(nomGMP);
        return this;
    }

    public void setNomGMP(String nomGMP) {
        this.nomGMP = nomGMP;
    }

    public String getNomGMM() {
        return this.nomGMM;
    }

    public Patient nomGMM(String nomGMM) {
        this.setNomGMM(nomGMM);
        return this;
    }

    public void setNomGMM(String nomGMM) {
        this.nomGMM = nomGMM;
    }

    public Integer getAge() {
        return this.age;
    }

    public Patient age(Integer age) {
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
        if (!(o instanceof Patient)) {
            return false;
        }
        return getId() != null && getId().equals(((Patient) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Patient{" +
            "id=" + getId() +
            ", ndossierP='" + getNdossierP() + "'" +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", dateNaissance='" + getDateNaissance() + "'" +
            ", lieuNaissance='" + getLieuNaissance() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", gouvernorat='" + getGouvernorat() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", reperes='" + getReperes() + "'" +
            ", tel='" + getTel() + "'" +
            ", prenomPere='" + getPrenomPere() + "'" +
            ", nomMere='" + getNomMere() + "'" +
            ", prenomMere='" + getPrenomMere() + "'" +
            ", nomGMP='" + getNomGMP() + "'" +
            ", nomGMM='" + getNomGMM() + "'" +
            ", age=" + getAge() +
            "}";
    }
}
