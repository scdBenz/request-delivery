package request_delivery.models.dto;

import request_delivery.domain.ClientEntity;
import request_delivery.domain.ContractorEntity;
import request_delivery.domain.RequestStatus;

import java.time.LocalDateTime;

public record RequestDTO(
        Long id,
        String clientName,
        String clientContact,
        String productTypeName,
        Double productVolume,
        String contractorName,
        String driverName,
        String machineName,
        LocalDateTime deliveryTime

) {
}
