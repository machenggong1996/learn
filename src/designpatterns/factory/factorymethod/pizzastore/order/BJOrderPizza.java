package designpatterns.factory.factorymethod.pizzastore.order;

import designpatterns.factory.factorymethod.pizzastore.pizza.BJCheesePizza;
import designpatterns.factory.factorymethod.pizzastore.pizza.BJPepperPizza;
import designpatterns.factory.factorymethod.pizzastore.pizza.Pizza;

/**
 * @author machenggong
 * @date 2020/12/20
 * @description
 */
public class BJOrderPizza extends OrderPizza{

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
