package tcucl.back_tcucl.repository.onglet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tcucl.back_tcucl.entity.onglet.achat.AchatOnglet;

@Repository
public interface AchatOngletRepository extends JpaRepository<AchatOnglet, Long> {

}
