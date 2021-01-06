package designpatterns.interpreter;

import java.util.HashMap;

/**
 * @author machenggong
 * @date 2021/1/5
 * @description 减法表达式
 */
public class SubExpression extends SymbolExpression{

    public SubExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int interpreter(HashMap<String, Integer> var) {

        return super.left.interpreter(var) - super.right.interpreter(var);
    }
}
