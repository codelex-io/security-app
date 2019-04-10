package io.codelex.securityapp.repository;

import io.codelex.securityapp.api.*;
import io.codelex.securityapp.repository.models.Client;
import io.codelex.securityapp.repository.models.Incident;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class RepositoryClientService {
    private final ClientRepository clientRepository;

    public RepositoryClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client addClient(AddClientRequest request) {
        Client client = new Client(
                request.getFirstName(),
                request.getLastName()
        );
        client = clientRepository.save(client);
        return client;
    }
    
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

    public void deleteAll() {
        clientRepository.deleteAll();
    }

    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
