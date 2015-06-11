package nate.threesheets.views.flavor_wheel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by nate on 6/10/15.
 */
public class FlavorWheel<E> extends View {

    private List<E> flavors;
    double rotation = -1.0*Math.PI / 2.0;

    public FlavorWheel(Context context, AttributeSet attrs, List<E> flavors){
        super(context, attrs);
        this.flavors = flavors;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3.0f);
        paint.setAntiAlias( true );

        Paint textPaint = new Paint();
        textPaint.setColor( Color.BLACK );
        textPaint.setStyle(Paint.Style.FILL );
        textPaint.setStrokeWidth(2.0f );
        textPaint.setTextSize( 40.0f );

        double radius = 300.0;
        float cx = (float)(canvas.getWidth()/2.0);
        float cy = (float)(canvas.getHeight()/2.0);

        canvas.drawCircle( cx, cy, (float)radius, paint );
        canvas.drawCircle( cx, cy, (float)(radius * 4.0/5.0), paint );
        canvas.drawCircle( cx, cy, (float)(radius * 3.0/5.0), paint );
        canvas.drawCircle( cx, cy, (float)(radius * 2.0/5.0), paint );
        canvas.drawCircle( cx, cy, (float)(radius * 1.0/5.0), paint );

        double degsPer = 2*Math.PI / getFlavors().size();

        for ( int i = 0; i < getFlavors().size(); i++ ){

            double angle = degsPer * i + rotation;

            float x = (float)(radius * Math.cos( angle )) + cx;
            float y = (float)(radius * Math.sin( angle )) + cy;

            canvas.drawLine(cx, cy, x, y, paint );

            String txt = getFlavors().get( i ).toString();
            float tWidth = textPaint.measureText(txt );

            float tx = (float)(((radius + 30.0) * Math.cos( angle )) + cx ) - (tWidth/2.0f);
            float ty = (float)((radius + 30.0) * Math.sin( angle )) + cy;

            canvas.drawText( txt, tx, ty, textPaint );
        }
    }

    private List<E> getFlavors(){
        return this.flavors;
    }

    public void rotateRight(){

    }

    public void rotateLeft(){

    }
}
