package nate.threesheets.persist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nate.threesheets.model.Drink;
import nate.threesheets.model.Varietal;
import nate.threesheets.model.Whiskey;
import nate.threesheets.model.Wine;
import nate.threesheets.model.WineFlavors;

/**
 * Created by nate on 6/12/15.
 */
public class DatabaseManager extends SQLiteOpenHelper{

    private static DatabaseManager instance;

    private DatabaseManager(Context context){
        super( context, "ThreeSheets", null, 1);
    }

    public static void initialize( Context context ){
        if ( instance != null ){
            return;
        }

        instance = new DatabaseManager(context);
    }

    public static DatabaseManager getInstance(){
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createWine = "CREATE TABLE " + DBConstants.WINE_TABLE + "(" +
                DBConstants.WINE_VINEYARD + " TEXT," +
                DBConstants.NAME + " TEXT," +
                DBConstants.DATE + " INTEGER," +
                DBConstants.PRICE + " REAL," +
                DBConstants.YEAR + " INTEGER," +
                DBConstants.LOCATION + " TEXT," +
                DBConstants.RATING + " INTEGER," +
                DBConstants.NOTES + " TEXT," +
                DBConstants.WINE_COLOR_R + " INTEGER," +
                DBConstants.WINE_COLOR_G + " INTEGER," +
                DBConstants.WINE_COLOR_B + " INTEGER,";

        for ( int i = 0; i < WineFlavors.values().length; i++ ){
            createWine += WineFlavors.values()[i].name() + " INTEGER,";
        }

        createWine += DBConstants.VARIETALS + " TEXT)";

        db.execSQL( createWine );
    }

    public void save( Drink wine ){

        String sql = "";

        if ( wine.getDate() == -1 ) {
            sql = wine.getSaveSql();
        }
        else {
            sql = wine.getUpdateSql();;
        }

        Log.d("SQL", sql);

        this.getWritableDatabase().execSQL(sql);
    }

    public void deleteDrink( Drink drink ){

        String tableName = "";

        if ( drink instanceof Wine ){
            tableName = DBConstants.WINE_TABLE;
        }
        else if ( drink instanceof Whiskey ){
            tableName = DBConstants.WHISKEY_TABLE;
        }

        String sql = "delete from " + tableName + " where date=" + drink.getDate();

        getWritableDatabase().execSQL( sql );
    }

    public List<Wine> getWines(){

        String sql = " select " + DBConstants.WINE_VINEYARD + "," +
                DBConstants.NAME + "," +
                DBConstants.LOCATION + "," +
                DBConstants.PRICE + "," +
                DBConstants.DATE + "," +
                DBConstants.RATING + "," +
                DBConstants.YEAR +  "," +
                DBConstants.NOTES + "," +
                DBConstants.WINE_COLOR_R + "," +
                DBConstants.WINE_COLOR_G + "," +
                DBConstants.WINE_COLOR_B + ",";

        for ( int i = 0; i < WineFlavors.values().length; i++ ){
            WineFlavors flavor = WineFlavors.values()[i];
            sql += flavor.name() + ",";
        }

        sql += DBConstants.VARIETALS + " from " + DBConstants.WINE_TABLE;

        List<Wine> wines = new ArrayList<Wine>();
        Cursor c = this.getReadableDatabase().rawQuery( sql, null );

        if ( c != null ){
            if ( c.moveToFirst() ){
                do {

                    Wine wine = new Wine();

                    wine.setFacility(c.getString(c.getColumnIndex(DBConstants.WINE_VINEYARD)));
                    wine.setName(c.getString(c.getColumnIndex(DBConstants.NAME)));
                    wine.setRating(c.getInt(c.getColumnIndex(DBConstants.RATING)));
                    wine.setYear(c.getInt(c.getColumnIndex(DBConstants.YEAR)));
                    wine.setBlue(c.getInt(c.getColumnIndex(DBConstants.WINE_COLOR_B)));
                    wine.setGreen(c.getInt(c.getColumnIndex(DBConstants.WINE_COLOR_G)));
                    wine.setRed(c.getInt(c.getColumnIndex(DBConstants.WINE_COLOR_R)));
                    wine.setDate(c.getInt(c.getColumnIndex(DBConstants.DATE)));
                    wine.setLocation(c.getString(c.getColumnIndex(DBConstants.LOCATION)));
                    wine.setPrice(c.getFloat(c.getColumnIndex(DBConstants.PRICE)));
                    wine.setNotes( c.getString( c.getColumnIndex(DBConstants.NOTES)));

                    String varietalString = c.getString( c.getColumnIndex(DBConstants.VARIETALS));
                    String[] varietalStrings = varietalString.split("9");

                    List<Varietal> varietals = new ArrayList<Varietal>();

                    for ( int j = 0; j < varietalStrings.length; j++ ){
                        String vString = varietalStrings[j];
                        varietals.add( Varietal.valueOf(varietalStrings[j]));
                    }

                    wine.setVarietals(varietals);

                    Map<WineFlavors, Integer> flavorMap = new HashMap<WineFlavors, Integer>();

                    for ( int k = 0; k < WineFlavors.values().length; k++ ){
                        WineFlavors flavor = WineFlavors.values()[k];

                        Integer value = c.getInt(c.getColumnIndex(flavor.name()));

                        flavorMap.put( flavor, value );
                    }

                    wine.setFlavorMap( flavorMap );

                    wines.add( wine );

                } while( c.moveToNext() );
            }
        }

        return wines;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
