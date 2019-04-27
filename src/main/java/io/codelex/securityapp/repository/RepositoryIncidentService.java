package io.codelex.securityapp.repository;

import io.codelex.securityapp.NotificationService;
import io.codelex.securityapp.api.AddIncidentRequest;
import io.codelex.securityapp.repository.models.Incident;
import io.codelex.securityapp.repository.models.Unit;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class RepositoryIncidentService {


    private final SimpleNearestUnitService simpleNearestUnitService;
    private final IncidentRepository repository;
    private final NotificationService notificationService;
    private final ClientRepository clientRepository;

    public RepositoryIncidentService(SimpleNearestUnitService simpleNearestUnitService,
                                     IncidentRepository repository,
                                     NotificationService notificationService,
                                     ClientRepository clientRepository) {
        this.simpleNearestUnitService = simpleNearestUnitService;
        this.repository = repository;
        this.notificationService = notificationService;
        this.clientRepository = clientRepository;
    }

    public Incident addIncident(AddIncidentRequest request) {
        Unit nearestUnit = simpleNearestUnitService.searchNearestUnit(request);

        Incident incident = new Incident(
                clientRepository.findClientByEmail(request.getEmail()),
                nearestUnit,
                request.getLatitude().setScale(6, RoundingMode.DOWN),
                request.getLongitude().setScale(6, RoundingMode.DOWN),
                LocalDateTime.now());
        incident = repository.save(incident);
        notificationService.sendNotification("Client rsequest for incident received.");
        notificationService.sendNotification("Notification to the closest nearestUnit sent");
        return incident;
    }

    public Incident findById(Long id) {
        return repository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

    public List<Incident> findAllIncident() {
        return new ArrayList<>(repository.findAll());
    }

}
