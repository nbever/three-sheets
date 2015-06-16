package nate.threesheets.model;

import android.graphics.Color;

/**
 * Created by nate on 6/14/15.
 */
public enum WhiskeyFlavors implements HasColor {

    OAKY( "Oaky", Color.rgb(255,236,158)),
    PEATY( "Peaty", Color.rgb(0,102,51)),
    SMOKEY( "Smokey", Color.rgb(96,96,96)),
    SPICEY( "Spicy", Color.rgb(204,0,0)),
    GRAINY( "Grainy", Color.rgb(178,102,255)),
    CORNEY( "Corney", Color.rgb(255,102,178)),
    FRUITY( "Fruity", Color.rgb( 127,0,255)),
    MALTY( "Malty", Color.rgb(102,0,0)),
    CARAMEL( "Caramel", Color.rgb(255,153,51)),
    VANILLA( "Vanilla", Color.rgb(255,255,204)),
    COCOA( "Cocoa", Color.rgb( 153,76,0)),
    SWEET( "Sweet", Color.rgb( 51,153,255)),
    HONEY( "Honey", Color.rgb(204,204,0)),
    BUTTERY( "Buttery", Color.rgb(0,204,102));

    private String niceName;
    private int color;

    WhiskeyFlavors(String niceName, int color){
        this.niceName = niceName;
        this.color = color;
    }

    public int getColor(){ return this.color; }
    public String getNiceName(){ return this.getNiceName(); }


}
