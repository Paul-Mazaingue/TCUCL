package tcucl.back_tcucl.entity.onglet.emissionFugitive.enums;

public enum EnumEmissionFugitive_TypeMachine {
    ARMOIRE(0, "Armoire"),
    DRV(1, "DRV"),
    EAU_GLACEE_MOINS_50KW(2, "Eau glacée < 50kW"),
    EAU_GLACEE_PLUS_50KW(3, "Eau glacée >50kW"),
    INCONNU(4, "Inconnu"),
    NA(5, "");

    private final int code;
    private final String libelle;

    EnumEmissionFugitive_TypeMachine(int code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public Integer getCode() {
        return code;
    }

    public String getLibelle() {
        return libelle;
    }

    public static EnumEmissionFugitive_TypeMachine fromCode(int code) {
        for (EnumEmissionFugitive_TypeMachine e : values()) {
            if (e.code == code) {
                return e;
            }
        }
        throw new IllegalArgumentException("Code invalide : " + code);
    }

    public static EnumEmissionFugitive_TypeMachine fromLibelle(String libelle) {
        for (EnumEmissionFugitive_TypeMachine e : values()) {
            if (e.libelle == libelle) {
                return e;
            }
        }
        throw new IllegalArgumentException("Code invalide : " + libelle);
    }
}
