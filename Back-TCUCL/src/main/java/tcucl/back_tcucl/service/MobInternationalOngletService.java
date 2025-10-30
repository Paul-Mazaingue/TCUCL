package tcucl.back_tcucl.service;

import org.springframework.web.multipart.MultipartFile;
import tcucl.back_tcucl.dto.onglet.mobInternational.MobInternationalOngletDto;
import tcucl.back_tcucl.dto.onglet.mobInternational.MobInternationalResultatDto;
import tcucl.back_tcucl.dto.onglet.mobInternational.VoyageDto;
import tcucl.back_tcucl.entity.onglet.mobInternationale.MobInternationalOnglet;

public interface MobInternationalOngletService {

    MobInternationalOnglet getMobInternationalOngletById(Long ongletId);

    void updateMobInternationalOngletPartiel(Long ongletId, MobInternationalOngletDto mobInternationalOngletDto);

    void ajouterVoyage(Long ongletId, VoyageDto voyageDto);

    void supprimerVoyage(Long ongletId, Long voyageId);

    void updateVoyagePartiel(Long ongletId, Long voyageId, VoyageDto dto);

    MobInternationalResultatDto getMobInternationalResultat(Long OngletId);

    void importVoyagesFromExcel(Long ongletId, MultipartFile file, boolean rajouter);
}
