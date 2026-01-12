package request_delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import request_delivery.domain.DriverEntity;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<DriverEntity, Long> {
    Optional<DriverEntity> findFirstByMachineIdAndIsActiveTrue(Long machineId);
}
