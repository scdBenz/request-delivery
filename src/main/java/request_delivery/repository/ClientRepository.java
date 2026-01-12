package request_delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import request_delivery.domain.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
