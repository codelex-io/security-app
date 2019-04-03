package io.codelex.securityapp.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Incident {
    
    @Valid
    @NotNull
    private Client client;
    @Valid
    @NotNull
    private Location incidentLocation;
    
    @JsonCreator
    public Incident(@JsonProperty("client") Client client,
                    @JsonProperty("incidentLocation") Location incidentLocation) {
        this.client = client;
        this.incidentLocation = incidentLocation;
    }

    public Client getClient() {
        return client;
    }

    public Location getIncidentLocation() {
        return incidentLocation;
    }
}
