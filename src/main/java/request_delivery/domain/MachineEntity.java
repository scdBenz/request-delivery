package request_delivery.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "machines", indexes = {
        @Index(name = "idx_machine_number", columnList = "machine_number",  unique = true),
        @Index(name = "idx_machine_contractor", columnList = "contractor_id") //TODO почитать про индекс в бд. Зачем он нужен
})
public class MachineEntity extends BaseEntity {

    @Column(name = "machine_name",  nullable = false)
    private String machineName;

    @Column(name = "machine_number", nullable = false, unique = true)
    private String machineNumber;

    @Column(name = "cargo_volume",  nullable = false)
    private Double cargoVolume;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contractor_id",  nullable = false)
    private ContractorEntity contractor;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    public MachineEntity() {
    }

    public MachineEntity(String machineName,
                         String machineNumber,
                         Double cargoVolume,
                         ContractorEntity contractor,
                         Boolean isActive) {
        this.machineName = machineName;
        this.machineNumber = machineNumber;
        this.cargoVolume = cargoVolume;
        this.contractor = contractor;
        this.isActive = isActive;
    }

    public String getMachineName() {
        return machineName;
    }

    public void setMachineName(String machineName) {
        this.machineName = machineName;
    }

    public String getMachineNumber() {
        return machineNumber;
    }

    public void setMachineNumber(String machineNumber) {
        this.machineNumber = machineNumber;
    }

    public Double getCargoVolume() {
        return cargoVolume;
    }

    public void setCargoVolume(Double cargoVolume) {
        this.cargoVolume = cargoVolume;
    }

    public ContractorEntity getContractor() {
        return contractor;
    }

    public void setContractor(ContractorEntity contractor) {
        this.contractor = contractor;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "MachineEntity{" +
                "id=" + getId() +
                "machineName='" + machineName + '\'' +
                ", machineNumber='" + machineNumber + '\'' +
                ", cargoVolume=" + cargoVolume +
                ", contractor=" + contractor +
                ", isActive=" + isActive +
                '}';
    }
}
