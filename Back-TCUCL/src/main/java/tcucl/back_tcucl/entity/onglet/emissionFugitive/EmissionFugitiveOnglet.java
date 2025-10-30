package tcucl.back_tcucl.entity.onglet.emissionFugitive;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import tcucl.back_tcucl.dto.onglet.emissionFugitive.MachineEmissionFugitiveDto;
import tcucl.back_tcucl.entity.onglet.Onglet;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "emission_fugitive_onglet")
public class EmissionFugitiveOnglet extends Onglet {


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "emission_fugitive_onglet_id")
    @Valid
    private List<MachineEmissionFugitive> machinesEmissionFugitive = new ArrayList<>();

    public EmissionFugitiveOnglet() {
        super();
    }

    public List<MachineEmissionFugitive> getMachinesEmissionFugitive() {
        return machinesEmissionFugitive;
    }

    public void setMachinesEmissionFugitive(List<MachineEmissionFugitive> machinesEmissionFugitive) {
        this.machinesEmissionFugitive = machinesEmissionFugitive;
    }

    public void ajouterMachineViaDto(MachineEmissionFugitiveDto machineEmissionFugitiveDto) {
        MachineEmissionFugitive machineEmissionFugitive = new MachineEmissionFugitive();

        machineEmissionFugitive.setDescriptionMachine(machineEmissionFugitiveDto.getDescriptionMachine());
        machineEmissionFugitive.setQuantiteFluideKg(machineEmissionFugitiveDto.getQuantiteFluideKg());
        machineEmissionFugitive.setTauxDeFuiteConnu(machineEmissionFugitiveDto.getTauxDeFuiteConnu());
        machineEmissionFugitive.setTauxDeFuite(machineEmissionFugitiveDto.getTauxDeFuite());
        machineEmissionFugitive.setTypeFluide(machineEmissionFugitiveDto.getTypeFluide());
        machineEmissionFugitive.setTypeMachine(machineEmissionFugitiveDto.getTypeMachine());

        this.machinesEmissionFugitive.add(machineEmissionFugitive);
    }


}