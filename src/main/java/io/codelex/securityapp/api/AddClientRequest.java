package io.codelex.securityapp.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddClientRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @JsonCreator
    public AddClientRequest(@JsonProperty("firstName") String firstName,
                            @JsonProperty("lastName") String lastName,
                            @JsonProperty("email") String email,
                            @JsonProperty("password") String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
