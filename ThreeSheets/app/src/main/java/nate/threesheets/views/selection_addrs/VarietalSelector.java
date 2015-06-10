package nate.threesheets.views.selection_addrs;

import android.content.Context;
import android.util.AttributeSet;

import java.util.Arrays;

import nate.threesheets.model.Varietal;

/**
 * Created by nate on 6/10/15.
 */
public class VarietalSelector extends SelectionAdder<Varietal> {

    public VarietalSelector(Context context, AttributeSet attrs){
        super( context, Arrays.asList( Varietal.values() ) );
    }
}
