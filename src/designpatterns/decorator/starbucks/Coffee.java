package designpatterns.decorator.starbucks;

import designpatterns.decorator.starbucks.Drink;

/**
 * @author machenggong
 * @date 2020/12/25
 * @description
 */
public class Coffee extends Drink {


    @Override
    public float cost() {
        return super.getPrice();
    }
}
