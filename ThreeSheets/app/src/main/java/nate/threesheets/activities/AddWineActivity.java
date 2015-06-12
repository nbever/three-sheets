package nate.threesheets.activities;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import nate.threesheets.R;
import nate.threesheets.model.WineFlavors;
import nate.threesheets.views.flavor_wheel.FlavorChanging;
import nate.threesheets.views.flavor_wheel.WineFlavorWheel;

/**
 * Created by nate on 6/10/15.
 */
public class AddWineActivity extends Activity implements SeekBar.OnSeekBarChangeListener, FlavorChanging<WineFlavors> {

    private ViewFlipper flipper;
    private WineFlavorWheel flavorWheel;
    private Button nextButton;
    private Button backButton;

    private SeekBar colorSlider;
    private SeekBar flavorSlider;

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

                int newColor = Color.rgb( red, green, blue );

                ((TextView) this.findViewById(R.id.txt_color)).setBackgroundColor(newColor);
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
