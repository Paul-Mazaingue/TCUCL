package tcucl.back_tcucl.entity.onglet.batiment.enums;

public enum EnumBatiment_TypeTravaux {

    VOIRIE(0),
    FONDATIONS_INFRASTRUCTURES(1),
    SUPERSTRUCTURES_MAÇONNERIE(2),
    COUVERTURE_ETANCHÉITÉ(3),
    CLOISONNEMENT_DOUBLAGE(4),
    FACADES_ET_MENUISERIES(5),
    REVETEMENTS_DE_SOLS_ET_MURS(6),
    CHAUFFAGE_VENTILATION_CLIMATISATION(7),
    SANITAIRES(8),
    ELECTRICITE(9),
    COMMUNICATION(10);

    private final int code;

    EnumBatiment_TypeTravaux(int code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static EnumBatiment_TypeTravaux fromCode(int code) {
        for (EnumBatiment_TypeTravaux e : values()) {
            if (e.code == code) {
                return e;
            }
        }
        throw new IllegalArgumentException("Code invalide : " + code);
    }
}
