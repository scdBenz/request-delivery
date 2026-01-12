package request_delivery.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public record ClientDTO(
        @Null
        Long id,
        @NotNull
        String name,
        @NotNull
        String contactNumber,
        @NotNull
        String address,
        boolean legalEntity

) {}


