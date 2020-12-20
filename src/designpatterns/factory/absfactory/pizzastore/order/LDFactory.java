package designpatterns.factory.absfactory.pizzastore.order;

import designpatterns.factory.absfactory.pizzastore.pizza.LDCheesePizza;
import designpatterns.factory.absfactory.pizzastore.pizza.LDPepperPizza;
import designpatterns.factory.absfactory.pizzastore.pizza.Pizza;

/**
 * @author machenggong
 * @date 2020/12/20
 * @description
 */
public class LDFactory implements AbsFactory {

    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if (orderType.equals("cheese")) {
            pizza = new LDCheesePizza();
        } else if (orderType.equals("pepper")) {
            pizza = new LDPepperPizza();
        }
        return pizza;
    }
}
