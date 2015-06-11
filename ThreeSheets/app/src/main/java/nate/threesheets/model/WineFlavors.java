package nate.threesheets.model;

/**
 * Created by nate on 6/10/15.
 */
public enum WineFlavors {

    FLORAL("Floral"), SPICY("Spicey"), ACIDIC("Acidic"), ASTRINGENT( "Astringent" ), FRUITY( "Fruity" );

    private String niceName;

    WineFlavors(String niceName){
        this.niceName = niceName;
    }

    public String getNiceName(){
        return niceName;
    }

    @Override
    public String toString() {
        return getNiceName();
    }
}
