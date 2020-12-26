package designpatterns.decorator.starbucks.decorators;

import designpatterns.decorator.starbucks.Drink;
import designpatterns.decorator.starbucks.decorators.Decorator;

/**
 * @author machenggong
 * @date 2020/12/25
 * @description
 */
public class Soy extends Decorator {


    public Soy(Drink drink) {
        super(drink);
        setDescription("豆浆");
        setPrice(1);
    }
}
