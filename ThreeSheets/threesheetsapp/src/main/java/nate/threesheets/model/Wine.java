package nate.threesheets.model;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nate.threesheets.persist.DBConstants;

/**
 * Created by nate on 6/13/15.
 */
public class Wine extends Drink{

    private int red;
    private int green;
    private int blue;
    private List<Varietal> varietals;
    private int year;

    public String getSaveSql(){

        String sql = "insert into " + DBConstants.WINE_TABLE + "(" +
                DBConstants.WINE_VINEYARD + "," +
                DBConstants.NAME + "," +
                DBConstants.RATING + "," +
                DBConstants.LOCATION + "," +
                DBConstants.DATE + "," +
                DBConstants.WINE_COLOR_R + "," +
                DBConstants.WINE_COLOR_G + "," +
                DBConstants.WINE_COLOR_B + "," +
                DBConstants.PRICE + "," +
                DBConstants.YEAR + "," +
                DBConstants.NOTES + ",";

        Iterator<? extends HasColor> wineIt = getFlavorMap().keySet().iterator();

        while( wineIt.hasNext() ){
            WineFlavors flavor = (WineFlavors)wineIt.next();
            sql += flavor.name() + ",";
        }

        int date = (int)((new Date()).getTime() / 1000);

        sql += DBConstants.VARIETALS + ") values ('" +
            getFacility() + "','" + getName() + "'," + getRating() + ",'" +
            getLocation() + "'," + date + "," + getRed() + "," +
            getGreen() + "," + getBlue() + "," + getPrice() + "," +
            getYear() + ",'" + getNotes() + "',";

        wineIt = getFlavorMap().keySet().iterator();

        while( wineIt.hasNext() ){
            Integer value = getFlavorMap().get( wineIt.next() );
            sql += value + ",";
        }

        String vString = "'";

        Iterator<Varietal> vIt = getVarietals().iterator();

        while( vIt.hasNext() ){
            Varietal v = vIt.next();

            vString += v.name();

            if ( vIt.hasNext() ){
                vString += "9";
            }
        }

        sql += vString + "')";

        return sql;
    }

    @Override
    public String getUpdateSql(){
        String sql = "UPDATE " + DBConstants.WINE_TABLE + " SET " +
                DBConstants.WINE_VINEYARD + "='" + getFacility() + "'," +
                DBConstants.RATING + "=" + getRating() + "," +
                DBConstants.NOTES + "='" + getNotes() + "'," +
                DBConstants.PRICE + "=" + getPrice() + "," +
                DBConstants.LOCATION + "='" + getLocation() + "'," +
                DBConstants.NAME + "='" + getName() + "'," +
                DBConstants.WINE_COLOR_R + "=" + getRed() + "," +
                DBConstants.WINE_COLOR_G + "=" + getGreen() + "," +
                DBConstants.WINE_COLOR_B + "=" + getBlue() + ",";

        String varietalString = "";

        Iterator<Varietal> varIt = getVarietals().iterator();

        while( varIt.hasNext() ){
            Varietal varietal = varIt.next();
            varietalString += varietal.name();

            if ( varIt.hasNext() ){
                varietalString += "9";
            }
        }

        sql += DBConstants.VARIETALS + "='" + varietalString + "',";

        Iterator<? extends HasColor> flavIt = getFlavorMap().keySet().iterator();

        while( flavIt.hasNext() ){
            WineFlavors flavor = (WineFlavors)flavIt.next();
            Integer value = getFlavorMap().get( flavor );

            sql += flavor.name() + "=" + value + ",";
        }

        sql += DBConstants.YEAR + "=" + getYear() + " WHERE " + DBConstants.DATE + "=" + getDate();

        return sql;
    }

    @Override
    public String toString(){
        return getFacility();
    }

    @Override
    public String getSubText(){
        return getYear() + " " + getName();
    }

    public int getYear(){
        return year;
    }

    public void setYear( int aYear ){
        this.year = aYear;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public List<Varietal> getVarietals() {
        return varietals;
    }

    public void setVarietals(List<Varietal> varietals) {
        this.varietals = varietals;
    }
}
