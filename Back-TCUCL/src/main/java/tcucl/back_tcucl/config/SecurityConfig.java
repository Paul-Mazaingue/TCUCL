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
    private final boolean devMode;
    private final String allowedOrigin;

    public SecurityConfig(CustomUserDetailsServiceImpl customUserDetailsService,
                          JwtUtils jwtUtils,
                          @Value("${app.dev-mode:false}") boolean devMode,
                          @Value("${app.cors.allowed-origin:http://localhost:4200}") String allowedOrigin) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtils = jwtUtils;
        this.devMode = devMode;
        this.allowedOrigin = allowedOrigin;

        // Simple log pour vérifier dans les logs que devMode est bien lu
        System.out.println(">>> SecurityConfig initialised - devMode=" + devMode + ", allowedOrigin=" + allowedOrigin);
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // CORS toujours activé tant qu'on a un front sur un autre port/domaine
        http.cors(Customizer.withDefaults());

        http
            .csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth ->
                auth
                    // Très important : laisser passer TOUTES les OPTIONS (preflight CORS)
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

                    // Le reste nécessite auth
                    .anyRequest().authenticated()
            )
            .addFilterBefore(new JwtFilter(customUserDetailsService, jwtUtils), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // En dev, on peut être plus permissif si besoin
        if (devMode) {
            // On accepte l'origine du .env + quelques valeurs utiles
            configuration.setAllowedOriginPatterns(List.of(
                    allowedOrigin,
                    "http://localhost:8081",
                    "http://localhost:8080",
                    "http://localhost:4200"
            ));
        } else {
            // En "prod", on garde uniquement ce qui vient de la config
            configuration.setAllowedOriginPatterns(List.of(allowedOrigin));
        }

        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
