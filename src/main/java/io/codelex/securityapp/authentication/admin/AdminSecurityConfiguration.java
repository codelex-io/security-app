package io.codelex.securityapp.authentication.admin;

import io.codelex.securityapp.authentication.user.UserRoles;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Order(200)
@Configuration
class AdminSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/admin-api/**")
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin-api/sign-in").permitAll()
                .anyRequest().hasRole(String.valueOf(UserRoles.ADMIN));
    }
}
