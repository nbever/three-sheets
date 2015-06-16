package nate.threesheets.model;

/**
 * Created by nate on 6/14/15.
 */
public enum WhiskeyTypes {

    WHISKEY( "Whiskey"),
    SCOTCH( "Scotch" ),
    BOURBON( "Bourbon" );

    private String niceName;

    WhiskeyTypes( String niceName ){
        this.niceName = niceName;
    }

    public String getNiceName(){
        return niceName;
    }
}
