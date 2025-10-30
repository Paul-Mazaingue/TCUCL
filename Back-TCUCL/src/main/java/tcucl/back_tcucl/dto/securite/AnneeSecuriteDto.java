package tcucl.back_tcucl.dto.securite;

public record AnneeSecuriteDto(
        Long anneeId,
        Long achatOngletId,
        Long autreImmobilisationOngletId,
        Long autreMobFrOngletId,
        Long batimentImmobilisationMobilierOngletId,
        Long dechetOngletId,
        Long emissionFugitiveOngletId,
        Long energieOngletId,
        Long generalOngletId,
        Long mobiliteDomicileTravailOngletId,
        Long mobInternationalOngletId,
        Long numeriqueOngletId,
        Long parkingVoirieOngletId,
        Long vehiculeOngletId
) {}
