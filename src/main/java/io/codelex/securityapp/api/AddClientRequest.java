package io.codelex.securityapp.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddClientRequest {

    private String firstName;
    private String lastName;

    @JsonCreator
    public AddClientRequest(@JsonProperty("firstName") String firstName,
                            @JsonProperty("lastName") String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
