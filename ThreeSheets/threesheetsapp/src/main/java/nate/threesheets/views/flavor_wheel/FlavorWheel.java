package nate.threesheets.views.flavor_wheel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nate.threesheets.model.HasColor;

/**
 * Created by nate on 6/10/15.
 */
public class FlavorWheel<E> extends View {

    private List<E> flavors;
    private FlavorChanging<E> callback;

    float rotation = 0.0f;
    float degreesPer = 0.0f;

    private Handler animationHandler;
    private float targetRotation;
    private boolean goRight = false;
    private int selectedIndex = 0;

    private Map<E, Integer> tasteMap;

    public FlavorWheel(Context context, AttributeSet attrs, List<E> flavors){
        super(context, attrs);
        this.flavors = flavors;

        this.degreesPer = 360.0f / flavors.size();

        animationHandler = new Handler();


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float cx = (float) (canvas.getWidth() / 2.0);
        float cy = (float) (canvas.getHeight() / 2.0);

        canvas.translate(cx, cy);

        float rotationQoutient = (getWheelRotation() - 90.0f) % 360.0f;

        if ( rotationQoutient < 0 ){
            rotationQoutient = 360.0f + rotationQoutient;
        }

        canvas.rotate(rotationQoutient);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3.0f);
        paint.setAntiAlias(true);

        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setStrokeWidth(2.0f);
        textPaint.setTextSize(40.0f);

        Paint flavorPaint = new Paint();
        flavorPaint.setColor( Color.BLUE );
        flavorPaint.setStyle(Paint.Style.FILL);

        float radius = 400;

        canvas.save();

        canvas.drawCircle(0, 0, radius, paint);
        canvas.drawCircle(0, 0, (float) (radius * 4.0 / 5.0), paint);
        canvas.drawCircle(0, 0, (float) (radius * 3.0 / 5.0), paint);
        canvas.drawCircle(0, 0, (float) (radius * 2.0 / 5.0), paint);
        canvas.drawCircle(0, 0, (float) (radius * 1.0 / 5.0), paint);

        for (int i = 0; i < getFlavors().size(); i++) {
            canvas.drawLine(0, 0, radius, 0, paint);

            // text stuff
            canvas.save();

            canvas.translate(radius + 10, 0);
            canvas.rotate(90.0f);
            String txt = getFlavors().get(i).toString();
            float tWidth = textPaint.measureText(txt);

            canvas.drawText(txt, 0 - (tWidth / 2.0f), 0, textPaint);

            canvas.translate(0, -50);

            Paint squarePaint = new Paint();
            squarePaint.setColor( ((HasColor)getFlavors().get(i)).getColor() );
            squarePaint.setStyle(Paint.Style.FILL);

            canvas.drawCircle(0,0,10,squarePaint);

            canvas.restore();

            // next
            canvas.rotate(getDegreesPer());

//            canvas.restore();
        }

        canvas.restore();

        // have to do the flavor path stuff first because it can't use rotate
        Path flavorPath = new Path();
        Iterator<E> flavorIt = getFlavors().iterator();
        int index = 0;

        float red = 0.0f;
        float blue = 0.0f;
        float green = 0.0f;

        float points = 0.0f;

        while( flavorIt.hasNext() ) {
            E key = flavorIt.next();

            Integer value = getTasteMap().get(key);

            points += value;

            int color = ((HasColor) key).getColor();
            red += Color.red(color) * value;
            blue += Color.blue(color) * value;
            green += Color.green(color) * value;

            float iRad = radius * (value / 10.0f);

            double angle = Math.toRadians(getDegreesPer() * index);
            float x = iRad * (float) Math.cos(angle);
            float y = iRad * (float) Math.sin(angle);

            if (index == 0) {
                flavorPath.moveTo(x, y);
            } else {
                flavorPath.lineTo(x, y);
            }

            index++;
        }

        red = red / (float)points;
        green = green / (float)points;
        blue = blue / (float)points;

        int blend = Color.rgb( (int)red, (int)green, (int)blue );
        flavorPaint.setColor( blend );

        canvas.drawPath( flavorPath, flavorPaint );

        if ( !stop() ) {

            animationHandler.postDelayed(new Runnable() {

                @Override
                public void run() {

                    rotateWheel(getDegreesPer() / 10.0f);
                    invalidate();
                }
            }, 10);
        }
    }

    private void rotateWheel( float increment ){

        if ( Math.abs( getWheelRotation() - getTargetRotation() ) < increment ){
            rotation = getTargetRotation();
            return;
        }

        if ( goRight ) {
            rotation += increment;
        }
        else {
            rotation -= increment;
        }

        if ( rotation < 0 ){
            rotation = 360.0f + rotation;
        }

        rotation = rotation % 360.0f;
    }

    public E getSelectedItem(){

        int index = (getFlavors().size() - selectedIndex) % 15;
        return getFlavors().get( index );
    }

    public void setCallback( FlavorChanging<E> callback ){
        this.callback = callback;
    }

    public void rotateRight(){

        goRight = true;
        selectedIndex++;

        if ( selectedIndex >= getFlavors().size() ){
            selectedIndex = 0;
        }

        targetRotation = getDegreesPer()*selectedIndex;

//        Log.d( "ROTATE", "Current: " + getWheelRotation() );
//        Log.d("ROTATE", "Target: " + getTargetRotation());

        notifyChanging();

        invalidate();
    }

    public void rotateLeft(){
        goRight = false;

        selectedIndex--;

        if ( selectedIndex < 0 ){
            selectedIndex = getFlavors().size()-1;
        }

        targetRotation = getDegreesPer()*selectedIndex;

//        Log.d( "ROTATE", "Current: " + getWheelRotation() );
//        Log.d("ROTATE", "Target: " + getTargetRotation());

        notifyChanging();

        invalidate();
    }

    private Boolean stop(){

        Boolean stop =  (getWheelRotation() == getTargetRotation());

        notifySet();

        return stop;
    }

    private float getWheelRotation(){
        return rotation;
    }

    private float getTargetRotation(){ return targetRotation; }

    private float getDegreesPer(){
        return this.degreesPer;
    }

    private List<E> getFlavors(){
        return this.flavors;
    }

    public Map<E, Integer> getTasteMap(){

        if ( tasteMap == null ){
            tasteMap = new HashMap<E, Integer>();

            for ( int i = 0; i < getFlavors().size(); i++ ){
                tasteMap.put(getFlavors().get(i), 0 );
            }
        }

        return tasteMap;
    }

    public void setTasteValue( E flavor, Integer value ){
        getTasteMap().put(flavor, value);
        invalidate();
    }

    private void notifyChanging(){

        if ( callback != null ){
            callback.flavorChanging();
        }
    }

    private void notifySet(){

        if ( callback != null ){
            callback.flavorSet( getSelectedItem(), getTasteMap().get( getSelectedItem() ) );
        }
    }

}
