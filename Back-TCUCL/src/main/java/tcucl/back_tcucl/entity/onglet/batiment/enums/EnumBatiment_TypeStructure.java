package tcucl.back_tcucl.entity.onglet.batiment.enums;

public enum EnumBatiment_TypeStructure{
        BETON(0),
        MIXTE(1),
        BOIS(2),
        NA(3);

        private final int code;

    EnumBatiment_TypeStructure(int code) {
            this.code = code;
        }

        public Integer getCode() {
            return code;
        }

        public static EnumBatiment_TypeStructure fromCode(int code) {
            for (EnumBatiment_TypeStructure e : values()) {
                if (e.code == code) {
                    return e;
                }
            }
            throw new IllegalArgumentException("Code invalide : " + code);
        }
    }

