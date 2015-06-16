package nate.threesheets.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

import nate.threesheets.R;
import nate.threesheets.model.Drink;
import nate.threesheets.model.DrinkType;
import nate.threesheets.model.Wine;
import nate.threesheets.persist.DatabaseManager;
import nate.threesheets.widgets.DrinkListAdapter;

public class BrowseActivity extends Activity implements AdapterView.OnItemClickListener {

    private ListView wineList;
    private ListAdapter wineAdapter;
    private List<? extends Drink> drinks;
    private boolean deleteMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
    }

    @Override
    protected void onResume() {
        super.onResume();

        resetQuery();
    }

    private void resetQuery(){
        Intent intent = this.getIntent();
        DrinkType drinkType = (DrinkType)intent.getExtras().get(DrinkType.DRINK_TYPE);
        wineAdapter = null;
        wineList = null;

        if ( drinkType.equals( DrinkType.BEER) ){

        }
        else if ( drinkType.equals( DrinkType.WINE ) ){
            drinks = DatabaseManager.getInstance().getWines();
        }
        else if ( drinkType.equals( DrinkType.SAKE ) ){

        }
        else {

        }

        // sets the initializers
        getWineList();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        if ( deleteMode == true ){

            final Drink drink = (Drink)getWineAdapter().getItem(position);

            new AlertDialog.Builder(this)
                    .setTitle("Title")
                    .setMessage("Are you sure you want to delete " + drink.toString() + "?" )
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {

                            DatabaseManager.getInstance().deleteDrink( drink );

                            Toast.makeText(getBaseContext(), "Wine was deleted.", Toast.LENGTH_SHORT).show();

                            resetQuery();
                        }})
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            setDeleteMode(false);
                        }
                    }).show();
        }
        else {
            editWine(position);
        }
    }


    private void editWine( int position ){
        Drink drink = (Drink)getWineAdapter().getItem( position );

        Intent viewDrink = null;

        if ( drink instanceof Wine){
            viewDrink = new Intent( this, AddWineActivity.class );
        }

        if ( viewDrink != null ){
            viewDrink.putExtra( Drink.DRINK, drink );
            startActivity(viewDrink);
        }
    }

    public void delete(View v){
        setDeleteMode( true );
    }

    public void setDeleteMode( boolean isit ){

        deleteMode = isit;
        ListView list = (ListView)findViewById(R.id.lst_wine);

        if ( isit ){
            list.setBackgroundColor(Color.LTGRAY);
        }
        else {
            list.setBackgroundColor(Color.WHITE);
        }
    }

    private ListView getWineList(){
        if ( wineList == null ){
            wineList = (ListView)this.findViewById(R.id.lst_wine);
            wineList.setAdapter( getWineAdapter() );
            wineList.setOnItemClickListener( this );
        }

        return wineList;
    }

    private ListAdapter getWineAdapter(){
        if ( wineAdapter == null ){
            wineAdapter = new DrinkListAdapter<>(this, getDrinks());
        }

        return wineAdapter;
    }

    private List<? extends Drink> getDrinks(){

        if ( drinks == null ){
            drinks = Collections.EMPTY_LIST;
        }
        return drinks;
    }
}
