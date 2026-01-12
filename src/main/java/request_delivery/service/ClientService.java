package request_delivery.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import request_delivery.domain.ClientEntity;
import request_delivery.repository.ClientRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public ClientEntity getClientById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Client with id " + id + " not found"));
    }

    public List<ClientEntity> findAllClients() {
        return repository.findAll();
    }

    public ClientEntity createClient(ClientEntity clientEntityToCreate) {
        //TODO optional попробовать
        if (clientEntityToCreate == null || clientEntityToCreate.getName() == null || clientEntityToCreate.getName().isBlank()) {
            throw new IllegalArgumentException("Client name is required");
        }
        return repository.save(clientEntityToCreate);
    }

    public ClientEntity updateClient(Long id, ClientEntity clientEntityToUpdate) {
        ClientEntity existing = getClientById(id);
        if (existing == null) {
            throw new EntityNotFoundException("Client with id " + id + " not found");
        }
        existing.setName(clientEntityToUpdate.getName());
        existing.setContactNumber(clientEntityToUpdate.getContactNumber());
        existing.setAddress(clientEntityToUpdate.getAddress());
        existing.setLegalEntity(clientEntityToUpdate.isLegalEntity());

        return repository.save(existing);

    }

    public void deleteClientById(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Client with id " + id + " not found");
        }
        repository.deleteById(id);
    }

}
