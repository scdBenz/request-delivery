package request_delivery.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import request_delivery.domain.RequestStatus;
import request_delivery.models.dto.CreateRequestDTO;

import java.util.Map;
import java.util.Set;

/**
 * Валидатор для бизнес-логики заявок.
 * Содержит правила валидации, которые не покрываются @NotNull, @Valid и т.д.
 */
@Component
public class RequestValidator {

    private static final Logger log = LoggerFactory.getLogger(RequestValidator.class);

    public void validateCreateRequestDTO(CreateRequestDTO dto){
        log.debug("Validating request DTO");

    }

    /**
     * Валидация переходов статуса заявок
     * DRAFT - переход возможен на SUBMITTED(Заявка подана)/CANCELLED(Заявка отменена)
     * SUBMITTED - переход возможен на CONFIRMED(Заявка подтверждена)/CANCELLED(Заявка отменена)
     * CONFIRMED - переход возможен на IN_PROGRESS(Заявка в проецессе исполнения)/CANCELLED(Заявка отменена)
     * IN_PROGRESS - переход возможен на COMPLETED(Заявка завершена)
     * COMPLETED - переход не возможен т.к. заявка завершена
     * CANCELLED - переход не возможен т.к заявка отменена
     */

    public void validateStatusTransition(RequestStatus currentStatus, RequestStatus newStatus){
        log.debug("Валидация перехода статуса: {} -> {}", currentStatus, newStatus);

        var validTransition = Map.of(
            RequestStatus.DRAFT, Set.of(RequestStatus.SUBMITTED, RequestStatus.CANCELLED),
            RequestStatus.SUBMITTED, Set.of(RequestStatus.CONFIRMED, RequestStatus.CANCELLED),
            RequestStatus.CONFIRMED, Set.of(RequestStatus.IN_PROGRESS, RequestStatus.CANCELLED),
            RequestStatus.IN_PROGRESS, Set.of(RequestStatus.COMPLETED),
            RequestStatus.COMPLETED, Set.of(),
            RequestStatus.CANCELLED, Set.of()
        );

        var allowedStatuses = validTransition.getOrDefault(currentStatus, Set.of());

        if(!allowedStatuses.contains(newStatus)){
            throw new IllegalArgumentException(
                    "Невозможно изменить статус с " + currentStatus + "на " + newStatus);
        }
        log.debug("Переход статуса валиден");
    }
}
