package designpatterns.factory.absfactory.pizzastore;

import designpatterns.factory.absfactory.pizzastore.order.BJFactory;
import designpatterns.factory.absfactory.pizzastore.order.OrderPizza;

/**
 * @author machenggong
 * @date 2020/12/20
 * @description
 */
public class PizzaStore {

    public static void main(String[] args) {
        new OrderPizza(new BJFactory());
    }

}
