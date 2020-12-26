package designpatterns.decorator.starbucks.decorators;

import designpatterns.decorator.starbucks.Drink;
import designpatterns.decorator.starbucks.decorators.Decorator;

/**
 * @author machenggong
 * @date 2020/12/25
 * @description
 */
public class Milk extends Decorator {
    public Milk(Drink drink) {
        super(drink);
        setDescription("牛奶");
        setPrice(2);
    }
}
