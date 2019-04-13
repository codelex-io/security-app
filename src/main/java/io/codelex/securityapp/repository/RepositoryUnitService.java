package io.codelex.securityapp.repository;

import io.codelex.securityapp.api.AddUnitRequest;
import io.codelex.securityapp.repository.models.Unit;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RepositoryUnitService {

    private final UnitRepository unitRepository;

    public RepositoryUnitService(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    public Unit addUnit(AddUnitRequest request) {
        Unit unit = new Unit(
                request.getLatitude(),
                request.getLongitude(),
                request.getAvailable()
        );
        unit = unitRepository.save(unit);
        return unit;
    }

    public void changeAvailability(Unit unit) {
        unitRepository.changeStatus();
    }

    public List<Unit> findAvailable() {
        return unitRepository.searchAvailable();
    }

    public Unit findById(Long id) {
        return unitRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }
}

