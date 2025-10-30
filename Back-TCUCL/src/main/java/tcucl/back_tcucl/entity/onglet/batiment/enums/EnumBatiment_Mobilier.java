package tcucl.back_tcucl.entity.onglet.batiment.enums;

public enum EnumBatiment_Mobilier {

    ARMOIRE(0, "Armoire"),
    CANAPE(1, "Canapé"),
    CHAISES_BOIS(2, "Chaises bois"),
    CHAISES_BOIS_TEXTILE(3, "Chaises bois textile"),
    CHAISES_PLASTIQUES(4, "Chaises plastiques"),
    CHAISES_MOYENNE(5, "Chaises \"moyenne\""),
    LIT(6, "Lit"),
    TABLE(7,"Table"),
    ASPIRATEUR_CLASSIQUE(8,"Aspirateur classique"),
    ASPIRATEUR_TRAINEAU_PRO(9,"Aspirateur à traineau pro"),
    CLIMATISEUR_MOBILE(10,"Climatiseur mobile"),
    CONGELATEUR_ARMOR(11,"Congélateur armoire"),
    CONGELATEUR_COFFRE(12,"Congélateur coffre"),
    LAVELINGE_7KG(13,"Lavelinge 7kg"),
    SECHE_LINGE_6KG(14,"Sèche linge 6 kg"),
    LAVE_VAISSELLE(15,"Lave vaisselle "),
    MACHINE_A_CAFE_EXPRESSO(16,"Machine à café expresso"),
    MACHINE_A_CAFE_FILTRE(17,"Machine à café filtre"),
    MACHINE_A_CAFE_DOSETTES(18,"Machine à café dosettes"),
    MACHINE_A_CAFE_MOYENNE(19,"Machine à café moyenne"),
    BOUILLOIRE(20,"Bouilloire"),
    MICRO_ONDES(21,"Micro-ondes"),
    PLAQUES_DE_CUISSON(22,"Plaques de cuisson "),
    RADIATEUR_ELECTRIQUE(23,"Radiateur électrique"),
    REFRIGERATEUR(24,"Réfrigérateur"),
    AUTRE_MOBILIER_EN_EUROS(25,"Autre mobilier en €"),
    AUTRE_MOBILIER_EN_TONNES(26,"Autre mobilier en Tonnes");

    private final int code;
    private final String libelle;

    EnumBatiment_Mobilier(int code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public Integer getCode() {
        return code;
    }

    public String getLibelle() {
        return libelle;
    }

    public static EnumBatiment_Mobilier fromCode(int code) {
        for (EnumBatiment_Mobilier e : values()) {
            if (e.code == code) {
                return e;
            }
        }
        throw new IllegalArgumentException("Code invalide : " + code);
    }
}
