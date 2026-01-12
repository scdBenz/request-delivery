package request_delivery.service;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import request_delivery.domain.*;
import request_delivery.models.dto.CreateRequestDTO;
import request_delivery.models.dto.RequestDTO;
import request_delivery.models.mapper.RequestMapper;
import request_delivery.repository.*;
import request_delivery.validator.RequestValidator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RequestServiceimpl implements RequestService {

    private static final Logger log = LoggerFactory.getLogger(RequestServiceimpl.class);

    private final RequestRepository requestRepository;
    private final ClientRepository clientRepository;
    private final ProductTypeRepository productTypeRepository;
    private final ContractorRepository contractorRepository;
    private final DriverRepository driverRepository;
    private final PriceCalculationService priceCalculationService;
    private final RequestValidator requestValidator;
    private final RequestMapper requestMapper;
    private final PriceCalculationServiceimpl priceCalculationServiceimpl;
    private final MachineRepository machineRepository;

    public RequestServiceimpl(RequestRepository requestRepository,
                              ClientRepository clientRepository,
                              ProductTypeRepository productTypeRepository,
                              ContractorRepository contractorRepository,
                              DriverRepository driverRepository,
                              PriceCalculationService priceCalculationService,
                              RequestValidator requestValidator,
                              RequestMapper requestMapper, PriceCalculationServiceimpl priceCalculationServiceimpl, MachineRepository machineRepository) {
        this.requestRepository = requestRepository;
        this.clientRepository = clientRepository;
        this.productTypeRepository = productTypeRepository;
        this.contractorRepository = contractorRepository;
        this.driverRepository = driverRepository;
        this.priceCalculationService = priceCalculationService;
        this.requestValidator = requestValidator;
        this.requestMapper = requestMapper;
        this.priceCalculationServiceimpl = priceCalculationServiceimpl;
        this.machineRepository = machineRepository;
    }

    @Override
    public RequestDTO createRequest(CreateRequestDTO createRequestDTO) {
        log.info("Начало создания новой заявки для клиента ID: {}", createRequestDTO.clientId());

        var client =  clientRepository.findById(createRequestDTO.clientId())
                .orElseThrow(()-> new EntityNotFoundException(
                        "Клиент с ID " + createRequestDTO.clientId() + "не найден."
                ));

        var productType = productTypeRepository.findById(createRequestDTO.productTypeId())
                .orElseThrow(()-> new EntityNotFoundException(
                        "Тип продукции с ID" + createRequestDTO.productTypeId() + "не найден."
                ));

        if (!productType.getActive()){
            throw new EntityNotFoundException("Выбранный тип продукции не активен");
        }

        log.debug("Получены сущности: клиент={}, продукт={}", client.getId(), productType.getName());

        BigDecimal productCost = priceCalculationServiceimpl.calculateProductPrice(
                createRequestDTO.productVolume(),
                productType.getBasePice()
        );

        log.debug("Рассчитана стоимость продукции: {}", productCost);

        BigDecimal deliveryCost = priceCalculationServiceimpl.calculateDeliveryPrice(
                createRequestDTO.productVolume(),
                createRequestDTO.distanceKm()
        );

        log.debug("Рассчитана стоимость доставки: {}", deliveryCost);

        BigDecimal totalCost = priceCalculationServiceimpl.calculateTotalCost(
                productCost,
                deliveryCost
        );

        log.debug("Рассчитана итоговая стоимость заявки: {}", totalCost);

        MachineEntity machine = selectAvailableMachine();
        if (machine == null){
            log.warn("Нет доступных машин для создания заявки");
//            throw new BusinessLogicException("На данный момент нет доступных машин для обработки заявки");
        }
        ContractorEntity contractor = machine.getContractor();

        DriverEntity driver = driverRepository
                .findFirstByMachineIdAndIsActiveTrue(machine.getId())
                .orElse(null);

        log.debug("По активной машине найдены....");

        RequestEntity request = new RequestEntity(
                client,
                productType,
                createRequestDTO.productVolume(),
                contractor,
                driver,
                machine,
                createRequestDTO.deliveryDate(),
                createRequestDTO.deliveryAddress(),
                productCost,
                deliveryCost,
                totalCost,
                RequestStatus.SUBMITTED
        );

        RequestEntity savedRequest = requestRepository.save(request);

        log.info("Заявка успешно создана: ID={}, клиент={}, статус={}, сумма={}",
                savedRequest.getId(), client.getName(), savedRequest.getStatus(), totalCost);


        return requestMapper.toDto(savedRequest);
    }

    @Override
    public Optional<RequestDTO> getRequestById(Long id) {
        log.debug("Получение заявки ID: {}", id);

        return requestRepository.findById(id)
                .map(requestMapper::toDto);
    }

    @Override
    public List<RequestDTO> getAllRequests() {
        List<RequestEntity> requests = requestRepository.findAll();
        return requests.stream()
                .map(requestMapper::toDto)
                .toList();
    }

    @Override
    public List<RequestDTO> getRequestsByClientId(Long clientId) {
        log.debug("Получение заявок клиента ID: {}", clientId);
        if (!clientRepository.existsById(clientId)) {
            throw new EntityNotFoundException("Клиент с ID " + clientId + "не найден");
        }

        List<RequestEntity> requests = requestRepository.findByClientId(clientId);
        return requests.stream()
                .map(requestMapper::toDto)
                .toList();
    }

    @Override
    public List<RequestDTO> getRequestsByStatus(RequestStatus status) {
        log.debug("Получение заявок со статусом: {}", status);
        List<RequestEntity> requests = requestRepository.findByStatus(status);
        return requests.stream()
                .map(requestMapper::toDto)
                .toList();
    }

    @Override
    public RequestDTO updateRequestStatus(Long requestId, RequestStatus newStatus) {
        log.info("Обновление статуса заявки ID : {} на {}", requestId, newStatus);
        RequestEntity request = requestRepository.findById(requestId)
                .orElseThrow(()-> new EntityNotFoundException("Заявка с ID" + requestId +" не найдена"));

        requestValidator.validateStatusTransition(request.getStatus(), newStatus);

        request.setStatus(newStatus);
        RequestEntity updateRequest = requestRepository.save(request);
        return requestMapper.toDto(updateRequest);
    }

    @Override
    public void cancelRequest(Long requestId) {
        log.info("Отмена заявки ID: {}", requestId);
        RequestEntity request = requestRepository.findById(requestId)
                .orElseThrow(()-> new EntityNotFoundException("Завка  с ID" + requestId + "не найден"));

        if (request.getStatus() == RequestStatus.CANCELLED ||
        request.getStatus() == RequestStatus.COMPLETED) {
            throw new EntityNotFoundException("Нельзя отменить заявку со статусом " + request.getStatus());
        }

        request.setStatus(RequestStatus.CANCELLED);
        requestRepository.save(request);

        log.info("Заявка ID: {} успешно отменена", requestId);
    }

    private MachineEntity selectAvailableMachine(){
        log.debug("Выбор доступной машины");
        return machineRepository.findFirstByIsActiveTrueOrderByCargoVolumeDesc().orElse(null);
    }
}
