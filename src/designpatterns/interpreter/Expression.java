package designpatterns.interpreter;

import java.util.HashMap;

/**
 * @author machenggong
 * @date 2021/1/5
 * @description 抽象类表达式 通过键值对可以获取到变量的值
 */
public abstract class Expression {

    /**
     * 解释公式和数值，key就是公式（表达式） 参数[a,b,c] value就是具体值
     * @param var
     */
    public abstract int interpreter(HashMap<String,Integer> var);

}
