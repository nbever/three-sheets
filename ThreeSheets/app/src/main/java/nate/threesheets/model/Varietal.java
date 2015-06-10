package nate.threesheets.model;

/**
 * Created by nate on 6/10/15.
 */
public enum Varietal {

    CHARDONNAY("Chardonnay"),CABERNET_SAUVIGNON( "Cabernet Sauvignon"), MERLOT("Merlot");

    private String niceName;

    Varietal( String name ){
        this.niceName = name;
    }

    public String getNiceName(){
        return niceName;
    }

}
