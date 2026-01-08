package tcucl.back_tcucl.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tcucl.back_tcucl.config.JwtUtils;
import tcucl.back_tcucl.repository.UtilisateurRepository;
import tcucl.back_tcucl.service.impl.CustomUserDetailsServiceImpl;
import tcucl.back_tcucl.entity.Utilisateur;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final CustomUserDetailsServiceImpl customUserDetailsService;
    private final JwtUtils jwtUtils;
    private final UtilisateurRepository utilisateurRepository;

    public JwtFilter(CustomUserDetailsServiceImpl customUserDetailsService, JwtUtils jwtUtils, UtilisateurRepository utilisateurRepository) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtils = jwtUtils;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String method = request.getMethod();
        String path = request.getRequestURI();

        // Debug pour voir ce qui passe dans le filtre
        // System.out.println(">>> JwtFilter: " + method + " " + path);

        // 1️⃣ Laisser passer TOUTES les requêtes OPTIONS (preflight)
        if ("OPTIONS".equalsIgnoreCase(method)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 2️⃣ Laisser passer les endpoints publics (sans JWT)
        if (path.equals("/api/auth/connexion")
                || path.equals("/api/auth/change-mdp-premiere-connexion")
            || path.equals("/api/auth/forgot-password")
            || path.equals("/api/auth/reset-password")
                || path.startsWith("/api/test")
                || path.startsWith("/swagger-ui")
                || path.startsWith("/v3/api-docs")) {

            filterChain.doFilter(request, response);
            return;
        }

        // 3️⃣ À partir d’ici : comportement JWT normal
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            username = jwtUtils.extractUsername(jwt);
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            Utilisateur utilisateur = utilisateurRepository.findByEmail(username).orElse(null);
            var issuedAt = jwtUtils.extractIssuedAt(jwt);
            if (utilisateur != null && utilisateur.getPasswordChangedAt() != null && issuedAt != null
                    && issuedAt.toInstant().isBefore(utilisateur.getPasswordChangedAt())) {
                filterChain.doFilter(request, response);
                return;
            }

            if (jwtUtils.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
