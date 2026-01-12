package request_delivery.models.mapper;

import org.springframework.stereotype.Component;
import request_delivery.models.dto.ClientDTO;
import request_delivery.domain.ClientEntity;

@Component
public class ClientMapper {

    public ClientDTO toDTO(ClientEntity entity) {
        if (entity == null) {
            return null;
        }
        return new ClientDTO(
                entity.getId(),
                entity.getName(),
                entity.getContactNumber(),
                entity.getAddress(),
                entity.isLegalEntity()
        );
    }

    public ClientEntity toEntity(ClientDTO dto) {
        if (dto == null) {
            return null;
        }
        return new ClientEntity(
                dto.id(),
                dto.name(),
                dto.contactNumber(),
                dto.address(),
                dto.legalEntity()
        );
    }
}
