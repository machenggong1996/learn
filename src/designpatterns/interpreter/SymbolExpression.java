package designpatterns.interpreter;

import java.util.HashMap;

/**
 * @author machenggong
 * @date 2021/1/5
 * @description
 */
public class SymbolExpression extends Expression{

    protected final Expression left;

    protected final Expression right;

    public SymbolExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    /**
     * SymbolExpression 让子类来实现 所以interpreter方法为空实现
     * @param var
     * @return
     */
    @Override
    public int interpreter(HashMap<String, Integer> var) {

        return 0;
    }
}
