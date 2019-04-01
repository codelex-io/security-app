package io.codelex.securityapp.api;

import javax.validation.constraints.NotNull;

public class Client {

    @NotNull
    private long id;

    @NotNull
    private Location location;


    public Client(@NotNull long id, @NotNull Location location) {
        this.id = id;
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
