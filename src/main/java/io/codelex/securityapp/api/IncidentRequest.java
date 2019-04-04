package io.codelex.securityapp.api;

public class IncidentRequest {

    private Incident incident;

    public IncidentRequest(Incident incident) {

        this.incident = incident;
    }

    public Incident getIncident() {
        return incident;
    }
}
