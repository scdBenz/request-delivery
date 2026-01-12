package request_delivery.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "requsets", indexes = {
        @Index(name = "idx_request_status", columnList = "status"),
        @Index(name = "idx_request_client_id", columnList = "client_id"),
        @Index(name = "idx_request_contractor_id", columnList = "contractor_id"),
        @Index(name = "idx_request_created_at", columnList = "created_at"),
})
public class RequestEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_type_id")
    private ProductTypeEntity productType;

    @Column(name = "product_volume", nullable = false)
    private Double productVolume;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contractor_id")
    private ContractorEntity contractor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private DriverEntity driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "machine_id")
    private MachineEntity machine;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "product_price", nullable = false)
    private BigDecimal productPrice;

    @Column(name = "delivery_cost", nullable = false)
    private BigDecimal deliveryCost;

    @Column(name = "total_cost", nullable = false)
    private BigDecimal totalCost;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RequestStatus status;


    public RequestEntity() {
    }

    public RequestEntity(ClientEntity client,
                         ProductTypeEntity productType,
                         Double productVolume,
                         ContractorEntity contractor,
                         DriverEntity driver,
                         MachineEntity machine,
                         LocalDateTime deliveryDate,
                         String deliveryAddress,
                         BigDecimal productPrice,
                         BigDecimal deliveryCost,
                         BigDecimal totalCost,
                         RequestStatus status) {
        this.client = client;
        this.productType = productType;
        this.productVolume = productVolume;
        this.contractor = contractor;
        this.driver = driver;
        this.machine = machine;
        this.deliveryDate = deliveryDate;
        this.deliveryAddress = deliveryAddress;
        this.productPrice = productPrice;
        this.deliveryCost = deliveryCost;
        this.totalCost = totalCost;
        this.status = status;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public ProductTypeEntity getProductType() {
        return productType;
    }

    public void setProductType(ProductTypeEntity productType) {
        this.productType = productType;
    }

    public Double getProductVolume() {
        return productVolume;
    }

    public void setProductVolume(Double productVolume) {
        this.productVolume = productVolume;
    }

    public ContractorEntity getContractor() {
        return contractor;
    }

    public void setContractor(ContractorEntity contractor) {
        this.contractor = contractor;
    }

    public DriverEntity getDriver() {
        return driver;
    }

    public void setDriver(DriverEntity driver) {
        this.driver = driver;
    }

    public MachineEntity getMachine() {
        return machine;
    }

    public void setMachine(MachineEntity machine) {
        this.machine = machine;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(BigDecimal deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RequestEntity{" +
                "id=" + getId() +
                "client=" + client +
                ", productType=" + productType +
                ", productVolume=" + productVolume +
                ", contractor=" + contractor +
                ", driver=" + driver +
                ", machine=" + machine +
                ", deliveryDate=" + deliveryDate +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", productPrice=" + productPrice +
                ", deliveryCost=" + deliveryCost +
                ", totalCost=" + totalCost +
                ", status=" + status +
                '}';
    }
}
