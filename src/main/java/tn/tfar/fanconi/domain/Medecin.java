package tn.tfar.fanconi.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Medecin.
 */
@Entity
@Table(name = "medecin")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Medecin implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "c_in")
    private Integer cIN;

    @Column(name = "nom_medecin")
    private String nomMedecin;

    @Column(name = "prenom_medecin")
    private String prenomMedecin;

    @Column(name = "grade")
    private String grade;

    @Column(name = "type_medecin")
    private String typeMedecin;

    @Column(name = "gouvernorat_m")
    private String gouvernoratM;

    @Column(name = "adresse_m")
    private String adresseM;

    @Column(name = "tel_m")
    private String telM;

    @Column(name = "poste_m")
    private String posteM;

    @Column(name = "fax_m")
    private String faxM;

    @Column(name = "email_m")
    private String emailM;

    @Column(name = "hopital")
    private String hopital;

    @Column(name = "service")
    private String service;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_content_type")
    private String imageContentType;

    @Column(name = "login")
    private String login;

    @Column(name = "passwd")
    private String passwd;

    @Column(name = "u_rl")
    private String uRL;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Medecin id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getcIN() {
        return this.cIN;
    }

    public Medecin cIN(Integer cIN) {
        this.setcIN(cIN);
        return this;
    }

    public void setcIN(Integer cIN) {
        this.cIN = cIN;
    }

    public String getNomMedecin() {
        return this.nomMedecin;
    }

    public Medecin nomMedecin(String nomMedecin) {
        this.setNomMedecin(nomMedecin);
        return this;
    }

    public void setNomMedecin(String nomMedecin) {
        this.nomMedecin = nomMedecin;
    }

    public String getPrenomMedecin() {
        return this.prenomMedecin;
    }

    public Medecin prenomMedecin(String prenomMedecin) {
        this.setPrenomMedecin(prenomMedecin);
        return this;
    }

    public void setPrenomMedecin(String prenomMedecin) {
        this.prenomMedecin = prenomMedecin;
    }

    public String getGrade() {
        return this.grade;
    }

    public Medecin grade(String grade) {
        this.setGrade(grade);
        return this;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTypeMedecin() {
        return this.typeMedecin;
    }

    public Medecin typeMedecin(String typeMedecin) {
        this.setTypeMedecin(typeMedecin);
        return this;
    }

    public void setTypeMedecin(String typeMedecin) {
        this.typeMedecin = typeMedecin;
    }

    public String getGouvernoratM() {
        return this.gouvernoratM;
    }

    public Medecin gouvernoratM(String gouvernoratM) {
        this.setGouvernoratM(gouvernoratM);
        return this;
    }

    public void setGouvernoratM(String gouvernoratM) {
        this.gouvernoratM = gouvernoratM;
    }

    public String getAdresseM() {
        return this.adresseM;
    }

    public Medecin adresseM(String adresseM) {
        this.setAdresseM(adresseM);
        return this;
    }

    public void setAdresseM(String adresseM) {
        this.adresseM = adresseM;
    }

    public String getTelM() {
        return this.telM;
    }

    public Medecin telM(String telM) {
        this.setTelM(telM);
        return this;
    }

    public void setTelM(String telM) {
        this.telM = telM;
    }

    public String getPosteM() {
        return this.posteM;
    }

    public Medecin posteM(String posteM) {
        this.setPosteM(posteM);
        return this;
    }

    public void setPosteM(String posteM) {
        this.posteM = posteM;
    }

    public String getFaxM() {
        return this.faxM;
    }

    public Medecin faxM(String faxM) {
        this.setFaxM(faxM);
        return this;
    }

    public void setFaxM(String faxM) {
        this.faxM = faxM;
    }

    public String getEmailM() {
        return this.emailM;
    }

    public Medecin emailM(String emailM) {
        this.setEmailM(emailM);
        return this;
    }

    public void setEmailM(String emailM) {
        this.emailM = emailM;
    }

    public String getHopital() {
        return this.hopital;
    }

    public Medecin hopital(String hopital) {
        this.setHopital(hopital);
        return this;
    }

    public void setHopital(String hopital) {
        this.hopital = hopital;
    }

    public String getService() {
        return this.service;
    }

    public Medecin service(String service) {
        this.setService(service);
        return this;
    }

    public void setService(String service) {
        this.service = service;
    }

    public byte[] getImage() {
        return this.image;
    }

    public Medecin image(byte[] image) {
        this.setImage(image);
        return this;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageContentType() {
        return this.imageContentType;
    }

    public Medecin imageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
        return this;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

    public String getLogin() {
        return this.login;
    }

    public Medecin login(String login) {
        this.setLogin(login);
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswd() {
        return this.passwd;
    }

    public Medecin passwd(String passwd) {
        this.setPasswd(passwd);
        return this;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getuRL() {
        return this.uRL;
    }

    public Medecin uRL(String uRL) {
        this.setuRL(uRL);
        return this;
    }

    public void setuRL(String uRL) {
        this.uRL = uRL;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Medecin)) {
            return false;
        }
        return getId() != null && getId().equals(((Medecin) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Medecin{" +
            "id=" + getId() +
            ", cIN=" + getcIN() +
            ", nomMedecin='" + getNomMedecin() + "'" +
            ", prenomMedecin='" + getPrenomMedecin() + "'" +
            ", grade='" + getGrade() + "'" +
            ", typeMedecin='" + getTypeMedecin() + "'" +
            ", gouvernoratM='" + getGouvernoratM() + "'" +
            ", adresseM='" + getAdresseM() + "'" +
            ", telM='" + getTelM() + "'" +
            ", posteM='" + getPosteM() + "'" +
            ", faxM='" + getFaxM() + "'" +
            ", emailM='" + getEmailM() + "'" +
            ", hopital='" + getHopital() + "'" +
            ", service='" + getService() + "'" +
            ", image='" + getImage() + "'" +
            ", imageContentType='" + getImageContentType() + "'" +
            ", login='" + getLogin() + "'" +
            ", passwd='" + getPasswd() + "'" +
            ", uRL='" + getuRL() + "'" +
            "}";
    }
}
