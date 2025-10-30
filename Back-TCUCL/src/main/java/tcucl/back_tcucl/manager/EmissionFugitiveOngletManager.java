package tcucl.back_tcucl.manager;

import tcucl.back_tcucl.dto.onglet.emissionFugitive.MachineEmissionFugitiveDto;
import tcucl.back_tcucl.dto.onglet.emissionFugitive.EmissionFugitiveOngletDto;
import tcucl.back_tcucl.entity.onglet.emissionFugitive.EmissionFugitiveOnglet;
import tcucl.back_tcucl.entity.onglet.emissionFugitive.MachineEmissionFugitive;

public interface EmissionFugitiveOngletManager {

    EmissionFugitiveOnglet getEmissionFugitiveOngletById(Long ongletId);

    void updateEmissionFugitiveOnglet(Long ongletId, EmissionFugitiveOngletDto emissionFugitiveOngletDto);

    MachineEmissionFugitive getMachineById(Long ongletId, Long machineId);

    void ajouterMachine(Long ongletId, MachineEmissionFugitiveDto machineEmissionFugitiveDto);

    void supprimerMachineFromOnglet(Long ongletId, Long machineId);

    void updateMachinePartiel(Long ongletId, Long machineId, MachineEmissionFugitiveDto machineEmissionFugitiveDto);
}
