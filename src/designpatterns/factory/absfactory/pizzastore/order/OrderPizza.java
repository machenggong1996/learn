package designpatterns.factory.absfactory.pizzastore.order;

import designpatterns.factory.absfactory.pizzastore.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author machenggong
 * @date 2020/12/19
 * @description
 */
public class OrderPizza {

    public OrderPizza(AbsFactory absFactory) {
        setFactory(absFactory);
    }

    AbsFactory absFactory;

    private void setFactory(AbsFactory absFactory) {
        Pizza pizza = null;
        String orderType = "";//用户输入
        this.absFactory = absFactory;
        do {
            orderType = getType();
            pizza = absFactory.createPizza(orderType);
            if (pizza != null) {
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            } else {
                break;
            }
        } while (true);
    }

    public String getType() {
        try {
            BufferedReader strIn = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("input pizza 种类:");
            String str = strIn.readLine();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
