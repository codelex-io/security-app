package io.codelex.securityapp.repository;

import io.codelex.securityapp.api.AddIncidentRequest;
import io.codelex.securityapp.repository.models.Client;
import io.codelex.securityapp.repository.models.Incident;
import org.springframework.stereotype.Component;

@Component
public class RepositoryIncidentService {

    private final IncidentRepository repository;

    public RepositoryIncidentService(IncidentRepository repository) {
        this.repository = repository;
    }

    public Incident addIncident(AddIncidentRequest request) {
        Incident incident = new Incident(
                new Client(request.getClient().getFirstName(),
                        request.getClient().getLastName()),
                request.getLatitude(),
                request.getLongitude()
        );
        incident = repository.save(incident);
        return incident;
    }
    
    
}
