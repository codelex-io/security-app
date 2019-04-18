package io.codelex.securityapp.repository;

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
    public Unit searchNearestUnit(Incident incident) {
        HashMap<Long, Unit> routeUnitHashMap = availableUnitHashMap(incident);

        List<Long> closestUnits = new ArrayList<>(routeUnitHashMap.keySet());
        Collections.sort(closestUnits);
        Long closestRoute = closestUnits.get(0);
        Unit unit = routeUnitHashMap.get(closestRoute);

        unitService.changeAvailability(unit);
        return unit;
    }

    private HashMap<Long, Unit> availableUnitHashMap(Incident incident) {
        List<Unit> unitList = new ArrayList<>(unitService.findAvailable());

        HashMap<Long, Unit> routeUnitHashMap = new HashMap<>();
        for (Unit unit : unitList) {
            Long distance = routeGateway.calculateRoute(unit, incident);
            routeUnitHashMap.put(distance, unit);
        }
        return routeUnitHashMap;
    }
}
