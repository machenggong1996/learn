package designpatterns.factory.simplefactory.order;

import designpatterns.factory.simplefactory.pizza.CheesePizza;
import designpatterns.factory.simplefactory.pizza.GreekPizza;
import designpatterns.factory.simplefactory.pizza.PepperPizza;
import designpatterns.factory.simplefactory.pizza.Pizza;

/**
 * @author machenggong
 * @date 2020/12/19
 * @description
 */
public class SimpleFactory {

    public static Pizza createPizza(String orderType){
        Pizza pizza = null;
        System.out.println("使用简单工厂模式");
        if ("greek".equals(orderType)) {
            pizza = new GreekPizza();
        } else if ("cheese".equals(orderType)) {
            pizza = new CheesePizza();
        } else if ("pepper".equals(orderType)) {
            pizza = new PepperPizza();
        }
        return pizza;
    }

}
