package io.codelex.securityapp.api;

import javax.persistence.*;

import java.math.BigDecimal;

public class Unit {

    @Id
    private long id;

    private BigDecimal latitude;
    private BigDecimal longitude;
    private Boolean available;

    public Unit(long id, BigDecimal latitude, BigDecimal longitude, Boolean available) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.available = available;
    }

    public long getId() {
        return id;
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
