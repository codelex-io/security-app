package io.codelex.securityapp.repository;

import io.codelex.securityapp.api.*;
import io.codelex.securityapp.repository.models.ClientRepository;
import io.codelex.securityapp.repository.models.IncidentRepository;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicLong;

public class RepositoryClientService {
    private final ClientRepository clientRepository;
    private final IncidentRepository incidentRepository;
    private final AtomicLong id = new AtomicLong();

    public RepositoryClientService(ClientRepository clientRepository, IncidentRepository incidentRepository) {
        this.clientRepository = clientRepository;
        this.incidentRepository = incidentRepository;
    }

    public Client addClient(AddClientRequest request) {
        Client client = new Client(
                id.incrementAndGet(),
                request.getFirstName(),
                request.getLastName()
        );
        client = clientRepository.save(client);
        return client;
    }
    
    public Incident requestForHelp(Incident request) {
        Incident incident = new Incident(
                id.incrementAndGet(),
                clientRepository.getOne(2L),
                request.getLatitude(),
                request.getLongitude()
        );
        incident = incidentRepository.save(incident);
        return incident;
    }
    
    public void cancelRequestForHelp(Incident request) {
        incidentRepository.delete(request);
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
