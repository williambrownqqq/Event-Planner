package com.zanchenko.alex.diploma.security.config;

import com.zanchenko.alex.diploma.security.jwt.AuthenticationTokenFilter;
import com.zanchenko.alex.diploma.security.jwt.AuthentictionEntryPointJwt;
import com.zanchenko.alex.diploma.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableMethodSecurity // to enable Spring Security’s web security support and provide the Spring MVC integration.
//  annotation in Spring is used to enable Spring Security's method-level security. Method-level security allows you to control access to individual methods in your Spring beans based on the roles or permissions of the authenticated user.
public class WebSecurityConfig{

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private AuthentictionEntryPointJwt unauthorizedHandler;

    @Bean
    public AuthenticationTokenFilter authenticationJwtTokenFilter() {
        return new AuthenticationTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector, AuthenticationProvider authenticationProvider, AuthenticationTokenFilter authenticationJwtTokenFilter)
            throws Exception {
        MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);

        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
//                    auth.requestMatchers("/api/auth/**").permitAll()
//                                .requestMatchers("/api/test/**").permitAll()
//                    .requestMatchers(antMatcher("/api/auth/**")).permitAll()
//                        .requestMatchers(antMatcher("/api/test/**")).permitAll()
                      .requestMatchers(mvcMatcherBuilder.pattern("/api/auth/**")).permitAll()
                      .requestMatchers(mvcMatcherBuilder.pattern("/api/test/**")).permitAll()
                      .requestMatchers(mvcMatcherBuilder.pattern("/events/**")).permitAll()
                      .requestMatchers(mvcMatcherBuilder.pattern("/facilities/**")).permitAll()
                      .requestMatchers(mvcMatcherBuilder.pattern("/admin-board/**")).permitAll()
                      .requestMatchers(mvcMatcherBuilder.pattern("/assign-event/**")).permitAll()
                            .anyRequest().authenticated()
                );
                http.authenticationProvider(authenticationProvider());

                http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
