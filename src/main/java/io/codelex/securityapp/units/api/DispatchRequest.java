package io.codelex.securityapp.units.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.codelex.securityapp.clients.api.Client;
import io.codelex.securityapp.common.Location;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class DispatchRequest {
    @Valid
    @NotNull       
    private Client client;
    @Valid
    @NotNull
    private Location clientLocation;
    
    @JsonCreator
    public DispatchRequest(@JsonProperty("client") Client client,
                           @JsonProperty("clientLocation") Location clientLocation) {
        this.client = client;
        this.clientLocation = clientLocation;
    }

    public Client getClient() {
        return client;
    }

    public Location getClientLocation() {
        return clientLocation;
    }
}
