package io.codelex.securityapp.route;

import io.codelex.securityapp.repository.models.Incident;
import io.codelex.securityapp.repository.models.Unit;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.math.RoundingMode;

@Component
public class RouteGateway {
    private final RestTemplate restTemplate = new RestTemplate();

    public Route calculateRoute(Unit unit, Incident incident) {

        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins=" + unit.getLongitude().setScale(6, RoundingMode.DOWN) + "," + unit.getLatitude().setScale(6, RoundingMode.DOWN) + "&destinations=" + incident.getLongitude().setScale(6, RoundingMode.DOWN) + "," + incident.getLatitude().setScale(6, RoundingMode.DOWN) + "&key=AIzaSyDcsLDxrAteVZEOc4h8zBJwVzs13Cb79OU";

        RouteResponse response = restTemplate.getForObject(url, RouteResponse.class);

        RouteResponse.Dist distance = response.getRows().get(0)
                .getElements().get(0).getDistance();

        return new Route(distance.getValue());
    }
}
