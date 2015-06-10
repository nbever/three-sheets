package nate.threesheets.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import java.util.List;

import nate.threesheets.R;
import nate.threesheets.model.DrinkType;
import nate.threesheets.widgets.DrinkButton;

/**
 * Created by nate on 5/29/15.
 */
public class DrinkTypeBar extends LinearLayout implements View.OnClickListener{

    private DrinkButton tglWhiskey;
    private DrinkButton tglSake;
    private DrinkButton tglWine;
    private DrinkButton tglBeer;

    private DrinkButton selectedButton;

    public DrinkTypeBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.addView(getTglWine());
        this.addView(getTglWhiskey());
        this.addView(getTglSake());
        this.addView(getTglBeer());

        getTglWine().setChecked(true);
        setSelectedButton(getTglWine());
    }

    public void onClick( View view ) {

        DrinkButton button = (DrinkButton) view;

        getTglWine().setChecked(false);
        getTglBeer().setChecked(false);
        getTglSake().setChecked(false);
        getTglWhiskey().setChecked(false);

        button.setChecked(true);
    }

    public DrinkType getSelectedType(){

        return getSelectedButton().getDrinkType();
    }

    private void setSelectedButton(DrinkButton button){
        selectedButton = button;
    }

    private DrinkButton getSelectedButton(){
        return selectedButton;
    }

    private DrinkButton getTglWhiskey() {

        if ( tglWhiskey == null ){
            tglWhiskey = new DrinkButton(getContext());
            tglWhiskey.setBackgroundOn(R.mipmap.ic_add);
            tglWhiskey.setBackgroundOff(R.mipmap.ic_launcher);
            tglWhiskey.setDrinkType(DrinkType.WHISKEY);
            tglWhiskey.setOnClickListener( this );
        }

        return tglWhiskey;
    }

    private DrinkButton getTglSake() {
        if (tglSake == null ){
            tglSake = new DrinkButton(getContext());
            tglSake.setBackgroundOff(R.mipmap.ic_launcher);
            tglSake.setBackgroundOn(R.mipmap.ic_add);
            tglSake.setDrinkType(DrinkType.SAKE);
            tglSake.setOnClickListener(this);
        }
        return tglSake;
    }

    private DrinkButton getTglWine() {
        if ( tglWine == null ){
            tglWine = new DrinkButton(getContext());
            tglWine.setDrinkType(DrinkType.WINE);
            tglWine.setBackgroundOff(R.mipmap.ic_launcher);
            tglWine.setBackgroundOn(R.mipmap.ic_add);
            tglWine.setOnClickListener(this);
        }
        return tglWine;
    }


    private DrinkButton getTglBeer() {

        if ( tglBeer == null ){
            tglBeer = new DrinkButton(getContext());
            tglBeer.setDrinkType(DrinkType.BEER);
            tglBeer.setBackgroundOff(R.mipmap.ic_launcher);
            tglBeer.setBackgroundOn(R.mipmap.ic_add);
            tglBeer.setOnClickListener(this);
        }
        return tglBeer;
    }
}
