package io.codelex.securityapp.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class ClientLogin {

    @Email
    private String email;

    @Size(min = 6, max = 12)
    private String password;


    @JsonCreator
    public ClientLogin(@JsonProperty("email") String email,
                       @JsonProperty("password") String password) {
        this.email = email.toLowerCase();
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
