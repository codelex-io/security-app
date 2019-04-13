package io.codelex.securityapp.repository.models;

import javax.persistence.*;
import java.math.BigDecimal;

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

    public Incident(Client client, BigDecimal latitude, BigDecimal longitude) {
        this.client = client;
        this.latitude = latitude;
        this.longitude = longitude;
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
