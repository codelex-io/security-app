package io.codelex.securityapp.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "incidents")
public class Incident {

    @Id
    private long id;

    @ManyToOne
    private Client client;

    @ManyToOne
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
