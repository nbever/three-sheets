package nate.threesheets.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import nate.threesheets.R;

/**
 * Created by nate on 6/10/15.
 */
public class AddWineActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.add_wine_activity);
    }


    @Override
    public void onClick(View v) {
        ViewFlipper flipper = (ViewFlipper)this.findViewById(R.id.viewFlipper);

        if ( v.equals( this.findViewById(R.id.btn_next ) ) ){
            flipper.setInAnimation(this, R.anim.in_from_left);
            flipper.setOutAnimation(this, R.anim.out_to_right);
            flipper.showNext();
        }
        else {
            flipper.setInAnimation(this, R.anim.in_from_right);
            flipper.setOutAnimation(this, R.anim.out_to_left);
            flipper.showPrevious();
        }
    }
}
