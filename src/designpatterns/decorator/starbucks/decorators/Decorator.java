package designpatterns.decorator.starbucks.decorators;

import designpatterns.decorator.starbucks.Drink;

/**
 * @author machenggong
 * @date 2020/12/25
 * @description
 */
public class Decorator extends Drink {

    private final Drink drink;

    public Decorator(Drink drink) {
        this.drink = drink;
    }

    @Override
    public float cost() {
        return super.getPrice() + drink.cost();
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " " + super.getPrice() + "&&" + drink.getDescription();
    }
}
