package io.codelex.securityapp.repository;

import io.codelex.securityapp.NotificationService;
import io.codelex.securityapp.api.AddIncidentRequest;
import io.codelex.securityapp.repository.models.Incident;
import io.codelex.securityapp.repository.models.Unit;
import io.codelex.securityapp.route.RouteGateway;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SimpleNearestUnitService implements NearestUnitService {

    private final RepositoryUnitService unitService;
    private final RouteGateway routeGateway;

    public SimpleNearestUnitService(RepositoryUnitService unitService,
                                    RouteGateway routeGateway) {
        this.unitService = unitService;
        this.routeGateway = routeGateway;
    }

    @Override
    public Unit searchNearestUnit(AddIncidentRequest request) {
        HashMap<Long, Unit> routeUnitHashMap = availableUnitHashMap(request);

        List<Long> closestUnits = new ArrayList<>(routeUnitHashMap.keySet());
        Collections.sort(closestUnits);
        Long closestRoute = closestUnits.get(0);
        Unit unit = routeUnitHashMap.get(closestRoute);

        unitService.changeAvailability(unit);
        return unit;
    }

    private HashMap<Long, Unit> availableUnitHashMap(AddIncidentRequest request) {
        List<Unit> unitList = new ArrayList<>(unitService.findAvailable());

        HashMap<Long, Unit> routeUnitHashMap = new HashMap<>();
        for (Unit unit : unitList) {
            Long distance = routeGateway.calculateRoute(unit, request);
            routeUnitHashMap.put(distance, unit);
        }
        return routeUnitHashMap;
    }
}
