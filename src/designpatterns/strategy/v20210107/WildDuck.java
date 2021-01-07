package designpatterns.strategy.v20210107;

/**
 * @author machenggong
 * @date 2021/1/7
 * @description
 */
public class WildDuck extends Duck{

    public WildDuck(FlyBehaviorStrategy flyBehaviorStrategy) {
        super(flyBehaviorStrategy);
    }

    public WildDuck(){
        this.flyBehaviorStrategy = new GoodFlyBehavior();
    }

    @Override
    public void display() {
        System.out.println("这是野鸭");
    }
}
