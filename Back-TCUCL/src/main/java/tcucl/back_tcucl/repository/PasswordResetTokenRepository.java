package tcucl.back_tcucl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tcucl.back_tcucl.entity.PasswordResetToken;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByTokenHashAndUsedAtIsNull(String tokenHash);

    long countByUtilisateurIdAndCreatedAtAfter(Long utilisateurId, Instant createdAfter);

    long countByRequestIpAndCreatedAtAfter(String requestIp, Instant createdAfter);
}
