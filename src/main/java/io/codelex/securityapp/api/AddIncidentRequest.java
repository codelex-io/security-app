package io.codelex.securityapp.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;

public class AddIncidentRequest {

    private BigDecimal latitude;
    private BigDecimal longitude;

    @JsonCreator
    public AddIncidentRequest(
            @JsonProperty("latitude") BigDecimal latitude,
            @JsonProperty("longitude") BigDecimal longitude) {
      
        this.latitude = latitude;
        this.longitude = longitude;
    }
    

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }
}
