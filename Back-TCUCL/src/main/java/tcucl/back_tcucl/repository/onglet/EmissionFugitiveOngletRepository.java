package tcucl.back_tcucl.repository.onglet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tcucl.back_tcucl.entity.onglet.emissionFugitive.EmissionFugitiveOnglet;

@Repository
public interface EmissionFugitiveOngletRepository extends JpaRepository<EmissionFugitiveOnglet, Long> {

}
