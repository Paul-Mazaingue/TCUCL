package tcucl.back_tcucl.manager.impl.onglet;


import org.springframework.stereotype.Component;
import tcucl.back_tcucl.dto.onglet.vehicule.VehiculeDto;
import tcucl.back_tcucl.dto.onglet.vehicule.VehiculeOngletDto;
import tcucl.back_tcucl.entity.onglet.vehicule.VehiculeOnglet;
import tcucl.back_tcucl.entity.onglet.vehicule.Vehicule;
import tcucl.back_tcucl.exceptionPersonnalisee.ElementNontrouveException;
import tcucl.back_tcucl.exceptionPersonnalisee.OngletNonTrouveIdException;
import tcucl.back_tcucl.manager.VehiculeOngletManager;
import tcucl.back_tcucl.repository.onglet.VehiculeOngletRepository;

@Component
public class VehiculeOngletManagerImpl implements VehiculeOngletManager {

    private final VehiculeOngletRepository vehiculeOngletRepository;

    public VehiculeOngletManagerImpl(VehiculeOngletRepository vehiculeOngletRepository) {
        this.vehiculeOngletRepository = vehiculeOngletRepository;
    }

    @Override
    public VehiculeOnglet getVehiculeOngletById(Long ongletId) {
        return vehiculeOngletRepository.findById(ongletId).orElseThrow(
                () -> new OngletNonTrouveIdException("Vehicule", ongletId));
    }

    @Override
    public Vehicule getVehiculeById(Long ongletId, Long vehiculeId) {
        VehiculeOnglet vehiculeOnglet = getVehiculeOngletById(ongletId);
        return vehiculeOnglet.getVehiculeList().stream()
                .filter(p -> p.getId().equals(vehiculeId))
                .findFirst()
                .orElseThrow(() -> new ElementNontrouveException("Vehicule",vehiculeId));

    }

    @Override
    public void updateVehiculeOngletPartiel(Long ongletId, VehiculeOngletDto vehiculeOngletDto) {
        VehiculeOnglet vehiculeOnglet = getVehiculeOngletById(ongletId);

        if (vehiculeOngletDto.getEstTermine() != null)
            vehiculeOnglet.setEstTermine(vehiculeOngletDto.getEstTermine());
        if (vehiculeOngletDto.getNote() != null) vehiculeOnglet.setNote(vehiculeOngletDto.getNote());

        if (vehiculeOngletDto.getVehiculeList() != null) {
            // On supprime les véhicules existants et on les remplace par les nouveaux
            vehiculeOnglet.getVehiculeList().clear();
            for (VehiculeDto vehiculeDto : vehiculeOngletDto.getVehiculeList()) {
                vehiculeOnglet.ajouterVehiculeViaDto(vehiculeDto);
            }
        }
        vehiculeOngletRepository.save(vehiculeOnglet);
    }

    @Override
    public void ajouterVehicule(Long ongletId, VehiculeDto vehiculeDto) {
        VehiculeOnglet vehiculeOnglet = getVehiculeOngletById(ongletId);
        vehiculeOnglet.ajouterVehiculeViaDto(vehiculeDto);
        vehiculeOngletRepository.save(vehiculeOnglet);
    }

    @Override
    public void supprimerVehicule(Long ongletId, Long vehiculeId) {
        VehiculeOnglet vehiculeOnglet = getVehiculeOngletById(ongletId);

        Vehicule vehicule = vehiculeOnglet.getVehiculeList()
                .stream()
                .filter(v -> v.getId().equals(vehiculeId))
                .findFirst()
                .orElseThrow(() -> new ElementNontrouveException("Vehicule",vehiculeId));

        vehiculeOnglet.getVehiculeList().remove(vehicule);

        vehiculeOngletRepository.save(vehiculeOnglet);
    }

    @Override
    public void updateVehiculePartiel(Long ongletId, Long vehiculeId, VehiculeDto vehiculeDto) {
        VehiculeOnglet vehiculeOnglet = getVehiculeOngletById(ongletId);

        Vehicule vehicule = vehiculeOnglet.getVehiculeList()
                .stream()
                .filter(v -> v.getId().equals(vehiculeId))
                .findFirst()
                .orElseThrow(() -> new ElementNontrouveException("Vehicule",vehiculeId));

        if (vehiculeDto.getModeleOuImmatriculation() != null) {
            vehicule.setModeleOuImmatriculation(vehiculeDto.getModeleOuImmatriculation());
        }
        if (vehiculeDto.getTypeVehicule() != null) {
            vehicule.setTypeVehicule(vehiculeDto.getTypeVehicule());
        }
        if (vehiculeDto.getNombreKilometresParVoitureMoyen() != null) {
            vehicule.setNombreKilometresParVoitureMoyen(vehiculeDto.getNombreKilometresParVoitureMoyen());
        }
        if (vehiculeDto.getNombreVehiculesIdentiques() != null) {
            vehicule.setNombreVehiculesIdentiques(vehiculeDto.getNombreVehiculesIdentiques());
        }
        if (vehiculeDto.getDateAjoutEnBase() != null) {
            vehicule.setDateAjoutEnBase(vehiculeDto.getDateAjoutEnBase());
        }

        vehiculeOnglet.getVehiculeList().add(vehicule);

        vehiculeOngletRepository.save(vehiculeOnglet);
    }
}

