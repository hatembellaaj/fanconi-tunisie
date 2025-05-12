package tn.tfar.fanconi.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Cytogeneticien.
 */
@Entity
@Table(name = "cytogeneticien")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Cytogeneticien implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private Integer code;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "service")
    private String service;

    @Column(name = "etab")
    private String etab;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "tel")
    private String tel;

    @Column(name = "poste")
    private String poste;

    @Column(name = "fax")
    private String fax;

    @Column(name = "email")
    private String email;

    @Lob
    @Column(name = "photo")
    private byte[] photo;

    @Column(name = "photo_content_type")
    private String photoContentType;

    @Column(name = "type")
    private String type;

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

    public Cytogeneticien id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return this.code;
    }

    public Cytogeneticien code(Integer code) {
        this.setCode(code);
        return this;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getNom() {
        return this.nom;
    }

    public Cytogeneticien nom(String nom) {
        this.setNom(nom);
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public Cytogeneticien prenom(String prenom) {
        this.setPrenom(prenom);
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getService() {
        return this.service;
    }

    public Cytogeneticien service(String service) {
        this.setService(service);
        return this;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getEtab() {
        return this.etab;
    }

    public Cytogeneticien etab(String etab) {
        this.setEtab(etab);
        return this;
    }

    public void setEtab(String etab) {
        this.etab = etab;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public Cytogeneticien adresse(String adresse) {
        this.setAdresse(adresse);
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return this.tel;
    }

    public Cytogeneticien tel(String tel) {
        this.setTel(tel);
        return this;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPoste() {
        return this.poste;
    }

    public Cytogeneticien poste(String poste) {
        this.setPoste(poste);
        return this;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getFax() {
        return this.fax;
    }

    public Cytogeneticien fax(String fax) {
        this.setFax(fax);
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return this.email;
    }

    public Cytogeneticien email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPhoto() {
        return this.photo;
    }

    public Cytogeneticien photo(byte[] photo) {
        this.setPhoto(photo);
        return this;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPhotoContentType() {
        return this.photoContentType;
    }

    public Cytogeneticien photoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
        return this;
    }

    public void setPhotoContentType(String photoContentType) {
        this.photoContentType = photoContentType;
    }

    public String getType() {
        return this.type;
    }

    public Cytogeneticien type(String type) {
        this.setType(type);
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogin() {
        return this.login;
    }

    public Cytogeneticien login(String login) {
        this.setLogin(login);
        return this;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswd() {
        return this.passwd;
    }

    public Cytogeneticien passwd(String passwd) {
        this.setPasswd(passwd);
        return this;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getuRL() {
        return this.uRL;
    }

    public Cytogeneticien uRL(String uRL) {
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
        if (!(o instanceof Cytogeneticien)) {
            return false;
        }
        return getId() != null && getId().equals(((Cytogeneticien) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Cytogeneticien{" +
            "id=" + getId() +
            ", code=" + getCode() +
            ", nom='" + getNom() + "'" +
            ", prenom='" + getPrenom() + "'" +
            ", service='" + getService() + "'" +
            ", etab='" + getEtab() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", tel='" + getTel() + "'" +
            ", poste='" + getPoste() + "'" +
            ", fax='" + getFax() + "'" +
            ", email='" + getEmail() + "'" +
            ", photo='" + getPhoto() + "'" +
            ", photoContentType='" + getPhotoContentType() + "'" +
            ", type='" + getType() + "'" +
            ", login='" + getLogin() + "'" +
            ", passwd='" + getPasswd() + "'" +
            ", uRL='" + getuRL() + "'" +
            "}";
    }
}
