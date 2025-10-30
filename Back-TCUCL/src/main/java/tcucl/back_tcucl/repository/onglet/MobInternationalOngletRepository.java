package tcucl.back_tcucl.repository.onglet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tcucl.back_tcucl.entity.onglet.mobInternationale.MobInternationalOnglet;

@Repository
public interface MobInternationalOngletRepository extends JpaRepository<MobInternationalOnglet, Long> {
    // This interface extends JpaRepository, which provides CRUD operations for the MobInternationalOnglet entity.
    // You can add custom query methods here if needed.
}
