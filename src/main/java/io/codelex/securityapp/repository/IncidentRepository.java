package io.codelex.securityapp.repository;

import io.codelex.securityapp.repository.models.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
}
