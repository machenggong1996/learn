package designpatterns.factory.simplefactory.pizza;

/**
 * @author machenggong
 * @date 2020/12/19
 * @description 奶酪披萨
 */
public class CheesePizza extends Pizza{

    @Override
    public void prepare() {
        System.out.println("准备奶酪披萨的原料");
    }
}
