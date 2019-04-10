package io.codelex.securityapp.route;

import io.codelex.securityapp.repository.SimpleNearestUnitService;
import io.codelex.securityapp.repository.models.Client;
import io.codelex.securityapp.repository.models.Incident;
import io.codelex.securityapp.repository.models.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class SimpleNearestUnitServiceTest {
    
  
    private RouteGateway routeGateway = new RouteGateway();

    private SimpleNearestUnitService nearestUnit = new SimpleNearestUnitService(routeGateway);

    @Test
    void should_return_unit() {
        //given
        Incident incident = new Incident(
                new Client("name", "surname"),
                new BigDecimal(56.254896),
                new BigDecimal(24.113705)
        );
        //when
        Unit unit = nearestUnit.searchNearestUnit(incident.getLatitude(), incident.getLongitude());
        //then
        Assertions.assertNotNull(unit);
    }

    @Test
    void should_get_unit_with_fastest_route() {
        //given
        Incident incident = new Incident(
                new Client("name", "surname"),
                new BigDecimal(56.254896),
                new BigDecimal(24.113705)
        );
        //when
        Unit unit = nearestUnit.searchNearestUnit(incident.getLatitude(), incident.getLongitude());
        //then
        Assertions.assertEquals(new BigDecimal(56.941887), unit.getLatitude());
    }

    
}