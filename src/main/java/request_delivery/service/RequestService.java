package request_delivery.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import request_delivery.domain.RequestStatus;
import request_delivery.models.dto.CreateRequestDTO;
import request_delivery.models.dto.RequestDTO;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public interface RequestService {

    RequestDTO createRequest(CreateRequestDTO createRequestDTO);

    Optional<RequestDTO> getRequestById(Long id);

    List<RequestDTO> getAllRequests();

    List<RequestDTO> getRequestsByClientId(Long clientId);

    List<RequestDTO> getRequestsByStatus(RequestStatus status);

    RequestDTO updateRequestStatus(Long requestId, RequestStatus newStatus);

    void cancelRequest(Long requestId);
}
