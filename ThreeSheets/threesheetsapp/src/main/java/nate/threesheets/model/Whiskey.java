package nate.threesheets.model;

import java.util.Map;

/**
 * Created by nate on 6/14/15.
 */
public class Whiskey extends Drink{

    private WhiskeyTypes type;
    private WhiskeyMalts malt;
    private String distillery;
    private String name;
    private String location;
    private Map<WhiskeyFlavors, Integer> flavors;

    @Override
    public String getSubText() {
        return null;
    }

    @Override
    public String getSaveSql() {
        return null;
    }

    @Override
    public String getUpdateSql() {
        return null;
    }

    private String notes;
    private float price;
    private int rating;
}
