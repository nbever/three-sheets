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

/**
 * Created by nate on 6/10/15.
 */
public class AddWineActivity extends Activity implements SeekBar.OnSeekBarChangeListener {

    private ViewFlipper flipper;
    private Button nextButton;
    private Button backButton;

    private SeekBar colorSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_wine_activity);
        getColorSlider().setOnSeekBarChangeListener( this );
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

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        float percentage = (float)progress / 100.0f;

        float h = (float)(57.0f + ((345.0f-57.0f) * percentage));
        float s = ((25.0f + ((100.0f-25.0f) * percentage)))/100.0f;
        float v = ((100.0f - ((100.0f-45.0f) * percentage)))/100.0f;

        float[] hsv = new float[]{h,s,v};
        int newColor = Color.HSVToColor(hsv);

        ((TextView) this.findViewById(R.id.txt_color)).setBackgroundColor(newColor);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
