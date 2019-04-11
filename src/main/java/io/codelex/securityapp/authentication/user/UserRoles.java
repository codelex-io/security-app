package io.codelex.securityapp.authentication.user;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoles implements GrantedAuthority {
    ADMIN,
    UNIT,
    USER;

    public String getAuthority() {
        return name();
    }
}