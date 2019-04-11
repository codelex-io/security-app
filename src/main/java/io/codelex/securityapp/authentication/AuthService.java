package io.codelex.securityapp.authentication;

import io.codelex.securityapp.authentication.user.UserRoles;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import static java.util.Collections.singleton;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Component
public class AuthService {

    public void authorise(String email, UserRoles role) {
        var authorities = singleton(new SimpleGrantedAuthority("ROLE_" + role.getAuthority()));
        var token = new UsernamePasswordAuthenticationToken(email, null, authorities);
        getContext().setAuthentication(token);
    }

    public void register(String email, UserRoles role) {
    }
    
    public void clearAuthentication() {
        getContext().setAuthentication(null);
    }
}