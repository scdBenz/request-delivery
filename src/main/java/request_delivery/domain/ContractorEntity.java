package request_delivery.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "contractors", indexes = {
        @Index(name = "idx_contractor_inn", columnList = "inn",  unique = true)
})
public class ContractorEntity extends BaseEntity {

    @Column(name = "company_name",  nullable = false)
    private String companyName;

    @Column(name = "contractor_person")
    private String contractorPerson;

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "inn",  nullable = false, unique = true)
    private String inn;

    @Column(name = "address",  nullable = false)
    private String address;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = Boolean.TRUE;

    @OneToMany(mappedBy = "contractor",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<DriverEntity> drivers = new ArrayList<>();

    @OneToMany(mappedBy = "contractor",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<MachineEntity> machines = new ArrayList<>();

    public ContractorEntity() {
    }

    public ContractorEntity(String companyName,
                            String contractorPerson,
                            String contactNumber,
                            String email, String inn,
                            String address, Boolean isActive,
                            List<DriverEntity> drivers,
                            List<MachineEntity> machines) {
        this.companyName = companyName;
        this.contractorPerson = contractorPerson;
        this.contactNumber = contactNumber;
        this.email = email;
        this.inn = inn;
        this.address = address;
        this.isActive = isActive;
        this.drivers = drivers;
        this.machines = machines;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContractorPerson() {
        return contractorPerson;
    }

    public void setContractorPerson(String contractorPerson) {
        this.contractorPerson = contractorPerson;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public List<DriverEntity> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<DriverEntity> drivers) {
        this.drivers = drivers;
    }

    public List<MachineEntity> getMachines() {
        return machines;
    }

    public void setMachines(List<MachineEntity> machines) {
        this.machines = machines;
    }

    @Override
    public String toString() {
        return "ContractorEntity{" +
                "id=" + getId() +
                "companyName='" + companyName + '\'' +
                ", contractorPerson='" + contractorPerson + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", email='" + email + '\'' +
                ", inn='" + inn + '\'' +
                ", address='" + address + '\'' +
                ", isActive=" + isActive +
                ", drivers=" + drivers +
                ", machines=" + machines +
                '}';
    }
}
