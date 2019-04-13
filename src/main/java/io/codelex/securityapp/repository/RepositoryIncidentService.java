package io.codelex.securityapp.repository;

import io.codelex.securityapp.NotificationService;
import io.codelex.securityapp.api.AddIncidentRequest;
import io.codelex.securityapp.repository.models.Client;
import io.codelex.securityapp.repository.models.Incident;
import io.codelex.securityapp.repository.models.Unit;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class RepositoryIncidentService {


    private final SimpleNearestUnitService simpleNearestUnitService;
    private final IncidentRepository repository;
    private final ClientRepository clientRepository;
    private final NotificationService notificationService;

    public RepositoryIncidentService(SimpleNearestUnitService simpleNearestUnitService,
                                     IncidentRepository repository,
                                     ClientRepository clientRepository, NotificationService notificationService) {
        this.simpleNearestUnitService = simpleNearestUnitService;
        this.repository = repository;
        this.clientRepository = clientRepository;
        this.notificationService = notificationService;
    }

    public Incident addIncident(AddIncidentRequest request) {
        Client incidentClient = clientRepository.findClientByEmail(request.getEmail());

        Incident incident = new Incident(
                incidentClient,
                request.getLatitude(),
                request.getLongitude()
        );
        incident = repository.save(incident);

        notificationService.sendNotification("Client " + incidentClient.getEmail() + " request for incident received");
        Unit respondingUnit = simpleNearestUnitService.searchNearestUnit(incident);

        notificationService.sendNotification("Notification to the closest unit with Id: " + respondingUnit.getId() + " sent");
        return incident;
    }

    public Incident findById(Long id) {
        return repository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

}
