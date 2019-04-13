package io.codelex.securityapp.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class AddIncidentRequest {
    
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String email;

    @JsonCreator
    public AddIncidentRequest(
            @JsonProperty("latitude") BigDecimal latitude,
            @JsonProperty("longitude") BigDecimal longitude,
            @JsonProperty("email") String email) {
      
        this.latitude = latitude;
        this.longitude = longitude;
        this.email = email;
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
