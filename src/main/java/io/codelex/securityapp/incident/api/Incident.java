package io.codelex.securityapp.incident.api;

import io.codelex.securityapp.common.Location;
import io.codelex.securityapp.clients.api.Client;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Incident {


    @NotEmpty
    private Client client;

    @NotNull
    private Location location;


    public Incident(Client client, Location location) {
        this.client = client;
        this.location = location;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
