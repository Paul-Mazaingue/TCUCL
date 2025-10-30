package tcucl.back_tcucl.service.impl.onglet;

import org.springframework.stereotype.Service;
import tcucl.back_tcucl.dto.onglet.emissionFugitive.MachineEmissionFugitiveDto;
import tcucl.back_tcucl.dto.onglet.emissionFugitive.EmissionFugitiveOngletDto;
import tcucl.back_tcucl.dto.onglet.emissionFugitive.EmissionFugitiveResultatDto;
import tcucl.back_tcucl.entity.facteurEmission.FacteurEmission;
import tcucl.back_tcucl.entity.facteurEmission.FacteurEmissionParametre;
import tcucl.back_tcucl.entity.onglet.emissionFugitive.EmissionFugitiveOnglet;
import tcucl.back_tcucl.entity.onglet.emissionFugitive.MachineEmissionFugitive;
import tcucl.back_tcucl.manager.EmissionFugitiveOngletManager;
import tcucl.back_tcucl.service.EmissionFugitiveOngletService;
import tcucl.back_tcucl.service.FacteurEmissionService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmissionFugitiveOngletServiceImpl implements EmissionFugitiveOngletService {

    private final EmissionFugitiveOngletManager emissionFugitiveOngletManager;
    private final FacteurEmissionService facteurEmissionService;

    public EmissionFugitiveOngletServiceImpl(EmissionFugitiveOngletManager emissionFugitiveOngletManager, FacteurEmissionService facteurEmissionService) {
        this.emissionFugitiveOngletManager = emissionFugitiveOngletManager;
        this.facteurEmissionService = facteurEmissionService;

    }

    @Override
    public EmissionFugitiveOnglet getEmissionFugitiveOngletById(Long ongletId) {
        return emissionFugitiveOngletManager.getEmissionFugitiveOngletById(ongletId);
    }

    @Override
    public void updateEmissionFugitiveOnglet(Long ongletId, EmissionFugitiveOngletDto emissionFugitiveOngletDto) {
        emissionFugitiveOngletManager.updateEmissionFugitiveOnglet(ongletId, emissionFugitiveOngletDto);
    }

    @Override
    public void ajouterMachine(Long ongletId, MachineEmissionFugitiveDto machineEmissionFugitiveDto) {
        emissionFugitiveOngletManager.ajouterMachine(ongletId, machineEmissionFugitiveDto);
    }

    @Override
    public void supprimerMachine(Long ongletId, Long machineId) {
        emissionFugitiveOngletManager.supprimerMachineFromOnglet(ongletId, machineId);
    }

    @Override
    public void updateMachinePartiel(Long ongletId, Long machineId, MachineEmissionFugitiveDto machineEmissionFugitiveDto) {
        emissionFugitiveOngletManager.updateMachinePartiel(ongletId, machineId, machineEmissionFugitiveDto);
    }

    @Override
    public EmissionFugitiveResultatDto getEmissionFugitiveResult(Long ongletId) {
        EmissionFugitiveOnglet emissionFugitiveOnglet = emissionFugitiveOngletManager.getEmissionFugitiveOngletById(ongletId);
        List<MachineEmissionFugitive> machines = emissionFugitiveOnglet.getMachinesEmissionFugitive();

        Map<Long, Float> emissionsParMachine = machines.stream()
                .collect(Collectors.toMap(
                        MachineEmissionFugitive::getId,
                        machine -> {
                            FacteurEmission facteurEmission = facteurEmissionService.findByCategorieAndType(
                                    FacteurEmissionParametre.EMISSIONS_FUGITIVES,
                                    machine.getTypeFluide().getLibelle()
                            );

                            Float quantiteFluide = machine.getQuantiteFluideKg();
                            Float tauxFuite = 0f;

                            if (Boolean.TRUE.equals(machine.getTauxDeFuiteConnu()) && machine.getTauxDeFuite() != null) {
                                tauxFuite = machine.getTauxDeFuite();
                            } else if (machine.getTauxDeFuite() == null && machine.getTypeMachine()!= null) {
                                FacteurEmission tauxDeFuite = facteurEmissionService.findByCategorieAndType(
                                        FacteurEmissionParametre.EMISSIONS_FUGITIVES,
                                        machine.getTypeMachine().getLibelle()
                                );

                                tauxFuite = tauxDeFuite.getFacteurEmission();
                            }
                            float fuiteKg = (quantiteFluide != null ? quantiteFluide : 0f) * tauxFuite;

                            return (facteurEmission.getFacteurEmission() * fuiteKg) / 1000f;
                        }
                ));
        float total = emissionsParMachine.values().stream()
                .reduce(0f, Float::sum);

        EmissionFugitiveResultatDto resultatDto = new EmissionFugitiveResultatDto();
        resultatDto.setEmissionGESMachine(emissionsParMachine);
        resultatDto.setTotalEmissionGES(total);

        return resultatDto;
    }
}
