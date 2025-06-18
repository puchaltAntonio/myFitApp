package com.learning.myfitapp.common.config;

import com.learning.myfitapp.common.web.error.handlers.AuthAccessDeniedHandler;
import com.learning.myfitapp.modules.auth.controllers.AuthController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilter(final HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(
                        httpSecurityCsrfConfigurer ->
                                httpSecurityCsrfConfigurer.ignoringRequestMatchers(
                                        String.format(
                                                "%s%s",
                                                AuthController.CONTROLLER_PATH,
                                                AuthController.POST_ACCESS_SUB_PATH
                                        ),
                                        String.format(
                                                "%s%s",
                                                AuthController.CONTROLLER_PATH,
                                                AuthController.POST_REFRESH_SUB_PATH
                                        ),
                                        String.format(
                                                "%s%s",
                                                AuthController.CONTROLLER_PATH,
                                                AuthController.POST_LOGOUT_SUB_PATH
                                        ),
                                        String.format(
                                                "%s%s",
                                                AuthController.CONTROLLER_PATH,
                                                AuthController.PUT_RECOVER_PASSWORD_PATH
                                        )
                                )
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(
                        authorizationManagerRequestMatcherRegistry ->
                                authorizationManagerRequestMatcherRegistry
                                        .requestMatchers(
                                                "/swagger-ui/**",
                                                "/v3/api-docs/**",
                                                "/actuator/**",
                                                String.format(
                                                        "%s%s",
                                                        AuthController.CONTROLLER_PATH,
                                                        AuthController.POST_ACCESS_SUB_PATH
                                                ),
                                                String.format(
                                                        "%s%s",
                                                        AuthController.CONTROLLER_PATH,
                                                        AuthController.POST_REFRESH_SUB_PATH
                                                ),
                                                String.format(
                                                        "%s%s",
                                                        AuthController.CONTROLLER_PATH,
                                                        AuthController.POST_LOGOUT_SUB_PATH
                                                ),
                                                String.format(
                                                        "%s%s",
                                                        AuthController.CONTROLLER_PATH,
                                                        AuthController.PUT_RECOVER_PASSWORD_PATH
                                                )
                                        ).permitAll()
                                        .anyRequest()
                                        .authenticated()
                )
                .exceptionHandling(
                        httpSecurityExceptionHandlingConfigurer ->
                                httpSecurityExceptionHandlingConfigurer.accessDeniedHandler(
                                        new AuthAccessDeniedHandler()
                                )
                )
                .oauth2ResourceServer(
                        httpSecurityOAuth2ResourceServerConfigurer ->
                                httpSecurityOAuth2ResourceServerConfigurer
                                        .jwt(
                                                jwtConfigurer ->
                                                        jwtConfigurer.jwtAuthenticationConverter(
                                                                jwtAuthenticationConverter()
                                                        )
                                        )
                );
        return http.build();
    }


    public JwtAuthenticationConverter jwtAuthenticationConverter() {return new JwtAuthenticationConverter(); }
}
