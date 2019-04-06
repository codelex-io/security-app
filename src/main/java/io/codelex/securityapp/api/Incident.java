package io.codelex.securityapp.api;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "incidents")
public class Incident {

    @Id
    private long id;
    @ManyToOne
    private Client client;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public Incident(long id, Client client, BigDecimal latitude, BigDecimal longitude) {
        this.id = id;
        this.client = client;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Client getClient() {
        return client;
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
}
