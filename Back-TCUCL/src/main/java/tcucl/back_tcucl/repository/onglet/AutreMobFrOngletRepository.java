package tcucl.back_tcucl.repository.onglet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tcucl.back_tcucl.entity.onglet.AutreMobFrOnglet;

@Repository
public interface AutreMobFrOngletRepository extends JpaRepository<AutreMobFrOnglet, Long> {

}
