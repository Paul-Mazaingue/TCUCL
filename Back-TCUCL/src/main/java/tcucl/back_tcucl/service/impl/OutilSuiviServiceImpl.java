package tcucl.back_tcucl.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tcucl.back_tcucl.dto.OutilSuiviDto;
import tcucl.back_tcucl.service.OutilSuiviService;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional(readOnly = true)
public class OutilSuiviServiceImpl implements OutilSuiviService {
    private static final Logger log = LoggerFactory.getLogger(OutilSuiviServiceImpl.class);
    @Override
    public OutilSuiviDto loadForEntite(Long entiteId) {
        log.info("[OutilSuivi] loadForEntite exécuté pour entiteId={}", entiteId);

        // Mocks alignés sur le composant Angular (remplacer par des calculs/requêtes réelles plus tard)
        List<Integer> years = Arrays.asList(2018, 2019, 2020, 2021, 2022, 2023, 2025, 2027, 2030, 2032, 2035);

        // Utiliser null pour "trous" (JSON ne supporte pas NaN)
        List<Float> objectif = Arrays.asList(800f, 780f, 740f, null, 680f, 640f, 620f, null, 560f, 540f, 500f);
        List<Float> realise  = Arrays.asList(850f, 700f, null, 720f, 710f, 590f, null, 605f, 540f, null, 480f);

        List<String> postes = Arrays.asList(
                "Emissions fugitives","Energie","Déplacements France","Déplacements internationaux",
                "Bâtiments, mobilier et parkings","Numérique","Autres immobilisations","Achats et restauration","Déchets"
        );

        Map<Integer, List<Float>> postesObjectifParAn = new LinkedHashMap<>();
        Map<Integer, List<Float>> postesRealiseParAn  = new LinkedHashMap<>();

        Map<Integer, Map<String, Float>> indicateursParAn = new LinkedHashMap<>();

        // Scénarios pour quelques années (comme dans le composant)
        Map<Integer, float[]> objScen = new HashMap<>();
        objScen.put(2018, new float[]{5, 140, 220, 260, 70, 30, 10, 8, 40});
        objScen.put(2020, new float[]{8, 90, 120, 110, 160, 35, 15, 9, 120});
        objScen.put(2022, new float[]{0, 80, 150, 180, 80, 60, 0, 6, 70});
        objScen.put(2025, new float[]{3, 60, 90, 120, 60, 30, 10, 50, 40});
        objScen.put(2030, new float[]{2, 70, 130, 0, 70, 25, 8, 7, 5});

        Map<Integer, float[]> reaScen = new HashMap<>();
        reaScen.put(2018, new float[]{6, 180, 260, 320, 95, 42, 12, 12, 55});
        reaScen.put(2020, new float[]{7, 85, 100, 95, 200, 50, 18, 11, 140});
        reaScen.put(2022, new float[]{0, 70, 140, 210, 85, 120, 0, 9, 90});
        reaScen.put(2025, new float[]{4, 55, 85, 130, 65, 38, 12, 90, 42});
        reaScen.put(2030, new float[]{3, 65, 110, 0, 60, 22, 6, 10, 3});

        for (Integer y : years) {
            float[] obj = objScen.get(y);
            float[] rea = reaScen.get(y);
            if (obj == null) obj = new float[]{12.0f, 95.0f, 181.2f, 234.4f, 89.0f, 41.0f, 22.0f, 5.5f, 76.9f};
            if (rea == null) rea = new float[]{ 9.5f,113.2f, 178.5f, 315.2f,108.7f, 47.6f, 18.0f,11.8f, 50.5f};

            postesObjectifParAn.put(y, toList(obj));
            postesRealiseParAn.put(y, toList(rea));
        }

        // Indicateurs base
        Map<String, Number> base = new LinkedHashMap<>();
        base.put("conso_energie", 3600);
        base.put("chauffage", 1500);
        base.put("electricite", 2100);
        base.put("intensite_carbone_energie", 177.02);
        base.put("distance_salarie", 2694930);
        base.put("part_modale_voiture_salarie", 1);
        base.put("part_modale_ve_salarie", 0);
        base.put("part_modale_doux_salarie", 0);
        base.put("distance_etudiants", 21288650);
        base.put("part_modale_voiture_etudiants", 0);
        base.put("part_modale_ve_etudiants", null);
        base.put("part_modale_doux_etudiants", 1);
        base.put("intensite_carbone_trajets", 91);
        base.put("distance_internationale", 563211);
        base.put("intensite_carbone_international", 145);
        base.put("nb_mobilier", null);
        base.put("nb_numerique", 4041);
        base.put("quantite_dechets", 164);

        for (int i = 0; i < years.size(); i++) {
            Integer y = years.get(i);
            double progress = (years.size() > 1) ? ((double) i / (years.size() - 1)) : 0d;
            double reduction = 1d - (progress * 0.7d);
            Map<String, Float> vals = new LinkedHashMap<>();
            vals.put("conso_energie", roundF(base.get("conso_energie"), reduction));
            vals.put("chauffage", roundF(base.get("chauffage"), reduction));
            vals.put("electricite", roundF(base.get("electricite"), reduction));
            vals.put("intensite_carbone_energie", floatOrNull(base.get("intensite_carbone_energie"), 1 - progress * 0.3));
            vals.put("distance_salarie", roundF(base.get("distance_salarie"), reduction));
            vals.put("part_modale_voiture_salarie", floatOrNull(base.get("part_modale_voiture_salarie"), 1 - progress * 0.3));
            vals.put("part_modale_ve_salarie", floatOrNull(base.get("part_modale_ve_salarie"), progress * 0.4));
            vals.put("part_modale_doux_salarie", floatOrNull(base.get("part_modale_doux_salarie"), 1 + progress * 0.3));
            vals.put("distance_etudiants", roundF(base.get("distance_etudiants"), reduction));
            vals.put("part_modale_voiture_etudiants", floatOrNull(base.get("part_modale_voiture_etudiants"), 1));
            vals.put("part_modale_ve_etudiants", floatOrNull(base.get("part_modale_ve_etudiants"), progress * 0.2));
            vals.put("part_modale_doux_etudiants", floatOrNull(base.get("part_modale_doux_etudiants"), 1 + progress * 0.2));
            vals.put("intensite_carbone_trajets", floatOrNull(base.get("intensite_carbone_trajets"), 1 - progress * 0.4));
            vals.put("distance_internationale", roundF(base.get("distance_internationale"), reduction));
            vals.put("intensite_carbone_international", floatOrNull(base.get("intensite_carbone_international"), 1 - progress * 0.25));
            vals.put("nb_mobilier", floatOrNull(base.get("nb_mobilier"), (i * 50))); // peut rester null
            vals.put("nb_numerique", floatOrNull(base.get("nb_numerique"), 1 + progress * 0.5));
            vals.put("quantite_dechets", roundF(base.get("quantite_dechets"), reduction));
            indicateursParAn.put(y, vals);
        }

        List<Float> globalTotals = Arrays.asList(12.5f, 11.5f, 10.9f, 11.8f, 10.3f, 9.7f, 9.2f, 9.8f, 8.4f, 7.9f, 7.2f);

        OutilSuiviDto dto = new OutilSuiviDto(entiteId, years, objectif, realise, postes, postesObjectifParAn, postesRealiseParAn, indicateursParAn, globalTotals);
        log.info("[OutilSuivi] loadForEntite terminé pour entiteId={} ({} années, {} postes)", entiteId, years.size(), postes.size());
        return dto;
    }

    private List<Float> toList(float[] arr) {
        List<Float> list = new ArrayList<>(arr.length);
        for (float v : arr) list.add(v);
        return list;
    }
    private Float roundF(Number base, double factor) {
        if (base == null) return null;
        return (float) Math.round(base.doubleValue() * factor);
    }
    private Float floatOrNull(Number base, double factor) {
        if (base == null) return null;
        return (float) (base.doubleValue() * factor);
    }
}
