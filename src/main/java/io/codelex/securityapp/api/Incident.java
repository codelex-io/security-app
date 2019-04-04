package io.codelex.securityapp.api;


import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.math.BigDecimal;

public class Incident {

    @Id
    private long id;
    @ManyToOne
    private Client client;
    @OneToOne
    private BigDecimal latitude;
    @OneToOne
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
