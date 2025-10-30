package tcucl.back_tcucl.service;

import tcucl.back_tcucl.dto.onglet.emissionFugitive.EmissionFugitiveResultatDto;
import tcucl.back_tcucl.dto.onglet.emissionFugitive.MachineEmissionFugitiveDto;
import tcucl.back_tcucl.dto.onglet.emissionFugitive.EmissionFugitiveOngletDto;
import tcucl.back_tcucl.entity.onglet.emissionFugitive.EmissionFugitiveOnglet;

public interface EmissionFugitiveOngletService {

    EmissionFugitiveOnglet getEmissionFugitiveOngletById(Long ongletId);

    void updateEmissionFugitiveOnglet(Long ongletId, EmissionFugitiveOngletDto emissionFugitiveOngletDto);

    void ajouterMachine(Long ongletId, MachineEmissionFugitiveDto machineEmissionFugitiveDto);

    void supprimerMachine(Long ongletId, Long machineId);

    void updateMachinePartiel(Long ongletId, Long machineId, MachineEmissionFugitiveDto machineEmissionFugitiveDto);

    EmissionFugitiveResultatDto getEmissionFugitiveResult(Long ongletId);

}
