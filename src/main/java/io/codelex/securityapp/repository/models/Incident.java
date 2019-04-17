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
    private BigDecimal latitude;
    private BigDecimal longitude;
    private LocalDateTime createTime;

    public Incident(Client client, BigDecimal latitude, BigDecimal longitude, LocalDateTime startTime) {
        this.client = client;
        this.latitude = latitude;
        this.longitude = longitude;
        this.createTime = startTime;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
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
}
