package tcucl.back_tcucl.entity.onglet.vehicule;

import jakarta.persistence.*;
import tcucl.back_tcucl.dto.onglet.vehicule.VehiculeDto;
import tcucl.back_tcucl.entity.onglet.Onglet;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicule_onglet")
public class VehiculeOnglet extends Onglet {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "vehicule_onglet_id")
    private List<Vehicule> vehiculeList = new ArrayList<>();

    public VehiculeOnglet() {
        super();
    }

    public List<Vehicule> getVehiculeList() {
        return vehiculeList;
    }

    public void setVehiculeList(List<Vehicule> vehiculeList) {
        this.vehiculeList = vehiculeList;
    }


    public void ajouterVehiculeViaDto(VehiculeDto vehiculeDto) {
        Vehicule vehicule = new Vehicule();
        vehicule.setModeleOuImmatriculation(vehiculeDto.getModeleOuImmatriculation());
        vehicule.setTypeVehicule(vehiculeDto.getTypeVehicule());
        vehicule.setNombreKilometresParVoitureMoyen(vehiculeDto.getNombreKilometresParVoitureMoyen());
        vehicule.setNombreVehiculesIdentiques(vehiculeDto.getNombreVehiculesIdentiques());
        vehicule.setDateAjoutEnBase(vehiculeDto.getDateAjoutEnBase());
        this.vehiculeList.add(vehicule);

    }
}
