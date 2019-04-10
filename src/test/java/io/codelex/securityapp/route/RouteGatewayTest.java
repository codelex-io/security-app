package io.codelex.securityapp.route;

import io.codelex.securityapp.repository.models.Client;
import io.codelex.securityapp.repository.models.Incident;
import io.codelex.securityapp.repository.models.Unit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
class RouteGatewayTest {

    RouteGateway routeGateway;

    @BeforeEach
    void setUp() {
        GoogleMapsProps props = new GoogleMapsProps();
        props.setApiUrl("https://maps.googleapis.com/maps/api/distancematrix/json?units=metric");
        props.setApiKey("AIzaSyDcsLDxrAteVZEOc4h8zBJwVzs13Cb79OU");

        routeGateway = new RouteGateway(props);
    }

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
        Long distanceTo = routeGateway.calculateRoute(unit, incident);
        Assertions.assertEquals(84174, distanceTo);
    }

}