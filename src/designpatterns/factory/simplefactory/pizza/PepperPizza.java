package designpatterns.factory.simplefactory.pizza;

/**
 * @author machenggong
 * @date 2020/12/19
 * @description
 */
public class PepperPizza extends Pizza{
    @Override
    public void prepare() {
        System.out.println("胡椒披萨准备原材料");
    }
}
