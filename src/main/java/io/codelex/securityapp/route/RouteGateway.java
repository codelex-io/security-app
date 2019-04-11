package io.codelex.securityapp.route;

import io.codelex.securityapp.repository.models.Incident;
import io.codelex.securityapp.repository.models.Unit;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;

@Component
public class RouteGateway {
    private final RestTemplate restTemplate = new RestTemplate();

    public RouteGateway(GoogleMapsProps props) {
        this.props = props;
    }

    private final GoogleMapsProps props;

    public Long calculateRoute(Unit unit, Incident incident) {

        BigDecimal unitLatitude = unit.getLatitude().setScale(6,RoundingMode.DOWN);
        BigDecimal unitLongitude = unit.getLongitude().setScale(6,RoundingMode.DOWN);

        BigDecimal incidentLatitude = incident.getLatitude().setScale(6,RoundingMode.DOWN);
        BigDecimal incidentLongitude = incident.getLongitude().setScale(6,RoundingMode.DOWN);

        URI uri = UriComponentsBuilder.fromHttpUrl(props.getApiUrl())
                .path("/maps/api/distancematrix/json?units=metric")
                .queryParam("origins", incidentLatitude + "," + incidentLongitude)
                .queryParam("destinations", unitLatitude + "," + unitLongitude)
                .queryParam("key", props.getApiKey())
                .build()
                .toUri();

        RouteResponse response = restTemplate.getForObject(uri, RouteResponse.class);

        RouteResponse.Dist distance = response.getRows().get(0)
                .getElements().get(0).getDistance();

        return distance.getValue();
    }
}

/*  String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins="
                + unit.getLongitude().setScale(6, RoundingMode.DOWN)
                + "," + unit.getLatitude().setScale(6, RoundingMode.DOWN)
                + "&destinations=" + incident.getLongitude().setScale(6, RoundingMode.DOWN) + ","
                + incident.getLatitude().setScale(6, RoundingMode.DOWN)
                + "&key=AIzaSyDcsLDxrAteVZEOc4h8zBJwVzs13Cb79OU";*/