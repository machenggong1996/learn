package designpatterns.factory.simplefactory.pizza;

/**
 * @author machenggong
 * @date 2020/12/19
 * @description 希腊披萨
 */
public class GreekPizza extends Pizza {

    @Override
    public void prepare() {
        System.out.println("给希腊披萨准备原材料");
    }
}
