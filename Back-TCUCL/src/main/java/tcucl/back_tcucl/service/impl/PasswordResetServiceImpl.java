package tcucl.back_tcucl.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tcucl.back_tcucl.dto.PasswordResetConfirmDto;
import tcucl.back_tcucl.dto.PasswordResetRequestDto;
import tcucl.back_tcucl.entity.PasswordResetToken;
import tcucl.back_tcucl.entity.Utilisateur;
import tcucl.back_tcucl.exceptionPersonnalisee.PasswordPolicyException;
import tcucl.back_tcucl.exceptionPersonnalisee.ResetPasswordRateLimitException;
import tcucl.back_tcucl.exceptionPersonnalisee.ResetPasswordTokenExpiredException;
import tcucl.back_tcucl.exceptionPersonnalisee.ResetPasswordTokenInvalidException;
import tcucl.back_tcucl.repository.PasswordResetTokenRepository;
import tcucl.back_tcucl.repository.UtilisateurRepository;
import tcucl.back_tcucl.service.EmailService;
import tcucl.back_tcucl.service.PasswordResetService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Optional;
import java.util.regex.Pattern;

import static tcucl.back_tcucl.Constante.PREMIERE_CONNEXION_FALSE;

@Service
public class PasswordResetServiceImpl implements PasswordResetService {

    private static final int TOKEN_BYTE_LENGTH = 32;
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d]).{10,}$");

    private final Logger logger = LoggerFactory.getLogger(PasswordResetServiceImpl.class);

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Value("${app.reset-password.url:${APP_RESET_PASSWORD_URL:http://localhost:4200/reset-password}}")
    private String resetPasswordUrl;

    @Value("${app.reset-password.token-validity-seconds:${APP_RESET_PASSWORD_TOKEN_VALIDITY_SECONDS:600}}")
    private long tokenValiditySeconds;

    @Value("${app.reset-password.rate-limit-per-user:${APP_RESET_PASSWORD_RATE_LIMIT_PER_USER:5}}")
    private int rateLimitPerUser;

    @Value("${app.reset-password.rate-limit-per-ip:${APP_RESET_PASSWORD_RATE_LIMIT_PER_IP:5}}")
    private int rateLimitPerIp;

    @Value("${app.reset-password.rate-limit-window-minutes:${APP_RESET_PASSWORD_RATE_LIMIT_WINDOW_MINUTES:60}}")
    private int rateLimitWindowMinutes;

    public PasswordResetServiceImpl(UtilisateurRepository utilisateurRepository,
                                    PasswordResetTokenRepository passwordResetTokenRepository,
                                    PasswordEncoder passwordEncoder,
                                    EmailService emailService) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailService = emailService;
    }

    @Override
    public void requestReset(PasswordResetRequestDto requestDto, String requestIp, String userAgent) {
        if (requestDto == null || requestDto.getEmail() == null || requestDto.getEmail().isBlank()) {
            return;
        }

        Instant now = Instant.now();
        Instant windowStart = now.minus(rateLimitWindowMinutes, ChronoUnit.MINUTES);

        if (requestIp != null && !requestIp.isBlank()) {
            long ipRequests = passwordResetTokenRepository.countByRequestIpAndCreatedAtAfter(requestIp, windowStart);
            if (ipRequests >= rateLimitPerIp) {
                throw new ResetPasswordRateLimitException();
            }
        }

        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findByEmail(requestDto.getEmail());
        if (utilisateurOpt.isEmpty()) {
            // Même réponse pour éviter la divulgation d'existence de compte
            return;
        }

        Utilisateur utilisateur = utilisateurOpt.get();

        long userRequests = passwordResetTokenRepository.countByUtilisateurIdAndCreatedAtAfter(utilisateur.getId(), windowStart);
        if (userRequests >= rateLimitPerUser) {
            throw new ResetPasswordRateLimitException();
        }

        String token = generateToken();
        String tokenHash = hashToken(token);

        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setUtilisateur(utilisateur);
        passwordResetToken.setTokenHash(tokenHash);
        passwordResetToken.setCreatedAt(now);
        passwordResetToken.setExpiresAt(now.plusSeconds(tokenValiditySeconds));
        passwordResetToken.setRequestIp(requestIp);
        passwordResetToken.setRequestUserAgent(userAgent);

        passwordResetTokenRepository.save(passwordResetToken);

        String resetLink = buildResetLink(token);
        emailService.sendPasswordResetEmail(utilisateur.getPrenom(), utilisateur.getNom(), utilisateur.getEmail(), resetLink);

        logger.info("Demande de réinitialisation de mot de passe pour l'utilisateur {}", utilisateur.getEmail());
    }

    @Override
    public void confirmReset(PasswordResetConfirmDto confirmDto) {
        if (confirmDto == null || confirmDto.getToken() == null || confirmDto.getToken().isBlank()) {
            throw new ResetPasswordTokenInvalidException();
        }
        if (!isPasswordValid(confirmDto.getNewPassword())) {
            throw new PasswordPolicyException();
        }

        Instant now = Instant.now();
        String tokenHash = hashToken(confirmDto.getToken());

        PasswordResetToken passwordResetToken = passwordResetTokenRepository
                .findByTokenHashAndUsedAtIsNull(tokenHash)
                .orElseThrow(ResetPasswordTokenInvalidException::new);

        if (now.isAfter(passwordResetToken.getExpiresAt())) {
            throw new ResetPasswordTokenExpiredException();
        }

        Utilisateur utilisateur = passwordResetToken.getUtilisateur();
        utilisateur.setMdp(passwordEncoder.encode(confirmDto.getNewPassword()));
        utilisateur.setEstPremiereConnexion(PREMIERE_CONNEXION_FALSE);
        utilisateur.setPasswordChangedAt(now);

        passwordResetToken.setUsedAt(now);

        utilisateurRepository.save(utilisateur);
        passwordResetTokenRepository.save(passwordResetToken);

        logger.info("Mot de passe réinitialisé pour l'utilisateur {}", utilisateur.getEmail());
    }

    private String generateToken() {
        byte[] randomBytes = new byte[TOKEN_BYTE_LENGTH];
        new SecureRandom().nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

    private String hashToken(String token) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(token.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("Impossible de hacher le token", e);
        }
    }

    private String buildResetLink(String token) {
        String base = resetPasswordUrl != null ? resetPasswordUrl : "http://localhost:4200/reset-password";
        if (base.contains("?")) {
            return base + "&token=" + token;
        }
        if (base.endsWith("/")) {
            base = base.substring(0, base.length() - 1);
        }
        return base + "?token=" + token;
    }

    private boolean isPasswordValid(String password) {
        if (password == null) {
            return false;
        }
        return PASSWORD_PATTERN.matcher(password).matches();
    }
}
