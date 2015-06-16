package nate.threesheets.model;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by nate on 6/13/15.
 */
public abstract class Drink implements Serializable{

    public static final String DRINK = "DRINK";

    private int rating;
    private String facility;
    private String name;
    private String location;
    private Map<? extends HasColor, Integer> flavorMap;
    private int date;
    private float price;
    private String notes;

    public abstract String getSubText();
    public abstract String getSaveSql();
    public abstract String getUpdateSql();

    public int getRating(){
        return this.rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Map<? extends HasColor, Integer> getFlavorMap() {
        return flavorMap;
    }

    public void setFlavorMap(Map<? extends HasColor, Integer> flavorMap) {
        this.flavorMap = flavorMap;
    }

    public int getDate(){
        return this.date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
