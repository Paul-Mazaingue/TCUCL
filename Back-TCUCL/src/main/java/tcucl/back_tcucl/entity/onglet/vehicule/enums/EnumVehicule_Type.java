package tcucl.back_tcucl.entity.onglet.vehicule.enums;

public enum EnumVehicule_Type {
    VEHICULE_THERMIQUE(0),
    VEHICULE_ELECTRIQUE(1),
    VEHICULE_HYBRIDE(2);

    private final int code;

    EnumVehicule_Type(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static EnumVehicule_Type fromCode(int code) {
        for (EnumVehicule_Type e : values()) {
            if (e.code == code) {
                return e;
            }
        }
        throw new IllegalArgumentException("Code invalide : " + code);
    }
}
