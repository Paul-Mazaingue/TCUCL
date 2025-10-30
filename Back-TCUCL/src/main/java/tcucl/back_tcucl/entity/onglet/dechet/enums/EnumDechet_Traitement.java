package tcucl.back_tcucl.entity.onglet.dechet.enums;

public enum EnumDechet_Traitement {
    RECYCLAGE(0,"Recyclage"),
    INCINERATION(1,"Incinération"),
    STOCKAGE(2,"Stockage"),
    COMPOSTAGE(3,"Compostage"),
    DECHARGE(4,"Décharge"),;

    private final int code;
    private final String libelle;

    EnumDechet_Traitement(int code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public int getCode() {
        return code;
    }

    public String getLibelle() {
        return libelle;
    }

    public static EnumDechet_Traitement fromCode(int code) {
        for (EnumDechet_Traitement e : values()) {
            if (e.code == code) {
                return e;
            }
        }
        throw new IllegalArgumentException("Code invalide : " + code);
    }
}
