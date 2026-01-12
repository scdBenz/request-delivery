package request_delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import request_delivery.domain.ContractorEntity;

import java.util.Optional;

public interface ContractorRepository extends JpaRepository<ContractorEntity, Long> {
//    Optional<ContractorEntity> findFirstByMachineIdAndIsActiveTrue(Long machineId);;
}
