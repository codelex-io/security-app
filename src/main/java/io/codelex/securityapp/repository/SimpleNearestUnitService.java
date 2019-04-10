package io.codelex.securityapp.repository;

import io.codelex.securityapp.route.Route;
import io.codelex.securityapp.repository.models.Client;
import io.codelex.securityapp.repository.models.Incident;
import io.codelex.securityapp.repository.models.Unit;
import io.codelex.securityapp.route.RouteGateway;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class SimpleNearestUnitService implements NearestUnitService {
    
    private final RouteGateway routeGateway;
    
    public SimpleNearestUnitService(RouteGateway routeGateway) {
        this.routeGateway = routeGateway;
    }

    private Incident incident = new Incident(
            new Client("name", "surname"),
            new BigDecimal(56.254896),
            new BigDecimal(24.113705)
    );
    
    @Override
    public Unit searchNearestUnit(BigDecimal latitude, BigDecimal longitude) {
        Unit unit = new Unit(
                new BigDecimal(56.941887),
                new BigDecimal(24.095740),
                true
        );
        Unit unit1 = new Unit(
                new BigDecimal(24.941887),
                new BigDecimal(56.095740),
                true
        );
        List<Unit> unitList = new ArrayList<>();
        unitList.add(unit);
        unitList.add(unit1);
        
        HashMap<Route, Unit> routeUnitHashMap = new HashMap<>();
        for (Unit value : unitList) {
            Route distance = routeGateway.calculateRoute(value, incident);
            routeUnitHashMap.put(distance, value);
        }
        List<Route> closestUnits = new ArrayList<>(routeUnitHashMap.keySet());
        Collections.sort(closestUnits);
        Route closestRoute = closestUnits.get(0);
        
        return routeUnitHashMap.get(closestRoute);
    }
}
