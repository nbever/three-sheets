package nate.threesheets.views.flavor_wheel;

import android.content.Context;
import android.util.AttributeSet;

import java.util.Arrays;

import nate.threesheets.model.WineFlavors;

/**
 * Created by nate on 6/10/15.
 */
public class WineFlavorWheel extends FlavorWheel<WineFlavors>{


    public WineFlavorWheel( Context context, AttributeSet attrs ){
        super(context, attrs, Arrays.asList( WineFlavors.values() ) );
    }
}
