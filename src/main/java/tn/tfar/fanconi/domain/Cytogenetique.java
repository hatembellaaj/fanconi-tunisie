package tn.tfar.fanconi.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Cytogenetique.
 */
@Entity
@Table(name = "cytogenetique")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Cytogenetique implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "n_dossier_patient")
    private String nDossierPatient;

    @Column(name = "n_etude_cyto")
    private Integer nEtudeCyto;

    @Column(name = "lymphocytes")
    private String lymphocytes;

    @Column(name = "date_sang")
    private LocalDate dateSang;

    @Column(name = "laboratoire")
    private String laboratoire;

    @Column(name = "agent_pontant")
    private String agentPontant;

    @Column(name = "instabilite")
    private String instabilite;

    @Column(name = "instabilite_pourcentage")
    private Float instabilitePourcentage;

    @Column(name = "i_r")
    private String iR;

    @Column(name = "i_r_pourcentage")
    private Float iRPourcentage;

    @Column(name = "moelle")
    private String moelle;

    @Column(name = "date_moelle")
    private LocalDate dateMoelle;

    @Column(name = "resultat_moelle")
    private String resultatMoelle;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Cytogenetique id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getnDossierPatient() {
        return this.nDossierPatient;
    }

    public Cytogenetique nDossierPatient(String nDossierPatient) {
        this.setnDossierPatient(nDossierPatient);
        return this;
    }

    public void setnDossierPatient(String nDossierPatient) {
        this.nDossierPatient = nDossierPatient;
    }

    public Integer getnEtudeCyto() {
        return this.nEtudeCyto;
    }

    public Cytogenetique nEtudeCyto(Integer nEtudeCyto) {
        this.setnEtudeCyto(nEtudeCyto);
        return this;
    }

    public void setnEtudeCyto(Integer nEtudeCyto) {
        this.nEtudeCyto = nEtudeCyto;
    }

    public String getLymphocytes() {
        return this.lymphocytes;
    }

    public Cytogenetique lymphocytes(String lymphocytes) {
        this.setLymphocytes(lymphocytes);
        return this;
    }

    public void setLymphocytes(String lymphocytes) {
        this.lymphocytes = lymphocytes;
    }

    public LocalDate getDateSang() {
        return this.dateSang;
    }

    public Cytogenetique dateSang(LocalDate dateSang) {
        this.setDateSang(dateSang);
        return this;
    }

    public void setDateSang(LocalDate dateSang) {
        this.dateSang = dateSang;
    }

    public String getLaboratoire() {
        return this.laboratoire;
    }

    public Cytogenetique laboratoire(String laboratoire) {
        this.setLaboratoire(laboratoire);
        return this;
    }

    public void setLaboratoire(String laboratoire) {
        this.laboratoire = laboratoire;
    }

    public String getAgentPontant() {
        return this.agentPontant;
    }

    public Cytogenetique agentPontant(String agentPontant) {
        this.setAgentPontant(agentPontant);
        return this;
    }

    public void setAgentPontant(String agentPontant) {
        this.agentPontant = agentPontant;
    }

    public String getInstabilite() {
        return this.instabilite;
    }

    public Cytogenetique instabilite(String instabilite) {
        this.setInstabilite(instabilite);
        return this;
    }

    public void setInstabilite(String instabilite) {
        this.instabilite = instabilite;
    }

    public Float getInstabilitePourcentage() {
        return this.instabilitePourcentage;
    }

    public Cytogenetique instabilitePourcentage(Float instabilitePourcentage) {
        this.setInstabilitePourcentage(instabilitePourcentage);
        return this;
    }

    public void setInstabilitePourcentage(Float instabilitePourcentage) {
        this.instabilitePourcentage = instabilitePourcentage;
    }

    public String getiR() {
        return this.iR;
    }

    public Cytogenetique iR(String iR) {
        this.setiR(iR);
        return this;
    }

    public void setiR(String iR) {
        this.iR = iR;
    }

    public Float getiRPourcentage() {
        return this.iRPourcentage;
    }

    public Cytogenetique iRPourcentage(Float iRPourcentage) {
        this.setiRPourcentage(iRPourcentage);
        return this;
    }

    public void setiRPourcentage(Float iRPourcentage) {
        this.iRPourcentage = iRPourcentage;
    }

    public String getMoelle() {
        return this.moelle;
    }

    public Cytogenetique moelle(String moelle) {
        this.setMoelle(moelle);
        return this;
    }

    public void setMoelle(String moelle) {
        this.moelle = moelle;
    }

    public LocalDate getDateMoelle() {
        return this.dateMoelle;
    }

    public Cytogenetique dateMoelle(LocalDate dateMoelle) {
        this.setDateMoelle(dateMoelle);
        return this;
    }

    public void setDateMoelle(LocalDate dateMoelle) {
        this.dateMoelle = dateMoelle;
    }

    public String getResultatMoelle() {
        return this.resultatMoelle;
    }

    public Cytogenetique resultatMoelle(String resultatMoelle) {
        this.setResultatMoelle(resultatMoelle);
        return this;
    }

    public void setResultatMoelle(String resultatMoelle) {
        this.resultatMoelle = resultatMoelle;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Cytogenetique)) {
            return false;
        }
        return getId() != null && getId().equals(((Cytogenetique) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Cytogenetique{" +
            "id=" + getId() +
            ", nDossierPatient='" + getnDossierPatient() + "'" +
            ", nEtudeCyto=" + getnEtudeCyto() +
            ", lymphocytes='" + getLymphocytes() + "'" +
            ", dateSang='" + getDateSang() + "'" +
            ", laboratoire='" + getLaboratoire() + "'" +
            ", agentPontant='" + getAgentPontant() + "'" +
            ", instabilite='" + getInstabilite() + "'" +
            ", instabilitePourcentage=" + getInstabilitePourcentage() +
            ", iR='" + getiR() + "'" +
            ", iRPourcentage=" + getiRPourcentage() +
            ", moelle='" + getMoelle() + "'" +
            ", dateMoelle='" + getDateMoelle() + "'" +
            ", resultatMoelle='" + getResultatMoelle() + "'" +
            "}";
    }
}
