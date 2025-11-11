package tcucl.back_tcucl.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

import static tcucl.back_tcucl.controller.ControllerConstante.REST_API;
import static tcucl.back_tcucl.controller.ControllerConstante.REST_AUTH;
import static tcucl.back_tcucl.controller.ControllerConstante.REST_CHANGE_MDP_PREMIERE_CONNEXION;
import static tcucl.back_tcucl.controller.ControllerConstante.REST_CONNEXION;
import tcucl.back_tcucl.filter.JwtFilter;
import tcucl.back_tcucl.service.impl.CustomUserDetailsServiceImpl;



@EnableMethodSecurity
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final CustomUserDetailsServiceImpl customUserDetailsService;
    private final JwtUtils jwtUtils;
    private final boolean devMode;

    public SecurityConfig(CustomUserDetailsServiceImpl customUserDetailsService,
                          JwtUtils jwtUtils,
                          @Value("${app.dev-mode:false}") boolean devMode) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtils = jwtUtils;
        this.devMode = devMode;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        if (devMode) {
            http.cors(Customizer.withDefaults());
        } else {
            http.cors(AbstractHttpConfigurer::disable);
        }

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(REST_API + REST_AUTH + REST_CONNEXION, REST_API + REST_AUTH + REST_CHANGE_MDP_PREMIERE_CONNEXION
                                    //     todo_toProd Supprimer les endpoints non sécurisés et de tests en production 
                                       ,REST_API + "/test/**" 
                                       ,REST_API + "/test"
                                       ,"/swagger-ui/**"
                                       ,"/v3/api-docs/**"
                                ).permitAll()
                                .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtFilter(customUserDetailsService, jwtUtils), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    @ConditionalOnProperty(name = "app.dev-mode", havingValue = "true")
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
