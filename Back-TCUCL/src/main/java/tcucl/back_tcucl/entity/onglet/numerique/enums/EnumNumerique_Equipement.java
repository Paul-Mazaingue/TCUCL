package tcucl.back_tcucl.entity.onglet.numerique.enums;

public enum EnumNumerique_Equipement {
    ECRAN(0,"Ecran "),
    ENCEINTES(1,"Enceintes"),
    IMPRIMANTE_JET_ENCRE(2,"Imprimante jet d'encre"),
    IMPRIMANTE_LASER(3,"Imprimante laser"),
    ORDINATEUR_FIXE(4,"Ordinateur fixe"),
    ORDINATEUR_FIXE_HAUTE_PERFORMANCE(5,"Ordinateur fixe haute performance"),
    ORDINATEUR_PORTABLE(6,"Ordinateur portable"),
    PHOTOCOPIEURS(7,"Photocopieurs"),
    SMARTPHONE(8,"Smartphone"),
    TABLETTE(9,"Tablette"),
    TELEVISION_MOYENNE_30_49_POUCES(10,"Télévision - moyenne 30-49 pouces"),
    TELEVISION_GRANDE_55_POUCES(11,"Télévision - grande 55 pouces"),
    ECRAN_PUBLICITAIRE_2M2(12,"Ecran publicitaire 2m²"),
    VIDEOPROJECTEURS(13,"Vidéoprojecteurs"),
    BAIE_DE_DISQUES(14,"Baie de disques"),
    MODEM(15,"Modem"),
    RACKS(16,"Racks"),
    SERVEUR(17,"Serveur "),
    SWITCH_ROUTEUR_FIREWALL(18,"Switch / routeur / firewall"),
    AUTRE_NUMERIQUE_EN_EUROS(19,"Autre numérique en € ");

    private final int code;
    private final String libelle;

    EnumNumerique_Equipement(int code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public int getCode() {
        return code;
    }

    public String getLibelle() {
        return libelle;
    }

    public static EnumNumerique_Equipement fromCode(int code) {
        for (EnumNumerique_Equipement e : values()) {
            if (e.code == code) {
                return e;
            }
        }
        throw new IllegalArgumentException("Code invalide : " + code);
    }
}
