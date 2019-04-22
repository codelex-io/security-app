package io.codelex.securityapp.route;

import io.codelex.securityapp.api.AddIncidentRequest;
import io.codelex.securityapp.repository.models.Incident;
import io.codelex.securityapp.repository.models.Unit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URI;

@Component
public class RouteGateway {
    private static final Logger LOG = LoggerFactory.getLogger(RouteGateway.class);
    private final RestTemplate restTemplate = new RestTemplate();

    public RouteGateway(GoogleMapsProps props) {
        this.props = props;
    }

    private final GoogleMapsProps props;

    public Long calculateRoute(Unit unit, AddIncidentRequest request) {

        BigDecimal unitLatitude = unit.getLatitude().setScale(6, RoundingMode.DOWN);
        BigDecimal unitLongitude = unit.getLongitude().setScale(6, RoundingMode.DOWN);

        BigDecimal incidentLatitude = request.getLatitude().setScale(6, RoundingMode.DOWN);
        BigDecimal incidentLongitude = request.getLongitude().setScale(6, RoundingMode.DOWN);
        try {
            URI uri = UriComponentsBuilder.fromHttpUrl(props.getApiUrl())
                    .path("/maps/api/distancematrix/json")
                    .queryParam("origins", incidentLatitude + "," + incidentLongitude)
                    .queryParam("destinations", unitLatitude + "," + unitLongitude)
                    .queryParam("key", props.getApiKey())
                    .build()
                    .toUri();

            RouteResponse response = restTemplate.getForObject(uri, RouteResponse.class);

            RouteResponse.Dist distance = response.getRows().get(0)
                    .getElements().get(0).getDistance();

            return distance.getValue();
        } catch (Exception e) {
            LOG.warn("External service error caught");
        }
        throw new IllegalStateException();
    }
}
