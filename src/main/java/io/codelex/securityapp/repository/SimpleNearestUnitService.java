package io.codelex.securityapp.repository;

import io.codelex.securityapp.repository.models.Client;
import io.codelex.securityapp.repository.models.Incident;
import io.codelex.securityapp.repository.models.Unit;
import io.codelex.securityapp.route.RouteGateway;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class SimpleNearestUnitService implements NearestUnitService {

    private final RepositoryUnitService unitService;
    private final RouteGateway routeGateway;

    public SimpleNearestUnitService(RepositoryUnitService unitService, RouteGateway routeGateway) {
        this.unitService = unitService;
        this.routeGateway = routeGateway;
    }

    private Incident incident = new Incident(
            new Client("name", "surname"),
            new BigDecimal(56.254896),
            new BigDecimal(24.113705)
    );

    @Override
    public Unit searchNearestUnit(BigDecimal latitude, BigDecimal longitude) {

        List<Unit> unitList = unitService.findAvailable();

        HashMap<Long, Unit> routeUnitHashMap = new HashMap<>();
        for (Unit unit : unitList) {
            Long distance = routeGateway.calculateRoute(unit, incident);
            routeUnitHashMap.put(distance, unit);
        }

        List<Long> closestUnits = new ArrayList<>(routeUnitHashMap.keySet());
        Collections.sort(closestUnits);
        Long closestRoute = closestUnits.get(0);

        return routeUnitHashMap.get(closestRoute);
    }
}
