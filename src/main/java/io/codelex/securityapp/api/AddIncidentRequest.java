package io.codelex.securityapp.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AddIncidentRequest {


    private String email;
    private BigDecimal latitude;
    private BigDecimal longitude;

    @JsonCreator
    public AddIncidentRequest(
            @JsonProperty("email") String email,
            @JsonProperty("latitude") BigDecimal latitude,
            @JsonProperty("longitude") BigDecimal longitude) {
        this.email = email;
        this.latitude = latitude.setScale(6, RoundingMode.DOWN);
        this.longitude = longitude.setScale(6, RoundingMode.DOWN);
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }
}
