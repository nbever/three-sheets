package nate.threesheets.views.selection_addrs;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import nate.threesheets.R;

/**
 * Created by nate on 6/10/15.
 */
public class SelectionAdder<E> extends LinearLayout implements View.OnClickListener{

    private List<E> options;
    private List<SelectionAdderItem> itemList;
    private Button addButton;

    public SelectionAdder(Context context, AttributeSet attrs, List<E> optionSet ){
        super( context, attrs );

        this.options = optionSet;

        this.setOrientation(VERTICAL);


        this.addView(createNewEntry(), new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT) );
        this.addView( getAddbutton(), new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT) );
    }

    private SelectionAdderItem createNewEntry(){

        SelectionAdderItem item = new SelectionAdderItem(this.getContext(), getOptions(), this);
        item.setId(getItemList().size());
        getItemList().add(item);

        return item;
    }

    public void setItems( List<E> selections ){

        itemList = new ArrayList<SelectionAdderItem>();

        for ( E selection : selections ) {
            SelectionAdderItem item = createNewEntry();
            item.setSelectedItem(selection);
        }
    }

    private List<E> getOptions(){
        if ( options == null ){
            options = new ArrayList<E>();
        }

        return options;
    }

    public List<SelectionAdderItem> getItemList(){
        if ( itemList == null ){
            itemList = new ArrayList<SelectionAdderItem>();
        }

        return itemList;
    }

    private Button getAddbutton(){
        if ( addButton == null ){
            addButton = new Button(this.getContext(), null, android.R.attr.buttonStyleSmall );

            addButton.setText("");
            addButton.setBackgroundResource(R.mipmap.ic_add);
            addButton.setOnClickListener(this);
            addButton.setMinHeight(0);
            addButton.setMinWidth(0);
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
