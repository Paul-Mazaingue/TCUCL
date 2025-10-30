package tcucl.back_tcucl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tcucl.back_tcucl.dto.securite.UtilisateurSecuriteDto;
import tcucl.back_tcucl.entity.Utilisateur;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    Boolean existsUtilisateurByEmail(String email);

    Optional<Utilisateur> findByEmail(String email);

    Optional<Utilisateur> findById(Long id);

    List<Utilisateur> findAllByEntiteId(Long entiteId);

    @Query("""
            SELECT new tcucl.back_tcucl.dto.securite.UtilisateurSecuriteDto(
                u.id,
                u.email,
                u.mdp,
                u.estSuperAdmin,
                u.estAdmin,
                e.id,
                np.id
            )
            FROM Utilisateur u
            LEFT JOIN u.entite e
            LEFT JOIN e.notesPermanentes np
            WHERE u.email = :utilisateurEmail
            """)
    Optional<UtilisateurSecuriteDto> findUtilisateurSecurityDTOByEmail(@Param("utilisateurEmail") String utilisateurEmail);

}
