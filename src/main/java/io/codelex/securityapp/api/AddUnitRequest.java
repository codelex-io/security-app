package io.codelex.securityapp.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class AddUnitRequest {

    private BigDecimal latitude;
    private BigDecimal longitude;
    private Boolean available; //todo primitive?
    
    @JsonCreator
    public AddUnitRequest(@JsonProperty("latitude") BigDecimal latitude,
                          @JsonProperty("longitude") BigDecimal longitude,
                          @JsonProperty("available") Boolean available) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.available = available;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public Boolean getAvailable() {
        return available;
    }
}
