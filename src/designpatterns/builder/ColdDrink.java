package designpatterns.builder;

/**
 * Created by machenggong on 2019/6/19.
 */
public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract float price();

}
