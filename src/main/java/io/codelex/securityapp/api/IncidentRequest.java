package io.codelex.securityapp.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class IncidentRequest {
    @NotNull
    private long id;
    @Valid
    @NotNull
    private Incident incident;
    
    @JsonCreator
    public IncidentRequest(@JsonProperty("id") long id,
                           @JsonProperty("incident") Incident incident) {
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
