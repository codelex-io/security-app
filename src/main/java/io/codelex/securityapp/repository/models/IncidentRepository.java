package io.codelex.securityapp.repository.models;

import io.codelex.securityapp.api.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
}
