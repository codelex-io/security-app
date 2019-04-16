package io.codelex.securityapp.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class AddUnitRequest {

    @Email
    private String email;
    @Size(min = 6, max = 12)
    private String password;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private boolean available;
    
    @JsonCreator
    public AddUnitRequest(@JsonProperty("email")String email,
                          @JsonProperty("password")String password,
                          @JsonProperty("latitude") BigDecimal latitude,
                          @JsonProperty("longitude") BigDecimal longitude,
                          @JsonProperty("available") Boolean available) {
        this.email = email.toLowerCase();
        this.password = password;
        this.latitude = latitude;
        this.longitude = longitude;
        this.available = available;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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
