package io.codelex.securityapp.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotEmpty;

public class Location {
    @NotEmpty
    private String latitude;
    @NotEmpty
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
