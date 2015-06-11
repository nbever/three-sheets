package nate.threesheets.views.selection_addrs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import nate.threesheets.widgets.SelectionAdderItem;

/**
 * Created by nate on 6/10/15.
 */
public class SelectionAdder<E> extends LinearLayout implements View.OnClickListener{

    private List<E> options;
    private List<SelectionAdderItem> itemList;
    private Button addButton;

    public SelectionAdder(Context context, List<E> optionSet ){
        super( context );

        this.options = optionSet;

        this.setOrientation(VERTICAL);


        this.addView(createNewEntry() );
        this.addView( getAddbutton() );
    }

    private SelectionAdderItem createNewEntry(){

        SelectionAdderItem item = new SelectionAdderItem(this.getContext(), getOptions(), this);
        item.setId(getItemList().size());
        getItemList().add(item);

        return item;
    }

    private List<E> getOptions(){
        if ( options == null ){
            options = new ArrayList<E>();
        }

        return options;
    }

    private List<SelectionAdderItem> getItemList(){
        if ( itemList == null ){
            itemList = new ArrayList<SelectionAdderItem>();
        }

        return itemList;
    }

    private Button getAddbutton(){
        if ( addButton == null ){
            addButton = new Button(this.getContext());
            addButton.setText( "Add" );
            addButton.setOnClickListener( this );
        }
        return addButton;
    }

    @Override
    public void onClick(View v) {

        if ( v.equals( getAddbutton() ) ){
            this.addView(createNewEntry(), this.getChildCount()-2);
        }
        else {
            this.removeView((View) v.getParent());
        }
    }
}
