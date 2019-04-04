package io.codelex.securityapp.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    private long id;
    private String firstName;
    private String lastName;
    
    @JsonCreator
    public Client(@JsonProperty("id") long id,
                  @JsonProperty("firstName") String firstName,
                  @JsonProperty("lastName") String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}