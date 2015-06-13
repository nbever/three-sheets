package nate.threesheets.activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nate.threesheets.R;
import nate.threesheets.model.Varietal;
import nate.threesheets.model.Wine;
import nate.threesheets.model.WineFlavors;
import nate.threesheets.persist.DatabaseManager;
import nate.threesheets.views.flavor_wheel.FlavorChanging;
import nate.threesheets.views.flavor_wheel.WineFlavorWheel;
import nate.threesheets.views.selection_addrs.SelectionAdder;
import nate.threesheets.views.selection_addrs.VarietalSelector;
import nate.threesheets.widgets.SelectionAdderItem;

/**
 * Created by nate on 6/10/15.
 */
public class AddWineActivity extends Activity implements SeekBar.OnSeekBarChangeListener, FlavorChanging<WineFlavors> {

    private ViewFlipper flipper;
    private WineFlavorWheel flavorWheel;
    private VarietalSelector varietalSelector;
    private Button nextButton;
    private Button backButton;

    private SeekBar colorSlider;
    private SeekBar flavorSlider;

    private int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_wine_activity);
        getColorSlider().setOnSeekBarChangeListener(this);
        getFlavorSlider().setOnSeekBarChangeListener(this);
        getFlavorWheel().setCallback( this );
    }

    public void flipNext(View v){
        getFlipper().setInAnimation(this, R.anim.in_from_right);
        getFlipper().setOutAnimation(this, R.anim.out_to_left);
        getFlipper().showNext();

        setButtons();
    }

    public void flipBack(View v){
        getFlipper().setInAnimation(this, R.anim.in_from_left);
        getFlipper().setOutAnimation(this, R.anim.out_to_right);
        getFlipper().showPrevious();

        setButtons();
    }

    public void done(View v){

        Wine wine = new Wine();
        wine.setVineyard(((TextView) findViewById(R.id.txt_winery)).getText().toString());
        wine.setName(((TextView) findViewById(R.id.txt_name)).getText().toString());
        wine.setRed(Color.red(color));
        wine.setGreen(Color.green(color));
        wine.setBlue(Color.blue(color));

        String price = ((TextView)findViewById(R.id.txt_price)).getText().toString();
        float number = 0.0f;

        try {
            number = Float.parseFloat( price );
        }
        catch( NumberFormatException e ){

        }

        wine.setPrice(number);

        int rating = (int)(((RatingBar)findViewById(R.id.ratingBar)).getRating() * 2.0f);
        wine.setRating(rating);

        Map<WineFlavors, Integer> tasteMap = ((WineFlavorWheel)findViewById(R.id.flavor_wheel)).getTasteMap();
        wine.setFlavors(tasteMap);

        VarietalSelector sel = getVarietalSelector();
        Iterator<SelectionAdderItem> itemIt = sel.getItemList().iterator();
        List<Varietal> vList = new ArrayList<Varietal>();

        while( itemIt.hasNext() ){
            SelectionAdderItem item = itemIt.next();

            vList.add( (Varietal)item.getSelectedItem() );
        }

        wine.setVarietals( vList );

        DatabaseManager.getInstance().save( wine );
    }


    public void nextFlavor(View v ){
        getFlavorWheel().rotateRight();
    }

    public void previousFlavor(View v){
        getFlavorWheel().rotateLeft();
    }


    private void setButtons(){
        if ( getFlipper().getCurrentView().equals( (View)this.findViewById(R.id.view_metadata) ) ){
            getNextButton().setEnabled(true);
            getBackButton().setEnabled(false);
        }
        else {
            getNextButton().setEnabled(false);
            getBackButton().setEnabled(true);
        }

        ScrollView scroller = (ScrollView)this.findViewById(R.id.scrollView);
        scroller.scrollTo(0, 0);
    }

    private ViewFlipper getFlipper(){
        if ( flipper == null ){
            flipper = (ViewFlipper)this.findViewById(R.id.viewFlipper);
        }

        return flipper;
    }

    private Button getNextButton(){
        if ( nextButton == null ){
            nextButton = (Button)this.findViewById(R.id.btn_next);
        }

        return nextButton;
    }

    private Button getBackButton(){
        if ( backButton == null ){
            backButton = (Button)this.findViewById(R.id.btn_previous);
        }

        return backButton;
    }

    private SeekBar getColorSlider(){
        if ( colorSlider == null ){
            colorSlider = (SeekBar)this.findViewById(R.id.sld_color);
        }

        return colorSlider;
    }

    private SeekBar getFlavorSlider(){

        if ( flavorSlider == null ){
            flavorSlider = (SeekBar)this.findViewById(R.id.sld_flavor);
        }
        return flavorSlider;
    }

    private WineFlavorWheel getFlavorWheel(){
        if ( flavorWheel == null ){
            flavorWheel = (WineFlavorWheel)this.findViewById(R.id.flavor_wheel);
        }
        return flavorWheel;
    }

    private VarietalSelector getVarietalSelector(){
        if ( varietalSelector == null ){
            varietalSelector = (VarietalSelector)this.findViewById(R.id.varietal_adder);
        }

        return varietalSelector;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        if ( seekBar == getColorSlider() ) {
                float percentage = (float) progress / 100.0f;

                float r = ((255.0f - ((255.0f - 115.0f) * percentage)));
                float g = ((252.0f - ((252.0f - 0.0f) * percentage)));
                float b = ((191.0f - ((191.0f - 29.0f) * percentage)));

                int red = Math.round( r );
                int green = Math.round( g );
                int blue = Math.round( b );

                int color = Color.rgb( red, green, blue );

                ((TextView) this.findViewById(R.id.txt_color)).setBackgroundColor(color);
        }
        else if ( seekBar == getFlavorSlider() ){

            getFlavorWheel().setTasteValue( getFlavorWheel().getSelectedItem(), progress );
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void flavorChanging(){
        getBackButton().setEnabled( false );
        getNextButton().setEnabled( false );
        getFlavorSlider().setEnabled( false );
    }

    @Override
    public void flavorSet( WineFlavors flavor, Integer value ){
        getBackButton().setEnabled( true );
        getNextButton().setEnabled( true );
        getFlavorSlider().setEnabled( true );
        getFlavorSlider().setProgress(value);
        ((TextView)this.findViewById(R.id.txt_active)).setText( flavor.getNiceName() );
    }
}
