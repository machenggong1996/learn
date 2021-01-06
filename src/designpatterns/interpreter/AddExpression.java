package designpatterns.interpreter;

import java.util.HashMap;

/**
 * @author machenggong
 * @date 2021/1/5
 * @description
 */
public class AddExpression extends SymbolExpression {


    public AddExpression(Expression left, Expression right) {
        super(left, right);
    }

    /**
     * 处理相加
     * @param var
     * @return
     */
    @Override
    public int interpreter(HashMap<String, Integer> var) {
        //返回表达式对应的值
        return super.left.interpreter(var) + super.right.interpreter(var);
    }
}
