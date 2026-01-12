package request_delivery.models.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import request_delivery.domain.RequestEntity;
import request_delivery.models.dto.CreateRequestDTO;
import request_delivery.models.dto.RequestDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequestMapper {

    // Response DTO ← Entity
    @Mapping(source = "client.name", target = "clientName")
    @Mapping(source = "client.contactNumber", target = "clientContact")
    @Mapping(source = "productType.name", target = "productTypeName")
    @Mapping(source = "contractor.companyName", target = "contractorName")
    @Mapping(source = "driver.name", target = "driverName")
    @Mapping(source = "machine.machineName", target = "machineName")
    @Mapping(source = "deliveryDate", target = "deliveryTime")
    RequestDTO toDto(RequestEntity entity);

    List<RequestDTO> toDtoList(List<RequestEntity> entities);

    // CreateRequestDTO → Entity (бизнес-логика в сервисе!)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "SUBMITTED")  // RequestStatus.SUBMITTED
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updateAt", ignore = true)
    @Mapping(target = "contractor", ignore = true)     // Система подберёт
    @Mapping(target = "driver", ignore = true)         // Система подберёт
    @Mapping(target = "machine", ignore = true)        // Система подберёт
    @Mapping(target = "productPrice", ignore = true)   // Рассчитать
    @Mapping(target = "deliveryCost", ignore = true)   // Рассчитать
    @Mapping(target = "totalCost", ignore = true)      // Рассчитать
    @Mapping(target = "deliveryAddress", source = "deliveryAddress")
    @Mapping(target = "deliveryDate", source = "deliveryDate")
    RequestEntity createDtoToEntity(CreateRequestDTO dto);
}
