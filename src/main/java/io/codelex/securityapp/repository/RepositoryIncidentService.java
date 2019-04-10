package io.codelex.securityapp.repository;

import io.codelex.securityapp.NotificationService;
import io.codelex.securityapp.api.AddIncidentRequest;
import io.codelex.securityapp.repository.models.Client;
import io.codelex.securityapp.repository.models.Incident;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class RepositoryIncidentService {

    private final IncidentRepository repository;
    private final NotificationService notificationService;

    public RepositoryIncidentService(IncidentRepository repository, NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    public Incident addIncident(AddIncidentRequest request) {
        Incident incident = new Incident(
                new Client("name", "surname"),
                request.getLatitude(),
                request.getLongitude()
        );
        incident = repository.save(incident);
        notificationService.sendNotification("Client requested for incident received");
        return incident;
    }

    public Incident findById(Long id) {
        return repository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
    
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
    
    public void deleteAll(){
        repository.deleteAll();
    }
}
