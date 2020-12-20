package designpatterns.factory.absfactory.pizzastore.order;

import designpatterns.factory.absfactory.pizzastore.pizza.BJCheesePizza;
import designpatterns.factory.absfactory.pizzastore.pizza.BJPepperPizza;
import designpatterns.factory.absfactory.pizzastore.pizza.Pizza;

/**
 * @author machenggong
 * @date 2020/12/20
 * @description
 */
public class BJFactory implements AbsFactory{

    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if(orderType.equals("cheese")){
            pizza = new BJCheesePizza();
        }else if(orderType.equals("pepper")){
            pizza = new BJPepperPizza();
        }
        return pizza;
    }
}
