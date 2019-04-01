package io.codelex.securityapp.clients.api;

import io.codelex.securityapp.common.Location;

public class AskForHelpRequest {

    private final Location location;

    public AskForHelpRequest(Location location) {
        this.location = location;
    }
}
