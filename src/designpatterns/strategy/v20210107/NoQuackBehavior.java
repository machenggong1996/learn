package designpatterns.strategy.v20210107;

/**
 * @author machenggong
 * @date 2021/1/7
 * @description
 */
public class NoQuackBehavior implements QuackBehaviorStrategy{

    @Override
    public void quack() {
        System.out.println("不会叫");
    }

}
