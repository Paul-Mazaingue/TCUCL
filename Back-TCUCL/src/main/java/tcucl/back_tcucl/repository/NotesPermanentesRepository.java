package tcucl.back_tcucl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tcucl.back_tcucl.entity.NotesPermanentes;

@Repository
public interface NotesPermanentesRepository extends JpaRepository<NotesPermanentes, Long> {
}
