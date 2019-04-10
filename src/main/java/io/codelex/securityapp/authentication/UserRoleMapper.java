package io.codelex.securityapp.authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

import static io.codelex.securityapp.authentication.UserRoles.*;

class UserRoleMapper implements GrantedAuthoritiesMapper {

    public Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<UserRoles> roles = EnumSet.noneOf(UserRoles.class);

        for (GrantedAuthority a: authorities) {
            if ("MY ADMIN GROUP".equals(a.getAuthority())) {
                roles.add(ROLE_ADMIN);
            } else if ("MY USER GROUP".equals(a.getAuthority())) {
                roles.add(ROLE_USER);
            } else if ("MY USER GROUP".equals(a.getAuthority())) {
                roles.add(ROLE_UNIT);
            }
        }
        return roles;
    }
}
