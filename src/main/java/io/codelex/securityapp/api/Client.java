package io.codelex.securityapp.api;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    private long id;
    private String firstName;
    private String lastName;

    public Client(long id, String firstName,
                  String lastName) {
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
