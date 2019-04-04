package io.codelex.securityapp.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Location {

    @ManyToMany
    @JoinTable(name = "incidents", joinColumns = @JoinColumn(name = "latitude"),
            inverseJoinColumns = @JoinColumn(name = "latitude"))
    private String latitude;

    @ManyToMany
    @JoinTable(name = "incidents", joinColumns = @JoinColumn(name = "longitude"),
            inverseJoinColumns = @JoinColumn(name = "longitude"))
    private String longitude;

    @JsonCreator
    public Location(@JsonProperty("latitude") String latitude,
                    @JsonProperty("longitude") String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
