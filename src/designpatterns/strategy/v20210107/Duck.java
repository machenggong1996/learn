package designpatterns.strategy.v20210107;

/**
 * @author machenggong
 * @date 2021/1/7
 * @description
 */
public abstract class Duck {

    protected FlyBehaviorStrategy flyBehaviorStrategy;

    public Duck() {

    }

    public Duck(FlyBehaviorStrategy flyBehaviorStrategy) {
        this.flyBehaviorStrategy = flyBehaviorStrategy;
    }

    /**
     * 显示鸭子名称
     */
    public abstract void display();

    public void quack() {
        System.out.println("鸭子嘎嘎叫");
    }

    public void swim() {
        System.out.println("鸭子会游泳");
    }

    public void fly() {
        if (flyBehaviorStrategy != null) {
            flyBehaviorStrategy.fly();
        }
    }

    public void setFlyBehaviorStrategy(FlyBehaviorStrategy flyBehaviorStrategy) {
        this.flyBehaviorStrategy = flyBehaviorStrategy;
    }
}
