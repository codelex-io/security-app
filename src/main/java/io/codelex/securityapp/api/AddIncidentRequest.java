package io.codelex.securityapp.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class AddIncidentRequest {
    
    private BigDecimal latitude;
    private BigDecimal longitude;

    @JsonCreator
    public AddIncidentRequest(
            @JsonProperty("latitude") BigDecimal latitude,
            @JsonProperty("longitude") BigDecimal longitude) {
      
        this.latitude = latitude.setScale(6, RoundingMode.DOWN);
        this.longitude = longitude.setScale(6, RoundingMode.DOWN);
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }
}
