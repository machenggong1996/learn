package designpatterns.visitor;

/**
 * @author machenggong
 * @date 2021/1/1
 * @description
 */
public class Wait extends Action{

    @Override
    public void getManResult(Man man) {
        System.out.println("男人待定");
    }

    @Override
    public void getWomanResult(Woman man) {
        System.out.println("女人待定");
    }
}
