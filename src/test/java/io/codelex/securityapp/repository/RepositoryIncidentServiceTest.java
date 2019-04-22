package io.codelex.securityapp.repository;

import io.codelex.securityapp.NotificationService;
import io.codelex.securityapp.api.AddIncidentRequest;
import io.codelex.securityapp.repository.models.Incident;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import static org.mockito.ArgumentMatchers.any;

import java.math.BigDecimal;
import java.math.RoundingMode;

class RepositoryIncidentServiceTest {

    private IncidentRepository repository = Mockito.mock(IncidentRepository.class);
    private SimpleNearestUnitService nearestUnitService = Mockito.mock(SimpleNearestUnitService.class);
    private NotificationService notificationService = Mockito.mock(NotificationService.class);
    private RepositoryIncidentService service = new RepositoryIncidentService(
            nearestUnitService,
            repository,
            notificationService, clientRepository);

    @Test
    void should_save_incident() {
        //given
        AddIncidentRequest request = new AddIncidentRequest(
                principal.getName(), email1, new BigDecimal(24.941887).setScale(6, RoundingMode.DOWN),
                new BigDecimal(56.095740).setScale(6, RoundingMode.DOWN)
        );
        Mockito.when(repository.save(any()))
                .thenAnswer((Answer) invocation -> invocation.getArguments()[0]);
        //when
        Incident incident = service.addIncident(request);
        //then
        Assertions.assertEquals(incident.getLatitude(), request.getLatitude());
        Assertions.assertEquals(incident.getLongitude(), request.getLongitude());
    }
}