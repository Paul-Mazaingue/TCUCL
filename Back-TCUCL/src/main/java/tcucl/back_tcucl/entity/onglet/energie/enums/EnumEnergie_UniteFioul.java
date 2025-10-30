package tcucl.back_tcucl.entity.onglet.energie.enums;

public enum EnumEnergie_UniteFioul {
    TONNE(0, "kgCO2e/tonnes"),
    MWH_PCS(1, "kgCO2e/MWh PCS"),
    M3(2, "kgCO2e/m3");

    private final int code;
    private final String libelle;


    EnumEnergie_UniteFioul(int code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public Integer getCode() {
        return code;
    }

    public String getLibelle() {
        return libelle;
    }

    public static EnumEnergie_UniteFioul fromCode(int code) {
        for (EnumEnergie_UniteFioul e : values()) {
            if (e.code == code) {
                return e;
            }
        }
        throw new IllegalArgumentException("Code invalide : " + code);
    }
}

