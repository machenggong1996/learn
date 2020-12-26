package designpatterns.decorator.starbucks;

import designpatterns.decorator.starbucks.decorators.Chocolate;
import designpatterns.decorator.starbucks.decorators.Milk;

/**
 * @author machenggong
 * @date 2020/12/25
 * @description
 */
public class Client {

    public static void main(String[] args) {
        //1. 点一份 LongBlack
        Drink order = new LongBlack();
        System.out.println("费用1:"+order.cost()+":"+order.getDescription());
        //2. 加牛奶进行装饰
        order = new Milk(order);
        System.out.println("加入牛奶费用:"+order.cost()+":"+order.getDescription());
        //3. 巧克力
        order = new Chocolate(order);
        System.out.println("加入牛奶+巧克力费用:"+order.cost()+":"+order.getDescription());
    }

}
