package designpatterns.visitor;

/**
 * @author machenggong
 * @date 2021/1/1
 * @description
 */
public class Man extends Person{

    @Override
    public void accept(Action action) {
        action.getManResult(this);
    }
}
