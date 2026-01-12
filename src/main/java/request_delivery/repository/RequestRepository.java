package request_delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import request_delivery.domain.RequestEntity;
import request_delivery.domain.RequestStatus;

import java.util.List;

public interface RequestRepository extends JpaRepository<RequestEntity, Long> {

    List<RequestEntity> findByClientId(Long clientId);

    List<RequestEntity> findByStatus(RequestStatus status);

    List<RequestEntity> findByContractorId(Long contractorId);

    List<RequestEntity> findByDriverId(Long driverId);

    List<RequestEntity> findByMachineId(Long machineId);


}
