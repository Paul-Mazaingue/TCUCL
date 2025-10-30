package tcucl.back_tcucl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tcucl.back_tcucl.entity.facteurEmission.FacteurEmission;

import java.util.Optional;

@Repository
public interface FacteurEmissionRepository extends JpaRepository<FacteurEmission, Long> {

    Optional<FacteurEmission> findByCategorieAndTypeAndUnite(String categorie, String type, String unite);
    Optional<FacteurEmission> findByCategorieAndType(String categorie, String type);

}
