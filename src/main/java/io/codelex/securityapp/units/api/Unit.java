package io.codelex.securityapp.units.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.codelex.securityapp.common.Location;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Unit {

    @NotNull
    private long id;
    @NotNull
    private boolean isAvailable;
    @Valid
    @NotNull
    private Location currentLocation;
    
    @JsonCreator
    public Unit(@JsonProperty("id") long id,
                @JsonProperty("isAvailable") boolean isAvailable,
                @JsonProperty("currentLocation") Location currentLocation) {
        this.id = id;
        this.isAvailable = isAvailable;
        this.currentLocation = currentLocation;
    }

    public long getId() {
        return id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }
}
