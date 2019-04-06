package io.codelex.securityapp.api;

import io.codelex.securityapp.repository.models.Incident;

public class IncidentRequest {

    private Incident incident;

    public IncidentRequest(Incident incident) {

        this.incident = incident;
    }

    public Incident getIncident() {
        return incident;
    }
}
