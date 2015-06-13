package nate.threesheets.persist;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import nate.threesheets.model.Drink;
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
                DBConstants.LOCATION + " TEXT," +
                DBConstants.RATING + " INTEGER," +
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
        String sql = wine.getSaveSql();

        Log.d("SQL", sql);

        this.getWritableDatabase().execSQL( sql );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
