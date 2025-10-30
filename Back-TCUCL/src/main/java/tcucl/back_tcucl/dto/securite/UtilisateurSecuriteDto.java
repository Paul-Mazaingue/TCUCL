package tcucl.back_tcucl.dto.securite;


public record UtilisateurSecuriteDto(
        Long utilisateurId,
        String email,
        String mdp,
        boolean estSuperAdmin,
        boolean estAdmin,
        Long entiteId,
        Long notesPermanentesId
) {}