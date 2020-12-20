package designpatterns.factory.factorymethod.pizzastore.pizza;

/**
 * @author machenggong
 * @date 2020/12/20
 * @description
 */
public class LDPepperPizza extends Pizza {

    @Override
    public void prepare() {
        setName("伦敦胡椒披萨");
        System.out.println("准备伦敦胡椒披萨");
    }
}
