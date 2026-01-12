package request_delivery.exception;

import java.time.LocalDateTime;

public record ErrorResponseDto(
        String code,
        String message,
        LocalDateTime errorTime
) {

}
