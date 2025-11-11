package tcucl.back_tcucl.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.Customizer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static tcucl.back_tcucl.controller.ControllerConstante.*;

import tcucl.back_tcucl.filter.JwtFilter;
import tcucl.back_tcucl.service.impl.CustomUserDetailsServiceImpl;

@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final CustomUserDetailsServiceImpl customUserDetailsService;
    private final JwtUtils jwtUtils;

    // origine du front (lue depuis application.properties / .env)
    private final String allowedOrigin;

    public SecurityConfig(CustomUserDetailsServiceImpl customUserDetailsService,
                          JwtUtils jwtUtils,
                          @Value("${app.cors.allowed-origin:http://192.168.1.22:8081}") String allowedOrigin) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtils = jwtUtils;
        this.allowedOrigin = allowedOrigin;

        System.out.println(">>> SecurityConfig init - allowedOrigin=" + allowedOrigin);
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // CORS toujours activé
        http.cors(Customizer.withDefaults());

        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth ->
                auth
                    // IMPORTANT : laisser passer TOUTES les pré-requêtes OPTIONS
                    .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                    // Endpoints publics
                    .requestMatchers(
                            REST_API + REST_AUTH + REST_CONNEXION,
                            REST_API + REST_AUTH + REST_CHANGE_MDP_PREMIERE_CONNEXION,
                            REST_API + "/test/**",
                            REST_API + "/test",
                            "/swagger-ui/**",
                            "/v3/api-docs/**"
                    ).permitAll()

                    // Tout le reste nécessite auth
                    .anyRequest().authenticated()
            )
            .addFilterBefore(new JwtFilter(customUserDetailsService, jwtUtils),
                             UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // On autorise ton front (et on peut rajouter localhost pour dev)
        configuration.setAllowedOrigins(List.of(
                allowedOrigin,
                "http://localhost:8081",
                "http://localhost:4200"
        ));

        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // CORS appliqué à toutes les routes
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
