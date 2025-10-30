package tcucl.back_tcucl.entity.onglet.parkingVoirie.enums;

public enum EnumParkingVoirie_TypeStructure {
    BETON_ARME(0),
    BITUME(1),
    SEMI_RIGIDE(2),
    NA(3);

    private final int code;

    EnumParkingVoirie_TypeStructure(int code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static EnumParkingVoirie_TypeStructure fromCode(int code) {
        for (EnumParkingVoirie_TypeStructure e : values()) {
            if (e.code == code) {
                return e;
            }
        }
        throw new IllegalArgumentException("Code invalide : " + code);
    }
}
