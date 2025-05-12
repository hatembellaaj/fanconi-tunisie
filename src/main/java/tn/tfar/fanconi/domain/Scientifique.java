package tn.tfar.fanconi.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Scientifique.
 */
@Entity
@Table(name = "scientifique")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Scientifique implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "code_sc")
    private Integer codeSC;

    @Column(name = "nom_sc")
    private String nomSC;

    @Column(name = "prenom_sc")
    private String prenomSC;

    @Column(name = "service_sc")
    private String serviceSC;

    @Column(name = "centre_sc")
    private String centreSC;

    @Column(name = "adresse_sc")
    private String adresseSC;

    @Column(name = "tel_sc")
    private String telSC;

    @Column(name = "email_sc")
    private String emailSC;

    @Lob
    @Column(name = "photo_sc")
    private byte[] photoSC;

    @Column(name = "photo_sc_content_type")
    private String photoSCContentType;

    @Column(name = "type_sc")
    private String typeSC;

    @Column(name = "login_sc")
    private String loginSC;

    @Column(name = "passwd_sc")
    private String passwdSC;

    @Column(name = "u_rl")
    private String uRL;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Scientifique id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCodeSC() {
        return this.codeSC;
    }

    public Scientifique codeSC(Integer codeSC) {
        this.setCodeSC(codeSC);
        return this;
    }

    public void setCodeSC(Integer codeSC) {
        this.codeSC = codeSC;
    }

    public String getNomSC() {
        return this.nomSC;
    }

    public Scientifique nomSC(String nomSC) {
        this.setNomSC(nomSC);
        return this;
    }

    public void setNomSC(String nomSC) {
        this.nomSC = nomSC;
    }

    public String getPrenomSC() {
        return this.prenomSC;
    }

    public Scientifique prenomSC(String prenomSC) {
        this.setPrenomSC(prenomSC);
        return this;
    }

    public void setPrenomSC(String prenomSC) {
        this.prenomSC = prenomSC;
    }

    public String getServiceSC() {
        return this.serviceSC;
    }

    public Scientifique serviceSC(String serviceSC) {
        this.setServiceSC(serviceSC);
        return this;
    }

    public void setServiceSC(String serviceSC) {
        this.serviceSC = serviceSC;
    }

    public String getCentreSC() {
        return this.centreSC;
    }

    public Scientifique centreSC(String centreSC) {
        this.setCentreSC(centreSC);
        return this;
    }

    public void setCentreSC(String centreSC) {
        this.centreSC = centreSC;
    }

    public String getAdresseSC() {
        return this.adresseSC;
    }

    public Scientifique adresseSC(String adresseSC) {
        this.setAdresseSC(adresseSC);
        return this;
    }

    public void setAdresseSC(String adresseSC) {
        this.adresseSC = adresseSC;
    }

    public String getTelSC() {
        return this.telSC;
    }

    public Scientifique telSC(String telSC) {
        this.setTelSC(telSC);
        return this;
    }

    public void setTelSC(String telSC) {
        this.telSC = telSC;
    }

    public String getEmailSC() {
        return this.emailSC;
    }

    public Scientifique emailSC(String emailSC) {
        this.setEmailSC(emailSC);
        return this;
    }

    public void setEmailSC(String emailSC) {
        this.emailSC = emailSC;
    }

    public byte[] getPhotoSC() {
        return this.photoSC;
    }

    public Scientifique photoSC(byte[] photoSC) {
        this.setPhotoSC(photoSC);
        return this;
    }

    public void setPhotoSC(byte[] photoSC) {
        this.photoSC = photoSC;
    }

    public String getPhotoSCContentType() {
        return this.photoSCContentType;
    }

    public Scientifique photoSCContentType(String photoSCContentType) {
        this.photoSCContentType = photoSCContentType;
        return this;
    }

    public void setPhotoSCContentType(String photoSCContentType) {
        this.photoSCContentType = photoSCContentType;
    }

    public String getTypeSC() {
        return this.typeSC;
    }

    public Scientifique typeSC(String typeSC) {
        this.setTypeSC(typeSC);
        return this;
    }

    public void setTypeSC(String typeSC) {
        this.typeSC = typeSC;
    }

    public String getLoginSC() {
        return this.loginSC;
    }

    public Scientifique loginSC(String loginSC) {
        this.setLoginSC(loginSC);
        return this;
    }

    public void setLoginSC(String loginSC) {
        this.loginSC = loginSC;
    }

    public String getPasswdSC() {
        return this.passwdSC;
    }

    public Scientifique passwdSC(String passwdSC) {
        this.setPasswdSC(passwdSC);
        return this;
    }

    public void setPasswdSC(String passwdSC) {
        this.passwdSC = passwdSC;
    }

    public String getuRL() {
        return this.uRL;
    }

    public Scientifique uRL(String uRL) {
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
        if (!(o instanceof Scientifique)) {
            return false;
        }
        return getId() != null && getId().equals(((Scientifique) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Scientifique{" +
            "id=" + getId() +
            ", codeSC=" + getCodeSC() +
            ", nomSC='" + getNomSC() + "'" +
            ", prenomSC='" + getPrenomSC() + "'" +
            ", serviceSC='" + getServiceSC() + "'" +
            ", centreSC='" + getCentreSC() + "'" +
            ", adresseSC='" + getAdresseSC() + "'" +
            ", telSC='" + getTelSC() + "'" +
            ", emailSC='" + getEmailSC() + "'" +
            ", photoSC='" + getPhotoSC() + "'" +
            ", photoSCContentType='" + getPhotoSCContentType() + "'" +
            ", typeSC='" + getTypeSC() + "'" +
            ", loginSC='" + getLoginSC() + "'" +
            ", passwdSC='" + getPasswdSC() + "'" +
            ", uRL='" + getuRL() + "'" +
            "}";
    }
}
