package request_delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import request_delivery.domain.ProductTypeEntity;

public interface ProductTypeRepository extends JpaRepository<ProductTypeEntity, Long> {
}
