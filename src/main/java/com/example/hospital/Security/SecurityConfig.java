package com.example.hospital.Security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final String authWhitelist[]={
            "/api/*/",

            "/api/*",
            // -- Swagger UI v2
            "/v2/api-docs", "/swagger-resources", "/swagger-resources/**", "/configuration/ui",
            "/configuration/security", "/swagger-ui.html", "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/*", "/swagger-ui/*"
            // other public endpoints of your API may be appended to this array

    };

    private AuthFilter authFilter(){return  new AuthFilter();}
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().addFilterAfter(new AuthFilter(), UsernamePasswordAuthenticationFilter.class)
                // . rememberMe() .tokenValiditySeconds(43200000).and()
                .authorizeRequests().antMatchers(HttpMethod.POST,authWhitelist).permitAll().antMatchers(authWhitelist).permitAll().antMatchers("/admin")
                .hasRole("admin").antMatchers("/user").hasRole("user").anyRequest().authenticated().and().formLogin()
                .disable();
        http.logout().clearAuthentication(true);
        http.cors();

    }
}
