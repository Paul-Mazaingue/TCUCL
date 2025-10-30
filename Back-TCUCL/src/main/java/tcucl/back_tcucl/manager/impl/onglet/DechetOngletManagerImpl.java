package tcucl.back_tcucl.manager.impl.onglet;

import org.springframework.stereotype.Component;
import tcucl.back_tcucl.dto.onglet.dechet.DechetDto;
import tcucl.back_tcucl.dto.onglet.dechet.DechetOngletDto;
import tcucl.back_tcucl.entity.onglet.dechet.DechetOnglet;
import tcucl.back_tcucl.entity.onglet.dechet.Dechet;
import tcucl.back_tcucl.exceptionPersonnalisee.OngletNonTrouveIdException;
import tcucl.back_tcucl.manager.DechetOngletManager;
import tcucl.back_tcucl.repository.onglet.DechetOngletRepository;

@Component
public class DechetOngletManagerImpl implements DechetOngletManager {

    private final DechetOngletRepository dechetOngletRepository;

    public DechetOngletManagerImpl(DechetOngletRepository dechetOngletRepository) {
        this.dechetOngletRepository = dechetOngletRepository;
    }

    @Override
    public DechetOnglet getDechetOngletById(Long ongletId) {
        return dechetOngletRepository.findById(ongletId).orElseThrow(() -> new OngletNonTrouveIdException("DechetOnglet", ongletId));
    }

    @Override
    public void updateDechetOngletPartiel(Long ongletId, DechetOngletDto dechetOngletDto) {
        DechetOnglet dechetOnglet = getDechetOngletById(ongletId);

        if (dechetOngletDto.getEstTermine() != null) {
            dechetOnglet.setEstTermine(dechetOngletDto.getEstTermine());
        }
        if (dechetOngletDto.getNote() != null) {
            dechetOnglet.setNote(dechetOngletDto.getNote());
        }

        // Ordures ménagères
        if (dechetOngletDto.getOrdures_menageres() != null) {
            Dechet ordures = dechetOnglet.getOrdures_menageres();
            if (ordures == null) {
                ordures = new Dechet();
                dechetOnglet.setOrdures_menageres(ordures);
            }
            DechetDto orduresDto = dechetOngletDto.getOrdures_menageres();
            if (orduresDto.getTraitement() != null)
                ordures.setTraitement(orduresDto.getTraitement());
            if (orduresDto.getQuantiteTonne() != null)
                ordures.setQuantiteTonne(orduresDto.getQuantiteTonne());
        }

        // Cartons
        if (dechetOngletDto.getCartons() != null) {
            Dechet cartons = dechetOnglet.getCartons();
            if (cartons == null) {
                cartons = new Dechet();
                dechetOnglet.setCartons(cartons);
            }
            DechetDto cartonsDto = dechetOngletDto.getCartons();
            if (cartonsDto.getTraitement() != null)
                cartons.setTraitement(cartonsDto.getTraitement());
            if (cartonsDto.getQuantiteTonne() != null)
                cartons.setQuantiteTonne(cartonsDto.getQuantiteTonne());
        }

        // Verre
        if (dechetOngletDto.getVerre() != null) {
            Dechet verre = dechetOnglet.getVerre();
            if (verre == null) {
                verre = new Dechet();
                dechetOnglet.setVerre(verre);
            }
            DechetDto verreDto = dechetOngletDto.getVerre();
            if (verreDto.getTraitement() != null)
                verre.setTraitement(verreDto.getTraitement());
            if (verreDto.getQuantiteTonne() != null)
                verre.setQuantiteTonne(verreDto.getQuantiteTonne());
        }

        // Métaux
        if (dechetOngletDto.getMetaux() != null) {
            Dechet metaux = dechetOnglet.getMetaux();
            if (metaux == null) {
                metaux = new Dechet();
                dechetOnglet.setMetaux(metaux);
            }
            DechetDto metauxDto = dechetOngletDto.getMetaux();
            if (metauxDto.getTraitement() != null)
                metaux.setTraitement(metauxDto.getTraitement());
            if (metauxDto.getQuantiteTonne() != null)
                metaux.setQuantiteTonne(metauxDto.getQuantiteTonne());
        }

        // Textile
        if (dechetOngletDto.getTextile() != null) {
            Dechet textile = dechetOnglet.getTextile();
            if (textile == null) {
                textile = new Dechet();
                dechetOnglet.setTextile(textile);
            }
            DechetDto textileDto = dechetOngletDto.getTextile();
            if (textileDto.getTraitement() != null)
                textile.setTraitement(textileDto.getTraitement());
            if (textileDto.getQuantiteTonne() != null)
                textile.setQuantiteTonne(textileDto.getQuantiteTonne());
        }

        dechetOngletRepository.save(dechetOnglet);
    }

}
