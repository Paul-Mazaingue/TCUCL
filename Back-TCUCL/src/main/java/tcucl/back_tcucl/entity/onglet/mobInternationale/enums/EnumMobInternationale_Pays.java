package tcucl.back_tcucl.entity.onglet.mobInternationale.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum EnumMobInternationale_Pays {
    ALBANIE(1, "Albanie", true, 1601,true),
    ALLEMAGNE(2, "Allemagne", true,878,true),
    ANDORRE(3, "Andorre", true,697,true),
    ARMENIE(4, "Armenie", true,3417,true),
    AUTRICHE(5, "Autriche", true,1037,true),
    BELGIQUE(6, "Belgique", true,263,true),
    BIELORUSSIE(7, "Bielorussie", true,1826,true),
    BOSNIE_HERZEGOVINE(8, "Bosnie-Herzegovine", true,1347,true),
    BULGARIE(9, "Bulgarie", true,1756,true),
    CHYPRE(10, "Chypre", true,2936,true),
    CROATIE(11, "Croatie", true,1076,true),
    DANEMARK(12, "Danemark", true,1027,true),
    ESPAGNE(13, "Espagne", true,1058,true),
    ESTONIE(14, "Estonie", true,1858,true),
    FINLANDE(15, "Finlande", true,1898,true),
    GIBRALTAR(16, "Gibraltar", true,1547,true),
    GRECE(17, "Grece", true,2110,true),
    GEORGIE(18, "Georgie", true,3370,true),
    HONGRIE(19, "Hongrie", true,1249,true),
    IRLANDE(20, "Irlande", true,786,true),
    ITALIE(21, "Italie", true,1102,true),
    LETTONIE(22, "Lettonie", true,1674,true),
    LIECHTENSTEIN(23, "Liechtenstein", true,566,true),
    LITUANIE(24, "Lituanie", true,1676,true),
    LUXEMBOURG(25, "Luxembourg", true,263,true),
    MACEDOINE_DU_NORD(26, "Macedoine du Nord", true,1710,true),
    MALTE(27, "Malte", true,1757,true),
    MOLDAVIE(28, "Moldavie", true,1975,true),
    MONACO(29, "Monaco", true,689,true),
    MONTENEGRO(30, "Montenegro", true,1491,true),
    NORVEGE(31, "Norvege", true,1359,true),
    PAYS_BAS(32, "Pays-Bas", true,430,true),
    POLOGNE(33, "Pologne", true,1344,true),
    PORTUGAL(34, "Portugal", true,1472,true),
    ROUMANIE(35, "Roumanie", true,1853,true),
    ROYAUME_UNI(36, "Royaume-Uni", true,634,true),
    REPUBLIQUE_TCHEQUE(37, "Republique Tcheque", true, 853,true),
    SAINT_MARIN(38, "Saint-Marin", true,946,true),
    SERBIE(39, "Serbie", true,1442,true),
    SLOVAQUIE(40, "Slovaquie", true,1254,true),
    SLOVENIE(41, "Slovenie", true,945,true),
    SUISSE(42, "Suisse", true,488,true),
    SUEDE(43, "Suede", true,1541,true),
    TURQUIE(44, "Turquie", true,2215,true),
    UKRAINE(45, "Ukraine", true,2034,true),
    VATICAN(46, "Vatican", true,1102,true),
    AFGHANISTAN(47, "Afghanistan", false,5581,false),
    AFRIQUE_DU_SUD(48, "Afrique du Sud", false,8921,false),
    ALGERIE(49, "Algerie", false,1373,false),
    ANGOLA(50, "Angola", false,6526,false),
    ANGUILLA(51, "Anguilla", false,6704,false),
    ANTIGUA_ET_BARBUDA(52, "Antigua et Barbuda", false,6704,false),
    ANTILLES_NEERLANDAISES(53, "Antilles neerlandaises", false,6764,false),
    ARABIE_SAOUDITE(54, "Arabie saoudite", false,4656,false),
    ARGENTINE(55, "Argentine", false,11113,false),
    ARUBA(56, "Aruba", false,7674,false),
    AUSTRALIE(57, "Australie", false,16960,false),
    AZERBAIDJAN(58, "Azerbaidjan", false,3808,false),
    BAHAMAS(59, "Bahamas", false,7285,false),
    BAHREIN(60, "Bahrein", false,4844,false),
    BANGLADESH(61, "Bangladesh", false,7858,false),
    BARBADE(62, "Barbade", false,6866,false),
    BELIZE(63, "Belize", false,8652,false),
    BENIN(64, "Benin", false,4748,false),
    BERMUDES(65, "Bermudes", false,5755,false),
    BHOUTAN(66, "Bhoutan", false,7559,false),
    BIRMANIE(67, "Birmanie", false,8632,false),
    BOLIVIE(68, "Bolivie", false,10029,false),
    BOTSWANA(69, "Botswana", false,8503,false),
    BRUNEI(70, "Brunei", false,11206,false),
    BRESIL(71, "Bresil", false,9194,false),
    BURKINA_FASO(72, "Burkina Faso", false,4073,false),
    BURUNDI(73, "Burundi", false,6395,false),
    CAMBODGE(74, "Cambodge", false,9939,false),
    CAMEROUN(75, "Cameroun", false,5113,false),
    CANADA(76, "Canada", false,6209,false),
    CAP_VERT(77, "Cap-Vert", false,4375,false),
    CHILI(78, "Chili", false,11686,false),
    CHINE(79, "Chine", false,9277,false),
    COLOMBIE(80, "Colombie", false,8655,false),
    COMORES(81, "Comores", false,7948,false),
    COOK_ILES(82, "Cook (iles)", false,16558,false),
    COREE_DU_NORD(83, "Coree du Nord", false,8770,false),
    COREE_DU_SUD(84, "Coree du Sud", false,8966,false),
    COSTA_RICA(85, "Costa Rica", false,8909,false),
    COTE_D_IVOIRE(86, "Cote d'Ivoire", false,4602,false),
    CUBA(87, "Cuba", false,7602,false),
    DJIBOUTI(88, "Djibouti", false,5527,false),
    DOMINIQUE(89, "Dominique", false,6801,false),
    EGYPTE(90, "Egypte", false,3209,false),
    EMIRATS_ARABES_UNIS(91, "Emirats Arabes Unis", false,5239,false),
    EQUATEUR(92, "Equateur", false,9427,false),
    ERYTHREE(93, "Erythree", false,5054,false),
    ETATS_FEDERES_DE_MICRONESIE(94, "Etats Federes de Micronesie", false,13380,false),
    ETATS_UNIS(95, "Etats-Unis", false,7934,false),
    ETHIOPIE(96, "Ethiopie", false,5586,false),
    FIDJI_ILES(97, "Fidji (iles)", false,16561,false),
    FEROE_ILES(98, "Feroe (iles)", false,1583,false),
    GABON(99, "Gabon", false,5444,false),
    GAMBIE(100, "Gambie", false,4258,false),
    GHANA(101, "Ghana", false,4544,false),
    GRENADE(102, "Grenade", false,7105,false),
    GROENLAND(103, "Groenland", false,3330,false),
    GUADELOUPE(104, "Guadeloupe", false,6757,false),
    GUAM(105, "Guam", false,12162,false),
    GUATEMALA(106, "Guatemala", false,8903,false),
    GUINEE(107, "Guinee", false,4396,false),
    GUINEE_EQUATORIALE(108, "Guinee Equatoriale", false,5301,false),
    GUINEE_BISSAU(109, "Guinee-Bissau", false,4399,false),
    GUYANA(110, "Guyana", false,7525,false),
    GEORGIE_DU_SUD_ET_ILES_SANDWICH(111, "Georgie du Sud et iles Sandwich", false,12068,false),
    HAITI(112, "Haiti", false,7325,false),
    HONDURAS(113, "Honduras", false,8674,false),
    HONG_KONG(114, "Hong Kong", false,9600,false),
    INDE(115, "Inde", false,6572,false),
    INDONESIE(116, "Indonesie", false,12394,false),
    IRAN(117, "Iran", false,4576,false),
    IRAQ(118, "Iraq", false,3854,false),
    ISLANDE(119, "Islande", false,2244,false),
    ISRAEL(120, "Israël", false,3288,false),
    JAMAIQUE(121, "Jamaique", false,7745,false),
    JAPON(122, "Japon", false,9729,false),
    JORDANIE(123, "Jordanie", false,3385,false),
    KAZAKHSTAN(124, "Kazakhstan", false,4751,false),
    KENYA(125, "Kenya", false,6314,false),
    KIRGHIZISTAN(126, "Kirghizistan", false,5533,false),
    KIRIBATI(127, "Kiribati", false,14352,false),
    KOWEIT(128, "Koweit", false,4379,false),
    LAOS(129, "Laos", false,9396,false),
    LESOTHO(130, "Lesotho", false,9084,false),
    LIBAN(131, "Liban", false,3191,false),
    LIBYE(132, "Libye", false,2636,false),
    LIBERIA(133, "Liberia", false,4840,false),
    MACAO(134, "Macao", false,9602,false),
    MADAGASCAR(135, "Madagascar", false,8758,false),
    MALAISIE(136, "Malaisie", false,10463,false),
    MALAWI(137, "Malawi", false,7600,false),
    MALDIVES(138, "Maldives", false,8333,false),
    MALI(139, "Mali", false,4175,false),
    MALOUINES_ILES(140, "Malouines (iles)", false,12642,false),
    MAROC(141, "Maroc", false,2131,false),
    MARSHALL_ILES(142, "Marshall (iles)", false,13694,false),
    MARTINIQUE(143, "Martinique", false,6845,false),
    MAURICE_ILE(144, "Maurice (ile)", false,9457,false),
    MAURITANIE(145, "Mauritanie", false,3801,false),
    MEXIQUE(146, "Mexique", false,9209,false),
    MONGOLIE(147, "Mongolie", false,7093,false),
    MONTSERRAT(148, "Montserrat", false,6761,false),
    MOZAMBIQUE(149, "Mozambique", false,8131,false),
    NAMIBIE(150, "Namibie", false,8012,false),
    NAURU(151, "Nauru", false,14440,false),
    NICARAGUA(152, "Nicaragua", false,8731,false),
    NIGER(153, "Niger", false,3483,false),
    NIGERIA(154, "Nigeria", false,4724,false),
    NIUE(155, "Niue", false,16626,false),
    NOUVELLE_CALEDONIE(156, "Nouvelle-Caledonie", false,16626,false),
    NOUVELLE_ZELANDE(157, "Nouvelle-Zelande", false,18545,false),
    NEPAL(158, "Nepal", false,7119,false),
    OMAN(159, "Oman", false,5704,false),
    OUGANDA(160, "Ouganda", false,6024,false),
    OUZBEKISTAN(161, "Ouzbekistan", false,4744,false),
    PAKISTAN(162, "Pakistan", false,5961,false),
    PALAOS(163, "Palaos", false,12217,false),
    PALESTINE(164, "Palestine", false,3319,false),
    PANAMA(165, "Panama", false,8697,false),
    PAPOUASIE_NOUVELLE_GUINEE(166, "Papouasie-Nouvelle-Guinee", false,14206,false),
    PARAGUAY(167, "Paraguay", false,10003,false),
    PHILIPPINES(168, "Philippines", false,10743,false),
    POLYNESIE_FRANCAISE(169, "Polynesie Française", false,15711,false),
    PORTO_RICO(170, "Porto Rico", false,6962,false),
    PEROU(171, "Perou", false,10287,false),
    QATAR(172, "Qatar", false,4979,false),
    RWANDA(173, "Rwanda", false,6250,false),
    REPUBLIQUE_CENTRAFRICAINE(174, "Republique Centrafricaine", false,4973,false),
    REPUBLIQUE_DOMINICAINE(175, "Republique Dominicaine", false,7182,false),
    REPUBLIQUE_DEMOCRATIQUE_DU_CONGO(176, "Republique Democratique du Congo", false,3019,false),
    REUNION_ILE(177, "Reunion (ile)", false,9391,false),
    RUSSIE(178, "Russie", false,2456,false),
    SAINT_CHRISTOPHE_ET_NIEVES(179, "Saint-Christophe-et-Nieves", false,6755,false),
    SAINT_VINCENT_ET_LES_GRENADINES(180, "Saint-Vincent-et-les-Grenadines", false,6991,false),
    SAINTE_LUCIE(181, "Sainte-Lucie", false,6908,false),
    SALOMON_ILES(182, "Salomon (iles)", false,15512,false),
    SALVADOR(183, "Salvador", false,8945,false),
    SAMOA_ILES(184, "Samoa (iles)", false,16084,false),
    SAMOA_AMERICAINES(185, "Samoa Americaines", false,16120,false),
    SAO_TOME_ET_PRINCIPE(186, "Sao Tome-et-Principe", false,5339,false),
    SEYCHELLES(187, "Seychelles", false,7853,false),
    SIERRA_LEONE(188, "Sierra Leone", false,4682,false),
    SINGAPOUR(189, "Singapour", false,10735,false),
    SOMALIE(190, "Somalie", false,6402,false),
    SOUDAN(191, "Soudan", false,3453,false),
    SRI_LANKA(192, "Sri Lanka", false,8502,false),
    SURINAME(193, "Suriname", false,8502,false),
    SWAZILAND(194, "Swaziland", false,8865,false),
    SYRIE(195, "Syrie", false,3286,false),
    SENEGAL(196, "Senegal", false,4221,false),
    TADJIKISTAN(197, "Tadjikistan", false,5414,false),
    TANZANIE(198, "Tanzanie", false,6874,false),
    TAIWAN(199, "Taiwan", false,9892,false),
    TCHAD(200, "Tchad", false,4059,false),
    THAILANDE(201, "Thailande", false,9438,false),
    TIMOR_ORIENTAL(202, "Timor Oriental", false,13150,false),
    TOGO(203, "Togo", false,4770,false),
    TONGA_ILES(204, "Tonga (iles)", false,18867,false),
    TRINITE_ET_TOBAGO(205, "Trinite-et-Tobago", false,7151,false),
    TUNISIE(206, "Tunisie", false,1489,false),
    TURKMENISTAN(207, "Turkmenistan", false,4558,false),
    TUVALU(208, "Tuvalu", false,15519,false),
    URUGUAY(209, "Uruguay", false,10777,false),
    VANUATU(210, "Vanuatu", false,16104,false),
    VENEZUELA(211, "Venezuela", false,7762,false),
    VIETNAM(212, "Vietnam", false, 10122,false),
    WALLIS_ET_FUTUNA_ILES(213, "Wallis et Futuna (iles)", false,16172,false),
    YEMEN(214, "Yemen", false,5512,false),
    ZAMBIE(215, "Zambie", false,7484,false),
    ZIMBABWE(216, "Zimbabwe", false,8018,false),
    AUTRE(217, "Autre:", false,0,false);

//ecosse et kosovo present dans le fichier de distance de l'excel mais pas dans celui dse destinations de la catho

    private final int code;
    private final String libelle;
    private final Boolean accessibleEnTrain;
    private final Integer distanceMoyenneVolOiseau;
    private final Boolean isEurope;

    EnumMobInternationale_Pays(int code, String libelle, Boolean accessibleEnTrain, Integer distanceMoyenneVolOiseau, Boolean isEurope) {
        this.code = code;
        this.libelle = libelle;
        this.accessibleEnTrain = accessibleEnTrain;
        this.distanceMoyenneVolOiseau = distanceMoyenneVolOiseau;
        this.isEurope = isEurope;
    }


    //LIBELLE
    public String getLibelle() {
        return libelle;
    }

    private static final Map<String, EnumMobInternationale_Pays> LIBELLE_TO_ENUM;

    static {
        LIBELLE_TO_ENUM = Arrays.stream(values())
                .collect(Collectors.toMap(
                        e -> e.libelle, // libelle sans modification
                        e -> e
                ));
    }

    public static EnumMobInternationale_Pays fromLibelle(String libelle) {
        try {
            if (libelle == null) return null;
            return LIBELLE_TO_ENUM.get(libelle);
        } catch (Exception e) {
            return null;
        }
    }

    //CODE

    public Integer getCode() {
        return code;
    }

    private static final Map<Integer, EnumMobInternationale_Pays> CODE_TO_ENUM;

    static {
        CODE_TO_ENUM = Arrays.stream(values())
                .collect(Collectors.toMap(EnumMobInternationale_Pays::getCode, e -> e));
    }

    public static EnumMobInternationale_Pays fromCode(int code) {
        EnumMobInternationale_Pays result = CODE_TO_ENUM.get(code);
        if (result == null) {
            throw new IllegalArgumentException("Code invalide : " + code);
        }
        return result;
    }

    //ACCESSIBLE EN TRAIN
    public Boolean getIsAccessibleEnTrain() {
        return accessibleEnTrain;
    }

    public Integer getDistanceMoyenneVolOiseau() {
        return distanceMoyenneVolOiseau;
    }

    public Boolean getIsEurope() {
        return isEurope;
    }
}


