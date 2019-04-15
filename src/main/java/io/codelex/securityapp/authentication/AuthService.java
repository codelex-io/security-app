package io.codelex.securityapp.authentication;

import io.codelex.securityapp.authentication.user.UserRoles;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import static java.util.Collections.singleton;
import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Component
public class AuthService {

    public void authorizeClient(String email, String password) {
        var clientAuthorities = singleton(new SimpleGrantedAuthority("ROLE_" + UserRoles.USER));
        var clientToken = new UsernamePasswordAuthenticationToken(email, password, clientAuthorities);
        getContext().setAuthentication(clientToken);
    }

    public void authorizeUnit(String email, String password) {
        var unitAuthorities = singleton(new SimpleGrantedAuthority("ROLE_" + UserRoles.UNIT));
        var unitToken = new UsernamePasswordAuthenticationToken(email, password, unitAuthorities);
        getContext().setAuthentication(unitToken);
    }

    public void clearAuthentication() {
        getContext().setAuthentication(null);
    }
}
