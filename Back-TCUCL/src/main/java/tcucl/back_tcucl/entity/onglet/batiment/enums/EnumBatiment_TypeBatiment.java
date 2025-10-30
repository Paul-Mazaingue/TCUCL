package tcucl.back_tcucl.entity.onglet.batiment.enums;

public enum EnumBatiment_TypeBatiment {
        BUREAUX(0),
        ENSEIGNEMENT(1),
        EQUIPEMENT_SPORTIF(2),
        HOPITAL(3),
        LOGEMENT_COLLECTIF(4),
        RESTAURATION(5),
        AUTRE(6),
        NA(7);

        private final int code;

    EnumBatiment_TypeBatiment(int code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }

        public static EnumBatiment_TypeBatiment fromCode(int code) {
            for (EnumBatiment_TypeBatiment e : values()) {
                if (e.code == code) {
                    return e;
                }
            }
            throw new IllegalArgumentException("Code invalide : " + code);
        }
    }

