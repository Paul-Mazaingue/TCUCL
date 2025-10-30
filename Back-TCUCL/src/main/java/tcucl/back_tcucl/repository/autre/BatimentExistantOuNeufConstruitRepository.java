package tcucl.back_tcucl.repository.autre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tcucl.back_tcucl.entity.onglet.batiment.BatimentExistantOuNeufConstruit;

@Repository
public interface BatimentExistantOuNeufConstruitRepository extends JpaRepository<BatimentExistantOuNeufConstruit, Long> {

}
