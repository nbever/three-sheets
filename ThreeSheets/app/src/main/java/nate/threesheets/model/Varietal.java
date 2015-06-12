package nate.threesheets.model;

/**
 * Created by nate on 6/10/15.
 */
public enum Varietal {

    CABERNET_FRANC( "Cabernet Franc"),
    CABERNET_SAUVIGNION( "Cabernet Sauvignon"),
    CHAMPAGNE( "Champagne" ),
    CHARDONNAY( "Chardonnay" ),
    CHIANTI( "Chianti" ),
    CHIANTI_CLASSICO( "Chianti Classico" ),
    GEWURTZTRAMINER( "Gewurtztraminer" ),
    MALBEC( "Malbec" ),
    MEAD( "Mead" ),
    MERLOT( "Merlot" ),
    MOSCATO( "Moscato" ),
    MUSCAT( "Muscat" ),
    PINOT_BLANC( "Pinot Blanc" ),
    PINOT_GRIGIO( "Pinot Grigio (Gris)" ),
    PINOT_NOIR( "Pinot Noir" ),
    PORT( "Port" ),
    ROSE( "Rose" ),
    SANGIOVESE( "Sangiovese" ),
    SAUVIGNON_BLANC( "Sauvignon Blanc" ),
    SHERRY( "Sherry" ),
    VIOGNIER( "Viognier" ),
    ZINFANDEL( "Zinfandel" ),
    ALBARINO("Albarino"),
    ALIGOTE("Aligote"),
    AMARONE("Amarone"),
    ARNEIS("Arneis"),
    ASTI_SPUMANTE("Asti Spumante"),
    AUSLESE("Auslese"),
    BANYLUS("Banylus"),
    BARARESCO("Barbaresco"),
    BARDOLINO("Bardolino"),
    BAROLO("Barolo"),
    BEAUJOLAIS("Beaujolais"),
    BLANC_DE_BLANCS("Blanc de Blancs"),
    BLANC_DE_NOIRS("Blanc de Noirs"),
    BLUSH("Blush"),
    BOAL_OR_BUAL("Boal or Bual"),
    BRUNELLO( "Brunello" ),
    CARIGNAN( "Carignan" ),
    CARMENERE( "Carmenere" ),
    CAVA( "Cava" ),
    CHARBONO( "Charbono" ),
    CHATEAUNEUF_DU_PAPE( "Chateauneuf-du-Pape" ),
    CHENIN_BLANC( "Chenin Blanc" ),
    CLARET( "Claret" ),
    COLOMBARD( "Colombard" ),
    CONSTANTIA( "Constantia" ),
    CORTESE( "Cortese" ),
    DOLCETTO( "Dolcetto" ),
    ELSWEIN( "Elswein" ),
    FRASCATI( "Frascati" ),
    FUME_BLANC( "Fume Blanc" ),
    GAMAY( "Gamay" ),
    GAMAY_BEAUJOLAIS( "Gamay Beaujolais" ),
    GATTINARA( "Gattinara" ),
    GRAPPA( "Grappa" ),
    GRENACHE( "Grenache" ),
    JOHANNISBERG_RIESLING( "Johannisberg Riesling" ),
    KIR( "Kir" ),
    LAMBRUSCO( "Lambrusco" ),
    LIEBFRAUMILCH( "Liebfraumilch" ),
    MADEIRA( "Madeira" ),
    MARC( "Marc" ),
    MARSALA( "Marsala" ),
    MARSANNE( "Marsanne" ),
    MERITAGE( "Meritage" ),
    MONTEPULCIANO( "Montepulciano" ),
    MOURVEDRE( "Mourvedre" ),
    MULLER_THURGAU( "Muller-Thurgau" ),
    NEBBIOLO( "Nebbiolo" ),
    PETITE_VERDOT( "Petite Verdot" ),
    PETITE_SIRAH( "Petite Sirah" ),
    PINO_MEUNIER( "Pinot Meunier" ),
    PINOTAGE( "Pinotage" ),
    RETSINA( "Retsina" ),
    ROUSSANNE( "Rousanne" ),
    SAUTERNS( "Sauterns" ),
    SEMILLON( "Semillon" ),
    SOAVE( "Soave" ),
    TOKAY( "Tokay" ),
    TRAMINER( "Traminer" ),
    UGNI_BLANC( "Ugni Blanc" ),
    VALPOLICELLA( "Valpolicella" ),
    VERDICCHIO( "Verdicchio" );

    private String niceName;

    Varietal( String name ){
        this.niceName = name;
    }

    public String getNiceName(){
        return niceName;
    }

    public String toString(){
        return getNiceName();
    }

}
