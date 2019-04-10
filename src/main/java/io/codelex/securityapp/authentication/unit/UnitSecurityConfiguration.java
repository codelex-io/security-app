package io.codelex.securityapp.authentication.unit;

import io.codelex.securityapp.authentication.user.UserRoles;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Order(100)
@Configuration
class UnitSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/units-api/**")
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/units-api/sign-in", "/units-api/register").permitAll()
                .anyRequest().hasRole(String.valueOf(UserRoles.UNIT));
    }
}
