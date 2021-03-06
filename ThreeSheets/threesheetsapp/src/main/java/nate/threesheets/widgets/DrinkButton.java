package nate.threesheets.widgets;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import nate.threesheets.R;
import nate.threesheets.model.DrinkType;

/**
 * Created by nate on 5/29/15.
 */
public class DrinkButton extends ToggleButton {

    private int backgroundOn;
    private int backgroundOff;
    private DrinkType drinkType;

    public DrinkButton( Context context ) {
        super(context);

        this.setBackgroundResource(getBackgroundOff());

        this.setTextOff("");
        this.setTextOn("");

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(200, 200);
        this.setLayoutParams(params);

        invalidate();
    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);

        if ( checked == true ){
            this.setBackgroundResource(getBackgroundOn());
        }
        else {
            this.setBackgroundResource(getBackgroundOff());
        }
    }

    public DrinkType getDrinkType(){
        return drinkType;
    }

    public void setDrinkType( DrinkType type ){
        drinkType = type;
    }

    public int getBackgroundOn(){
        return backgroundOn;
    }

    public void setBackgroundOn(int aBackground){
        backgroundOn = aBackground;
    }

    public int getBackgroundOff(){
        return backgroundOff;
    }

    public void setBackgroundOff(int aBackground){
        backgroundOff = aBackground;
    }
}
