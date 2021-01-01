package designpatterns.visitor;

/**
 * @author machenggong
 * @date 2021/1/1
 * @description
 */
public abstract class Action {

    //得到男性的评价
    public abstract void getManResult(Man man);

    //得到女性的评价
    public abstract void getWomanResult(Woman man);

}
