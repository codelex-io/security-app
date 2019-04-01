package io.codelex.securityapp.units.api;

import io.codelex.securityapp.common.Location;

import javax.validation.constraints.NotEmpty;

public class Unit {

    @NotEmpty
    private long id;

    private boolean isAvailable;

    private Location location;

    public Unit(@NotEmpty long id, boolean isAvailable, Location location) {
        this.id = id;
        this.isAvailable = isAvailable;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
