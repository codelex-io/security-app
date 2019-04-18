package io.codelex.securityapp.repository;

import io.codelex.securityapp.Password;
import io.codelex.securityapp.api.*;
import io.codelex.securityapp.repository.models.Client;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class RepositoryClientService {

    private Password encoder;
    private final ClientRepository clientRepository;

    public RepositoryClientService(Password encoder, ClientRepository clientRepository) {
        this.encoder = encoder;
        this.clientRepository = clientRepository;
    }

    public Client addClient(AddClientRequest request) {
        Client client = new Client(
                inputValidator(request.getFirstName()),
                inputValidator(request.getLastName()),
                request.getEmail(),
                encoder.passwordEncoder().encode(request.getPassword()));
        client = clientRepository.save(client);
        return client;
    }

    public boolean isEmailPresent(String email) {
        return clientRepository.isEmailPresent(email);
    }

    public boolean isPasswordMatching(String email, String password) {
        Client client = getClientByEmail(email);
        return encoder.passwordEncoder().matches(password, client.getPassword());
    }


    public Client findById(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    private Client getClientByEmail(String email) {
        return clientRepository.findClientByEmail(email);
    }

    private String inputValidator(String input) {
        String correct = input.toLowerCase().replaceAll("\\s+", "");
        return correct.toLowerCase().trim().substring(0, 1).toUpperCase() + correct.substring(1);
    }
}
