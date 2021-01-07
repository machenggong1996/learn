package designpatterns.strategy.v20210107;

/**
 * @author machenggong
 * @date 2021/1/7
 * @description
 */
public class GoodFlyBehavior implements FlyBehaviorStrategy{
    @Override
    public void fly() {
        System.out.println("飞翔技术很好");
    }
}
