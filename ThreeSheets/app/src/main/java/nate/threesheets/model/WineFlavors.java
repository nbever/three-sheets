package nate.threesheets.model;


import android.graphics.Color;

/**
 * Created by nate on 6/10/15.
 */
public enum WineFlavors implements HasColor{

    HEAT( "Heat", Color.rgb(0, 249, 240)),
    DARK_FRUIT( "Dark Fruit", Color.rgb(147,6,70)),
    TROPICAL( "Tropical", Color.rgb(157,223,76)),
    HERBAL( "Herbal", Color.rgb(69,124,1)),
    FLORAL( "Floral", Color.rgb(241,79,126)),
    SPICY( "Spicy", Color.rgb(254,168,23)),
    TANNIC( "Tannic", Color.rgb(164,0,223)),
    WOODY( "Woody", Color.rgb(136,89,56)),
    MINERAL( "Mineral", Color.rgb(51,104,195)),
    SWEET( "Sweet", Color.rgb(34,226,139)),
    SOUR( "Sour", Color.rgb(255,235,0)),
    BODY( "Body", Color.BLACK),
    LEGS( "Legs", Color.WHITE ),
    LINGER( "Linger", Color.rgb(142,254,190)),
    NOSE( "Nose", Color.rgb(161,212,1));

    private String niceName;
    private int color;

    WineFlavors(String niceName, int color){
        this.niceName = niceName;
        this.color = color;
    }

    public String getNiceName(){
        return niceName;
    }

    public int getColor(){
        return color;
    }

    @Override
    public String toString() {
        return getNiceName();
    }
}
