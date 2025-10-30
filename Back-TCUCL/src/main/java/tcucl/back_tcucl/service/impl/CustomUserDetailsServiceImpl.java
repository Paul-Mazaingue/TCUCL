package tcucl.back_tcucl.service.impl;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tcucl.back_tcucl.dto.securite.AnneeSecuriteDto;
import tcucl.back_tcucl.dto.securite.UtilisateurSecuriteDto;
import tcucl.back_tcucl.service.AnneeService;
import tcucl.back_tcucl.service.UtilisateurService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {


    private final UtilisateurService utilisateurService;
    private final AnneeService anneeService;

    public CustomUserDetailsServiceImpl(UtilisateurService utilisateurService, AnneeService anneeService) {
        this.utilisateurService = utilisateurService;
        this.anneeService = anneeService;
    }

    //Ne pas changer le nom de la méthode loadByUsername en loadUserByEmail, sinon l'authentification ne fonctionnera pas
    //loadByUsername Override la méthode loadUserByUsername de l'interface UserDetailsService qui fait partie de Spring Security (le framework)
    // Pas toucher !
    @Override
    public UserDetails loadUserByUsername(String utilisateurEmail) throws UsernameNotFoundException {

        UtilisateurSecuriteDto utilisateurSecuriteDto = utilisateurService.findUtilisateurSecuriteDtoByEmail(utilisateurEmail);
        List<AnneeSecuriteDto> anneesSecuriteDtoList = anneeService.getAnneeSecuriteDtoByEntiteId(utilisateurSecuriteDto.entiteId());

        if (anneesSecuriteDtoList.isEmpty()) {
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        SimpleGrantedAuthority utilisateurAuthority;

        authorities.add(new SimpleGrantedAuthority("ROLE_UTILISATEUR_" + utilisateurSecuriteDto.utilisateurId()));

        if (utilisateurSecuriteDto.estSuperAdmin()) {
            utilisateurAuthority = new SimpleGrantedAuthority("ROLE_SUPERADMIN");
        } else if (utilisateurSecuriteDto.estAdmin()) {
            utilisateurAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN_" + utilisateurSecuriteDto.entiteId()));
        } else {
            utilisateurAuthority = new SimpleGrantedAuthority("ROLE_USER");
        }
        authorities.add(utilisateurAuthority);

        if(!utilisateurSecuriteDto.estSuperAdmin()){
            authorities.add(new SimpleGrantedAuthority("ROLE_ENTITE_" + utilisateurSecuriteDto.entiteId()));
            authorities.add(new SimpleGrantedAuthority("ROLE_NOTES_PERMANENTES_" + utilisateurSecuriteDto.notesPermanentesId()));

            anneesSecuriteDtoList.forEach(annee -> {
                //année
                authorities.add(new SimpleGrantedAuthority("ROLE_ANNEE_" + annee.anneeId()));

                //onglet
                authorities.add(new SimpleGrantedAuthority("ROLE_ONGLET_" + annee.achatOngletId()));
                authorities.add(new SimpleGrantedAuthority("ROLE_ONGLET_" + annee.autreImmobilisationOngletId()));
                authorities.add(new SimpleGrantedAuthority("ROLE_ONGLET_" + annee.autreMobFrOngletId()));
                authorities.add(new SimpleGrantedAuthority("ROLE_ONGLET_" + annee.batimentImmobilisationMobilierOngletId()));
                authorities.add(new SimpleGrantedAuthority("ROLE_ONGLET_" + annee.dechetOngletId()));
                authorities.add(new SimpleGrantedAuthority("ROLE_ONGLET_" + annee.emissionFugitiveOngletId()));
                authorities.add(new SimpleGrantedAuthority("ROLE_ONGLET_" + annee.energieOngletId()));
                authorities.add(new SimpleGrantedAuthority("ROLE_ONGLET_" + annee.generalOngletId()));
                authorities.add(new SimpleGrantedAuthority("ROLE_ONGLET_" + annee.mobInternationalOngletId()));
                authorities.add(new SimpleGrantedAuthority("ROLE_ONGLET_" + annee.mobiliteDomicileTravailOngletId()));
                authorities.add(new SimpleGrantedAuthority("ROLE_ONGLET_" + annee.numeriqueOngletId()));
                authorities.add(new SimpleGrantedAuthority("ROLE_ONGLET_" + annee.parkingVoirieOngletId()));
                authorities.add(new SimpleGrantedAuthority("ROLE_ONGLET_" + annee.vehiculeOngletId()));
            });

        }

        return new org.springframework.security.core.userdetails.User(
                utilisateurSecuriteDto.email(),
                utilisateurSecuriteDto.mdp(),
                authorities
        );
    }
}