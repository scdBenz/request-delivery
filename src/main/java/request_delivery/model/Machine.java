package request_delivery.model;

public class Machine {
    private Long id;
    private String machineName;
    private String machineNumber;
    private Double cargoVolume;

    public Machine() {
    }

    public Machine(Long id, String machineName, String machineNumber, Double cargoVolume) {
        this.id = id;
        this.machineName = machineName;
        this.machineNumber = machineNumber;
        this.cargoVolume = cargoVolume;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Machine{" +
                "id=" + id +
                ", machineName='" + machineName + '\'' +
                ", machineNumber='" + machineNumber + '\'' +
                ", cargoVolume=" + cargoVolume +
                '}';
    }
}
