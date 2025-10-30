package tcucl.back_tcucl.entity.onglet.parkingVoirie;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import tcucl.back_tcucl.dto.onglet.parkingVoirie.ParkingVoirieDto;
import tcucl.back_tcucl.entity.onglet.Onglet;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "parking_voirie_onglet")
public class ParkingVoirieOnglet extends Onglet {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "parking_voirie_onglet_id")
    @Valid
    private List<ParkingVoirie> parkingVoirieList = new ArrayList<>();

    public ParkingVoirieOnglet() {
        super();
    }

    public List<ParkingVoirie> getParkingVoirieList() {
        return parkingVoirieList;
    }

    public void setParkingVoirieList(List<ParkingVoirie> parkingVoirieList) {
        this.parkingVoirieList = parkingVoirieList;
    }

    public void ajouterParkingVoirieViaDto(ParkingVoirieDto parkingVoirieDto) {
        ParkingVoirie parkingVoirie = new ParkingVoirie();
        parkingVoirie.setNomOuAdresse(parkingVoirieDto.getNomOuAdresse());
        parkingVoirie.setDateConstruction(parkingVoirieDto.getDateConstruction());
        parkingVoirie.setEmissionsGesConnues(parkingVoirieDto.getEmissionsGesConnues());
        parkingVoirie.setEmissionsGesReelles(parkingVoirieDto.getEmissionsGesReelles());
        parkingVoirie.setType(parkingVoirieDto.getType());
        parkingVoirie.setNombreM2(parkingVoirieDto.getNombreM2());
        parkingVoirie.setTypeStructure(parkingVoirieDto.getTypeStructure());
        parkingVoirie.setDateAjoutEnBase(parkingVoirieDto.getDateAjoutEnBase());

        this.parkingVoirieList.add(parkingVoirie);
    }
}
