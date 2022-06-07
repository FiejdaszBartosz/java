package entity;

import javax.persistence.*;
import java.util.Collection;

@Entity

@Table(name = "person", schema = "labhibernate")

@NamedQuery(name = "PersonEntity.ById", query = "SELECT p FROM PersonEntity p where p.pId = ?1")
@NamedQuery(name = "PersonEntity.ByTown", query = "SELECT p FROM PersonEntity p, AddressInfoEntity a WHERE a.town = ?1 AND a.personByAiPId = p")
@NamedQuery(name = "PersonEntity.ByState", query = "SELECT p FROM PersonEntity p, AddressInfoEntity a WHERE a.state = ?1 AND a.personByAiPId = p")
@NamedQuery(name = "PersonEntity.ByCountry", query = "SELECT p FROM PersonEntity p, AddressInfoEntity a WHERE a.country = ?1 AND a.personByAiPId = p")
@NamedQuery(name = "PersonEntity.ByName", query = "SELECT p FROM PersonEntity p where p.pName = ?1")
@NamedQuery(name = "PersonEntity.BySurname", query = "SELECT p FROM PersonEntity p where p.pSurname = ?1")
@NamedQuery(name = "PersonEntity.ByEmail", query = "SELECT p FROM PersonEntity p where p.pEmail = ?1")
@NamedQuery(name = "DeleteAddressRecord", query = "Delete from AddressInfoEntity a where a.aiId = ?1")

public class PersonEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "p_id")
    private int pId;
    @Basic
    @Column(name = "p_name")
    private String pName;
    @Basic
    @Column(name = "p_surname")
    private String pSurname;
    @Basic
    @Column(name = "p_email")
    private String pEmail;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personByAiPId", fetch = FetchType.EAGER)
    private Collection<AddressInfoEntity> addressInfosByPId;

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpSurname() {
        return pSurname;
    }

    public void setpSurname(String pSurname) {
        this.pSurname = pSurname;
    }

    public String getpEmail() {
        return pEmail;
    }

    public void setpEmail(String pEmail) {
        this.pEmail = pEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonEntity that = (PersonEntity) o;

        if (pId != that.pId) return false;
        if (pName != null ? !pName.equals(that.pName) : that.pName != null) return false;
        if (pSurname != null ? !pSurname.equals(that.pSurname) : that.pSurname != null) return false;
        if (pEmail != null ? !pEmail.equals(that.pEmail) : that.pEmail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pId;
        result = 31 * result + (pName != null ? pName.hashCode() : 0);
        result = 31 * result + (pSurname != null ? pSurname.hashCode() : 0);
        result = 31 * result + (pEmail != null ? pEmail.hashCode() : 0);
        return result;
    }

    public Collection<AddressInfoEntity> getAddressInfosByPId() {
        return addressInfosByPId;
    }

    public void setAddressInfosByPId(Collection<AddressInfoEntity> addressInfosByPId) {
        this.addressInfosByPId = addressInfosByPId;
    }

    public String personEntityToString() {
        return getpName() + " " + getpSurname() + " " + getpEmail() + "\n";
    }
}
