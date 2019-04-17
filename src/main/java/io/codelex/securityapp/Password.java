package io.codelex.securityapp;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Password {
    
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
