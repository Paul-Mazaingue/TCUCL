package tcucl.back_tcucl.entity.onglet.energie.enums;

public enum EnumEnergie_NomReseauVille {
    LILLE(0),
    LOMME(1),
    LAMBERSAART(2),
    ROUBAIX(3);

    private final int code;

    EnumEnergie_NomReseauVille(int code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public static EnumEnergie_NomReseauVille fromCode(int code) {
        for (EnumEnergie_NomReseauVille e : values()) {
            if (e.code == code) {
                return e;
            }
        }
        throw new IllegalArgumentException("Code invalide : " + code);
    }
}
