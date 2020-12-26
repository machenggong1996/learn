package designpatterns.decorator.starbucks.decorators;

import designpatterns.decorator.starbucks.Drink;

/**
 * @author machenggong
 * @date 2020/12/25
 * @description
 */
public class Chocolate extends Decorator {

    public Chocolate(Drink drink) {
        super(drink);
        setDescription("巧克力");
        setPrice(3);
    }
}
