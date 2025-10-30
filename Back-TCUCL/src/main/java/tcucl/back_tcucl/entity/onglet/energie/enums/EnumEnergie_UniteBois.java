package tcucl.back_tcucl.entity.onglet.energie.enums;

public enum EnumEnergie_UniteBois {
    TONNE(0, "kgCO2e/tonnes"),
    MWH_PCS(1, "kgCO2e/MWh PCS");

    private final int code;
    private final String libelle;

    EnumEnergie_UniteBois(int code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public Integer getCode() {
        return code;
    }

    public String getLibelle() {
        return libelle;
    }

    public static EnumEnergie_UniteBois fromCode(int code) {
        for (EnumEnergie_UniteBois e : values()) {
            if (e.code == code) {
                return e;
            }
        }
        throw new IllegalArgumentException("Code invalide : " + code);
    }
}
