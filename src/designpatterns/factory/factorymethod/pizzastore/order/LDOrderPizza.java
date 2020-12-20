package designpatterns.factory.factorymethod.pizzastore.order;

import designpatterns.factory.factorymethod.pizzastore.pizza.*;

/**
 * @author machenggong
 * @date 2020/12/20
 * @description
 */
public class LDOrderPizza extends OrderPizza{

    @Override
    public Pizza createPizza(String orderType) {
        Pizza pizza = null;
        if(orderType.equals("cheese")){
            pizza = new LDCheesePizza();
        }else if(orderType.equals("pepper")){
            pizza = new LDPepperPizza();
        }
        return pizza;
    }
}
