package tcucl.back_tcucl.manager.impl;

import org.springframework.stereotype.Component;
import tcucl.back_tcucl.dto.securite.UtilisateurSecuriteDto;
import tcucl.back_tcucl.manager.UtilisateurManager;
import tcucl.back_tcucl.entity.Utilisateur;
import tcucl.back_tcucl.exceptionPersonnalisee.UtilisateurNonTrouveIdException;
import tcucl.back_tcucl.exceptionPersonnalisee.UtilisateurNonTrouveEmailException;
import tcucl.back_tcucl.repository.UtilisateurRepository;

import java.util.List;
import java.util.Optional;


@Component
public class UtilisateurManagerImpl implements UtilisateurManager {

    UtilisateurRepository utilisateurRepository;

    public UtilisateurManagerImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public Utilisateur getUtilisateurParId(Long utilisateurId) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(utilisateurId);
        if(utilisateur.isEmpty()){
            throw new UtilisateurNonTrouveIdException(utilisateurId);
        }
        return utilisateur.get();
    }

    @Override
    public Utilisateur getUtilisateurParEmail(String utilisateurEmail) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findByEmail(utilisateurEmail);
        if(utilisateur.isEmpty()){
            throw new UtilisateurNonTrouveEmailException(utilisateurEmail);
        }
        return utilisateur.get();
    }

    @Override
    public List<Utilisateur> getAllUtilisateurParEntiteId(Long entiteId) {
        return utilisateurRepository.findAllByEntiteId(entiteId);
    }

    @Override
    public boolean isEmailDejaPris(String utilisateurEmail){
        return utilisateurRepository.existsUtilisateurByEmail(utilisateurEmail);
    }

    @Override
    public Utilisateur save(Utilisateur utilisateur){
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public void supprimerUtilisateur(Long utilisateurId) {
        utilisateurRepository.deleteById(utilisateurId);
    }

    @Override
    public UtilisateurSecuriteDto findUtilisateurSecurityDTOByEmail(String utilisateurEmail) {
        Optional<UtilisateurSecuriteDto> utilisateur = utilisateurRepository.findUtilisateurSecurityDTOByEmail(utilisateurEmail);
        if(utilisateur.isEmpty()){
            throw new UtilisateurNonTrouveEmailException(utilisateurEmail);
        }
        return utilisateur.get();
    }
}
