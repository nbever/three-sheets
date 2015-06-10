package nate.threesheets.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import nate.threesheets.R;
import nate.threesheets.model.DrinkType;
import nate.threesheets.views.DrinkTypeBar;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void toggleClicked( View view ){

    }

    public void addDrinkClicked( View view ){

        DrinkTypeBar drinkBar = (DrinkTypeBar) this.findViewById(R.id.drink_bar);

        Intent addActivity = null;

        switch( drinkBar.getSelectedType() ){
            case BEER:
                break;
            case WHISKEY:
                break;
            case WINE:
                addActivity = new Intent( this, AddWineActivity.class);
                break;
            case SAKE:
                break;
        }

        addActivity.putExtra(DrinkType.DRINK_TYPE, drinkBar.getSelectedType() );
        startActivity(addActivity);
    }
}