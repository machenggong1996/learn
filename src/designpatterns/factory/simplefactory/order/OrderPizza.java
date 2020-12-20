package designpatterns.factory.simplefactory.order;

import designpatterns.factory.simplefactory.pizza.Pizza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author machenggong
 * @date 2020/12/19
 * @description
 */
public class OrderPizza {

//    public OrderPizza() {
//        Pizza pizza = null;
//        String orderType;
//        do {
//            orderType = getType();
//
//            pizza.setName(orderType);
//            pizza.prepare();
//            pizza.bake();
//            pizza.cut();
//            pizza.box();
//        } while (true);
//    }

    public OrderPizza(){
        setFactory();
    }

    Pizza pizza = null;
    public void setFactory(){
        String orderType = "";//用户输入
        do{
            orderType= getType();
            pizza = SimpleFactory.createPizza(orderType);
            if(pizza!=null){
                pizza.setName(orderType);
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            }else {
                break;
            }
        }while (true);
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
