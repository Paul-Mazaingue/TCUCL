package tcucl.back_tcucl.entity.onglet.achat.enums;

public enum EnumAchatRestauration_Methode {
    METHODE_RAPIDE(0),
    METHODE_INTERMEDIAIRE(1),
    METHODE_DETAILLE(2),
    NAN(3);

    private final int code;

    EnumAchatRestauration_Methode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static EnumAchatRestauration_Methode fromCode(int code) {
        for (EnumAchatRestauration_Methode e : values()) {
            if (e.code == code) {
                return e;
            }
        }
        throw new IllegalArgumentException("Code invalide : " + code);
    }
}
