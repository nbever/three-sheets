package nate.threesheets.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import nate.threesheets.model.Drink;

/**
 * Created by nate on 6/13/15.
 */
public class DrinkRatingBar<E extends Drink> extends View{

    private int rating = 0;
    private float scale = 1.0f;

    public DrinkRatingBar(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        float realValue = (float)getRating() / 2.0f;
        float remainder = realValue - Math.round(realValue);

        realValue = (float)Math.floor(realValue);

        boolean drawHalf = (remainder != 0 );
        float radius = 50.0f;

        Paint paint = new Paint();
        paint.setColor(Color.parseColor( "#960044" ) );
        paint.setStyle(Paint.Style.FILL_AND_STROKE);

        canvas.translate(radius / 2, radius/2);

        canvas.scale(scale, scale );

        for ( int i = 0; i < realValue; i++ ){

            if ( i != 0 ) {
                canvas.translate((2*radius) + 2, 0);
            }

            canvas.drawCircle(0, 0, radius, paint);
        }

        if ( drawHalf ){
            canvas.translate((2*radius) + 2, 0);
            RectF oval = new RectF( 0-radius, 0-radius, radius, radius );
            canvas.drawArc(oval, 90.0f, 180.0f, true, paint );
        }

    }

    public void setScale( float scale ){
        this.scale = scale;
    }

    public void setRating( int value ){
        rating = value;
    }

    public int getRating(){
        return rating;
    }
}
