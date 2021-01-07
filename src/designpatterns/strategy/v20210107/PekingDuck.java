package designpatterns.strategy.v20210107;

/**
 * @author machenggong
 * @date 2021/1/7
 * @description
 */
public class PekingDuck extends Duck{

    public PekingDuck(){
        flyBehaviorStrategy = new NoFlyBehavior();
    }

    @Override
    public void display() {
        System.out.println("北京鸭");
    }
}
