package request_delivery.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record CreateRequestDTO(
        @NotNull
        Long clientId,

        @NotNull
        Long productTypeId,

        @NotNull
        @Positive
        Double productVolume,

        @NotNull
        LocalDateTime deliveryDate,

        @NotNull
        String deliveryAddress,

        @NotNull
        @Positive
        Integer distanceKm
) {
}
