package io.codelex.securityapp.repository.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "incidents")
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "incidents_seq")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Client client;
    @ManyToOne(cascade = CascadeType.ALL)
    private Unit unit;

    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalDateTime createTime;

    public Incident(Client client, Unit unit, BigDecimal latitude, BigDecimal longitude, LocalDateTime startTime) {
        this.client = client;
        this.unit=unit;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createTime = startTime;
    }

    public Unit getUnit() {
        return unit;
    }

    public Client getClient() {
        return client;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

}
