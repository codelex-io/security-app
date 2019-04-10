package io.codelex.securityapp.authentication;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoles implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_UNIT,
    ROLE_USER;

    public String getAuthority() {
        return name();
    }
}