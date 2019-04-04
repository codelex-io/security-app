package io.codelex.securityapp.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Incident {

    private long id;
    private Client client;
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
