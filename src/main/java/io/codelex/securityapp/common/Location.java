package io.codelex.securityapp.common;

import javax.validation.constraints.NotEmpty;

public class Location {

    @NotEmpty
    private String latitude;

    @NotEmpty
    private String longitude;


    public Location(@NotEmpty String latitude, @NotEmpty String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
