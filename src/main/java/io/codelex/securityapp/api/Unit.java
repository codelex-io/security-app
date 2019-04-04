package io.codelex.securityapp.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Unit {

    @Id
    private long id;
    private Location location;
    private Boolean available;
    
    @JsonCreator
    public Unit(@JsonProperty long id,
                @JsonProperty Location location,
                @JsonProperty Boolean available) {
        this.id = id;
        this.location = location;
        this.available = available;
    }

    public long getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public Boolean getAvailable() {
        return available;
    }
}
