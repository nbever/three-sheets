package nate.threesheets.model;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nate.threesheets.persist.DBConstants;

/**
 * Created by nate on 6/13/15.
 */
public class Wine implements Drink{

    private String vineyard;
    private String name;
    private int rating;
    private Map<WineFlavors, Integer> flavors;
    private int red;
    private int green;
    private int blue;
    private float price;
    private String location;
    private List<Varietal> varietals;
    private int date;

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
                DBConstants.PRICE + ",";

        Iterator<WineFlavors> wineIt = getFlavors().keySet().iterator();

        while( wineIt.hasNext() ){
            WineFlavors flavor = wineIt.next();
            sql += flavor.name() + ",";
        }

        int date = (int)((new Date()).getTime() / 1000);

        sql += DBConstants.VARIETALS + ") values ('" +
            getVineyard() + "','" + getName() + "'," + getRating() + ",'" +
            getLocation() + "'," + date + "," + getRed() + "," +
            getGreen() + "," + getBlue() + "," + getPrice() + ",";

        wineIt = getFlavors().keySet().iterator();

        while( wineIt.hasNext() ){
            Integer value = getFlavors().get( wineIt.next() );
            sql += value + ",";
        }

        String vString = "'";

        Iterator<Varietal> vIt = getVarietals().iterator();

        while( vIt.hasNext() ){
            Varietal v = vIt.next();

            vString += v.getNiceName();

            if ( vIt.hasNext() ){
                vString += "|";
            }
        }

        sql += vString + "')";

        return sql;
    }

    public int getDate(){
        return date;
    }

    public void setDate( int date ){
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Map<WineFlavors, Integer> getFlavors() {
        return flavors;
    }

    public void setFlavors(Map<WineFlavors, Integer> flavors) {
        this.flavors = flavors;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Varietal> getVarietals() {
        return varietals;
    }

    public void setVarietals(List<Varietal> varietals) {
        this.varietals = varietals;
    }

    public String getVineyard() {
        return vineyard;
    }

    public void setVineyard(String vineyard) {
        this.vineyard = vineyard;
    }
}
