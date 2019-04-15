package io.codelex.securityapp.repository;

import io.codelex.securityapp.api.*;
import io.codelex.securityapp.repository.models.Client;
import org.springframework.stereotype.Component;
import java.util.NoSuchElementException;

@Component
public class RepositoryClientService{
    private final ClientRepository clientRepository;

    public RepositoryClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client addClient(AddClientRequest request) {
        Client client = new Client(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword()); //todo password encryption
        client = clientRepository.save(client);
        return client;
    }

    public boolean isEmailPresent(String email) {
        return clientRepository.isEmailPresent(email);
    }

    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}
