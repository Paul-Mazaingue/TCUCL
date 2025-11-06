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
        List<Integer> years = Arrays.asList(2019, 2020, 2021, 2022, 2023, 2024, 2025, 2026, 2027, 2028, 2029, 2030);

        // Utiliser null pour "trous" (JSON ne supporte pas NaN)
        List<Float> objectif = Arrays.asList(780f, 750f, 720f, 680f, 650f, 620f, 590f, 589f, 550f, 520f, 490f, 460f);
        List<Float> realise  = Arrays.asList(780f, 740f, 710f, 700f, 670f, 700f, 600f, 580f, 600f, 489f, 500f, 460f);

        List<String> postes = Arrays.asList(
                "Emissions fugitives","Energie","Déplacements France","Déplacements internationaux",
                "Bâtiments, mobilier et parkings","Numérique","Autres immobilisations","Achats et restauration","Déchets"
        );

        Map<Integer, List<Float>> postesObjectifParAn = new LinkedHashMap<>();
        Map<Integer, List<Float>> postesRealiseParAn  = new LinkedHashMap<>();

        Map<Integer, Map<String, Float>> indicateursParAn = new LinkedHashMap<>();

        // Scénarios pour quelques années avec des données vraiment différentes
        Map<Integer, float[]> objScen = new HashMap<>();
        objScen.put(2019, new float[]{12, 165, 245, 290, 95, 45, 18, 15, 65});
        objScen.put(2020, new float[]{9, 125, 180, 220, 140, 55, 22, 12, 95});
        objScen.put(2022, new float[]{4, 95, 135, 175, 105, 75, 8, 18, 85});
        objScen.put(2024, new float[]{6, 70, 110, 145, 85, 50, 14, 35, 60});
        objScen.put(2026, new float[]{2, 55, 95, 130, 70, 42, 11, 28, 50});
        objScen.put(2028, new float[]{1, 45, 80, 110, 60, 35, 9, 22, 42});
        objScen.put(2030, new float[]{0, 35, 65, 90, 50, 28, 6, 18, 35});

        Map<Integer, float[]> reaScen = new HashMap<>();
        reaScen.put(2019, new float[]{14, 190, 280, 335, 110, 52, 20, 18, 75});
        reaScen.put(2020, new float[]{11, 140, 195, 240, 155, 62, 25, 15, 110});
        reaScen.put(2022, new float[]{5, 105, 150, 195, 115, 88, 10, 22, 95});
        reaScen.put(2024, new float[]{7, 78, 125, 160, 95, 58, 16, 42, 68});
        reaScen.put(2026, new float[]{3, 62, 108, 145, 78, 48, 13, 32, 56});
        reaScen.put(2028, new float[]{2, 50, 88, 120, 68, 40, 11, 26, 47});
        reaScen.put(2030, new float[]{1, 40, 72, 100, 55, 32, 8, 21, 38});

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

        List<Float> globalTotals = Arrays.asList(12.2f, 11.8f, 13f, 10.5f, 10.0f, 14f, 8.9f, 8.3f, 7.8f, 7.3f, 6.8f, 6.2f);

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
