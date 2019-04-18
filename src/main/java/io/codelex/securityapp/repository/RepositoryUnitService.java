package io.codelex.securityapp.repository;

import io.codelex.securityapp.Password;
import io.codelex.securityapp.api.AddUnitRequest;
import io.codelex.securityapp.repository.models.Unit;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RepositoryUnitService {

    private Password encoder;

    private final UnitRepository unitRepository;

    public RepositoryUnitService(Password encoder, UnitRepository unitRepository) {
        this.encoder = encoder;
        this.unitRepository = unitRepository;
    }

    public Unit addUnit(AddUnitRequest request) {
        Unit unit = new Unit(
                request.getEmail(),
                encoder.passwordEncoder().encode(request.getPassword()),
                request.getLatitude(),
                request.getLongitude(),
                request.getAvailable()
        );
        unit = unitRepository.save(unit);
        return unit;
    }

    public boolean isEmailPresent(String email) {
        return unitRepository.isEmailPresent(email);
    }

    public boolean isPasswordMatching(String email, String password) {
        Unit unit = getUnitByEmail(email);
        return encoder.passwordEncoder().matches(password, unit.getPassword());
    }

    private Unit getUnitByEmail(String email) {
        return unitRepository.findUnitByEmail(email);
    }

    void changeAvailability(Unit unit) {
        unitRepository.changeStatus(unit.getId());
    }

    public List<Unit> findAvailable() {
        return unitRepository.searchAvailable();
    }

    public Unit findById(Long id) {
        return unitRepository.findById(id)
                .orElseThrow(NoSuchElementException::new);
    }

}

