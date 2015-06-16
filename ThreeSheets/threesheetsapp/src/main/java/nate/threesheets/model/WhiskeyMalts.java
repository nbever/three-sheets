package nate.threesheets.model;

/**
 * Created by nate on 6/14/15.
 */
public enum WhiskeyMalts {

    SINGLE( "Single Malt" ),
    DOUBLE_MALT( "Double Malt"),
    BLEND( "Blended" );

    private String niceName;

    WhiskeyMalts( String niceName ){
        this.niceName = niceName;
    }

    public String getNiceName(){
        return niceName;
    }
}
