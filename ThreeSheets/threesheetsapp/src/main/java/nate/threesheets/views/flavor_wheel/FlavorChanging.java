package nate.threesheets.views.flavor_wheel;

/**
 * Created by nate on 6/11/15.
 */
public interface FlavorChanging<E> {

    public void flavorChanging();
    public void flavorSet( E flavor, Integer value );
}
