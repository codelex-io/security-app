package io.codelex.securityapp.authentication.client;

import io.codelex.securityapp.authentication.user.UserRoles;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Order(101)
@Configuration
class ClientSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/clients-api/**")
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/clients-api/sign-in", "/clients-api/register").permitAll()
                /*    .antMatchers("/incident-api/**").permitAll()
                   .antMatchers("/units-api/**").permitAll()*/
                .anyRequest().hasRole(String.valueOf(UserRoles.USER));
    }
}
