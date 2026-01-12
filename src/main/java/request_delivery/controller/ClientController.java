package request_delivery.controller;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import request_delivery.models.dto.ClientDTO;
import request_delivery.domain.ClientEntity;
import request_delivery.models.mapper.ClientMapper;
import request_delivery.service.ClientService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    private static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    private ClientService clientService;
    private ClientMapper clientMapper;

    public ClientController(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @GetMapping
    public ResponseEntity<List<ClientDTO>> findAllClients() {
        logger.info("Finding all clients");
        List<ClientDTO> clients = clientService.findAllClients()
                .stream()
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable("id") Long id) {
        logger.info("Finding client by id {}", id);
        ClientEntity entity = clientService.getClientById(id);
        ClientDTO dto = clientMapper.toDTO(entity);
        return  ResponseEntity
                .status(HttpStatus.OK)
                .body(dto);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody @Valid ClientDTO clientDTO) {
        logger.info("Creating client");
        ClientEntity createToEntity = clientMapper.toEntity(clientDTO);
        ClientEntity created = clientService.createClient(createToEntity);
        ClientDTO responseDTO = clientMapper.toDTO(created);
        logger.info("Create client {}", responseDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.LOCATION, "/api/v1/clients/" + responseDTO.id())
                .body(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(
            @PathVariable("id") Long id,
            @RequestBody @Valid ClientDTO clientDTOToUpdate
    ) {
        logger.info("Updating client by id {}", id);
        ClientEntity updatedToEntity = clientMapper.toEntity(clientDTOToUpdate);
        ClientEntity updated =  clientService.updateClient(id, updatedToEntity);
        ClientDTO responseDTO = clientMapper.toDTO(updated);
        logger.info("Update client {}", responseDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id) {
        logger.info("Deleting client by id {}", id);
        clientService.deleteClientById(id);
        logger.info("Delete client by id {}", id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build(); //204 No Content
    }
}
