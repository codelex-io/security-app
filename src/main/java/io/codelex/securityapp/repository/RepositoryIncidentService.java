package io.codelex.securityapp.repository;

import io.codelex.securityapp.NotificationService;
import io.codelex.securityapp.api.AddIncidentRequest;
import io.codelex.securityapp.repository.models.Client;
import io.codelex.securityapp.repository.models.Incident;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Component
public class RepositoryIncidentService {


    private final SimpleNearestUnitService simpleNearestUnitService;
    private final IncidentRepository repository;
    private final NotificationService notificationService;

    public RepositoryIncidentService(SimpleNearestUnitService simpleNearestUnitService,
                                     IncidentRepository repository,
                                     NotificationService notificationService) {
        this.simpleNearestUnitService = simpleNearestUnitService;
        this.repository = repository;
        this.notificationService = notificationService;
    }

    public Incident addIncident(AddIncidentRequest request) {
        Incident incident = new Incident(
                new Client("name", "surname", "email", "password"),
                request.getLatitude().setScale(6, RoundingMode.DOWN),
                request.getLongitude().setScale(6, RoundingMode.DOWN),
                LocalDateTime.now());
        incident = repository.save(incident);
        notificationService.sendNotification("Client request for incident received.");
        simpleNearestUnitService.searchNearestUnit(incident);
        notificationService.sendNotification("Notification to the closest unit sent");
        return incident;
    }

    public Incident findById(Long id) {
        return repository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

}
