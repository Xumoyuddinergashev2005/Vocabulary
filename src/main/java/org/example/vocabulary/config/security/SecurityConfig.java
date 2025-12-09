package org.example.vocabulary.config.security;

import org.example.vocabulary.config.security.check.TokenAuthenticationEntryPoint;
import org.example.vocabulary.config.security.filter.TokenAuthenticationFilter;
import org.example.vocabulary.config.security.filter.TokenAuthenticationProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final String[] WHITE_LIST = {
            "/auth/**",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/docs/**"
    };

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            TokenAuthenticationEntryPoint entryPoint,
            TokenAuthenticationProvider authenticationProvider,
            TokenAuthenticationFilter tokenAuthenticationFilter) throws Exception {

        http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITE_LIST).permitAll()
                        // .requestMatchers(ADMIN_LIST).hasRole("ADMIN")
                        // .requestMatchers(ADMIN_LIST).hasAnyRole("ADMIN" , "STUDENT")
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(eConfig -> {
                    eConfig.authenticationEntryPoint(entryPoint);
                    // eConfig.accessDeniedHandler()
                });

        return http.build();
    }

}