package io.codelex.securityapp.repository.models;

import javax.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "units")
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "units_seq")
    private Long id;

    private BigDecimal latitude;
    private BigDecimal longitude;
    private Boolean available;

    public Unit(BigDecimal latitude, BigDecimal longitude, Boolean available) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.available = available;
    }

    public Unit() {
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
