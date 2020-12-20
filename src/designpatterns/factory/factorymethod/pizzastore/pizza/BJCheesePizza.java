package designpatterns.factory.factorymethod.pizzastore.pizza;

/**
 * @author machenggong
 * @date 2020/12/20
 * @description
 */
public class BJCheesePizza extends Pizza {

    @Override
    public void prepare() {
        setName("北京奶酪披萨");
        System.out.println("准备北京奶酪披萨");
    }
}
