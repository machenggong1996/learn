package designpatterns.factory.absfactory.pizzastore.order;

import designpatterns.factory.absfactory.pizzastore.pizza.Pizza;

/**
 * @author machenggong
 * @date 2020/12/20
 * @description
 */
public interface AbsFactory {

    Pizza createPizza(String orderType);

}
