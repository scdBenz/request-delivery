package request_delivery.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import request_delivery.domain.RequestStatus;
import request_delivery.models.dto.CreateRequestDTO;
import request_delivery.models.dto.RequestDTO;
import request_delivery.service.RequestServiceimpl;

import java.util.List;

@RestController
@RequestMapping("/api/v1/requests")
public class RequestController {

    private static final Logger log = LoggerFactory.getLogger(ClientController.class);


    private RequestServiceimpl requestServiceimpl;

    public RequestController(RequestServiceimpl requestServiceimpl) {
        this.requestServiceimpl = requestServiceimpl;
    }

    /**
     * Создание новой заявки
     */

    @PostMapping
    public ResponseEntity<RequestDTO> createRequest(
            @Valid @RequestBody CreateRequestDTO createRequestDTO) {
        log.info("POST /api/v1/requests - Создание новой заявки для клиента ID:{}",
                createRequestDTO.clientId());
        RequestDTO createdRequest = requestServiceimpl.createRequest(createRequestDTO);
        log.info("Заявка успешно создана: ID ={}", createdRequest.id());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header(HttpHeaders.LOCATION, "/api/v1/requests/" + createdRequest.id())
                .body(createdRequest);
    }

    /**
     * Получение заявки по ID
     */

    @GetMapping("/{id}")
    public ResponseEntity<RequestDTO> getRequestById(@PathVariable("id") Long id) {
        log.info("GET /api/v1/requests/{} - Finding request(Получение заявки)", id);

        return requestServiceimpl.getRequestById(id)
                .map(r -> {
                    log.info("Завка ID={} найдена", id);
                    return ResponseEntity.ok(r);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Получение всех заявок
     */

    @GetMapping
    public ResponseEntity<List<RequestDTO>> getAllRequests() {
        log.info("GET /api/v1/requests - получение всех заявок");
        List<RequestDTO> allRequests = requestServiceimpl.getAllRequests();
        log.info("Получено {} заявок",  allRequests.size());
        return ResponseEntity.ok(allRequests);
    }

    /**
     * Получение заявок клиента по ID
     */

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<RequestDTO>> getRequestsByClientId(@PathVariable("clientId") Long clientId) {
        log.info("GET /api/v1/requests/client/{} - получение заявок клиента", clientId);
        List<RequestDTO> allRequests = requestServiceimpl.getRequestsByClientId(clientId);
        log.info("Получено {} заявок клиента {}", allRequests.size(), clientId);
        return ResponseEntity.ok(allRequests);
    }

    /**
     * Получение заявок по статусу
     */

    @GetMapping("/by-status")
    public ResponseEntity<List<RequestDTO>> getRequestsByStatus(@RequestParam RequestStatus status) {
        log.info("GET /api/v1/requests/by-status - Получение заявок со статусом {}", status);
        List<RequestDTO> allRequests = requestServiceimpl.getRequestsByStatus(status);
        log.info("Получено {} заявок со статусом {}", allRequests.size(), status);
        return ResponseEntity.ok(allRequests);
    }

    /**
     * Обновление статуса заявки
     */

    @PatchMapping("/{id}/status")
    public ResponseEntity<RequestDTO> updateRequestStatus(
            @PathVariable("id") Long id,
            @RequestParam RequestStatus newStatus)
    {
        log.info("PATH /api/v1/requests/{}/status - Обновление статуса заявки на {}", id, newStatus);
        RequestDTO updatedRequest = requestServiceimpl.updateRequestStatus(id, newStatus);
        log.info("Статус заявки {} успешно изменен на {}",  id, newStatus);
        return ResponseEntity.ok(updatedRequest);
    }

    /**
     * Отмена заявки
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<RequestDTO> deleteRequest(@PathVariable("id") Long id) {
        log.info("DELETE /api/v1/requests/{} - Отмена заявки", id);
        requestServiceimpl.cancelRequest(id);
        log.info("Заявка {} успешно отменена", id);
        return ResponseEntity.noContent().build();
    }
}
