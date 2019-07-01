package designpatterns.builder;

/**
 * Created by machenggong on 2019/6/19.
 */
public abstract class Burger implements Item {

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();

}
