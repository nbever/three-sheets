package nate.threesheets.widgets;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import nate.threesheets.R;

/**
 * Created by nate on 6/10/15.
 */
public class SelectionAdderItem<E> extends LinearLayout {

    private Spinner typeSpinner;
    private SpinnerAdapter typeSpinnerAdapter;
    private List<E> options;
    private Button removeButton;
    private OnClickListener clickListener;

    public SelectionAdderItem(Context context, List<E> options, OnClickListener clickListener ){
        super( context );

        this.setOrientation(HORIZONTAL);
        this.options = options;
        this.clickListener = clickListener;

        this.addView(getTypeSpinner());
        this.addView(getRemoveButton());
    }

    public E getSelectedItem(){

        return (E)getTypeSpinner().getSelectedItem();
    }

    private List<E> getOptions(){

        if ( options == null ){
            options = new ArrayList<E>();
        }

        return options;
    }

    private Spinner getTypeSpinner(){
        if ( typeSpinner == null ){
            typeSpinner = new Spinner( this.getContext() );
            typeSpinner.setAdapter( getTypeSpinnerAdapter() );
        }

        return typeSpinner;
    }

    private SpinnerAdapter getTypeSpinnerAdapter() {
        if (typeSpinnerAdapter == null) {
            typeSpinnerAdapter = new ArrayAdapter(this.getContext(), android.R.layout.simple_spinner_dropdown_item, getOptions());
        }

        return typeSpinnerAdapter;
    }

    private Button getRemoveButton(){
        if ( removeButton == null ){
            removeButton = new Button(this.getContext());
            removeButton.setText("-");
            removeButton.setOnClickListener(getClickListener());
        }

        return removeButton;
    }

    private OnClickListener getClickListener(){
        return clickListener;
    }
}
