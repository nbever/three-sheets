package nate.threesheets.widgets;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nate.threesheets.model.Drink;
import nate.threesheets.model.Wine;
import nate.threesheets.views.WineRowView;

/**
 * Created by nate on 6/13/15.
 */
public class DrinkListAdapter<E extends Drink> extends BaseAdapter{

    private List<E> drinks;
    private Context context;

    public DrinkListAdapter( Context context, List<E> drinks ){
        this.context = context;
        this.drinks = drinks;
    }

    private List<E> getDrinks(){
        if ( drinks == null ){
            drinks = Collections.EMPTY_LIST;
        }

        return drinks;
    }

    @Override
    public int getCount() {
        return getDrinks().size();
    }

    @Override
    public Object getItem(int position) {
        return getDrinks().get(position);
    }

    @Override
    public long getItemId(int position) {
        return getDrinks().get(position).getDate();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        WineRowView wineView = new WineRowView(context, null);
        Drink drink = (Drink)getItem(position);

        wineView.getName().setText(drink.toString());

        Iterator<?> keyIt = drink.getFlavorMap().keySet().iterator();

        while( keyIt.hasNext() ){
            Object key = keyIt.next();
            Integer value = drink.getFlavorMap().get(key);
            wineView.getFlavorWheel().setTasteValue(key, value);
        }

        wineView.getDrinkRatingBar().setRating(drink.getRating());
        wineView.getSubText().setText( drink.getSubText() );

        return wineView;
    }
}
