package request_delivery.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "clients")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",  nullable = false)
    private String name;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "is_legal_entity")
    private boolean legalEntity;

    public ClientEntity() {
    }

    public ClientEntity(Long id, String name, String contactNumber, String address, boolean legalEntity) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
        this.address = address;
        this.legalEntity = legalEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isLegalEntity() {
        return legalEntity;
    }

    public void setLegalEntity(boolean legalEntity) {
        this.legalEntity = legalEntity;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", address='" + address + '\'' +
                ", legalEntity=" + legalEntity +
                '}';
    }
}
