package designpatterns.builder;

/**
 * Created by machenggong on 2019/6/19.
 */
public class ChickenBurger extends Burger {

    @Override
    public float price() {
        return 50.5f;
    }

    @Override
    public String name() {
        return "Chicken Burger";
    }

}
