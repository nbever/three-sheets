package nate.threesheets.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.AttributedCharacterIterator;
import java.util.Date;
import java.util.Random;

import nate.threesheets.R;
import nate.threesheets.model.Drink;
import nate.threesheets.model.Wine;
import nate.threesheets.views.flavor_wheel.FlavorChanging;
import nate.threesheets.views.flavor_wheel.FlavorWheel;
import nate.threesheets.views.flavor_wheel.WineFlavorWheel;
import nate.threesheets.widgets.DrinkRatingBar;

/**
 * Created by nate on 6/13/15.
 */
public class WineRowView extends RelativeLayout {

    private TextView name;
    private TextView subText;
    private DrinkRatingBar drinkRatingBar;
    private FlavorWheel flavorWheel;

    private LinearLayout leftLayout;
    private LinearLayout rightLayout;

    private AttributeSet attrs;

    private int randomSeed = (int)(Math.random() * Integer.MAX_VALUE-1000) + 1000;

    public WineRowView(Context context, AttributeSet attrs){
        super(context, attrs);
        this.attrs = attrs;

        this.setMinimumHeight(200);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT );

        this.setLayoutParams(lp);

        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        rlp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, this.getId());
        rlp.addRule(RelativeLayout.ALIGN_PARENT_TOP, this.getId());

        this.addView(getRightLayout(), rlp);

        RelativeLayout.LayoutParams llp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        llp.addRule( RelativeLayout.ALIGN_PARENT_LEFT, this.getId());
        llp.addRule(RelativeLayout.ALIGN_PARENT_TOP, this.getId());

        this.addView(getLeftLayout(), llp);
    }

    public LinearLayout getRightLayout(){

        if ( rightLayout == null ){
            rightLayout = new LinearLayout(getContext());

            rightLayout.setOrientation(LinearLayout.VERTICAL);

            rightLayout.addView( getFlavorWheel() );
        }

        return rightLayout;
    }

    public LinearLayout getLeftLayout(){
        if ( leftLayout == null ){
            leftLayout = new LinearLayout( getContext() );

            leftLayout.setOrientation(LinearLayout.VERTICAL);

            leftLayout.addView(getName());
            leftLayout.addView( getSubText() );
            leftLayout.addView( getDrinkRatingBar() );
        }

        return leftLayout;
    }

    public TextView getName() {

        if ( name == null ){
            name = new TextView(getContext());
            name.setTextSize(20);
            name.setTypeface(Typeface.DEFAULT_BOLD);
        }
        return name;
    }

    public DrinkRatingBar getDrinkRatingBar() {
        if ( drinkRatingBar == null ){
            drinkRatingBar = new DrinkRatingBar(getContext(),attrs);
            drinkRatingBar.setScale(0.3f);
            drinkRatingBar.setMinimumWidth(200);
            drinkRatingBar.setMinimumHeight(50);
        }
        return drinkRatingBar;
    }

    public TextView getSubText(){
        if ( subText == null ){
            subText = new TextView(getContext());
            subText.setTextSize(15);
        }

        return subText;
    }

    public FlavorWheel getFlavorWheel() {
        if ( flavorWheel == null ){

            flavorWheel = new WineFlavorWheel(getContext(),attrs);
//            flavorWheel.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            flavorWheel.setMiniMode(true);
            flavorWheel.setScale(0.4f);
            flavorWheel.setMinimumWidth(150);
            flavorWheel.setMinimumHeight(200);
//            flavorWheel.setBackgroundColor(Color.BLACK);
        }
        return flavorWheel;
    }
}
