package request_delivery.domain;


import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @Param name - наименование продукции на предприятии.
 * @Param description - описание продукции (Заглушка, чтобы не добавлять много параметров.
 * К примеру, пластичность, морозостойкость и т.д.)
 * @Param basePrice - стоимость за единицу измерения.
 * @Param unitOfMeasure - единица измерения(м3, кг, тонна)
 * @Param isActive - проверка наличия продукции на предприятии.
 */

@Entity
@Table(name = "product_types", indexes = {
        @Index(name = "idx_product_type_name", columnList = "name", unique = true)
})
public class ProductTypeEntity extends BaseEntity { //TODO

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "base_price", nullable = false)
    private BigDecimal basePice;

    @Column(name = "unit_of_measure",  nullable = false)
    private String unitOfMeasure;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = Boolean.TRUE;

    public ProductTypeEntity() {
    }

    public ProductTypeEntity(String name,
                             String description,
                             BigDecimal basePice,
                             String unitOfMeasure,
                             Boolean isActive) {
        this.name = name;
        this.description = description;
        this.basePice = basePice;
        this.unitOfMeasure = unitOfMeasure;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBasePice() {
        return basePice;
    }

    public void setBasePice(BigDecimal basePice) {
        this.basePice = basePice;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "ProductTypeEntity{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", basePice=" + basePice +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
