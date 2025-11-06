package tcucl.back_tcucl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tcucl.back_tcucl.entity.Trajectoire;

import java.util.Optional;

public interface TrajectoireRepository extends JpaRepository<Trajectoire, Long> {
    Optional<Trajectoire> findByEntite_Id(Long entiteId);
}
