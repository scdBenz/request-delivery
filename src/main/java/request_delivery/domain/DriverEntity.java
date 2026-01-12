package request_delivery.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "drivers", indexes = {
        @Index(name = "idx_driver_contact", columnList = "contact_number"),
        @Index(name = "idx_driver_contractor", columnList = "contractor_id")
})
public class DriverEntity extends BaseEntity {

    @Column(name = "name",  nullable = false)
    private String name;

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contractor_id",  nullable = false)
    private ContractorEntity contractor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "machine_id")
    private MachineEntity machine;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    public DriverEntity() {
    }

    public DriverEntity(String name,
                        String contactNumber,
                        ContractorEntity contractor,
                        MachineEntity machine,
                        Boolean isActive) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.contractor = contractor;
        this.machine = machine;
        this.isActive = isActive;
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

    public ContractorEntity getContractor() {
        return contractor;
    }

    public void setContractor(ContractorEntity contractor) {
        this.contractor = contractor;
    }

    public MachineEntity getMachine() {
        return machine;
    }

    public void setMachine(MachineEntity machine) {
        this.machine = machine;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "DriverEntity{" +
                "id=" + getId() +
                "name='" + name + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", contractor=" + contractor +
                ", machine=" + machine +
                ", isActive=" + isActive +
                '}';
    }
}
