package io.codelex.securityapp.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class IncidentRequest {

    private Long id;
    private Incident incident;

    public IncidentRequest(Long id,
                           Incident incident) {
        this.id = id;
        this.incident = incident;
    }

    public long getId() {
        return id;
    }

    public Incident getIncident() {
        return incident;
    }
}
