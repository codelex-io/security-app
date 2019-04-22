package io.codelex.securityapp.repository;

import io.codelex.securityapp.api.AddIncidentRequest;
import io.codelex.securityapp.repository.models.Incident;
import io.codelex.securityapp.repository.models.Unit;

public interface NearestUnitService {

    Unit searchNearestUnit(AddIncidentRequest request);
}
