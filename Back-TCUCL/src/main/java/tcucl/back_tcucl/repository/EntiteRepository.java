package tcucl.back_tcucl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tcucl.back_tcucl.entity.Entite;
import java.util.Optional;

@Repository
public interface EntiteRepository extends JpaRepository<Entite, Long> {

    public Entite findEntiteById(Long entiteId);

    public boolean existsByNomAndType(String entiteNom, String entiteType);

    Optional<Entite> findByNom(String nom);

    Optional<Entite> findFirstByType(String type);
}
