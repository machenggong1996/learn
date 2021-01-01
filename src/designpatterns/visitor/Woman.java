package designpatterns.visitor;

/**
 * @author machenggong
 * @date 2021/1/1
 * @description
 */
public class Woman extends Person{
    //1. 这里使用了双分派 参数传入woman
    //2. 自己传给 action
    @Override
    public void accept(Action action) {
        action.getWomanResult(this);
    }
}
