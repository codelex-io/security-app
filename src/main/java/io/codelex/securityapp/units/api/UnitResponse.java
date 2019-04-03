package io.codelex.securityapp.units.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class UnitResponse {
    @NotNull
    private boolean isAccepted;

    @JsonCreator
    public UnitResponse(@JsonProperty("isAccepted") boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public boolean isAccepted() {
        return isAccepted;
    }
}
