package designpatterns.factory.factorymethod.pizzastore.pizza;

/**
 * @author machenggong
 * @date 2020/12/20
 * @description
 */
public class LDCheesePizza extends Pizza {

    @Override
    public void prepare() {
        setName("伦敦奶酪披萨");
        System.out.println("准备伦敦奶酪披萨");
    }
}
