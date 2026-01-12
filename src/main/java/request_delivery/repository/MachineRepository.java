package request_delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import request_delivery.domain.MachineEntity;

import java.util.Optional;

public interface MachineRepository extends JpaRepository<MachineEntity, Long> {
    Optional<MachineEntity> findFirstByIsActiveTrueOrderByCargoVolumeDesc();
}
