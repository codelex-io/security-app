package io.codelex.securityapp.route;

import io.codelex.securityapp.repository.models.Client;
import io.codelex.securityapp.repository.models.Incident;
import io.codelex.securityapp.repository.models.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;


class RouteGatewayTest {
    
    private RouteGateway routeGateway = new RouteGateway();

    @Test
    void should_get_distance_from_unit_to_incident() {
        //given
        Unit unit = new Unit(
                new BigDecimal(56.941887),
                new BigDecimal(24.095740),
                true
        );
        Incident incident = new Incident(
                new Client("name", "surname"),
                new BigDecimal(56.254896),
                new BigDecimal(24.113705)
        );
        //then
        Route distanceTo = routeGateway.calculateRoute(unit, incident);
        Assertions.assertEquals(124891, distanceTo.getDistance());
    }

}