package tcucl.back_tcucl.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tcucl.back_tcucl.dto.securite.AnneeSecuriteDto;
import tcucl.back_tcucl.entity.Annee;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnneeRepository extends JpaRepository<Annee, Long> {

//    @Query("""
//            SELECT new tcucl.back_tcucl.dto.securite.AnneeSecuriteDto(
//                a.id,
//                ao.id,
//                aio.id,
//                amfo.id,
//                bimo.id,
//                do.id,
//                efo.id,
//                eno.id,
//                go.id,
//                mdto.id,
//                mio.id,
//                no.id,
//                pvo.id,
//                vo.id
//            )
//            FROM Annee a
//            LEFT JOIN a.achatOnglet ao
//            LEFT JOIN a.autreImmobilisationOnglet aio
//            LEFT JOIN a.autreMobFrOnglet amfo
//            LEFT JOIN a.batimentImmobilisationMobilierOnglet bimo
//            LEFT JOIN a.dechetOnglet do
//            LEFT JOIN a.emissionFugitiveOnglet efo
//            LEFT JOIN a.energieOnglet eno
//            LEFT JOIN a.generalOnglet go
//            LEFT JOIN a.mobiliteDomicileTravailOnglet mdto
//            LEFT JOIN a.mobInternationalOnglet mio
//            LEFT JOIN a.numeriqueOnglet no
//            LEFT JOIN a.parkingVoirieOnglet pvo
//            LEFT JOIN a.vehiculeOnglet vo
//            WHERE a.entite.id = :entiteId
//            """)
//    List<AnneeSecuriteDto> findAnneesSecurityByEntiteId(@Param("entiteId") Long entiteId);

    @EntityGraph(attributePaths = "onglets")
    List<Annee> findByEntiteId(Long entiteId);

    Optional<Annee> findByEntiteIdAndAnneeValeur(Long entiteId, int anneeValeur);


}
